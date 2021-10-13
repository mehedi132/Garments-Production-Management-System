/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garments.management.system;

/**
 *
 * @author mh200
 */
public class order {
    int oid,bid,pid,quantity;
    String o_date;
    float tamount;
    String d_date, o_status;

    public order(int oid, int bid, int pid, int quantity, String o_date, float tamount, String d_date, String o_status) {
        this.oid = oid;
        this.bid = bid;
        this.pid = pid;
        this.quantity = quantity;
        this.o_date = o_date;
        this.tamount = tamount;
        this.d_date = d_date;
        this.o_status = o_status;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getO_date() {
        return o_date;
    }

    public void setO_date(String o_date) {
        this.o_date = o_date;
    }

    public float getTamount() {
        return tamount;
    }

    public void setTamount(float tamount) {
        this.tamount = tamount;
    }

    public String getD_date() {
        return d_date;
    }

    public void setD_date(String d_date) {
        this.d_date = d_date;
    }

    public String getO_status() {
        return o_status;
    }

    public void setO_status(String o_status) {
        this.o_status = o_status;
    }
    
    
}
