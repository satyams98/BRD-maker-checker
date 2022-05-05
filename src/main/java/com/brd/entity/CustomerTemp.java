package com.brd.entity;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class CustomerTemp {
    @Id
    @Column(name = "customerCode", nullable = false, length = 10)
    private String customerCode;
    private long customerId;
    private String customerName;
    private String add1;
    private String add2;
    private int pinCode;
    private String emailAdd;
    private Long contactNo;
    private String primaryContactPerson;
    private RecordStatus recordStatus;
    private ActiveInactiveFlag activeInactiveFlag;
    private LocalDate createDate;
    private String createdBy;
    private LocalDateTime modifiedDate;
    private String modifiedBy;
    private LocalDateTime authorizeDate;
    private String authorizeBy;

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getAdd1() {
        return add1;
    }

    public void setAdd1(String customerAdd1) {
        this.add1 = customerAdd1;
    }

    public String getAdd2() {
        return add2;
    }

    public void setAdd2(String customerAdd2) {
        this.add2 = customerAdd2;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public String getEmailAdd() {
        return emailAdd;
    }

    public void setEmailAdd(String emailAdd) {
        this.emailAdd = emailAdd;
    }

    public Long getContactNo() {
        return contactNo;
    }

    public void setContactNo(Long contactNo) {
        this.contactNo = contactNo;
    }

    public String getPrimaryContactPerson() {
        return primaryContactPerson;
    }

    public void setPrimaryContactPerson(String primaryContactPerson) {
        this.primaryContactPerson = primaryContactPerson;
    }

    public RecordStatus getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }

    public ActiveInactiveFlag getActiveInactiveFlag() {
        return activeInactiveFlag;
    }

    public void setActiveInactiveFlag(ActiveInactiveFlag activeInactiveFlag) {
        this.activeInactiveFlag = activeInactiveFlag;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getAuthorizeDate() {
        return authorizeDate;
    }

    public void setAuthorizeDate(LocalDateTime authorizeDate) {
        this.authorizeDate = authorizeDate;
    }

    public String getAuthorizeBy() {
        return authorizeBy;
    }

    public void setAuthorizeBy(String authorizeBy) {
        this.authorizeBy = authorizeBy;
    }

}
