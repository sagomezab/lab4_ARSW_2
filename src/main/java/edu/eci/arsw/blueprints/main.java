package edu.eci.arsw.blueprints;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

public class main{
    public static void main(String args[]) throws BlueprintNotFoundException, BlueprintPersistenceException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bpServices = ac.getBean(BlueprintsServices.class);

        Point[] pts1 = new Point[]{new Point(10,10), new Point(15,15)};
        Blueprint bp1 = new Blueprint("INSI", "ARSW", pts1);
        Blueprint bp2 = new Blueprint("INSI", "CVDS");

        bpServices.addNewBlueprint(bp1);
        bpServices.addNewBlueprint(bp2);

        //for(Blueprint blueprint : bpServices.getAllBlueprints()){
          //  System.out.println(blueprint.getName());
          //  System.out.println(blueprint.getAuthor());
        //}

        //redundacy();
        subsampling();
    }

    public static void redundacy() throws BlueprintPersistenceException, BlueprintNotFoundException{
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bpServices = ac.getBean(BlueprintsServices.class);
        Point[] pts0 = new Point[]{new Point(25, 10), new Point(15, 15), new Point(15, 15), new Point(10, 5)};
        Blueprint bp0 = new Blueprint("INSI", "ARSW",pts0);
        System.out.println("Before using the filter: " + bp0.toString());
        for(Point punto: bp0.getPoints()){
            System.out.print("(" + punto.getX() + "," + punto.getY() + ")");
        }
        System.out.println();
        bpServices.addNewBlueprint(bp0);
        Blueprint filtering = bpServices.filterBluePrint(bp0);
        System.out.println("After using the filter: " + bpServices.getBlueprint("INSI", "ARSW").toString());
        for(Point punto: filtering.getPoints()){
            System.out.print("(" + punto.getX() + "," + punto.getY() + ")");
        }
    }

    public static void subsampling() throws BlueprintPersistenceException, BlueprintNotFoundException{
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bpServices = ac.getBean(BlueprintsServices.class);
        Point[] pts0 = new Point[]{new Point(25, 10), new Point(15, 15), new Point(15, 15), new Point(10, 5)};
        Blueprint bp0 = new Blueprint("INSI", "ARSW",pts0);
        System.out.println("Before using the filter: " + bp0.toString());
        for(Point punto: bp0.getPoints()){
            System.out.print("(" + punto.getX() + "," + punto.getY() + ")");
        }
        System.out.println();
        bpServices.addNewBlueprint(bp0);
        Blueprint filtering = bpServices.filterBluePrint(bp0);
        System.out.println("After using the filter: " + bpServices.getBlueprint("INSI", "ARSW").toString());
        for(Point punto: filtering.getPoints()){
            System.out.print("(" + punto.getX() + "," + punto.getY() + ")");
        }
    }





}