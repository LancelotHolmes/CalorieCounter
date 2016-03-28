/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id27315681.service;

import com.id27315681.Users;
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
@Path("com.id27315681.users")
public class UsersFacadeREST extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "CalorieCounterServerPU")
    private EntityManager em;

    public UsersFacadeREST() {
        super(Users.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Users entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Users entity) {
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
    public Users find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    //---------my code part--------------------------------------------------------
    //--------basic
    @GET  //query the user info by accurate userId
    @Path("findByUserId/{userId}")
    @Produces({"application/json"})
    public List<Users> findByUserId(@PathParam("userId") int userId) {
        Query query = em.createNamedQuery("Users.findByUserId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }
    
    @GET  //query the user info by accurate pwd
    @Path("findByPwd/{pwd}")
    @Produces({"application/json"})
    public List<Users> findByPwd(@PathParam("pwd") String pwd) {
        Query query = em.createNamedQuery("Users.findByPwd");
        query.setParameter("pwd", pwd);
        return query.getResultList();
    }
    
    @GET  //query the user info by accurate name
    @Path("findByUserName/{userName}")
    @Produces({"application/json"})
    public List<Users> findByUserName(@PathParam("userName") String userName) {
        Query query = em.createNamedQuery("Users.findByUserName");
        query.setParameter("userName", userName);
        return query.getResultList();
    }

    @GET   //query user info by name regardless of the case
    @Path("findByUpperName/{userName}")
    @Produces({"application/json"})
    public List<Users> findByUpperName(@PathParam("userName") String userName) {
        TypedQuery<Users> q = em.createQuery("select u from Users u where UPPER(u.userName)=UPPER('" + userName + "')", Users.class);
        return q.getResultList();
    }
    
    @GET  //query the user info by age
    @Path("findByAge/{age}")
    @Produces({"application/json"})
    public List<Users> findByAge(@PathParam("age") int age) {
        Query query = em.createNamedQuery("Users.findByAge");
        query.setParameter("age", age);
        return query.getResultList();
    }
    
    @GET  //query the user info by height
    @Path("findByHeight/{height}")
    @Produces({"application/json"})
    public List<Users> findByHeight(@PathParam("height") int height) {
        Query query = em.createNamedQuery("Users.findByHeight");
        query.setParameter("height", height);
        return query.getResultList();
    }
    
    @GET  //query the user info by weight
    @Path("findByWeight/{weight}")
    @Produces({"application/json"})
    public List<Users> findByWeight(@PathParam("weight") int weight) {
        Query query = em.createNamedQuery("Users.findByWeight");
        query.setParameter("weight", weight);
        return query.getResultList();
    }
    
    @GET  //query the user info by gender
    @Path("findByGender/{gender}")
    @Produces({"application/json"})
    public List<Users> findByGender(@PathParam("gender") String gender) {
        Query query = em.createNamedQuery("Users.findByGender");
        query.setParameter("gender", gender);
        return query.getResultList();
    }
    
    @GET  //query the user info by levelOfActivity
    @Path("findByLevelOfActivity/{levelOfActivity}")
    @Produces({"application/json"})
    public List<Users> findByLevelOfActivity(@PathParam("levelOfActivity") short levelOfActivity) {
        Query query = em.createNamedQuery("Users.findByLevelOfActivity");
        query.setParameter("levelOfActivity", levelOfActivity);
        return query.getResultList();
    }
    
    @GET  //query the user info by stepsPerMile
    @Path("findByStepsPerMile/{stepsPerMile}")
    @Produces({"application/json"})
    public List<Users> findByStepsPerMile(@PathParam("stepsPerMile") short stepsPerMile) {
        Query query = em.createNamedQuery("Users.findByStepsPerMile");
        query.setParameter("stepsPerMile", stepsPerMile);
        return query.getResultList();
    }
    
    //----------  advanced query
    @GET    //query user info by age and gender
    @Path("findByAgeAndGender/{age}/{gender}")
    @Produces({"application/json"})
    public List<Users> findByAgeAndGender(@PathParam("age")int age,@PathParam("gender") String gender){//gender采用枚举类型
        Query query=em.createNamedQuery("Users.findByAgeAndGender");
        query.setParameter("age", age);
        query.setParameter("gender", gender);
        return query.getResultList();
    }

    //--------------------------end of my code part
    
    
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
