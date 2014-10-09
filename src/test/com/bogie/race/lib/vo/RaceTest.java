/**
 * RaceTest.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.race.lib.vo;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.bogie.common.lib.vo.Complexion;
import com.bogie.common.lib.vo.EyeColor;
import com.bogie.common.lib.vo.Gender;
import com.bogie.common.lib.vo.HairColor;
import com.bogie.common.lib.vo.SkinColor;

/**
 * RaceTest
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class RaceTest extends TestCase
{
    public void testRace()
    {
        Session session = null;
        
        try
        {
            // This step will read hibernate.cfg.xml and prepare hibernate for
            // use
            SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();

            session = sessionFactory.openSession();
            
            Transaction transaction = session.beginTransaction();
            
            Gender          gender = new Gender();
            SkinColor       skinColor = new SkinColor();
            HairColor       hairColor = new HairColor();
            EyeColor        eyeColor = new EyeColor();
            Complexion      complexion = new Complexion();
            RaceSkinColor   raceSkinColor = new RaceSkinColor();
            RaceHairColor   raceHairColor = new RaceHairColor();
            RaceEyeColor    raceEyeColor = new RaceEyeColor();
            RaceComplexion  raceComplexion = new RaceComplexion();
            Race            race = new Race();
            
            gender.setName("gender");
            Integer genderId = (Integer)session.save(gender);
            
            skinColor.setName("skinColor");
            Integer skinColorId = (Integer)session.save(skinColor);
            
            hairColor.setName("hairColor");
            Integer hairColorId = (Integer)session.save(hairColor);

            eyeColor.setName("eyeColor");
            Integer eyeColorId = (Integer)session.save(eyeColor);

            complexion.setName("complexion");
            Integer complexionId = (Integer)session.save(complexion);

            raceSkinColor.setSkinColor(skinColor);
            raceSkinColor.setRace(race);
            raceSkinColor.setGender(gender);
            
            raceEyeColor.setEyeColor(eyeColor);
            raceEyeColor.setRace(race);
            raceEyeColor.setGender(gender);
            
            raceHairColor.setHairColor(hairColor);
            raceHairColor.setRace(race);
            raceHairColor.setGender(gender);
            
            raceComplexion.setComplexion(complexion);
            raceComplexion.setRace(race);
            raceComplexion.setGender(gender);
            
            race.getSkinColors().add(raceSkinColor);
            race.getHairColors().add(raceHairColor);
            race.getEyeColors().add(raceEyeColor);
            race.getComplexions().add(raceComplexion);
            
            race.setName("race");
            Integer raceId = (Integer)session.save(race);
            
            transaction.commit();

            session.flush();
            session.close();

            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();

            session = sessionFactory.openSession();

            gender = (Gender)session.load(Gender.class, genderId);
            skinColor = (SkinColor)session.load(SkinColor.class, skinColorId);
            hairColor = (HairColor)session.load(HairColor.class, hairColorId);
            eyeColor = (EyeColor)session.load(EyeColor.class, eyeColorId);
            complexion = (Complexion)session.load(Complexion.class, complexionId);
            race = (Race)session.load(Race.class, raceId);
             
            transaction = session.beginTransaction();
            
            session.delete(race);
            session.delete(skinColor);
            session.delete(hairColor);
            session.delete(eyeColor);
            session.delete(complexion);
            session.delete(gender);
            
            transaction.commit();
            
            System.out.println(race.getSkinColors().iterator().next().getSkinColor().getName());
            System.out.println(race.getHairColors().iterator().next().getHairColor().getName());
            System.out.println(race.getEyeColors().iterator().next().getEyeColor().getName());
            System.out.println(race.getComplexions().iterator().next().getComplexion().getName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            session.flush();
            session.close();
        }
    }
}
