package com.access.api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "PC_CUSTOMER_BASE")
public class PcCustomerBase implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_PC_DEPARTMENT_BASE_ID.nextval from dual")
    private Short id;

    @Column(name = "DEPID")
    private Short depid;

    @Column(name = "ROLEID")
    private Short roleid;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "USERNAMEPY")
    private String usernamepy;

    @Column(name = "SEX")
    private Short sex;

    @Column(name = "TEL")
    private String tel;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "IM")
    private String im;

    @Column(name = "IDCARDTYPE")
    private Short idcardtype;

    @Column(name = "IDCARDNO")
    private String idcardno;

    @Column(name = "PROVINCEID")
    private Short provinceid;

    @Column(name = "NATION")
    private Short nation;

    @Column(name = "BIRTHDAY")
    private Date birthday;

    @Column(name = "WORKNO")
    private String workno;

    @Column(name = "STATUS")
    private Short status;

    @Column(name = "ISOPEN")
    private Short isopen;

    @Column(name = "OPENDATATIME")
    private Date opendatatime;

    @Column(name = "EDUID")
    private Short eduid;

    @Column(name = "USECARDSTATUS")
    private Short usecardstatus;

    @Column(name = "CARDID")
    private Short cardid;

    @Column(name = "ISPERSONALRULE")
    private Short ispersonalrule;

    @Column(name = "COMPANYID")
    private Short companyid;

    @Column(name = "FAMILYADDR")
    private String familyaddr;

    @Column(name = "ZIP")
    private String zip;

    @Column(name = "REMARK")
    private Object remark;

    @Column(name = "VERFLAG")
    private Short verflag;

    @Column(name = "VER")
    private Short ver;

    @Column(name = "ISACTIVED")
    private Short isactived;

    @Column(name = "IDENTITYID")
    private Short identityid;

    @Column(name = "SUBCARDID")
    private Short subcardid;

    @Column(name = "ENTRYDATE")
    private Date entrydate;

    @Column(name = "RESIGNATIONDATE")
    private Date resignationdate;

    @Column(name = "POLITICALTYPEID")
    private Short politicaltypeid;

    @Column(name = "MARRIAGESTATUS")
    private Short marriagestatus;

    @Column(name = "VALIDDATE")
    private Date validdate;

    @Column(name = "CARDVER")
    private Short cardver;

    @Column(name = "IDENTITYIDDL")
    private Short identityiddl;

    @Column(name = "IFPUSHFARE")
    private String ifpushfare;

    @Column(name = "RESERVER1")
    private Short reserver1;

    @Column(name = "RESERVER2")
    private String reserver2;

    @Column(name = "CUSTOMERCARDCODE")
    private String customercardcode;

    @Column(name = "DEPTCODE")
    private String deptcode;

    @Column(name = "RESERVER3")
    private Short reserver3;

    @Column(name = "STUDENTID")
    private String studentid;

    @Column(name = "CARDNUMBER")
    private String cardnumber;

    @Column(name = "ISFACE")
    private Short isface;

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
     * @return DEPID
     */
    public Short getDepid() {
        return depid;
    }

    /**
     * @param depid
     */
    public void setDepid(Short depid) {
        this.depid = depid;
    }

    /**
     * @return ROLEID
     */
    public Short getRoleid() {
        return roleid;
    }

    /**
     * @param roleid
     */
    public void setRoleid(Short roleid) {
        this.roleid = roleid;
    }

    /**
     * @return USERNAME
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return USERNAMEPY
     */
    public String getUsernamepy() {
        return usernamepy;
    }

    /**
     * @param usernamepy
     */
    public void setUsernamepy(String usernamepy) {
        this.usernamepy = usernamepy;
    }

    /**
     * @return SEX
     */
    public Short getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(Short sex) {
        this.sex = sex;
    }

    /**
     * @return TEL
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return EMAIL
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return IM
     */
    public String getIm() {
        return im;
    }

    /**
     * @param im
     */
    public void setIm(String im) {
        this.im = im;
    }

    /**
     * @return IDCARDTYPE
     */
    public Short getIdcardtype() {
        return idcardtype;
    }

    /**
     * @param idcardtype
     */
    public void setIdcardtype(Short idcardtype) {
        this.idcardtype = idcardtype;
    }

    /**
     * @return IDCARDNO
     */
    public String getIdcardno() {
        return idcardno;
    }

    /**
     * @param idcardno
     */
    public void setIdcardno(String idcardno) {
        this.idcardno = idcardno;
    }

    /**
     * @return PROVINCEID
     */
    public Short getProvinceid() {
        return provinceid;
    }

    /**
     * @param provinceid
     */
    public void setProvinceid(Short provinceid) {
        this.provinceid = provinceid;
    }

    /**
     * @return NATION
     */
    public Short getNation() {
        return nation;
    }

    /**
     * @param nation
     */
    public void setNation(Short nation) {
        this.nation = nation;
    }

    /**
     * @return BIRTHDAY
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
     * @return STATUS
     */
    public Short getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * @return ISOPEN
     */
    public Short getIsopen() {
        return isopen;
    }

    /**
     * @param isopen
     */
    public void setIsopen(Short isopen) {
        this.isopen = isopen;
    }

    /**
     * @return OPENDATATIME
     */
    public Date getOpendatatime() {
        return opendatatime;
    }

    /**
     * @param opendatatime
     */
    public void setOpendatatime(Date opendatatime) {
        this.opendatatime = opendatatime;
    }

    /**
     * @return EDUID
     */
    public Short getEduid() {
        return eduid;
    }

    /**
     * @param eduid
     */
    public void setEduid(Short eduid) {
        this.eduid = eduid;
    }

    /**
     * @return USECARDSTATUS
     */
    public Short getUsecardstatus() {
        return usecardstatus;
    }

    /**
     * @param usecardstatus
     */
    public void setUsecardstatus(Short usecardstatus) {
        this.usecardstatus = usecardstatus;
    }

    /**
     * @return CARDID
     */
    public Short getCardid() {
        return cardid;
    }

    /**
     * @param cardid
     */
    public void setCardid(Short cardid) {
        this.cardid = cardid;
    }

    /**
     * @return ISPERSONALRULE
     */
    public Short getIspersonalrule() {
        return ispersonalrule;
    }

    /**
     * @param ispersonalrule
     */
    public void setIspersonalrule(Short ispersonalrule) {
        this.ispersonalrule = ispersonalrule;
    }

    /**
     * @return COMPANYID
     */
    public Short getCompanyid() {
        return companyid;
    }

    /**
     * @param companyid
     */
    public void setCompanyid(Short companyid) {
        this.companyid = companyid;
    }

    /**
     * @return FAMILYADDR
     */
    public String getFamilyaddr() {
        return familyaddr;
    }

    /**
     * @param familyaddr
     */
    public void setFamilyaddr(String familyaddr) {
        this.familyaddr = familyaddr;
    }

    /**
     * @return ZIP
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return REMARK
     */
    public Object getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(Object remark) {
        this.remark = remark;
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
     * @return IDENTITYID
     */
    public Short getIdentityid() {
        return identityid;
    }

    /**
     * @param identityid
     */
    public void setIdentityid(Short identityid) {
        this.identityid = identityid;
    }

    /**
     * @return SUBCARDID
     */
    public Short getSubcardid() {
        return subcardid;
    }

    /**
     * @param subcardid
     */
    public void setSubcardid(Short subcardid) {
        this.subcardid = subcardid;
    }

    /**
     * @return ENTRYDATE
     */
    public Date getEntrydate() {
        return entrydate;
    }

    /**
     * @param entrydate
     */
    public void setEntrydate(Date entrydate) {
        this.entrydate = entrydate;
    }

    /**
     * @return RESIGNATIONDATE
     */
    public Date getResignationdate() {
        return resignationdate;
    }

    /**
     * @param resignationdate
     */
    public void setResignationdate(Date resignationdate) {
        this.resignationdate = resignationdate;
    }

    /**
     * @return POLITICALTYPEID
     */
    public Short getPoliticaltypeid() {
        return politicaltypeid;
    }

    /**
     * @param politicaltypeid
     */
    public void setPoliticaltypeid(Short politicaltypeid) {
        this.politicaltypeid = politicaltypeid;
    }

    /**
     * @return MARRIAGESTATUS
     */
    public Short getMarriagestatus() {
        return marriagestatus;
    }

    /**
     * @param marriagestatus
     */
    public void setMarriagestatus(Short marriagestatus) {
        this.marriagestatus = marriagestatus;
    }

    /**
     * @return VALIDDATE
     */
    public Date getValiddate() {
        return validdate;
    }

    /**
     * @param validdate
     */
    public void setValiddate(Date validdate) {
        this.validdate = validdate;
    }

    /**
     * @return CARDVER
     */
    public Short getCardver() {
        return cardver;
    }

    /**
     * @param cardver
     */
    public void setCardver(Short cardver) {
        this.cardver = cardver;
    }

    /**
     * @return IDENTITYIDDL
     */
    public Short getIdentityiddl() {
        return identityiddl;
    }

    /**
     * @param identityiddl
     */
    public void setIdentityiddl(Short identityiddl) {
        this.identityiddl = identityiddl;
    }

    /**
     * @return IFPUSHFARE
     */
    public String getIfpushfare() {
        return ifpushfare;
    }

    /**
     * @param ifpushfare
     */
    public void setIfpushfare(String ifpushfare) {
        this.ifpushfare = ifpushfare;
    }

    /**
     * @return RESERVER1
     */
    public Short getReserver1() {
        return reserver1;
    }

    /**
     * @param reserver1
     */
    public void setReserver1(Short reserver1) {
        this.reserver1 = reserver1;
    }

    /**
     * @return RESERVER2
     */
    public String getReserver2() {
        return reserver2;
    }

    /**
     * @param reserver2
     */
    public void setReserver2(String reserver2) {
        this.reserver2 = reserver2;
    }

    /**
     * @return CUSTOMERCARDCODE
     */
    public String getCustomercardcode() {
        return customercardcode;
    }

    /**
     * @param customercardcode
     */
    public void setCustomercardcode(String customercardcode) {
        this.customercardcode = customercardcode;
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
     * @return RESERVER3
     */
    public Short getReserver3() {
        return reserver3;
    }

    /**
     * @param reserver3
     */
    public void setReserver3(Short reserver3) {
        this.reserver3 = reserver3;
    }

    /**
     * @return STUDENTID
     */
    public String getStudentid() {
        return studentid;
    }

    /**
     * @param studentid
     */
    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    /**
     * @return CARDNUMBER
     */
    public String getCardnumber() {
        return cardnumber;
    }

    /**
     * @param cardnumber
     */
    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    /**
     * @return ISFACE
     */
    public Short getIsface() {
        return isface;
    }

    /**
     * @param isface
     */
    public void setIsface(Short isface) {
        this.isface = isface;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", depid=").append(depid);
        sb.append(", roleid=").append(roleid);
        sb.append(", username=").append(username);
        sb.append(", usernamepy=").append(usernamepy);
        sb.append(", sex=").append(sex);
        sb.append(", tel=").append(tel);
        sb.append(", email=").append(email);
        sb.append(", im=").append(im);
        sb.append(", idcardtype=").append(idcardtype);
        sb.append(", idcardno=").append(idcardno);
        sb.append(", provinceid=").append(provinceid);
        sb.append(", nation=").append(nation);
        sb.append(", birthday=").append(birthday);
        sb.append(", workno=").append(workno);
        sb.append(", status=").append(status);
        sb.append(", isopen=").append(isopen);
        sb.append(", opendatatime=").append(opendatatime);
        sb.append(", eduid=").append(eduid);
        sb.append(", usecardstatus=").append(usecardstatus);
        sb.append(", cardid=").append(cardid);
        sb.append(", ispersonalrule=").append(ispersonalrule);
        sb.append(", companyid=").append(companyid);
        sb.append(", familyaddr=").append(familyaddr);
        sb.append(", zip=").append(zip);
        sb.append(", remark=").append(remark);
        sb.append(", verflag=").append(verflag);
        sb.append(", ver=").append(ver);
        sb.append(", isactived=").append(isactived);
        sb.append(", identityid=").append(identityid);
        sb.append(", subcardid=").append(subcardid);
        sb.append(", entrydate=").append(entrydate);
        sb.append(", resignationdate=").append(resignationdate);
        sb.append(", politicaltypeid=").append(politicaltypeid);
        sb.append(", marriagestatus=").append(marriagestatus);
        sb.append(", validdate=").append(validdate);
        sb.append(", cardver=").append(cardver);
        sb.append(", identityiddl=").append(identityiddl);
        sb.append(", ifpushfare=").append(ifpushfare);
        sb.append(", reserver1=").append(reserver1);
        sb.append(", reserver2=").append(reserver2);
        sb.append(", customercardcode=").append(customercardcode);
        sb.append(", deptcode=").append(deptcode);
        sb.append(", reserver3=").append(reserver3);
        sb.append(", studentid=").append(studentid);
        sb.append(", cardnumber=").append(cardnumber);
        sb.append(", isface=").append(isface);
        sb.append("]");
        return sb.toString();
    }
}