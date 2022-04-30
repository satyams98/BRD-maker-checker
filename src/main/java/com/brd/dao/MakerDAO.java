package com.brd.dao;

import com.brd.entity.Checker;
import com.brd.entity.Maker;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class MakerDAO extends DAOImplementation {
    @Autowired
    private SessionFactory sessionFactory;
    final long id = 10120;
    final String userName = "Shivam";
    final String pass = "password";
    private Maker maker=null;
    @Transactional
    private boolean createMaker(){
        if (maker==null) {
            maker = new Maker();
            maker.setId(id);
            maker.setUserName(userName);
            maker.setPass(pass);
            sessionFactory.getCurrentSession().save(maker);
        }
        return true;
    }
    public Maker getMaker() {
        createMaker();
        return sessionFactory.getCurrentSession().get(Maker.class, id);
    }
}
