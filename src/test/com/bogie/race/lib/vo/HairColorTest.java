/**
 * HairColorTest.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.race.lib.vo;

import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.bogie.common.lib.vo.HairColor;

/**
 * HairColorTest
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class HairColorTest extends TestCase
{
    public void testHairColor()
    {
        Session session = null;
        
        try
        {
            // This step will read hibernate.cfg.xml and prepare hibernate for
            // use
            SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();

            session = sessionFactory.openSession();
            
            Transaction transaction = session.beginTransaction();
            
            // Create new instance of Contact and set values in it by
            // reading them from form object
            System.out.println("Inserting Record");
            HairColor   hairColor = new HairColor("hairColor");
            
            Integer id = (Integer)session.save(hairColor);

            transaction.commit();

            HairColor hairColor2 = (HairColor)session.load(HairColor.class, id);
            
            System.out.println("Done");
            
            hairColor2.setName("hairColorUpdate");
            
            transaction = session.beginTransaction();
            
            session.saveOrUpdate(hairColor2);
            
            transaction.commit();
            
            hairColor = (HairColor)session.load(HairColor.class, id);
            
            System.out.println(hairColor.getName());

            List hairColors = session.createQuery("from HairColor").list();
            
            transaction = session.beginTransaction();
            
            session.delete(hairColor);
            
            transaction.commit();
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
             // Actual contact insertion will happen at this step
            session.flush();
            session.close();
        }
    }
}
