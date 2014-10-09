/**
 * CharacterTest.java
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
 * CharacterTest
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class CharacterTest extends TestCase
{
    public void testCharacter()
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
            CharacterBase   character = new CharacterBase();
            
            characterRace.setRace(race);
            characterRace.setCharacter(character);
            
            character.getRaces().add(characterRace);
            character.setName("name");
            
            Integer characterId = (Integer)session.save(character);

            transaction.commit();

//            session.flush();
//            session.close();
//
//            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
//
//            session = sessionFactory.openSession();

            race = (Race)session.load(Race.class, raceId);

            System.out.println(race.getName());

            CharacterBase character2 = (CharacterBase)session.load(CharacterBase.class, characterId);
            
            transaction = session.beginTransaction();
            
            session.delete(character2);
            session.delete(race);
            
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
