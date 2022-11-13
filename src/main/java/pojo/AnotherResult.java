package pojo;

import entity.ExpenseCategory;

import java.math.BigDecimal;

public class AnotherResult {
    private ExpenseCategory expenseCategory;
    private BigDecimal sum;

    public ExpenseCategory getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(ExpenseCategory expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public AnotherResult(ExpenseCategory expenseCategory, BigDecimal sum) {
        this.expenseCategory = expenseCategory;
        this.sum = sum;
    }
}
