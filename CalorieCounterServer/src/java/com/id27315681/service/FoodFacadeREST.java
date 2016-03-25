/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id27315681.service;

import com.id27315681.Food;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author zy751
 */
@Stateless
@Path("com.id27315681.food")
public class FoodFacadeREST extends AbstractFacade<Food> {

    @PersistenceContext(unitName = "CalorieCounterServerPU")
    private EntityManager em;

    public FoodFacadeREST() {
        super(Food.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Food entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Food entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Food find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Food> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Food> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    //-------------start of my code------------------
    //---------basic query
    @GET  //query the food info by accurate foodId
    @Path("findByFoodId/{foodId}")
    @Produces({"application/json"})
    public List<Food> findByFoodId(@PathParam("foodId") int foodId) {
        Query query = em.createNamedQuery("Food.findByFoodId");
        query.setParameter("foodId", foodId);
        return query.getResultList();
    }
    
    
    @GET  //query the food info by accurate name
    @Path("findByFoodName/{foodName}")
    @Produces({"application/json"})
    public List<Food> findByFoodName(@PathParam("foodName") String foodName) {
        Query query = em.createNamedQuery("Food.findByFoodName");
        query.setParameter("foodName", foodName);
        return query.getResultList();
    }
    
    @GET   //query food info by name regardless of the case
    @Path("findByUpperName/{foodName}")
    @Produces({"application/json"})
    public List<Food> findByUpperName(@PathParam("foodName") String foodName) {
        TypedQuery<Food> q = em.createQuery("select f from Food f where UPPER(f.foodName)=UPPER('" + foodName + "')", Food.class);
        return q.getResultList();
    }
    
    @GET  //query the food info by accurate calorie
    @Path("findByCalorie/{calorie}")
    @Produces({"application/json"})
    public List<Food> findByCalorie(@PathParam("calorie") double calorie) {
        Query query = em.createNamedQuery("Food.findByCalorie");
        query.setParameter("calorie", calorie);
        return query.getResultList();
    }
    
    @GET  //query the food info by accurate fat
    @Path("findByFat/{fat}")
    @Produces({"application/json"})
    public List<Food> findByFat(@PathParam("fat") double fat) {
        Query query = em.createNamedQuery("Food.findByFat");
        query.setParameter("fat", fat);
        return query.getResultList();
    }
    
     @GET  //query the food info by accurate measure
    @Path("findByMeasure/{measure}")
    @Produces({"application/json"})
    public List<Food> findByMeasure(@PathParam("measure") String measure) {
        Query query = em.createNamedQuery("Food.findByMeasure");
        query.setParameter("measure", measure);
        return query.getResultList();
    }
    
    @GET  //query the food info by accurate serving
    @Path("findByServing/{serving}")
    @Produces({"application/json"})
    public List<Food> findByServing(@PathParam("serving") double serving) {
        Query query = em.createNamedQuery("Food.findByServing");
        query.setParameter("serving", serving);
        return query.getResultList();
    }
    
    //----------advanced query
    @GET
    @Path("findByCalorieANDFat/{calorie}/{fat}")
    @Produces({"application/json"})
    public List<Food> findByCalorieANDFat(@PathParam("calorie") double calorie, @PathParam("fat") double fat){
        TypedQuery<Food> q = em.createQuery("select f from Food f where f.calorie=:calorie and f.fat=:fat", Food.class);
        q.setParameter("fat", fat);
        q.setParameter("calorie", calorie);
        return q.getResultList();
    }
    
  
    
    //-------------end of my code------------------
    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
