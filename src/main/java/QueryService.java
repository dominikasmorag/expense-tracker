import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import pojo.MonthlyExpenseSum;
import pojo.Result;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class QueryService {
    private SessionFactory factory;

    public QueryService(SessionFactory factory) {
        this.factory = factory;
    }

    public List getReport(int month, int year) {
        Session session = factory.openSession();
        String queryString = "select ex.value, cat.name from Expense ex WHERE MONTH(ex.date) = month(:date)" +
                "INNER JOIN ExpenseCategory cat ON " +
                "ex.category_id cat";
        Query query = session.createQuery(queryString);
        try {
            query.setParameter("date", LocalDate.of(year, month, 30));
        } catch (IllegalArgumentException ex) {
            System.err.println("only numbers can be used");
        }
        return query.list();
    }

    public List<MonthlyExpenseSum> testReport(int month, int year) {
        Session session = factory.openSession();
//        Query<MonthlyExpenseSum> query = session.createQuery("select new pojo.MonthlyExpenseSum(ex.value, cat.name) " +
//                "from entity.Expense e, ExpenseCategory cat ON e.category_id = cat.id GROUP BY cat.category_id");

//        Query<MonthlyExpenseSum> query = session.createQuery("select new pojo.MonthlyExpenseSum(SUM(e.value), cat) " +
//                "from entity.Expense e inner join ExpenseCategory cat on e.expenseCategory.id = cat.id GROUP BY cat.id");

        Query<MonthlyExpenseSum> query = session.createQuery("select new pojo.MonthlyExpenseSum(SUM(e.value), e.expenseCategory) " +
                "from entity.Expense e WHERE MONTH(e.date) = month(:date) AND YEAR(e.date) = year(:date) GROUP BY e.expenseCategory.id");
        try {
            query.setParameter("date", LocalDate.of(year, month, 30));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        List<MonthlyExpenseSum> results = query.list();
        return results;
    }

}
