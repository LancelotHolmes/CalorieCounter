/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id27315681.service;

import com.id27315681.Report;
import com.id27315681.ReportPK;
import com.id27315681.Users;
//import java.util.Date;
import java.sql.Date;
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
@Path("com.id27315681.report")
public class ReportFacadeREST extends AbstractFacade<Report> {

    @PersistenceContext(unitName = "CalorieCounterServerPU")
    private EntityManager em;

    private ReportPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;userId=userIdValue;recordDate=recordDateValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        com.id27315681.ReportPK key = new com.id27315681.ReportPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> userId = map.get("userId");
        if (userId != null && !userId.isEmpty()) {
            key.setUserId(new java.lang.Integer(userId.get(0)));
        }
        java.util.List<String> recordDate = map.get("recordDate");
        if (recordDate != null && !recordDate.isEmpty()) {
            key.setRecordDate(new java.util.Date(recordDate.get(0)));
        }
        return key;
    }

    public ReportFacadeREST() {
        super(Report.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Report entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, Report entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        com.id27315681.ReportPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Report find(@PathParam("id") PathSegment id) {
        com.id27315681.ReportPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    //--------------------my code--------------------------------------------
    @GET  //query the report info by userId
    @Path("findByUserId/{userId}")
    @Produces({"application/json"})
    public List<Report> findByUserId(@PathParam("userId") int userId) {
        Query query = em.createNamedQuery("Report.findByUserId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }
    
    @GET  //query the report info by recordDate
    @Path("findByRecordDate/{recordDate}")
    @Produces({"application/json"})
    public List<Report> findByRecordDate(@PathParam("recordDate") Date recordDate) {    //mysql中的Date对应java.sql.Date，而非java.util.Date
        Query query = em.createNamedQuery("Report.findByRecordDate");
        query.setParameter("recordDate", recordDate);
        return query.getResultList();
    }
    
    @GET  //query the report info by calorieConsumed
    @Path("findByCalorieConsumed/{calorieConsumed}")
    @Produces({"application/json"})
    public List<Report> findByCalorieConsumed(@PathParam("calorieConsumed") double calorieConsumed) {    
        Query query = em.createNamedQuery("Report.findByCalorieConsumed");
        query.setParameter("calorieConsumed", calorieConsumed);
        return query.getResultList();
    }
    
    @GET  //query the report info by calorieBurned
    @Path("findByCalorieBurned/{calorieBurned}")
    @Produces({"application/json"})
    public List<Report> findByCalorieBurned(@PathParam("calorieBurned") double calorieBurned) {    
        Query query = em.createNamedQuery("Report.findByCalorieBurned");
        query.setParameter("calorieBurned", calorieBurned);
        return query.getResultList();
    }
    
    @GET  //query the report info by calorieGoal
    @Path("findByCalorieGoal/{calorieGoal}")
    @Produces({"application/json"})
    public List<Report> findByCalorieGoal(@PathParam("calorieGoal") double calorieGoal) {    
        Query query = em.createNamedQuery("Report.findByCalorieGoal");
        query.setParameter("calorieGoal", calorieGoal);
        return query.getResultList();
    }
    
    @GET  //query the report info by calorieRemaining
    @Path("findByCalorieRemaining/{calorieRemaining}")
    @Produces({"application/json"})
    public List<Report> findByCalorieRemaining(@PathParam("calorieRemaining") double calorieRemaining) {    
        Query query = em.createNamedQuery("Report.findByCalorieRemaining");
        query.setParameter("calorieRemaining", calorieRemaining);
        return query.getResultList();
    }
    
    @GET  //query the report info by totalSteps
    @Path("findByTotalSteps/{totalSteps}")
    @Produces({"application/json"})
    public List<Report> findByTotalSteps(@PathParam("totalSteps") int totalSteps) {
        Query query = em.createNamedQuery("Report.findByTotalSteps");
        query.setParameter("totalSteps", totalSteps);
        return query.getResultList();
    }
    
    /*//------------------------Calculation with rest methods
    @GET
    @Path("CalculateBMR/{userId}")
    @Produces({"application/json"})
    public String CalculateBMR(@PathParam("userId") int userId){
        // dynamic query by id, just for test——>可用现成方法改进
        TypedQuery<Users> q = em.createQuery("select u from Users u where u.userId=:userId", Users.class);
        q.setParameter("userId", userId);
        // get the instance of Class Users through q
        Users user=q.getSingleResult();
        String gender=user.getGender();
        double weight=user.getWeight();
        double height=user.getHeight();
        short age=user.getAge();
        //count BMR by the parameters transmitted from Users
        Double bmr=Double.valueOf(0);
        if(gender.toUpperCase().equals("M"))     //——>可用 ? :
        {   
            bmr=13.75*weight+5*height-6.76*age+66;
            return bmr.toString();
        }
        else if(gender.toUpperCase().equals("F")){
            bmr=9.56*weight+1.85*height-4.68*age+655;
            return bmr.toString();
        }
         
        else
            return bmr.toString();
    }*/
    
    //------------------------Calculation with rest methods
    @GET
    @Path("CalculateBMR/{userId}")
    @Produces({"application/json"})
    public String CalculateBMR(@PathParam("userId") int userId){
        Query q=em.createNamedQuery("Users.findByUserId");
        q.setParameter("userId", userId);
        // get the instance of Class Users through q
        Users user=(Users)q.getSingleResult();
        String gender=user.getGender();
        double weight=user.getWeight();
        double height=user.getHeight();
        short age=user.getAge();
        //count BMR by the parameters transmitted from Users
        Double bmr=Double.valueOf(0);
        if(gender.toUpperCase().equals("M"))     //——>可用 ? :
        {   
            bmr=13.75*weight+5*height-6.76*age+66;
            return bmr.toString();
        }
        else if(gender.toUpperCase().equals("F")){
            bmr=9.56*weight+1.85*height-4.68*age+655;
            return bmr.toString();
        }
        else
            return bmr.toString();
    }
    
    
    
    //--------------------my code--------------------------------------------

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
