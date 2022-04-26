package com.brd.dao;

import java.util.List;

import com.brd.entity.CustomerPerm;
import com.brd.entity.CustomerTemp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(readOnly = true)
public class DAOImplementation implements DAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public boolean createNewTempRecord(CustomerTemp customerTemp) {
        sessionFactory.getCurrentSession().save(customerTemp);
        return true;
    }
    @Transactional
    @Override
    public boolean createNewPermRecord(CustomerPerm customerPerm) {
        sessionFactory.getCurrentSession().save(customerPerm);
        return true;
    }

    @Override
    public CustomerTemp getTempRecord(String customerCode) {
        return sessionFactory.getCurrentSession().get(CustomerTemp.class, customerCode);

    }
    

    @Override
    public CustomerPerm getPermRecord(String customerCode) {
        return sessionFactory.getCurrentSession().get(CustomerPerm.class, customerCode);
    }

    @Transactional
    @Override
    public boolean updateTempRecord(CustomerTemp customerTemp) {
        sessionFactory.getCurrentSession().update(customerTemp);
        return true;
    }

    @Transactional
    @Override
    public boolean updatePermRecord(CustomerPerm customerPerm) {
        sessionFactory.getCurrentSession().update(customerPerm);
        return true;

    }
    @Transactional
    @Override
    public boolean deleteTempRecord(CustomerTemp customerTemp) {
        sessionFactory.getCurrentSession().delete(customerTemp);
        return true;
    }
    @Transactional
    @Override
    public boolean deletePermRecord(CustomerPerm customerPerm) {
        sessionFactory.getCurrentSession().delete(customerPerm);
        return true;
    }
    @Override
    public List<CustomerTemp> getTempCustomerList() {
        
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM CustomerTemp");
        session.getSession().beginTransaction();
        List<CustomerTemp> customerList = query.getResultList();
        session.getTransaction().commit();
        return customerList;
    }
    @Override
    public List<CustomerPerm> getPermCustomerList() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM CustomerPerm");
        session.getSession().beginTransaction();
        List<CustomerPerm> customerList = query.getResultList();
        session.getTransaction().commit();
        return customerList;
    }
}
