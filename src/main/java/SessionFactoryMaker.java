import entity.Expense;
import entity.ExpenseCategory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class SessionFactoryMaker {
    private static SessionFactory factory;

    private static void configureFactory() {
        try {
            factory = new Configuration()
                    .addAnnotatedClass(Expense.class)
                    .addAnnotatedClass(ExpenseCategory.class)
                    .configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object");
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static org.hibernate.SessionFactory getFactory() {
        if (factory == null) {
            configureFactory();
        }

        return factory;
    }
}
