package pl.javastart.personalbudget;

import java.math.BigDecimal;
import java.util.Date;

public class Receipt {
    private Integer id;
    private String type;
    private String description;
    private BigDecimal amount;
    private String date;

    public Receipt(String type, String description, BigDecimal amount, String date) {
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public Receipt(Integer id, String type, String description, BigDecimal amount, String date) {
        this(type, description, amount, date);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
