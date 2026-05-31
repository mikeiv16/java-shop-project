package service;

import data.Receipt;

import java.io.FileWriter;
import java.io.IOException;

public class ReceiptServiceImpl implements ReceiptService{
    @Override
    public void saveReceiptFile(Receipt receipt) throws IOException {
        String fileName = "receipt_" + receipt.getShopName() + "_" + receipt.getNumber() + ".txt";

        try (FileWriter fw = new FileWriter(fileName)){
            fw.write(receipt.toString());
        } catch (IOException e) {
            System.out.println("Error caught while saving receipt file: " + e);
        }
    }

    @Override
    public void readReceiptFile(String file) {

    }

    @Override
    public void serializeReceipt(Receipt receipt) {

    }

    @Override
    public Receipt deserializeReceipt(String file) {
        return null;
    }
}
