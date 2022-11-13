import DAO.ExpenseCategoryDAO;
import DAO.ExpenseDAO;
import org.hibernate.SessionFactory;
import java.util.Scanner;

public class ProgramController {
    private final ExpenseCategoryDAO expenseCategoryDAO;
    private final ExpenseDAO expenseDAO;
    private final QueryService queryService;

    public ProgramController(SessionFactory factory) {
        this.expenseCategoryDAO = new ExpenseCategoryDAO(factory);
        this.expenseDAO = new ExpenseDAO(factory);
        this.queryService = new QueryService(factory);
    }

    public void showMenu() {
        Scanner sc = new Scanner(System.in);
        String s = "";
        while(!"exit".equals(s)) {
            System.out.println(showOptions());
            s = sc.next();
            if (!"exit".equals(s)) {
                switch (s) {
                    case "1" -> System.out.println(expenseCategoryDAO.show());
                    case "2" -> expenseCategoryDAO.addCategories();
                    case "3" -> expenseDAO.addExpenses();
                    case "4" -> queryService.getReport(10, 2022);
                    case "exit" -> System.exit(0);
                    default -> System.out.println("Wrong char");
                }
            }
        }
    }

    private String showOptions() {
        return """
                1 - show categories
                2 - add categories
                3 - add expense
                """;
    }

}
