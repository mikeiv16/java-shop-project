package service;

import data.Receipt;

import java.io.IOException;

public interface ReceiptService {

    void saveReceiptFile(Receipt receipt) throws IOException;
    void readReceiptFile(String file);
    void serializeReceipt(Receipt receipt);
    Receipt deserializeReceipt(String file);
}
