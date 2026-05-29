package data;

public class CashRegister {

    private int cashRegisterNumber;
    private Cashier workingCashier;

    public CashRegister(int cashRegisterNumber, Cashier workingCashier) {
        this.cashRegisterNumber = cashRegisterNumber;
        this.workingCashier = workingCashier;
    }

    public int getCashRegisterNumber() {
        return cashRegisterNumber;
    }

    public void setCashRegisterNumber(int cashRegisterNumber) {
        this.cashRegisterNumber = cashRegisterNumber;
    }

    public Cashier getWorkingCashier() {
        return workingCashier;
    }

    public void setWorkingCashier(Cashier workingCashier) {
        this.workingCashier = workingCashier;
    }

    @Override
    public String toString() {
        return "CashRegister{" +
                "cashRegisterNumber=" + cashRegisterNumber +
                ", workingCashier=" + workingCashier +
                '}';
    }
}
