package DAO;

import entity.ExpenseCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ExpenseCategoryDAO extends DAO {
    public HashMap<String, ExpenseCategory> categoryHashMap;

    public ExpenseCategoryDAO(SessionFactory factory)
    {
        super(factory);
        categoryHashMap = new HashMap<>();
        List<String> categoryStr = show();
        List<ExpenseCategory> categoryObj = getCategoryObjects();
        for (int i = 0; i < categoryStr.size(); i++) {
            categoryHashMap.put(categoryStr.get(i), categoryObj.get(i));
        }
    }

    public List<String> show() {
        List<ExpenseCategory> list = getSession().createQuery("SELECT name FROM ExpenseCategory name", ExpenseCategory.class).getResultList();
        List<String> categories = new ArrayList<>();
        for(ExpenseCategory ex : list) {
            categories.add(ex.getName().toLowerCase());
        }
        return categories;
    }

    public void addCategory(String category) {
        insert(category);
    }

    public void addCategories() {
        ArrayList<ExpenseCategory> categoriesToAdd = new ArrayList<>();
        ArrayList<String> notAdded = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String s;
        System.out.println("Enter your new categories name (each separated by a space)");
        s = sc.nextLine();
        String[] splitted = s.split(" ");

        for (String str : splitted) {
            if (!show().contains(str.toLowerCase())) {
                ExpenseCategory expenseCategory = new ExpenseCategory();
                expenseCategory.setName(str);
                categoriesToAdd.add(expenseCategory);
            }
            else {
                notAdded.add(str);
            }
        }
        insert(categoriesToAdd);
        if(!notAdded.isEmpty()) {
            System.err.println("categories " + notAdded + "couldn't be added because they already exist");
        }
    }

    private Session getSession() {
        return factory.openSession();
    }

    private void insert(ArrayList<ExpenseCategory> cat) {
        try(Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            for(ExpenseCategory category : cat) {
                session.save(category);
            }
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void insert(String category) {
        try(Session session = factory.openSession()) {
            ExpenseCategory expenseCategory = new ExpenseCategory();
            expenseCategory.setName(category);
            Transaction tx = session.beginTransaction();
            session.save(expenseCategory);
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

     public List getCategoryObjects() {
        return getSession().createQuery("SELECT ec FROM ExpenseCategory ec", ExpenseCategory.class).list();
    }
}
