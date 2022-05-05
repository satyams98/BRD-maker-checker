package com.brd.service;

import com.brd.dao.DAO;
import com.brd.dao.DAOImplementation;
import com.brd.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CheckerActions {
    @Autowired
    @Qualifier("checkerDAO")
    private DAO checkerDAO;


    public boolean authorize(String checker, CustomerTemp customerTemp){
        customerTemp.setActiveInactiveFlag(ActiveInactiveFlag.A);
        if(customerTemp.getRecordStatus().equals(RecordStatus.N)) {
            CustomerPerm customerPerm = FillRecord.toPerm(customerTemp);
            customerPerm.setRecordStatus(RecordStatus.A);
            customerPerm.setAuthorizeBy(checker);
            customerPerm.setAuthorizeDate(LocalDateTime.now());
            checkerDAO.createNewPermRecord(customerPerm);
            checkerDAO.deleteTempRecord(customerTemp);
        }
        else if(customerTemp.getRecordStatus().equals(RecordStatus.M)){
            checkerDAO.deleteTempRecord(customerTemp);
            CustomerPerm customerPerm = FillRecord.toPerm(customerTemp);
            customerPerm.setRecordStatus(RecordStatus.A);
            customerPerm.setAuthorizeBy(checker);
            customerPerm.setAuthorizeDate(LocalDateTime.now());
            checkerDAO.updatePermRecord(customerPerm);
        }
        else if(customerTemp.getRecordStatus().equals(RecordStatus.D)){
            checkerDAO.deleteTempRecord(customerTemp);
            CustomerPerm customerPerm = FillRecord.toPerm(customerTemp);
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
        checkerDAO.updateTempRecord(customerTemp);
        return true;
    }
}
