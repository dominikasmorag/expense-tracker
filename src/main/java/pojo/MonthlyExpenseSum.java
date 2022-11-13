package pojo;

import entity.ExpenseCategory;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public class MonthlyExpenseSum {
    private BigDecimal sum;
    private ExpenseCategory expenseCategory;

    public MonthlyExpenseSum(BigDecimal sum, ExpenseCategory expenseCategory) {
        this.sum = sum;
        this.expenseCategory = expenseCategory;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public ExpenseCategory getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(ExpenseCategory expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public String toString() {
        return "Sum: " + sum + ", Category: " + expenseCategory.getName();
    }
}
