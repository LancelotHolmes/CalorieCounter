/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id27315681.service;

import com.id27315681.Eat;
import com.id27315681.Food;
import com.id27315681.Report;
import com.id27315681.ReportPK;
import com.id27315681.Users;
//import java.util.Date;
import java.sql.Date;
import java.util.ArrayList;
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

    public final double KG_LBS = 2.2046;         //1kg=2.2046lbs

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
    //------------------------Calculation with rest methods  test version
    /*@GET
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
    }*/
    //------------------------Calculation with rest methods  app version
    public double CalculateBMR(int userId) {
        Query q = em.createNamedQuery("Users.findByUserId");
        q.setParameter("userId", userId);
        // get the instance of Class Users through q
        Users user = (Users) q.getSingleResult();
        String gender = user.getGender();
        double weight = user.getWeight();
        double height = user.getHeight();
        short age = user.getAge();
        //count BMR by the parameters transmitted from Users
//        Double bmr=Double.valueOf(0);
        double bmr = 0;
        if (gender.toUpperCase().equals("M")) //——>可用 ? :
        {
            bmr = 13.75 * weight + 5 * height - 6.76 * age + 66;
//            return bmr;
        } else if (gender.toUpperCase().equals("F")) {
            bmr = 9.56 * weight + 1.85 * height - 4.68 * age + 655;
//            return bmr;
        }
//        System.out.println(bmr);
        System.out.println("BMR= "+bmr);
        return bmr;
    }

    //-----------------------Calculation Calorie Burned at rest
    public double CalorieBurnedAtRest(int userId) {
        Query q = em.createNamedQuery("Users.findByUserId");
        q.setParameter("userId", userId);
        // get the instance of Class Users through q
        Users user = (Users) q.getSingleResult();
        int levelOfAct = user.getLevelOfActivity();
        // get BMR
        double bmr = CalculateBMR(userId);
        //count calorie burned at rest by the parameter activity level transmitted from Users
        double calorieRest = bmr;
        switch (levelOfAct) {
            case 1:
                calorieRest *= 1.2;
                break;
            case 2:
                calorieRest *= 1.375;
                break;
            case 3:
                calorieRest *= 1.55;
                break;
            case 4:
                calorieRest *= 1.725;
                break;
            case 5:
                calorieRest *= 1.9;
                break;
            default:
                System.out.println("Wrong activity level");
                break;
        }
        System.out.println("Calorie burned at rest for a person: "+calorieRest);
        return calorieRest;

    }

    //----------------------------Calculation calorie burned by steps from Report by userId & recordDate
    public double CalorieBurnedBySteps(int userId, Date recordDate) {
        Query q = em.createNamedQuery("Users.findByUserId");
        q.setParameter("userId", userId);
        // get the instance of Class Users through q
        Users user = (Users) q.getSingleResult();
        // get parameter weight&Steps per mile from Users
        double weight = user.getWeight() * KG_LBS;
        double stepsPerMile = user.getStepsPerMile();

//        Query qf=em.createNamedQuery("Report.findByUserId");
        //locate the record from Report by userId and recordDate
        TypedQuery<Report> qr = em.createQuery("select r from Report r where r.reportPK.userId=:userId and r.reportPK.recordDate=:recordDate", Report.class);
        qr.setParameter("userId", userId);  //这里会产生一个错误在于Eat表和Report表不统一而产生的500查询不到错误
        qr.setParameter("recordDate", recordDate);
        Report report = (Report) qr.getSingleResult();
        //get parameter total steps per day from Report
        double totalSteps = report.getTotalSteps();
        //calorieBurnedPerMile——>calorieBurnedPerStep
        double calorieBurnedPerMile = weight * 0.49;
        double calorieBurnedPerStep = calorieBurnedPerMile / stepsPerMile;
        System.out.println("Calorie burned by Steps for a person: "+calorieBurnedPerStep * totalSteps);
        return calorieBurnedPerStep * totalSteps;
    }

    //----------------------Calorie Burned Per Day
    @GET
    @Path("CalorieBurnedPerDay/{userId}/{recordDate}")
    @Produces({"application/json"})
    public String CalorieBurnedPerDay(@PathParam("userId") int userId,@PathParam("recordDate") Date recordDate) {
        double calorieBurnedAtRest = CalorieBurnedAtRest(userId);
        double calorieBurnedBySteps = CalorieBurnedBySteps(userId, recordDate);
        return Double.toString(calorieBurnedAtRest + calorieBurnedBySteps);

    }

    //-------------------Calorie Burned during a period
    @GET
    @Path("CalorieBurnedOFPeriod/{userId}/{startDate}/{endDate}")
    @Produces({"application/json"})
    public String CalorieBurnedOFPeriod(@PathParam("userId") int userId,@PathParam("startDate") Date sDate,@PathParam("endDate") Date eDate){
        double calorieBurnedOFPeriod=0;
        TypedQuery<Report> q=em.createQuery("select r from Report r where r.reportPK.userId=:userId and r.reportPK.recordDate between :startDate and :endDate",Report.class);
        q.setParameter("userId", userId);
        q.setParameter("startDate", sDate);
        q.setParameter("endDate", eDate);
        List<Report> report=q.getResultList();
        for(Report r:report){
            calorieBurnedOFPeriod+=r.getCalorieBurned();
        }
        return Double.toString(calorieBurnedOFPeriod);
    }

    //--------------------------Calorie Consumed Per Day
    @GET
    @Path("CalorieConsumedPerDay/{userId}/{recordDate}")
    @Produces({"application/json"})
    public String CalorieConsumedPerDay(@PathParam("userId") int userId,@PathParam("recordDate") Date recordDate) {
//        Query q=em.createNamedQuery("Report.findByUserIdANDRecordDate");
        TypedQuery<Eat> q = em.createQuery("select e from Eat e where e.eatPK.userId=" + userId + " and e.eatPK.eatTime like " + "'" + recordDate + "%'", Eat.class);
        //在一条createQuery语句中:userId不能和+userId+共存
//        q.setParameter("userId", userId);
        // get the parameter quentity of Class Eat through q
        List<Eat> diet = q.getResultList();  //exception record , Vector cannot be converted into ArrayList,之前用ArrayList强转出了问题
        // get foodId and then get the parameter of calorie of food by foodId from table Food
        double calorieConsumed = 0;
        for (Eat e : diet) {
            double quentity = e.getQuentity();
            int foodId = e.getFoodId().getFoodId();
            Query qf = em.createNamedQuery("Food.findByFoodId");
            qf.setParameter("foodId", foodId);
            Food food = (Food) qf.getSingleResult();
            double calorieOFFood = food.getCalorie();
            calorieConsumed += quentity * calorieOFFood;
        }
        return Double.toString(calorieConsumed);
    }
    
    //-------------------Calorie Consumed during a period
    @GET
    @Path("CalorieConsumedOFPeriod/{userId}/{startDate}/{endDate}")
    @Produces({"application/json"})
    public String CalorieConsumedOFPeriod(@PathParam("userId") int userId,@PathParam("startDate") Date sDate,@PathParam("endDate") Date eDate){
        double calorieConsumedOFPeriod=0;
        TypedQuery<Report> q=em.createQuery("select r from Report r where r.reportPK.userId=:userId and r.reportPK.recordDate between :startDate and :endDate",Report.class);
        q.setParameter("userId", userId);
        q.setParameter("startDate", sDate);
        q.setParameter("endDate", eDate);
        List<Report> report=q.getResultList();
        for(Report r:report){
            calorieConsumedOFPeriod+=r.getCalorieConsumed();
        }
        return Double.toString(calorieConsumedOFPeriod);
    }

    //--------------------my code--------------------------------------------
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
