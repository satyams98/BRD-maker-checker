package com.brd.service;

import com.brd.dao.DAO;
import com.brd.dao.DAOImplementation;
import com.brd.entity.CustomerPerm;
import com.brd.entity.CustomerTemp;
import com.brd.entity.RecordStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.Map;

@Service
public class CheckerActions {
    @Autowired
    @Qualifier("checkerDAO")
    private DAO checkerDAO;


    public boolean authorize(String checker, CustomerTemp customerTemp){
        if(customerTemp.getRecordStatus().equals(RecordStatus.N)) {
            checkerDAO.deleteTempRecord(customerTemp);
            CustomerPerm customerPerm = (CustomerPerm) customerTemp;
            customerPerm.setRecordStatus(RecordStatus.A);
            customerPerm.setAuthorizeBy(checker);
            customerPerm.setAuthorizeDate(LocalDateTime.now());
            customerPerm.setAuthorizes(new AbstractMap.SimpleEntry<>(customerPerm.getAuthorizeDate(), customerPerm.getAuthorizeBy()));
            checkerDAO.createNewPermRecord(customerPerm);
        }
        else if(customerTemp.getRecordStatus().equals(RecordStatus.M)){
            checkerDAO.deleteTempRecord(customerTemp);
            CustomerPerm customerPerm = (CustomerPerm) customerTemp;
            customerPerm.setRecordStatus(RecordStatus.A);
            customerPerm.setAuthorizeBy(checker);
            customerPerm.setAuthorizeDate(LocalDateTime.now());
            customerPerm.setAuthorizes(new AbstractMap.SimpleEntry<>(customerPerm.getAuthorizeDate(), customerPerm.getAuthorizeBy()));
            checkerDAO.updatePermRecord(customerPerm);

        }
        else if(customerTemp.getRecordStatus().equals(RecordStatus.D)){
            checkerDAO.deleteTempRecord(customerTemp);
            CustomerPerm customerPerm = (CustomerPerm) customerTemp;
            checkerDAO.deletePermRecord(customerPerm);

        }
        return true;
    }
    public boolean reject(CustomerTemp customerTemp){
        if(customerTemp.getRecordStatus().equals(RecordStatus.N)) {
            customerTemp.setRecordStatus(RecordStatus.NR);
        }
        else if(customerTemp.getRecordStatus().equals(RecordStatus.M)){
            customerTemp.setRecordStatus(RecordStatus.MR);
        }
        else if(customerTemp.getRecordStatus().equals(RecordStatus.D)){
            customerTemp.setRecordStatus(RecordStatus.DR);
        }
        return true;
    }
}
