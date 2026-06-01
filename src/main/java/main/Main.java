package main;

import data.*;
import exceptions.ExpiredStockException;
import exceptions.NotEnoughMoneyException;
import exceptions.NotEnoughQuantityException;
import service.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/// /// всичко на конзолата е написано на латиница, понеже ако го напиша на кирилица ми извежда само ????, дори след промяна на UTF-8 пак стои така
public class Main {
    static void main() throws IOException {
        StockService stockService = new StockServiceImpl();
        ShopService shopService = new ShopServiceImpl();
        CashRegisterService cashRegisterService = new CashRegisterServiceImpl(stockService, shopService);
        ReceiptService receiptService = new ReceiptServiceImpl();
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));


        //primeren magazin:
        Map<Category, BigDecimal> priceIncreases = new EnumMap<>(Category.class);
        priceIncreases.put(Category.FOOD, BigDecimal.valueOf(10));
        priceIncreases.put(Category.NOT_FOOD, BigDecimal.valueOf(20));

        Stock apple = new Stock("Qbulki (na broika) ", BigDecimal.valueOf(1.5), Category.FOOD, LocalDate.now().plusDays(10));
        Stock porkMeat = new Stock("Svinsko meso (na kg)", BigDecimal.valueOf(7.50), Category.FOOD, LocalDate.now().plusDays(8));
        Stock kiseloMlqko = new Stock("Kiselo mqlko 2%", BigDecimal.valueOf(1), Category.FOOD, LocalDate.now().plusDays(1));
        Stock milk = new Stock("Prqsno mlqko 3%", BigDecimal.valueOf(2.20), Category.FOOD, LocalDate.now().minusDays(3));
        Stock chair = new Stock("Stol", BigDecimal.valueOf(120), Category.NOT_FOOD, null);

        Map<Stock, BigDecimal> stocksInventory = new HashMap<>();  // <stoka, kolichestvo>
        stocksInventory.put(apple, BigDecimal.valueOf(25));
        stocksInventory.put(milk, BigDecimal.valueOf(2));
        stocksInventory.put(porkMeat, BigDecimal.valueOf(3.2));
        stocksInventory.put(kiseloMlqko, BigDecimal.valueOf(10));
        stocksInventory.put(chair, BigDecimal.valueOf(2));

        Cashier cashier1 = new Cashier("Misho Ivanov", BigDecimal.valueOf(1500));
        Cashier cashier2 = new Cashier("Rumen Radev", BigDecimal.valueOf(2000));
        List<Cashier> cashiers = new ArrayList<>();
        cashiers.add(cashier1);
        cashiers.add(cashier2);

        CashRegister kasa1 = new CashRegister(1, cashier1);
        CashRegister kasa2 = new CashRegister(2, cashier2);
        List<CashRegister> cashRegisters = new ArrayList<>();
        cashRegisters.add(kasa1);
        cashRegisters.add(kasa2);

        List<Receipt> receiptList = new ArrayList<>();
        Shop shop1 = new Shop(cashiers, cashRegisters, "Kaufland", receiptList, stocksInventory, priceIncreases, 3, BigDecimal.valueOf(50), BigDecimal.ZERO);


        boolean isOpen = true;
        System.out.println("Shop app:");

        System.out.println("Informaciq otnosno magazina: ");
        System.out.println(shop1);
        System.out.println("Jelaete li da vuvedete dopulnitelni razhodi kum magazina (smetki za tok, naem, dostavki, t.n.):");
        System.out.println("Y/N?");
        String answer = consoleReader.readLine();
        if(answer.equalsIgnoreCase("Y")){
            System.out.println("Vuvedete obshta suma na dopulnitelnite razhodi:");
            try{
                BigDecimal extraExpenses = new BigDecimal(consoleReader.readLine());
                shop1.setExtraExpenses(extraExpenses);
                System.out.println("Uspeshno zapisani dopulnitelni razhodi.");
            }
            catch (Exception e){
                System.out.println("Nevaldina suma. Dopulnitelni razhodi = €0.00");
            }

        }

        while(isOpen){
            System.out.println("==== MENU: ====");
            System.out.println("Prodajbi:");
            System.out.println("1. Nova prodajba");
            System.out.println("Kasovi belejki:");
            System.out.println("2. Prochitane na kasova belejka (.txt)");
            System.out.println("3. Deserializaciq na kasova belejka (.ser)");
            System.out.println("Finansi:");
            System.out.println("4. Finansov otchet na magazina");
            System.out.println("5. Izhod");
            System.out.println("===============");
            System.out.println("Izberete opciq = ");

            String option =  consoleReader.readLine();
            switch(option){
                case "1":
                    System.out.println("===============");
                    System.out.println("Prodajba:");
                    Map<Stock, BigDecimal> shoppingCart = new HashMap<>();
                    shoppingCart.put(milk, BigDecimal.valueOf(2)); //iztekul srok
                    shoppingCart.put(apple, BigDecimal.valueOf(30)); //ima samo 25 nalichni a usera iska 30
                    shoppingCart.put(chair, BigDecimal.valueOf(1));
                    shoppingCart.put(kiseloMlqko, BigDecimal.valueOf(1)); //50% namalenie poneje izticha utre
                    shoppingCart.put(porkMeat, BigDecimal.valueOf(1.2));

                    BigDecimal customerGivenMoney = BigDecimal.valueOf(400);
                    boolean checkoutComplete = false;
                    boolean customerAborted = false;
                    while(!checkoutComplete && !customerAborted){
                        try{
                            Receipt receipt = cashRegisterService.sellStocks(shop1, kasa1, shoppingCart, customerGivenMoney);

                            System.out.println("Uspehsna prodajba. Belejka:");
                            shop1.getReceipts().add(receipt);
                            receiptService.saveReceiptFile(receipt);
                            receiptService.serializeReceipt(receipt);

                            System.out.println(receipt);
                            checkoutComplete = true;
                        }
                        catch(ExpiredStockException e){

                            System.out.println("Greshka: " + e.getMessage());
                            System.out.println("Jelaete li da mahnem stokata ot kolichkata i da produljite (Y) ili da prekratite cqlata pokupka (N)?");
                            if(consoleReader.readLine().equalsIgnoreCase("Y")){
                                shoppingCart.remove(e.getStock());
                            }
                            else customerAborted = true;
                        }
                        catch(NotEnoughQuantityException e){
                            System.out.println("Greshka. Nqma dostatuchno kolichestvo za " + e.getStock().getName());
                            shoppingCart.remove(e.getStock());
                            BigDecimal available = shop1.getStocks().get(e.getStock());
                            System.out.println("Nalichni sa samo: " + available);
                            System.out.println("Iskate li da zakupite samo nalichnite broiki? (Y/N)");
                            if(consoleReader.readLine().equalsIgnoreCase("Y")){
                                shoppingCart.put(e.getStock(), available);
                            }
                            else customerAborted = true;

                        }
                        catch(NotEnoughMoneyException e){
                            System.out.println(e.getMessage());
                            customerAborted = true;
                        }


                    }
                    if(customerAborted == true){
                        System.out.println("Prodajbata e prekratena.");
                    }
                    break;
                case "2":
                    System.out.println("===============");
                    System.out.println("Chetene na belejka:");
                    System.out.println("Ime na file: ");
                    String file = consoleReader.readLine();
                    receiptService.readReceiptFile(file);
                    break;
                case "3":
                    System.out.println("===============");
                    System.out.println("Deserializaciq: ");
                    System.out.println("Ime na file: ");
                    String fileS = consoleReader.readLine();
                    Receipt r = receiptService.deserializeReceipt(fileS);

                    if(r != null) {
                        System.out.println("Belejkata e deserializirana uspeshno.");
                        System.out.println(r);
                    }
                    else{
                        System.out.println("File-a ne e nameren i belejkata ne e deserializirana");
                    }
                    break;

                case "4":
                    System.out.println("===============");
                    System.out.println("Finansov otchet:");
                    System.out.println("Prihodi: €" + shopService.calculateShopIncome(shop1));
                    System.out.println("Razhodi (stoka + zaplati + dop. razhodi): €" + shopService.calculateShopExpenses(shop1));
                    System.out.println("Profit/zaguba: €" + shopService.calculateShopProfit(shop1));
                    break;
                case "5":
                    isOpen = false;
                    System.out.println("izlizane...");
                    break;
                default:
                    System.out.println("Nqma takava opciq, opitaite otnovo.");
                    break;
            }
        }

    }
}
