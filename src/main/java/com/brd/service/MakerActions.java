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

    public boolean createNewRecord(CustomerTemp customerTemp){
        customerTemp.setCustomerCode("s101");
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

    public boolean modifies(String checker, CustomerTemp customerTemp){
        CustomerPerm customerPerm = makerDAO.getPermRecord(customerTemp.getCustomerCode());
        if(customerTemp.getRecordStatus().equals(RecordStatus.N) ||
                customerTemp.getRecordStatus().equals(RecordStatus.M)||
       customerPerm!=null ){

            if(customerPerm!=null){
                customerTemp.setRecordStatus(RecordStatus.M);
            }
        }

        if(customerTemp.getRecordStatus().equals(RecordStatus.NR)||
        customerTemp.getRecordStatus().equals(RecordStatus.MR)){
            customerTemp.setRecordStatus(RecordStatus.M);
        }

        customerTemp.setModifiedDate(LocalDateTime.now());
        customerTemp.setModifiedBy(checker);
        customerTemp.setModifies(new AbstractMap.SimpleEntry<>(customerTemp.getModifiedDate(), customerTemp.getModifiedBy()));
        makerDAO.updateTempRecord(customerTemp);
       return true;
    }

    public boolean deletes(CustomerTemp customerTemp){
        if(customerTemp.getRecordStatus().equals(RecordStatus.M)||
        customerTemp.getRecordStatus().equals(RecordStatus.N)||
        customerTemp.getRecordStatus().equals(RecordStatus.NR)||
        customerTemp.getRecordStatus().equals(RecordStatus.MR)){

            makerDAO.deleteTempRecord(customerTemp);
        }
        else {
            CustomerPerm customerPerm = makerDAO.getPermRecord(customerTemp.getCustomerCode());
            if (customerPerm != null) {
                customerTemp.setRecordStatus(RecordStatus.D);
                makerDAO.updateTempRecord(customerTemp);
            }
        }
        return true;
    }


}
