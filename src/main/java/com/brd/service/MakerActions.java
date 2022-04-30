package com.brd.service;

import com.brd.dao.DAO;
import com.brd.entity.CustomerPerm;
import com.brd.entity.CustomerTemp;
import com.brd.entity.RecordStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.*;

@Service
public class MakerActions {
    @Autowired
    @Qualifier("makerDAO")
    private DAO makerDAO;

    public boolean createNewRecord(String maker, CustomerTemp customerTemp){
        customerTemp.setCreatedBy(maker);
        customerTemp.setRecordStatus(RecordStatus.N);
        customerTemp.setCreateDate(LocalDate.now());
        return makerDAO.createNewTempRecord(customerTemp);
    }

    public List<CustomerTemp> getTempCustomerList() {
        return makerDAO.getTempCustomerList();

    }
    public List<CustomerPerm> getPermCustomerList(){
        return makerDAO.getPermCustomerList();
    }

    public boolean modifies(String maker, CustomerTemp customerTemp){

        if(customerTemp.getRecordStatus().equals(RecordStatus.A)){
            customerTemp.setRecordStatus(RecordStatus.M);
        }
        else if(customerTemp.getRecordStatus().equals(RecordStatus.MR)){
            customerTemp.setRecordStatus(RecordStatus.M);
        }
        else if(customerTemp.getRecordStatus().equals(RecordStatus.NR)){
            customerTemp.setRecordStatus(RecordStatus.N);
        }

        customerTemp.setModifiedBy(maker);
        LocalDateTime now = LocalDateTime.now();
        customerTemp.setModifiedDate(now);
        customerTemp.setModifies(new AbstractMap.SimpleEntry<>(now, maker));
        makerDAO.updateTempRecord(customerTemp);
       return true;
    }

    public boolean deletes(CustomerTemp customerTemp){
        if(customerTemp.getRecordStatus().equals(RecordStatus.A)){
            customerTemp.setRecordStatus(RecordStatus.D);
            makerDAO.createNewTempRecord(customerTemp);
        }
        else{
            makerDAO.deleteTempRecord(customerTemp);
        }
        return true;
    }
}
