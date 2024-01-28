import DAO.ExpenseCategoryDAO;
import entity.ExpenseCategory;
import org.hibernate.SessionFactory;
import pojo.MonthlyExpenseSum;

import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("a", 20);

        System.out.println();
        SessionFactory factory = SessionFactoryMaker.getFactory();
        ProgramController programController = new ProgramController(factory);
       programController.showMenu();
        QueryService queryService = new QueryService(factory);
        System.out.println(queryService.getReport(10, 2022));
        List<MonthlyExpenseSum> list = queryService.testReport(11, 2022);
        for (Object o : list) {
            System.out.println(o);
        }
    }

}


