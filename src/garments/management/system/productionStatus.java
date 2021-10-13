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
public class productionStatus {
    int psid,oid,pid;
    String pdate;
    int torder,todaycom,totalcom,uncom;
    String ldate;

    public productionStatus(int psid, int oid, int pid, String pdate, int torder, int todaycom, int totalcom, int uncom, String ldate) {
        this.psid = psid;
        this.oid = oid;
        this.pid = pid;
        this.pdate = pdate;
        this.torder = torder;
        this.todaycom = todaycom;
        this.totalcom = totalcom;
        this.uncom = uncom;
        this.ldate = ldate;
    }

    public int getPsid() {
        return psid;
    }

    public void setPsid(int psid) {
        this.psid = psid;
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

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public int getTorder() {
        return torder;
    }

    public void setTorder(int torder) {
        this.torder = torder;
    }

    public int getTodaycom() {
        return todaycom;
    }

    public void setTodaycom(int todaycom) {
        this.todaycom = todaycom;
    }

    public int getTotalcom() {
        return totalcom;
    }

    public void setTotalcom(int totalcom) {
        this.totalcom = totalcom;
    }

    public int getUncom() {
        return uncom;
    }

    public void setUncom(int uncom) {
        this.uncom = uncom;
    }

    public String getLdate() {
        return ldate;
    }

    public void setLdate(String ldate) {
        this.ldate = ldate;
    }
    
    
}
