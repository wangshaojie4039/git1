package cn.imexue.ec.common.model;

import java.io.Serializable;
import java.math.BigDecimal;

import cn.imexue.ec.common.model.common.DataEntity;

public class Customer extends DataEntity implements Serializable{
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 6228794611892509029L;

    private String name;

    private String mobile;

    private String linkman;

    private String tel;

    private String address;

    private Byte roleType;

    private String bankCardName;

    private String bankCardNo;

    private String bankCardBranch;

    private BigDecimal withdrawAmount;

    private BigDecimal withdrawBalance;

    private Byte isActive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Byte getRoleType() {
        return roleType;
    }

    public void setRoleType(Byte roleType) {
        this.roleType = roleType;
    }

    public String getBankCardName() {
        return bankCardName;
    }

    public void setBankCardName(String bankCardName) {
        this.bankCardName = bankCardName;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankCardBranch() {
        return bankCardBranch;
    }

    public void setBankCardBranch(String bankCardBranch) {
        this.bankCardBranch = bankCardBranch;
    }

    public BigDecimal getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(BigDecimal withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public BigDecimal getWithdrawBalance() {
        return withdrawBalance;
    }

    public void setWithdrawBalance(BigDecimal withdrawBalance) {
        this.withdrawBalance = withdrawBalance;
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

}