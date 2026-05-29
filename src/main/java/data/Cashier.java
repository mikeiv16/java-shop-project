package data;

import java.math.BigDecimal;
import java.util.UUID;

public class Cashier {
    private final UUID uuid;
    private String name;
    private BigDecimal salary;

    public Cashier(String name, BigDecimal salary) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.salary = salary;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cashier{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

}
