/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id27315681.service;

import com.id27315681.Eat;
import com.id27315681.EatPK;
import com.id27315681.Food;
import java.sql.Date;
import java.sql.Timestamp;

//import java.util.Date;
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
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author zy751
 */
@Stateless
@Path("com.id27315681.eat")
public class EatFacadeREST extends AbstractFacade<Eat> {

    @PersistenceContext(unitName = "CalorieCounterServerPU")
    private EntityManager em;

    private EatPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;userId=userIdValue;eatTime=eatTimeValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        com.id27315681.EatPK key = new com.id27315681.EatPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> userId = map.get("userId");
        if (userId != null && !userId.isEmpty()) {
            key.setUserId(new java.lang.Integer(userId.get(0)));
        }
        java.util.List<String> eatTime = map.get("eatTime");
        if (eatTime != null && !eatTime.isEmpty()) {
            key.setEatTime(new java.util.Date(eatTime.get(0)));
        }
        return key;
    }

    public EatFacadeREST() {
        super(Eat.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Eat entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, Eat entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        com.id27315681.EatPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Eat find(@PathParam("id") PathSegment id) {
        com.id27315681.EatPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Eat> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Eat> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    //--------------------start of my code----------------------
    @GET  //query the eat info by foodId
    @Path("findByFoodId/{foodId}")
    @Produces({"application/json"})
    public List<Eat> findByFoodId(@PathParam("foodId") int foodId) {   //sth wrong
        Query query = em.createNamedQuery("Eat.findByFoodId");
        query.setParameter("foodId", foodId);
        return query.getResultList();
    }
    
    @GET  //query the eat info by userId
    @Path("findByUserId/{userId}")
    @Produces({"application/json"})
    public List<Eat> findByUserId(@PathParam("userId") int userId) {
        Query query = em.createNamedQuery("Eat.findByUserId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }
    
    @GET  //query the eat info by eatTime
    @Path("findByEatTime/{eatTime}")
    @Produces({"application/json"})
    public List<Eat> findByEatTime(@PathParam("eatTime") Timestamp eatTime) {    //mysql中的DateTime对应java.sql.Timestamp
        Query query = em.createNamedQuery("Eat.findByEatTime");
        query.setParameter("eatTime", eatTime);
        return query.getResultList();
    }

    @GET  //query the eat info by quentity
    @Path("findByQuentity/{quentity}")
    @Produces({"application/json"})
    public List<Eat> findByQuentity(@PathParam("quentity") double quentity) {    
        Query query = em.createNamedQuery("Eat.findByQuentity");
        query.setParameter("quentity", quentity);
        return query.getResultList();
    }
    
    @GET  //query the eat info by accurate measure
    @Path("findByMeasure/{measure}")
    @Produces({"application/json"})
    public List<Eat> findByMeasure(@PathParam("measure") String measure) {
        Query query = em.createNamedQuery("Eat.findByMeasure");
        query.setParameter("measure", measure);
        return query.getResultList();
    }
    
    //---------advanced query
    //--------dynamic query ByUserNameANDFoodName
    @GET
    @Path("findByUserNameANDFoodName/{userName}/{foodName}")
    @Produces({"application/json"})
    public List<Eat> findByUserNameANDFoodName(@PathParam("userName") String userName,@PathParam("foodName") String foodName){
        TypedQuery<Eat> q = em.createQuery("select e from Eat e where e.users.userName=:userName and e.foodId.foodName=:foodName", Eat.class);
        q.setParameter("userName", userName);
        q.setParameter("foodName", foodName);
        return q.getResultList();
    }

    // static query ByUserNameANDFoodName
//    @GET  
//    @Path("accurateFindByUserNameANDFoodName/{userName}/{foodName}")
//    @Produces({"application/json"})
//    public List<Eat> accurateFindByUserNameANDFoodName(@PathParam("userName") String userName,@PathParam("foodName") String foodName) {
//        Query query = em.createNamedQuery("Eat.accurateFindByUserNameANDFoodName");
//        query.setParameter("foodName", foodName);
//        query.setParameter("userName", userName);
//        return query.getResultList();
//    }
    
    @GET  //static query FindByUserNameANDEatTime
    @Path("FindByUserNameANDEatTime/{userName}/{eatTime}")
    @Produces({"application/json"})
    public String FindByUserNameANDEatTime(@PathParam("userName") String userName,@PathParam("eatTime") Timestamp eatTime) {
        Query query = em.createNamedQuery("Eat.FindByUserNameANDEatTime");
        query.setParameter("eatTime", eatTime);
        query.setParameter("userName", userName);
        Object[] result=(Object[])query.getSingleResult();
        String uname=result[0].toString();
        String fname=result[1].toString();
        String etime=result[2].toString();
        return uname+" ate "+fname+" at "+etime;
    }
    
    //-------------------try insert eat info by post
    /*@GET
    @Path("InsertByAll/{foodId}/{userId}/{eatTime}/{quentity}/{measure}")
    @Produces({"application/json"})
    public void InsertByAll(@PathParam("foodId") int foodId,@PathParam("userId") int userId,@PathParam("eatTime") Timestamp eatTime, @PathParam("quentity") double quentity,@PathParam("measure") String measure){
        try{
        Eat eat=new Eat(userId,eatTime);
        eat.setMeasure(measure);
        eat.setQuentity(quentity);
        eat.setFood_Id(foodId);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }*/


    //--------------------end of my code----------------------
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
