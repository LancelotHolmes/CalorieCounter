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
public class EatPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eat_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eatTime;

    public EatPK() {
    }

    public EatPK(int userId, Date eatTime) {
        this.userId = userId;
        this.eatTime = eatTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getEatTime() {
        return eatTime;
    }

    public void setEatTime(Date eatTime) {
        this.eatTime = eatTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (eatTime != null ? eatTime.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EatPK)) {
            return false;
        }
        EatPK other = (EatPK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if ((this.eatTime == null && other.eatTime != null) || (this.eatTime != null && !this.eatTime.equals(other.eatTime))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.id27315681.EatPK[ userId=" + userId + ", eatTime=" + eatTime + " ]";
    }
    
}
