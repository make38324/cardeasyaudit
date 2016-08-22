package com.swntek.czm.cardeasyaudit.pojo;

import java.io.Serializable;

/**
 * Created by caozhimin on 2016/8/5.
 * email：496950806@qq.com
 */
public class Audit implements Serializable {
    private String id;
    private String manager;
    private String name;
    private String detail_addr;
    private String address;
    private String address1;
    private String c_name;
    private String phone;
    private String create_date;
    /**
     * 0 待审核
     * 1 审核通过
     * 2 审核失败
     */
    private String status;
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setManager(String manager){
        this.manager = manager;
    }
    public String getManager(){
        return this.manager;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return this.address;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getPhone(){
        return this.phone;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setRemark(String remark){
        this.remark = remark;
    }
    public String getRemark(){
        return this.remark;
    }
    public String getCreate_date() {
        return create_date;
    }
    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }
    public String getDetail_addr() {
        return detail_addr;
    }
    public void setDetail_addr(String detail_addr) {
        this.detail_addr = detail_addr;
    }
    public String getAddress1() {
        return address1;
    }
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }
}
