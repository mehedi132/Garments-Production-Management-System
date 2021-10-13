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
public class production {
    int productionId,oid,pid,eid,cproduct;
    float tsalary;
    String date;

    public production(int productionId, int oid, int pid, int eid, int cproduct, float tsalary, String date) {
        this.productionId = productionId;
        this.oid = oid;
        this.pid = pid;
        this.eid = eid;
        this.cproduct = cproduct;
        this.tsalary = tsalary;
        this.date = date;
    }

    public int getProductionId() {
        return productionId;
    }

    public void setProductionId(int productionId) {
        this.productionId = productionId;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getCproduct() {
        return cproduct;
    }

    public void setCproduct(int cproduct) {
        this.cproduct = cproduct;
    }

    public float getTsalary() {
        return tsalary;
    }

    public void setTsalary(float tsalary) {
        this.tsalary = tsalary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
