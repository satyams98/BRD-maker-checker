package com.brd.dao;

import com.brd.entity.Checker;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(readOnly = true)
public class CheckerDAO extends DAOImplementation {
    @Autowired
    private SessionFactory sessionFactory;
    final long id = 10120;
    final String userName = "Satyam";
    final String pass = "password";
    private Checker checker=null;
    @Transactional
    private boolean createChecker(){
        if (checker==null) {
            checker = new Checker();
            checker.setId(id);
            checker.setUserName(userName);
            checker.setPass(pass);
            sessionFactory.getCurrentSession().save(checker);
        }
        return true;
    }
    public Checker getChecker() {
        createChecker();
        return sessionFactory.getCurrentSession().get(Checker.class, id);
    }
}
