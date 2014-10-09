/**
 * CharacterRaceTest.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.character.lib.vo;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.bogie.race.lib.vo.Race;

/**
 * CharacterRaceTest
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class CharacterRaceTest extends TestCase
{
    public void testCharacterRace()
    {
        Session session = null;
        
        try
        {
            // This step will read hibernate.cfg.xml and prepare hibernate for
            // use
            SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();

            session = sessionFactory.openSession();
            
            Transaction transaction = session.beginTransaction();
            
            Race    race = new Race();
            
            race.setName("race");
            
            Integer raceId = (Integer)session.save(race);
            
            transaction.commit();

            transaction = session.beginTransaction();

            CharacterRace   characterRace = new CharacterRace();
            
            //characterRace.setCharacterId(1);
            characterRace.setRace(race);
            
            Integer characterRaceId = (Integer)session.save(characterRace);

            transaction.commit();

//            session.flush();
//            session.close();
//
//            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
//
//            session = sessionFactory.openSession();

            race = (Race)session.load(Race.class, raceId);

            System.out.println(race.getName());

            CharacterRace characterRace2 = (CharacterRace)session.load(CharacterRace.class, characterRaceId);
            
            transaction = session.beginTransaction();
            
            session.delete(characterRace2);
            session.delete(race);
            
            transaction.commit();

            System.out.println(characterRace2.getName());
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
