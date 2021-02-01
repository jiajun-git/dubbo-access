package com.access.api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "PC_DEV_INFO")
public class PcDevInfo implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_PC_DEV_INFO_ID.nextval from dual")
    private Integer id;

    @Column(name = "PID")
    private Integer pid;

    @Column(name = "DEVCODE")
    private String devcode;

    @Column(name = "DEVTYPEID")
    private Integer devtypeid;

    @Column(name = "DEVNAME")
    private String devname;

    @Column(name = "ADDR")
    private Integer addr;

    @Column(name = "PADDR")
    private Integer paddr;

    @Column(name = "COMMCONTEXT")
    private String commcontext;

    @Column(name = "WORKSTATIONID")
    private Integer workstationid;

    @Column(name = "NETSTATUS")
    private Integer netstatus;

    @Column(name = "DEVSTATUS")
    private Integer devstatus;

    @Column(name = "VER")
    private Integer ver;

    @Column(name = "VERFLAG")
    private Integer verflag;

    @Column(name = "ISACTIVED")
    private Integer isactived;

    @Column(name = "FUNCTIONTYPE")
    private Integer functiontype;

    @Column(name = "ISSENDSMS")
    private Integer issendsms;

    @Column(name = "COLLECTSTIONCODE")
    private Integer collectstioncode;

    @Column(name = "NETSTATUSTIME")
    private Date netstatustime;

    /**
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return PID
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * @param pid
     */
    public void setPid(Integer pid) {
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
    public Integer getDevtypeid() {
        return devtypeid;
    }

    /**
     * @param devtypeid
     */
    public void setDevtypeid(Integer devtypeid) {
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
    public Integer getAddr() {
        return addr;
    }

    /**
     * @param addr
     */
    public void setAddr(Integer addr) {
        this.addr = addr;
    }

    /**
     * @return PADDR
     */
    public Integer getPaddr() {
        return paddr;
    }

    /**
     * @param paddr
     */
    public void setPaddr(Integer paddr) {
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
    public Integer getWorkstationid() {
        return workstationid;
    }

    /**
     * @param workstationid
     */
    public void setWorkstationid(Integer workstationid) {
        this.workstationid = workstationid;
    }

    /**
     * @return NETSTATUS
     */
    public Integer getNetstatus() {
        return netstatus;
    }

    /**
     * @param netstatus
     */
    public void setNetstatus(Integer netstatus) {
        this.netstatus = netstatus;
    }

    /**
     * @return DEVSTATUS
     */
    public Integer getDevstatus() {
        return devstatus;
    }

    /**
     * @param devstatus
     */
    public void setDevstatus(Integer devstatus) {
        this.devstatus = devstatus;
    }

    /**
     * @return VER
     */
    public Integer getVer() {
        return ver;
    }

    /**
     * @param ver
     */
    public void setVer(Integer ver) {
        this.ver = ver;
    }

    /**
     * @return VERFLAG
     */
    public Integer getVerflag() {
        return verflag;
    }

    /**
     * @param verflag
     */
    public void setVerflag(Integer verflag) {
        this.verflag = verflag;
    }

    /**
     * @return ISACTIVED
     */
    public Integer getIsactived() {
        return isactived;
    }

    /**
     * @param isactived
     */
    public void setIsactived(Integer isactived) {
        this.isactived = isactived;
    }

    /**
     * @return FUNCTIONTYPE
     */
    public Integer getFunctiontype() {
        return functiontype;
    }

    /**
     * @param functiontype
     */
    public void setFunctiontype(Integer functiontype) {
        this.functiontype = functiontype;
    }

    /**
     * @return ISSENDSMS
     */
    public Integer getIssendsms() {
        return issendsms;
    }

    /**
     * @param issendsms
     */
    public void setIssendsms(Integer issendsms) {
        this.issendsms = issendsms;
    }

    /**
     * @return COLLECTSTIONCODE
     */
    public Integer getCollectstioncode() {
        return collectstioncode;
    }

    /**
     * @param collectstioncode
     */
    public void setCollectstioncode(Integer collectstioncode) {
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