/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class InMemoryPersistenceTest {
    
    @Test
    public void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException{
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15)};
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);
        
        ibpp.saveBlueprint(bp0);
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        ibpp.saveBlueprint(bp);
        
        assertNotNull("Loading a previously stored blueprint returned null.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()));
        
        assertEquals("Loading a previously stored blueprint returned a different blueprint.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()), bp);
        
    }


    @Test
    public void saveExistingBpTest() {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        try {
            ibpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
        
        Point[] pts2=new Point[]{new Point(10, 10),new Point(20, 20)};
        Blueprint bp2=new Blueprint("john", "thepaint",pts2);

        try{
            ibpp.saveBlueprint(bp2);
            fail("An exception was expected after saving a second blueprint with the same name and autor");
        }
        catch (BlueprintPersistenceException ex){
            
        }
                
        
    }

    @Test
    public void shouldGetBlueprint(){
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
        try{
            Point[] pts1 = new Point[]{new Point(10,10), new Point(15,15)};
            Blueprint bp1 = new Blueprint("INSI", "ARSW", pts1);
            ibpp.saveBlueprint(bp1);
            Blueprint bp2 = ibpp.getBlueprint("INSI", "ARSW");
            assertEquals(bp2.getAuthor(), "INSI");
            assertEquals(bp2.getName(), "ARSW");
        }catch (BlueprintNotFoundException e){
            fail("Author not found");
        }catch (BlueprintPersistenceException e){
            fail("Not added correctly");
        }
    }

    @Test
    public void shouldGetBlueprintByAuthor(){
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
        Point[] pts1 = new Point[]{new Point(10,10), new Point(15,15)};
        Blueprint bp1 = new Blueprint("INSI", "ARSW", pts1);
        Point[] pts2 = new Point[]{new Point(11,11), new Point(16,16)};
        Blueprint bp2 = new Blueprint("INSI", "CVDS", pts2);
        Point[] pts3 = new Point[]{new Point(12,12), new Point(17,17)};
        Blueprint bp3 = new Blueprint("INSI", "POOB", pts3);
        Point[] pts4 = new Point[]{new Point(13,13), new Point(18,18)};
        Blueprint bp4 = new Blueprint("IELC", "CAYD", pts4);
        try{
            ibpp.saveBlueprint(bp1);
            ibpp.saveBlueprint(bp2);
            ibpp.saveBlueprint(bp3);
            ibpp.saveBlueprint(bp4);
            assertEquals(ibpp.getBlueprintsByAuthor("INSI").size(), 3);
        }catch(BlueprintNotFoundException e){
            fail(e.getMessage());
        }catch(BlueprintPersistenceException e){
            fail(e.getMessage());
        }
    }

    @Test
    public void shouldNotGetBlueprintByAuthor(){
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
        Point[] pts1 = new Point[]{new Point(10,10), new Point(15,15)};
        Blueprint bp1 = new Blueprint("INSI", "ARSW", pts1);
        Point[] pts2 = new Point[]{new Point(11,11), new Point(16,16)};
        Blueprint bp2 = new Blueprint("INSI", "CVDS", pts2);
        Point[] pts3 = new Point[]{new Point(12,12), new Point(17,17)};
        Blueprint bp3 = new Blueprint("INSI", "POOB", pts3);
        Point[] pts4 = new Point[]{new Point(13,13), new Point(18,18)};
        Blueprint bp4 = new Blueprint("IELC", "CAYD", pts4);
        try{
            ibpp.saveBlueprint(bp1);
            ibpp.saveBlueprint(bp2);
            ibpp.saveBlueprint(bp3);
            ibpp.saveBlueprint(bp4);
            assertNotEquals(ibpp.getBlueprintsByAuthor("INS").size(), 3);
        }catch(BlueprintNotFoundException e){
            fail(e.getMessage());
        }catch(BlueprintPersistenceException e){
            fail(e.getMessage());
        }
    }

    
}
