package pojo;

import entity.ExpenseCategory;

import java.math.BigDecimal;

public class Result {
    private String description;
    private BigDecimal value;
    private BigDecimal sum;
    private ExpenseCategory expenseCategory;

    public Result(String description, BigDecimal value) {
        this.description = description;
        this.value = value;
    }

    public Result() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String categoryName) {
        this.description = categoryName;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String toString() {
        return description + " " + value.toString();
    }
}
