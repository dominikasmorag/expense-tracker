package Other;

import DAO.ExpenseCategoryDAO;
import entity.Expense;
import entity.ExpenseCategory;
import payment.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataInsert {
    private final Scanner sc;
    private final ExpenseCategoryDAO expenseCategoryDAO;

    public DataInsert(ExpenseCategoryDAO expenseCategoryDAO) {
        this.sc = new Scanner(System.in);
        this.expenseCategoryDAO = expenseCategoryDAO;
    }

    public List<Expense> askForExpense() {
        String s = "";
        List<Expense> expenses = new ArrayList<>();
        while(true) {
            Expense expense = new Expense();
            expense.setDescription(askForDescription());
            expense.setValue(askForValue());
            expense.setCategoryId(askForCategoryId());
            expense.setPaymentMethod(askForPaymentMethod());
            expense.setDate(LocalDateTime.now());
            expenses.add(expense);
            System.out.println("do you want to add another expense? y/n");
            s = sc.nextLine();
            if(s.equalsIgnoreCase("n")) {
                break;
            }
        }
        return expenses;
    }

    private String askForDescription() {
        System.out.println("Description: ");
        String description = sc.nextLine();
        return description;
    }

    private BigDecimal askForValue() {
        BigDecimal value;
        System.out.println("Value: ");
        value=sc.nextBigDecimal();
        sc.nextLine();
        return value;
    }

    private PaymentMethod askForPaymentMethod() {
            System.out.println("Payment method [CARD, CASH]: ");
            String input = sc.nextLine();
            if("cash".equalsIgnoreCase(input) || "card".equalsIgnoreCase(input)) {
                input = input.toUpperCase();
                return PaymentMethod.valueOf(input);
            }
            else {
                System.out.println("There is no such payment method");
                return null;
            }
    }

    private ExpenseCategory askForCategoryId() {
        String category = "";
        System.out.println("Categories: " + expenseCategoryDAO.show() + "\nCategory: ");

        while("".equals(category)) {
            String input = sc.nextLine();
            if(expenseCategoryDAO.show().contains(input.toLowerCase())) {
                category = input;
            }
            else {
                System.out.println("That category doesn't exist");
            }
        }
        return expenseCategoryDAO.categoryHashMap.get(category);
    }

}
