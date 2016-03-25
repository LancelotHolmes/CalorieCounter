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
@Table(name = "food")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Food.findAll", query = "SELECT f FROM Food f"),
    @NamedQuery(name = "Food.findByFoodId", query = "SELECT f FROM Food f WHERE f.foodId = :foodId"),
    @NamedQuery(name = "Food.findByFoodName", query = "SELECT f FROM Food f WHERE f.foodName = :foodName"),
    @NamedQuery(name = "Food.findByCalorie", query = "SELECT f FROM Food f WHERE f.calorie = :calorie"),
    @NamedQuery(name = "Food.findByFat", query = "SELECT f FROM Food f WHERE f.fat = :fat"),
    @NamedQuery(name = "Food.findByMeasure", query = "SELECT f FROM Food f WHERE f.measure = :measure"),
    @NamedQuery(name = "Food.findByServing", query = "SELECT f FROM Food f WHERE f.serving = :serving")})
public class Food implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "food_id")
    private Integer foodId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "food_name")
    private String foodName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "calorie")
    private double calorie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fat")
    private double fat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "measure")
    private String measure;
    @Basic(optional = false)
    @NotNull
    @Column(name = "serving")
    private double serving;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "foodId")
    private Collection<Eat> eatCollection;

    public Food() {
    }

    public Food(Integer foodId) {
        this.foodId = foodId;
    }

    public Food(Integer foodId, String foodName, double calorie, double fat, String measure, double serving) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.calorie = calorie;
        this.fat = fat;
        this.measure = measure;
        this.serving = serving;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getCalorie() {
        return calorie;
    }

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public double getServing() {
        return serving;
    }

    public void setServing(double serving) {
        this.serving = serving;
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
        hash += (foodId != null ? foodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Food)) {
            return false;
        }
        Food other = (Food) object;
        if ((this.foodId == null && other.foodId != null) || (this.foodId != null && !this.foodId.equals(other.foodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.id27315681.Food[ foodId=" + foodId + " ]";
    }
    
}
