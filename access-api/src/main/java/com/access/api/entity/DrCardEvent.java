package com.access.api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "DR_CARD_EVENT")
public class DrCardEvent implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_DR_CARD_EVENT_ID.nextval from dual")
    private Integer id;

    @Column(name = "DEVID")
    private Integer devid;

    @Column(name = "DOORADDR")
    private Integer dooraddr;

    @Column(name = "READERADDR")
    private Integer readeraddr;

    @Column(name = "CARDNO")
    private String cardno;

    @Column(name = "EVENTTIME")
    private Date eventtime;

    @Column(name = "COLLECTTIME")
    private Date collecttime;

    @Column(name = "WRITETIME")
    private Date writetime;

    @Column(name = "RECFLAG")
    private Integer recflag;

    @Column(name = "COLLECTID")
    private Integer collectid;

    @Column(name = "CUSTID")
    private Integer custid;

    @Column(name = "CUSTNAME")
    private String custname;

    @Column(name = "DEPTID")
    private Integer deptid;

    @Column(name = "DEPTNAME")
    private String deptname;

    @Column(name = "IMAGESIZE")
    private Integer imagesize;

    @Column(name = "IMAGENAME")
    private String imagename;

    @Column(name = "DEVNAME")
    private String devname;

    @Column(name = "WORKNO")
    private String workno;

    @Column(name = "DEPTCODE")
    private String deptcode;

    @Column(name = "ISFACERECORD")
    private Integer isfacerecord;

    @Column(name = "DIRECTION")
    private Integer direction;

    @Column(name = "SN_LDID")
    private String snLdid;

    @Column(name = "IMAGEDATA")
    private byte[] imagedata;

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
     * @return DOORADDR
     */
    public Integer getDooraddr() {
        return dooraddr;
    }

    /**
     * @param dooraddr
     */
    public void setDooraddr(Integer dooraddr) {
        this.dooraddr = dooraddr;
    }

    /**
     * @return READERADDR
     */
    public Integer getReaderaddr() {
        return readeraddr;
    }

    /**
     * @param readeraddr
     */
    public void setReaderaddr(Integer readeraddr) {
        this.readeraddr = readeraddr;
    }

    /**
     * @return CARDNO
     */
    public String getCardno() {
        return cardno;
    }

    /**
     * @param cardno
     */
    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    /**
     * @return EVENTTIME
     */
    public Date getEventtime() {
        return eventtime;
    }

    /**
     * @param eventtime
     */
    public void setEventtime(Date eventtime) {
        this.eventtime = eventtime;
    }

    /**
     * @return COLLECTTIME
     */
    public Date getCollecttime() {
        return collecttime;
    }

    /**
     * @param collecttime
     */
    public void setCollecttime(Date collecttime) {
        this.collecttime = collecttime;
    }

    /**
     * @return WRITETIME
     */
    public Date getWritetime() {
        return writetime;
    }

    /**
     * @param writetime
     */
    public void setWritetime(Date writetime) {
        this.writetime = writetime;
    }

    /**
     * @return RECFLAG
     */
    public Integer getRecflag() {
        return recflag;
    }

    /**
     * @param recflag
     */
    public void setRecflag(Integer recflag) {
        this.recflag = recflag;
    }

    /**
     * @return COLLECTID
     */
    public Integer getCollectid() {
        return collectid;
    }

    /**
     * @param collectid
     */
    public void setCollectid(Integer collectid) {
        this.collectid = collectid;
    }

    /**
     * @return CUSTID
     */
    public Integer getCustid() {
        return custid;
    }

    /**
     * @param custid
     */
    public void setCustid(Integer custid) {
        this.custid = custid;
    }

    /**
     * @return CUSTNAME
     */
    public String getCustname() {
        return custname;
    }

    /**
     * @param custname
     */
    public void setCustname(String custname) {
        this.custname = custname;
    }

    /**
     * @return DEPTID
     */
    public Integer getDeptid() {
        return deptid;
    }

    /**
     * @param deptid
     */
    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    /**
     * @return DEPTNAME
     */
    public String getDeptname() {
        return deptname;
    }

    /**
     * @param deptname
     */
    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    /**
     * @return IMAGESIZE
     */
    public Integer getImagesize() {
        return imagesize;
    }

    /**
     * @param imagesize
     */
    public void setImagesize(Integer imagesize) {
        this.imagesize = imagesize;
    }

    /**
     * @return IMAGENAME
     */
    public String getImagename() {
        return imagename;
    }

    /**
     * @param imagename
     */
    public void setImagename(String imagename) {
        this.imagename = imagename;
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
     * @return WORKNO
     */
    public String getWorkno() {
        return workno;
    }

    /**
     * @param workno
     */
    public void setWorkno(String workno) {
        this.workno = workno;
    }

    /**
     * @return DEPTCODE
     */
    public String getDeptcode() {
        return deptcode;
    }

    /**
     * @param deptcode
     */
    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    /**
     * @return ISFACERECORD
     */
    public Integer getIsfacerecord() {
        return isfacerecord;
    }

    /**
     * @param isfacerecord
     */
    public void setIsfacerecord(Integer isfacerecord) {
        this.isfacerecord = isfacerecord;
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
     * @return SN_LDID
     */
    public String getSnLdid() {
        return snLdid;
    }

    /**
     * @param snLdid
     */
    public void setSnLdid(String snLdid) {
        this.snLdid = snLdid;
    }

    /**
     * @return IMAGEDATA
     */
    public byte[] getImagedata() {
        return imagedata;
    }

    /**
     * @param imagedata
     */
    public void setImagedata(byte[] imagedata) {
        this.imagedata = imagedata;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", devid=").append(devid);
        sb.append(", dooraddr=").append(dooraddr);
        sb.append(", readeraddr=").append(readeraddr);
        sb.append(", cardno=").append(cardno);
        sb.append(", eventtime=").append(eventtime);
        sb.append(", collecttime=").append(collecttime);
        sb.append(", writetime=").append(writetime);
        sb.append(", recflag=").append(recflag);
        sb.append(", collectid=").append(collectid);
        sb.append(", custid=").append(custid);
        sb.append(", custname=").append(custname);
        sb.append(", deptid=").append(deptid);
        sb.append(", deptname=").append(deptname);
        sb.append(", imagesize=").append(imagesize);
        sb.append(", imagename=").append(imagename);
        sb.append(", devname=").append(devname);
        sb.append(", workno=").append(workno);
        sb.append(", deptcode=").append(deptcode);
        sb.append(", isfacerecord=").append(isfacerecord);
        sb.append(", direction=").append(direction);
        sb.append(", snLdid=").append(snLdid);
        sb.append(", imagedata=").append(imagedata);
        sb.append("]");
        return sb.toString();
    }
}