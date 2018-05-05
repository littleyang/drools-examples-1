package com.sample.rules;

import static com.sample.rules.RulesUtils.checkErrors;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.sample.travel.domain.Customer;
import com.sample.travel.domain.Reservation;



public class TravelsFires {

    public static void main(String[] args) {

//        bagFees();
        
        upgradeClassFees();
    }

    static void upgradeClassFees() {

        KieServices services = KieServices.Factory.get();
        KieContainer container = services.getKieClasspathContainer();
        if(checkErrors(container)) {
            throw new IllegalArgumentException("Rule Errors.");
        }
        
        KieSession ksession = container.newKieSession("TravelsSession");
        
        Reservation reservation = new Reservation();
        reservation.setBagsChecked(3); 
        reservation.setClassUpgrade(true);
        reservation.setComfortUpgrade(true);
        reservation.setDrinkUpgrade(true);
        reservation.setEntertainmentUpgrade(true);
        reservation.setFlightInsurance(true);
        reservation.setMealUpgrade(true);
        ksession.insert(reservation);
        
        
        Customer customer = new Customer();
        customer.setLoyaltyLevel("Bronze");
        customer.setAge(22);
        ksession.insert(customer);
        
        ksession.fireAllRules();
        
        ksession.getQueryResults("getBagFees").forEach(c -> {
            System.out.println(c.get("Fee"));
        });
        
        ksession.getQueryResults("getUpgradeClassFees").forEach(c -> {
            System.out.println(c.get("Fee"));
        });
        
        ksession.getQueryResults("getComfortFees").forEach(c -> {
            System.out.println(c.get("Fee"));
        });
        
        ksession.getQueryResults("getDrinksFees").forEach(c -> {
            System.out.println(c.get("Fee"));
        });
        
        ksession.getQueryResults("getEntertainmentFees").forEach(c -> {
            System.out.println(c.get("Fee"));
        });
        
        ksession.getQueryResults("getInsuranceFees").forEach(c -> {
            System.out.println(c.get("Fee"));
        });
        
        ksession.getQueryResults("getMealsFees").forEach(c -> {
            System.out.println(c.get("Fee"));
        });
       
       
        ksession.dispose();
    }

    static void bagFees() {

        KieServices services = KieServices.Factory.get();
        KieContainer container = services.getKieClasspathContainer();
        if(checkErrors(container)) {
            throw new IllegalArgumentException("Rule Errors.");
        }
        
        KieSession ksession = container.newKieSession("TravelsSession");
        
        Reservation reservation = new Reservation();
        reservation.setBagsChecked(3);     
        ksession.insert(reservation);
        
        
        Customer customer = new Customer();
        customer.setLoyaltyLevel("None");
        ksession.insert(customer);
        
        ksession.fireAllRules();
        
        ksession.getQueryResults("getFees").forEach(c -> {
            System.out.println(c.get("Fee"));
        });
       
        ksession.dispose();
    }

}
