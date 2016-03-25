/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id27315681;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author zy751
 */
@Embeddable
public class ReportPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "record_date")
    @Temporal(TemporalType.DATE)
    private Date recordDate;

    public ReportPK() {
    }

    public ReportPK(int userId, Date recordDate) {
        this.userId = userId;
        this.recordDate = recordDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (recordDate != null ? recordDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReportPK)) {
            return false;
        }
        ReportPK other = (ReportPK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if ((this.recordDate == null && other.recordDate != null) || (this.recordDate != null && !this.recordDate.equals(other.recordDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.id27315681.ReportPK[ userId=" + userId + ", recordDate=" + recordDate + " ]";
    }
    
}
