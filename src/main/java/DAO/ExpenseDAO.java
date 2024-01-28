package DAO;

import Other.DataInsert;
import entity.Expense;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class ExpenseDAO extends DAO {
    private final ExpenseCategoryDAO expenseCategoryDAO;

    public ExpenseDAO(SessionFactory factory) {
        super(factory);
        this.expenseCategoryDAO = new ExpenseCategoryDAO(factory);
    }

    public void addExpenses() {
        DataInsert dataInsert = new DataInsert(expenseCategoryDAO);
        List<Expense> list = dataInsert.askForExpense();
        if(insert(list)) {
            System.out.println("Expenses added");
        }

    }

    private boolean insert(List<Expense> expenses) {
        try(Session session = factory.openSession()) {
            for(Expense expense : expenses) {
                Transaction tx = session.beginTransaction();
                session.save(expense);
                tx.commit();
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
