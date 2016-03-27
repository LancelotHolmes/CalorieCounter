/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id27315681;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zy751
 */
@Entity
@Table(name = "report")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r"),
    @NamedQuery(name = "Report.findByUserId", query = "SELECT r FROM Report r WHERE r.reportPK.userId = :userId"),
    @NamedQuery(name = "Report.findByRecordDate", query = "SELECT r FROM Report r WHERE r.reportPK.recordDate = :recordDate"),
    @NamedQuery(name = "Report.findByCalorieConsumed", query = "SELECT r FROM Report r WHERE r.calorieConsumed = :calorieConsumed"),
    @NamedQuery(name = "Report.findByCalorieBurned", query = "SELECT r FROM Report r WHERE r.calorieBurned = :calorieBurned"),
    @NamedQuery(name = "Report.findByCalorieGoal", query = "SELECT r FROM Report r WHERE r.calorieGoal = :calorieGoal"),
    @NamedQuery(name = "Report.findByCalorieRemaining", query = "SELECT r FROM Report r WHERE r.calorieRemaining = :calorieRemaining"),
    @NamedQuery(name = "Report.findByTotalSteps", query = "SELECT r FROM Report r WHERE r.totalSteps = :totalSteps"),
    @NamedQuery(name="Report.findByUserIdANDRecordDate", query="select r from Report r where r.reportPK.userId=:userId and r.reportPK.recordDate=:recordDate")
})
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReportPK reportPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "calorie_consumed")
    private Double calorieConsumed;
    @Column(name = "calorie_burned")
    private Double calorieBurned;
    @Column(name = "calorie_goal")
    private Double calorieGoal;
    @Column(name = "calorie_remaining")
    private Double calorieRemaining;
    @Column(name = "total_steps")
    private Double totalSteps;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public Report() {
    }

    public Report(ReportPK reportPK) {
        this.reportPK = reportPK;
    }

    public Report(int userId, Date recordDate) {
        this.reportPK = new ReportPK(userId, recordDate);
    }

    public ReportPK getReportPK() {
        return reportPK;
    }

    public void setReportPK(ReportPK reportPK) {
        this.reportPK = reportPK;
    }

    public Double getCalorieConsumed() {
        return calorieConsumed;
    }

    public void setCalorieConsumed(Double calorieConsumed) {
        this.calorieConsumed = calorieConsumed;
    }

    public Double getCalorieBurned() {
        return calorieBurned;
    }

    public void setCalorieBurned(Double calorieBurned) {
        this.calorieBurned = calorieBurned;
    }

    public Double getCalorieGoal() {
        return calorieGoal;
    }

    public void setCalorieGoal(Double calorieGoal) {
        this.calorieGoal = calorieGoal;
    }

    public Double getCalorieRemaining() {
        return calorieRemaining;
    }

    public void setCalorieRemaining(Double calorieRemaining) {
        this.calorieRemaining = calorieRemaining;
    }

    public Double getTotalSteps() {
        return totalSteps;
    }

    public void setTotalSteps(Double totalSteps) {
        this.totalSteps = totalSteps;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reportPK != null ? reportPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.reportPK == null && other.reportPK != null) || (this.reportPK != null && !this.reportPK.equals(other.reportPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.id27315681.Report[ reportPK=" + reportPK + " ]";
    }
    
}
