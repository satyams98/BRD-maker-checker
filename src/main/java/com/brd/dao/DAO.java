package com.brd.dao;

import com.brd.entity.CustomerPerm;
import com.brd.entity.CustomerTemp;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface DAO {
        public boolean createNewTempRecord(CustomerTemp customerTemp);
        public boolean createNewPermRecord(CustomerPerm customerPerm);

        public CustomerTemp getTempRecord(String customerCode);
        public CustomerPerm getPermRecord(String customerCode);
        
        public List<CustomerTemp> getTempCustomerList();
        public List<CustomerPerm> getPermCustomerList();


        public boolean updateTempRecord(CustomerTemp customerTemp);
        public boolean updatePermRecord(CustomerPerm customerPerm);

        public boolean deleteTempRecord(CustomerTemp customerTemp);
        public boolean deletePermRecord(CustomerPerm customerPerm);
}
