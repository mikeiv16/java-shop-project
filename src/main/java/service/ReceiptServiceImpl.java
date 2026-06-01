package service;

import data.Receipt;

import java.io.*;

public class ReceiptServiceImpl implements ReceiptService{
    @Override
    public void saveReceiptFile(Receipt receipt) throws IOException {
        String fileName = "src/main/java/receipts/txt/receipt_" + receipt.getShopName() + "_" + receipt.getNumber() + ".txt";

        try (FileWriter fw = new FileWriter(fileName)){
            fw.write(receipt.toString());
        } catch (IOException e) {
            System.out.println("Error caught while saving receipt file: " + e);
        }
    }

    @Override
    public void readReceiptFile(String file) {
//        try(FileReader fr = new FileReader(file)){
//            int chr;
//            while((chr = fr.read()) != -1){
//                System.out.print((char)chr);
//            }
//            System.out.println();
//        }
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        }
        catch (IOException ex){
            System.out.println("Error while reading the receipt file: " + ex);
        }
    }

    @Override
    public void serializeReceipt(Receipt receipt) throws IOException {
        String filePath = "src/main/java/receipts/ser/receipt_" + receipt.getShopName() + "_" + receipt.getNumber() + ".ser";
        try(
                FileOutputStream fos = new FileOutputStream(filePath);
                ObjectOutputStream outputStream = new ObjectOutputStream(fos);
        ){
            outputStream.writeObject(receipt);
        }
        catch(IOException e){
            System.out.println("Error while serializing receipt: " + e);
        }
    }

    @Override
    public Receipt deserializeReceipt(String file) throws IOException {
        try(
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream inputStream = new ObjectInputStream(fis);
                ){
            Receipt receipt = (Receipt) inputStream.readObject();
            return receipt;
        }
        catch(ClassNotFoundException e){
            System.err.println("Class not found when trying to deserialize receipt: " + e);
        }
        catch(IOException e){
            System.err.println("IO Error while deserializing receipt: " + e);
        }

        return null;
    }
}
