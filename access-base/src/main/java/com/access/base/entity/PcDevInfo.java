package com.access.base.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "PC_DEV_INFO")
public class PcDevInfo {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_PC_DEV_INFO_ID.nextval from dual")
    private Short id;

    @Column(name = "PID")
    private Short pid;

    @Column(name = "DEVCODE")
    private String devcode;

    @Column(name = "DEVTYPEID")
    private Short devtypeid;

    @Column(name = "DEVNAME")
    private String devname;

    @Column(name = "ADDR")
    private Short addr;

    @Column(name = "PADDR")
    private Short paddr;

    @Column(name = "COMMCONTEXT")
    private String commcontext;

    @Column(name = "WORKSTATIONID")
    private Short workstationid;

    @Column(name = "NETSTATUS")
    private Short netstatus;

    @Column(name = "DEVSTATUS")
    private Short devstatus;

    @Column(name = "VER")
    private Short ver;

    @Column(name = "VERFLAG")
    private Short verflag;

    @Column(name = "ISACTIVED")
    private Short isactived;

    @Column(name = "FUNCTIONTYPE")
    private Short functiontype;

    @Column(name = "ISSENDSMS")
    private Short issendsms;

    @Column(name = "COLLECTSTIONCODE")
    private Short collectstioncode;

    @Column(name = "NETSTATUSTIME")
    private Date netstatustime;

    /**
     * @return ID
     */
    public Short getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Short id) {
        this.id = id;
    }

    /**
     * @return PID
     */
    public Short getPid() {
        return pid;
    }

    /**
     * @param pid
     */
    public void setPid(Short pid) {
        this.pid = pid;
    }

    /**
     * @return DEVCODE
     */
    public String getDevcode() {
        return devcode;
    }

    /**
     * @param devcode
     */
    public void setDevcode(String devcode) {
        this.devcode = devcode;
    }

    /**
     * @return DEVTYPEID
     */
    public Short getDevtypeid() {
        return devtypeid;
    }

    /**
     * @param devtypeid
     */
    public void setDevtypeid(Short devtypeid) {
        this.devtypeid = devtypeid;
    }

    /**
     * @return DEVNAME
     */
    public String getDevname() {
        return devname;
    }

    /**
     * @param devname
     */
    public void setDevname(String devname) {
        this.devname = devname;
    }

    /**
     * @return ADDR
     */
    public Short getAddr() {
        return addr;
    }

    /**
     * @param addr
     */
    public void setAddr(Short addr) {
        this.addr = addr;
    }

    /**
     * @return PADDR
     */
    public Short getPaddr() {
        return paddr;
    }

    /**
     * @param paddr
     */
    public void setPaddr(Short paddr) {
        this.paddr = paddr;
    }

    /**
     * @return COMMCONTEXT
     */
    public String getCommcontext() {
        return commcontext;
    }

    /**
     * @param commcontext
     */
    public void setCommcontext(String commcontext) {
        this.commcontext = commcontext;
    }

    /**
     * @return WORKSTATIONID
     */
    public Short getWorkstationid() {
        return workstationid;
    }

    /**
     * @param workstationid
     */
    public void setWorkstationid(Short workstationid) {
        this.workstationid = workstationid;
    }

    /**
     * @return NETSTATUS
     */
    public Short getNetstatus() {
        return netstatus;
    }

    /**
     * @param netstatus
     */
    public void setNetstatus(Short netstatus) {
        this.netstatus = netstatus;
    }

    /**
     * @return DEVSTATUS
     */
    public Short getDevstatus() {
        return devstatus;
    }

    /**
     * @param devstatus
     */
    public void setDevstatus(Short devstatus) {
        this.devstatus = devstatus;
    }

    /**
     * @return VER
     */
    public Short getVer() {
        return ver;
    }

    /**
     * @param ver
     */
    public void setVer(Short ver) {
        this.ver = ver;
    }

    /**
     * @return VERFLAG
     */
    public Short getVerflag() {
        return verflag;
    }

    /**
     * @param verflag
     */
    public void setVerflag(Short verflag) {
        this.verflag = verflag;
    }

    /**
     * @return ISACTIVED
     */
    public Short getIsactived() {
        return isactived;
    }

    /**
     * @param isactived
     */
    public void setIsactived(Short isactived) {
        this.isactived = isactived;
    }

    /**
     * @return FUNCTIONTYPE
     */
    public Short getFunctiontype() {
        return functiontype;
    }

    /**
     * @param functiontype
     */
    public void setFunctiontype(Short functiontype) {
        this.functiontype = functiontype;
    }

    /**
     * @return ISSENDSMS
     */
    public Short getIssendsms() {
        return issendsms;
    }

    /**
     * @param issendsms
     */
    public void setIssendsms(Short issendsms) {
        this.issendsms = issendsms;
    }

    /**
     * @return COLLECTSTIONCODE
     */
    public Short getCollectstioncode() {
        return collectstioncode;
    }

    /**
     * @param collectstioncode
     */
    public void setCollectstioncode(Short collectstioncode) {
        this.collectstioncode = collectstioncode;
    }

    /**
     * @return NETSTATUSTIME
     */
    public Date getNetstatustime() {
        return netstatustime;
    }

    /**
     * @param netstatustime
     */
    public void setNetstatustime(Date netstatustime) {
        this.netstatustime = netstatustime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pid=").append(pid);
        sb.append(", devcode=").append(devcode);
        sb.append(", devtypeid=").append(devtypeid);
        sb.append(", devname=").append(devname);
        sb.append(", addr=").append(addr);
        sb.append(", paddr=").append(paddr);
        sb.append(", commcontext=").append(commcontext);
        sb.append(", workstationid=").append(workstationid);
        sb.append(", netstatus=").append(netstatus);
        sb.append(", devstatus=").append(devstatus);
        sb.append(", ver=").append(ver);
        sb.append(", verflag=").append(verflag);
        sb.append(", isactived=").append(isactived);
        sb.append(", functiontype=").append(functiontype);
        sb.append(", issendsms=").append(issendsms);
        sb.append(", collectstioncode=").append(collectstioncode);
        sb.append(", netstatustime=").append(netstatustime);
        sb.append("]");
        return sb.toString();
    }
}