package service;

import data.Receipt;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ReceiptService {

    void saveReceiptFile(Receipt receipt) throws IOException;
    void readReceiptFile(String file) throws FileNotFoundException;
    void serializeReceipt(Receipt receipt) throws IOException;
    Receipt deserializeReceipt(String file) throws IOException;
    //TO do: funkciq izchislqvashta kolko bi bil prihoda ako se prodadat vsichki stoki v magazina
}
