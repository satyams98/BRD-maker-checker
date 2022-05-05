package com.brd.entity;

public class FillRecord {
    static public CustomerPerm toPerm(CustomerTemp customerTemp){
        CustomerPerm customerPerm = new CustomerPerm();
        customerPerm.setCustomerCode(customerTemp.getCustomerCode());
        customerPerm.setCustomerId(customerTemp.getCustomerId());
        customerPerm.setCustomerName(customerTemp.getCustomerName());
        customerPerm.setAdd1(customerTemp.getAdd1());
        customerPerm.setAdd2(customerTemp.getAdd2());
        customerPerm.setPinCode(customerTemp.getPinCode());
        customerPerm.setEmailAdd(customerTemp.getEmailAdd());
        customerPerm.setContactNo(customerTemp.getContactNo());
        customerPerm.setPrimaryContactPerson(customerTemp.getPrimaryContactPerson());
        customerPerm.setRecordStatus(customerTemp.getRecordStatus());
        customerPerm.setActiveInactiveFlag(customerTemp.getActiveInactiveFlag());
        customerPerm.setCreateDate(customerTemp.getCreateDate());
        customerPerm.setCreatedBy(customerTemp.getCreatedBy());
        customerPerm.setModifiedDate(customerTemp.getModifiedDate());
        customerPerm.setModifiedBy(customerTemp.getModifiedBy());
        customerPerm.setAuthorizeDate(customerTemp.getAuthorizeDate());
        customerPerm.setAuthorizeBy(customerTemp.getAuthorizeBy());
        return customerPerm;

    }
    static public CustomerTemp toTemp(CustomerPerm customerPerm){
        CustomerTemp customerTemp = new CustomerTemp();
        customerTemp.setCustomerCode(customerPerm.getCustomerCode());
        customerTemp.setCustomerId(customerPerm.getCustomerId());
        customerTemp.setCustomerName(customerPerm.getCustomerName());
        customerTemp.setAdd1(customerPerm.getAdd1());
        customerTemp.setAdd2(customerPerm.getAdd2());
        customerTemp.setPinCode(customerPerm.getPinCode());
        customerTemp.setEmailAdd(customerPerm.getEmailAdd());
        customerTemp.setContactNo(customerPerm.getContactNo());
        customerTemp.setPrimaryContactPerson(customerPerm.getPrimaryContactPerson());
        customerTemp.setRecordStatus(customerPerm.getRecordStatus());
        customerTemp.setActiveInactiveFlag(customerPerm.getActiveInactiveFlag());
        customerTemp.setCreateDate(customerPerm.getCreateDate());
        customerTemp.setCreatedBy(customerPerm.getCreatedBy());
        customerTemp.setModifiedDate(customerPerm.getModifiedDate());
        customerTemp.setModifiedBy(customerPerm.getModifiedBy());
        customerTemp.setAuthorizeDate(customerPerm.getAuthorizeDate());
        customerTemp.setAuthorizeBy(customerPerm.getAuthorizeBy());
        return customerTemp;

    }
}
