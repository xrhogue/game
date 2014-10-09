/**
 * RaceServiceTest.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.race.bus;

import java.util.List;

import org.junit.Test;

import com.bogie.common.CommonTestCase;
import com.bogie.common.lib.vo.Complexion;
import com.bogie.common.lib.vo.EyeColor;
import com.bogie.common.lib.vo.HairColor;
import com.bogie.common.lib.vo.SkinColor;
import com.bogie.race.lib.vo.Race;
import com.bogie.race.lib.vo.RaceComplexion;
import com.bogie.race.lib.vo.RaceEyeColor;
import com.bogie.race.lib.vo.RaceHairColor;
import com.bogie.race.lib.vo.RaceSkinColor;

public class RaceServiceTest extends CommonTestCase
{
    @Test
    public void testRaces()
    {
        RaceService raceService = (RaceService)applicationContext.getBean("raceService");
        Race        race = createRace("race");
        
        raceService.saveRace(race);
        
        List<Race>  races = raceService.findRaces(null);
        List<Race>  allRaces = raceService.findAllRaces();
        
        raceService.deleteRace(race);
    }
    
    @Test
    public void testRaceChildren()
    {
        RaceService raceService = (RaceService)applicationContext.getBean("raceService");
        Race        race = createRace("race");
        Race        childRace = createRace("childRace");
        
        race.addChild(childRace);
        raceService.saveRace(race);
        
        List<Race>  races = raceService.findRaces(null);
        List<Race>  allRaces = raceService.findAllRaces();
        
        raceService.deleteRace(childRace);
        raceService.deleteRace(race);
    }

    private Race createRace(String raceName)
    {
        PhysicalAppearanceService physicalAppearanceService = (PhysicalAppearanceService)applicationContext.getBean("physicalAppearanceService");
        
        SkinColor       skinColor = new SkinColor("skinColor");
        HairColor       hairColor = new HairColor("hairColor");
        EyeColor        eyeColor = new EyeColor("eyeColor");
        Complexion      complexion = new Complexion("complexion");
        RaceSkinColor   raceSkinColor = new RaceSkinColor(skinColor);
        RaceHairColor   raceHairColor = new RaceHairColor(hairColor);
        RaceEyeColor    raceEyeColor = new RaceEyeColor(eyeColor);
        RaceComplexion  raceComplexion = new RaceComplexion(complexion);
        Race            race = new Race();

        physicalAppearanceService.saveSkinColor(skinColor);
        physicalAppearanceService.saveHairColor(hairColor);
        physicalAppearanceService.saveEyeColor(eyeColor);
        physicalAppearanceService.saveComplexion(complexion);
        
        race.addSkinColor(raceSkinColor);
        race.addHairColor(raceHairColor);
        race.addEyeColor(raceEyeColor);
        race.addComplexion(raceComplexion);
        
        race.setName(raceName);
        
        return race;
    }
}
