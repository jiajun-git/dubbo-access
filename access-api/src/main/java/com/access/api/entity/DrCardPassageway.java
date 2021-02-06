package com.access.api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "DR_CARD_PASSAGEWAY")
public class DrCardPassageway implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_DR_DOOR_CARD_PASSAGEWAY_ID.nextval from dual")
    private Integer id;

    @Column(name = "CHANNEL")
    private Integer channel;

    @Column(name = "CHANNELMODE")
    private Integer channelmode;

    @Column(name = "CHANNELDIRECTION")
    private Integer channeldirection;

    @Column(name = "ISTAIL")
    private Integer istail;

    @Column(name = "CARDNO")
    private String cardno;

    @Column(name = "EVENTTIME")
    private Date eventtime;

    @Column(name = "DEVID")
    private Integer devid;

    @Column(name = "DOORADDR")
    private Integer dooraddr;

    @Column(name = "READERADDR")
    private Integer readeraddr;

    @Column(name = "WRITETIME")
    private Date writetime;

    @Column(name = "COLLECTTIME")
    private Date collecttime;

    @Column(name = "ISLAWLESS")
    private Integer islawless;

    @Column(name = "IMAGENAME")
    private String imagename;

    @Column(name = "IMAGECODE")
    private String imagecode;

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

    @Column(name = "DEVNAME")
    private String devname;

    @Column(name = "WORKNO")
    private String workno;

    @Column(name = "DEPTCODE")
    private String deptcode;

    @Column(name = "COLLECTID")
    private Integer collectid;

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
     * @return CHANNEL
     */
    public Integer getChannel() {
        return channel;
    }

    /**
     * @param channel
     */
    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    /**
     * @return CHANNELMODE
     */
    public Integer getChannelmode() {
        return channelmode;
    }

    /**
     * @param channelmode
     */
    public void setChannelmode(Integer channelmode) {
        this.channelmode = channelmode;
    }

    /**
     * @return CHANNELDIRECTION
     */
    public Integer getChanneldirection() {
        return channeldirection;
    }

    /**
     * @param channeldirection
     */
    public void setChanneldirection(Integer channeldirection) {
        this.channeldirection = channeldirection;
    }

    /**
     * @return ISTAIL
     */
    public Integer getIstail() {
        return istail;
    }

    /**
     * @param istail
     */
    public void setIstail(Integer istail) {
        this.istail = istail;
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
     * @return ISLAWLESS
     */
    public Integer getIslawless() {
        return islawless;
    }

    /**
     * @param islawless
     */
    public void setIslawless(Integer islawless) {
        this.islawless = islawless;
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
     * @return IMAGECODE
     */
    public String getImagecode() {
        return imagecode;
    }

    /**
     * @param imagecode
     */
    public void setImagecode(String imagecode) {
        this.imagecode = imagecode;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", channel=").append(channel);
        sb.append(", channelmode=").append(channelmode);
        sb.append(", channeldirection=").append(channeldirection);
        sb.append(", istail=").append(istail);
        sb.append(", cardno=").append(cardno);
        sb.append(", eventtime=").append(eventtime);
        sb.append(", devid=").append(devid);
        sb.append(", dooraddr=").append(dooraddr);
        sb.append(", readeraddr=").append(readeraddr);
        sb.append(", writetime=").append(writetime);
        sb.append(", collecttime=").append(collecttime);
        sb.append(", islawless=").append(islawless);
        sb.append(", imagename=").append(imagename);
        sb.append(", imagecode=").append(imagecode);
        sb.append(", custid=").append(custid);
        sb.append(", custname=").append(custname);
        sb.append(", deptid=").append(deptid);
        sb.append(", deptname=").append(deptname);
        sb.append(", imagesize=").append(imagesize);
        sb.append(", devname=").append(devname);
        sb.append(", workno=").append(workno);
        sb.append(", deptcode=").append(deptcode);
        sb.append(", collectid=").append(collectid);
        sb.append("]");
        return sb.toString();
    }
}