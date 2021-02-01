package com.access.api.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "DR_DEV_DOOR")
public class DrDevDoor implements Serializable {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DEVID")
    private Integer devid;

    @Column(name = "DEVNAME")
    private String devname;

    @Column(name = "ADDR")
    private Integer addr;

    @Column(name = "PADDR")
    private Integer paddr;

    @Column(name = "ISACTIVED")
    private Integer isactived;

    @Column(name = "VER")
    private Integer ver;

    @Column(name = "VERFLAG")
    private Integer verflag;

    @Column(name = "DOORSTATUS")
    private Integer doorstatus;

    @Column(name = "OPENLOCKTIME")
    private Integer openlocktime;

    @Column(name = "OPENDOORTIME")
    private Integer opendoortime;

    @Column(name = "DIRECTION")
    private Integer direction;

    @Column(name = "ADDVER")
    private Integer addver;

    @Column(name = "DELVER")
    private Integer delver;

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
     * @return DEVID
     */
    public Integer getDevid() {
        return devid;
    }

    /**
     * @param devid
     */
    public void setDevid(Integer devid) {
        this.devid = devid;
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
     * @return DOORSTATUS
     */
    public Integer getDoorstatus() {
        return doorstatus;
    }

    /**
     * @param doorstatus
     */
    public void setDoorstatus(Integer doorstatus) {
        this.doorstatus = doorstatus;
    }

    /**
     * @return OPENLOCKTIME
     */
    public Integer getOpenlocktime() {
        return openlocktime;
    }

    /**
     * @param openlocktime
     */
    public void setOpenlocktime(Integer openlocktime) {
        this.openlocktime = openlocktime;
    }

    /**
     * @return OPENDOORTIME
     */
    public Integer getOpendoortime() {
        return opendoortime;
    }

    /**
     * @param opendoortime
     */
    public void setOpendoortime(Integer opendoortime) {
        this.opendoortime = opendoortime;
    }

    /**
     * @return DIRECTION
     */
    public Integer getDirection() {
        return direction;
    }

    /**
     * @param direction
     */
    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    /**
     * @return ADDVER
     */
    public Integer getAddver() {
        return addver;
    }

    /**
     * @param addver
     */
    public void setAddver(Integer addver) {
        this.addver = addver;
    }

    /**
     * @return DELVER
     */
    public Integer getDelver() {
        return delver;
    }

    /**
     * @param delver
     */
    public void setDelver(Integer delver) {
        this.delver = delver;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", devid=").append(devid);
        sb.append(", devname=").append(devname);
        sb.append(", addr=").append(addr);
        sb.append(", paddr=").append(paddr);
        sb.append(", isactived=").append(isactived);
        sb.append(", ver=").append(ver);
        sb.append(", verflag=").append(verflag);
        sb.append(", doorstatus=").append(doorstatus);
        sb.append(", openlocktime=").append(openlocktime);
        sb.append(", opendoortime=").append(opendoortime);
        sb.append(", direction=").append(direction);
        sb.append(", addver=").append(addver);
        sb.append(", delver=").append(delver);
        sb.append("]");
        return sb.toString();
    }
}