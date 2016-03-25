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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zy751
 */
@Entity
@Table(name = "eat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eat.findAll", query = "SELECT e FROM Eat e"),
    @NamedQuery(name = "Eat.findByUserId", query = "SELECT e FROM Eat e WHERE e.eatPK.userId = :userId"),
    @NamedQuery(name = "Eat.findByEatTime", query = "SELECT e FROM Eat e WHERE e.eatPK.eatTime = :eatTime"),
    @NamedQuery(name = "Eat.findByQuentity", query = "SELECT e FROM Eat e WHERE e.quentity = :quentity"),
    @NamedQuery(name = "Eat.findByMeasure", query = "SELECT e FROM Eat e WHERE e.measure = :measure"),
    //--------------my code--------------
    @NamedQuery(name = "Eat.findByFoodId", query="select e from Eat e where e.foodId.foodId= :foodId")
})
public class Eat implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EatPK eatPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quentity")
    private Double quentity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "measure")
    private String measure;
    @JoinColumn(name = "food_id", referencedColumnName = "food_id")
    @ManyToOne(optional = false)
    private Food foodId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public Eat() {
    }

    public Eat(EatPK eatPK) {
        this.eatPK = eatPK;
    }

    public Eat(EatPK eatPK, String measure) {
        this.eatPK = eatPK;
        this.measure = measure;
    }

    public Eat(int userId, Date eatTime) {
        this.eatPK = new EatPK(userId, eatTime);
    }

    public EatPK getEatPK() {
        return eatPK;
    }

    public void setEatPK(EatPK eatPK) {
        this.eatPK = eatPK;
    }

    public Double getQuentity() {
        return quentity;
    }

    public void setQuentity(Double quentity) {
        this.quentity = quentity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public Food getFoodId() {     //it's weired that foodId has become type of Food
        return foodId;
    }

    public void setFoodId(Food foodId) {
        this.foodId = foodId;
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
        hash += (eatPK != null ? eatPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Eat)) {
            return false;
        }
        Eat other = (Eat) object;
        if ((this.eatPK == null && other.eatPK != null) || (this.eatPK != null && !this.eatPK.equals(other.eatPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.id27315681.Eat[ eatPK=" + eatPK + " ]";
    }
    
}
