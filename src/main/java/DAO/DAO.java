package DAO;

import org.hibernate.SessionFactory;


public abstract class DAO {
    protected final SessionFactory factory;

    public DAO(SessionFactory factory) {
        this.factory = factory;
    }

}
