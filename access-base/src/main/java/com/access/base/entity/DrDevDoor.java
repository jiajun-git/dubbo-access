package com.access.base.entity;

import javax.persistence.*;

@Table(name = "DR_DEV_DOOR")
public class DrDevDoor {
    @Id
    @Column(name = "ID")
    private Short id;

    @Column(name = "DEVID")
    private Short devid;

    @Column(name = "DEVNAME")
    private String devname;

    @Column(name = "ADDR")
    private Short addr;

    @Column(name = "PADDR")
    private Short paddr;

    @Column(name = "ISACTIVED")
    private Short isactived;

    @Column(name = "VER")
    private Short ver;

    @Column(name = "VERFLAG")
    private Short verflag;

    @Column(name = "DOORSTATUS")
    private Short doorstatus;

    @Column(name = "OPENLOCKTIME")
    private Short openlocktime;

    @Column(name = "OPENDOORTIME")
    private Short opendoortime;

    @Column(name = "DIRECTION")
    private Short direction;

    @Column(name = "ADDVER")
    private Short addver;

    @Column(name = "DELVER")
    private Short delver;

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
     * @return DEVID
     */
    public Short getDevid() {
        return devid;
    }

    /**
     * @param devid
     */
    public void setDevid(Short devid) {
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
     * @return DOORSTATUS
     */
    public Short getDoorstatus() {
        return doorstatus;
    }

    /**
     * @param doorstatus
     */
    public void setDoorstatus(Short doorstatus) {
        this.doorstatus = doorstatus;
    }

    /**
     * @return OPENLOCKTIME
     */
    public Short getOpenlocktime() {
        return openlocktime;
    }

    /**
     * @param openlocktime
     */
    public void setOpenlocktime(Short openlocktime) {
        this.openlocktime = openlocktime;
    }

    /**
     * @return OPENDOORTIME
     */
    public Short getOpendoortime() {
        return opendoortime;
    }

    /**
     * @param opendoortime
     */
    public void setOpendoortime(Short opendoortime) {
        this.opendoortime = opendoortime;
    }

    /**
     * @return DIRECTION
     */
    public Short getDirection() {
        return direction;
    }

    /**
     * @param direction
     */
    public void setDirection(Short direction) {
        this.direction = direction;
    }

    /**
     * @return ADDVER
     */
    public Short getAddver() {
        return addver;
    }

    /**
     * @param addver
     */
    public void setAddver(Short addver) {
        this.addver = addver;
    }

    /**
     * @return DELVER
     */
    public Short getDelver() {
        return delver;
    }

    /**
     * @param delver
     */
    public void setDelver(Short delver) {
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