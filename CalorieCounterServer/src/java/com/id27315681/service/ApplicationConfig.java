/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id27315681.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author zy751
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.id27315681.service.EatFacadeREST.class);
        resources.add(com.id27315681.service.FoodFacadeREST.class);
        resources.add(com.id27315681.service.ReportFacadeREST.class);
        resources.add(com.id27315681.service.UsersFacadeREST.class);
    }
    
}
