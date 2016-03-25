/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id27315681;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author zy751
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId"),
    @NamedQuery(name = "Users.findByUserName", query = "SELECT u FROM Users u WHERE u.userName = :userName"),
    @NamedQuery(name = "Users.findByPwd", query = "SELECT u FROM Users u WHERE u.pwd = :pwd"),
    @NamedQuery(name = "Users.findByAge", query = "SELECT u FROM Users u WHERE u.age = :age"),
    @NamedQuery(name = "Users.findByHeight", query = "SELECT u FROM Users u WHERE u.height = :height"),
    @NamedQuery(name = "Users.findByWeight", query = "SELECT u FROM Users u WHERE u.weight = :weight"),
    @NamedQuery(name = "Users.findByGender", query = "SELECT u FROM Users u WHERE u.gender = :gender"),
    @NamedQuery(name = "Users.findByLevelOfActivity", query = "SELECT u FROM Users u WHERE u.levelOfActivity = :levelOfActivity"),
    @NamedQuery(name = "Users.findByStepsPerMile", query = "SELECT u FROM Users u WHERE u.stepsPerMile = :stepsPerMile"),
    //---------my code part
    @NamedQuery(name="Users.findByUpperName",query="select u from Users u where UPPER(u.userName)=UPPER(u.userName)"),
    @NamedQuery(name="Users.findByAgeAndGender", query="select u from Users u where u.age=:age and u.gender=:gender")
})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "user_name")
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "pwd")
    private String pwd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "age")
    private short age;
    @Basic(optional = false)
    @NotNull
    @Column(name = "height")
    private double height;
    @Basic(optional = false)
    @NotNull
    @Column(name = "weight")
    private double weight;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "gender")
    private String gender;
    @Basic(optional = false)
    @NotNull
    @Column(name = "level_of_activity")
    private short levelOfActivity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steps_per_mile")
    private double stepsPerMile;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<Report> reportCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<Eat> eatCollection;

    public Users() {
    }

    public Users(Integer userId) {
        this.userId = userId;
    }

    public Users(Integer userId, String userName, String pwd, short age, double height, double weight, String gender, short levelOfActivity, double stepsPerMile) {
        this.userId = userId;
        this.userName = userName;
        this.pwd = pwd;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.levelOfActivity = levelOfActivity;
        this.stepsPerMile = stepsPerMile;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public short getLevelOfActivity() {
        return levelOfActivity;
    }

    public void setLevelOfActivity(short levelOfActivity) {
        this.levelOfActivity = levelOfActivity;
    }

    public double getStepsPerMile() {
        return stepsPerMile;
    }

    public void setStepsPerMile(double stepsPerMile) {
        this.stepsPerMile = stepsPerMile;
    }

    @XmlTransient
    public Collection<Report> getReportCollection() {
        return reportCollection;
    }

    public void setReportCollection(Collection<Report> reportCollection) {
        this.reportCollection = reportCollection;
    }

    @XmlTransient
    public Collection<Eat> getEatCollection() {
        return eatCollection;
    }

    public void setEatCollection(Collection<Eat> eatCollection) {
        this.eatCollection = eatCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.id27315681.Users[ userId=" + userId + " ]";
    }
    
}
