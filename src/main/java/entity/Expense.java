package entity;

import org.hibernate.annotations.GenericGenerator;
import payment.PaymentMethod;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(generator = "incrementator")
    @GenericGenerator(name = "incrementator", strategy = "increment")
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "value")
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name="category_id")
    private ExpenseCategory expenseCategory;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "date")
    private LocalDateTime date;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ExpenseCategory getCategoryId() {
        return expenseCategory;
    }

    public void setCategoryId(ExpenseCategory expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

}
