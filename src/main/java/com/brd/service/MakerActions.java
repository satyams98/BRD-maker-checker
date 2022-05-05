package com.brd.service;
import com.brd.dao.DAO;
import com.brd.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MakerActions {
    @Autowired
    @Qualifier("makerDAO")
    private DAO makerDAO;

    public boolean createNewRecord(String maker, CustomerTemp customerTemp){
        long id = ThreadLocalRandom.current().nextLong(1000, 5000);
        customerTemp.setCustomerId(id);
        customerTemp.setCustomerCode("Cust"+id);
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

         if(customerTemp.getRecordStatus().equals(RecordStatus.MR)){
            customerTemp.setRecordStatus(RecordStatus.M);
        }
        else if(customerTemp.getRecordStatus().equals(RecordStatus.NR)){
            customerTemp.setRecordStatus(RecordStatus.N);
        }

        customerTemp.setModifiedBy(maker);
        LocalDateTime now = LocalDateTime.now();
        customerTemp.setModifiedDate(now);
        makerDAO.updateTempRecord(customerTemp);
       return true;
    }
    public boolean modifies(String maker, CustomerPerm customerPerm){
        if(customerPerm.getRecordStatus().equals(RecordStatus.A)){
            CustomerTemp customerTemp = FillRecord.toTemp(customerPerm);
            customerTemp.setRecordStatus(RecordStatus.M);
            customerTemp.setModifiedBy(maker);
            LocalDateTime now = LocalDateTime.now();
            customerTemp.setModifiedDate(now);
            makerDAO.createNewTempRecord(customerTemp);
        }
        return true;
    }

    public boolean deletes(CustomerTemp customerTemp){
            makerDAO.deleteTempRecord(customerTemp);
        return true;
    }

    public boolean deletes(CustomerPerm customerPerm){
        if(customerPerm.getRecordStatus().equals(RecordStatus.A)){
            CustomerTemp customerTemp = FillRecord.toTemp(customerPerm);
            customerTemp.setRecordStatus(RecordStatus.D);
            makerDAO.createNewTempRecord(customerTemp);
        }
        return true;
    }
}
