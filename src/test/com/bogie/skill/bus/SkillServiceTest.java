/**
 * SkillServiceTest.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.skill.bus;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.bogie.common.CommonTestCase;
import com.bogie.common.bus.CommonService;
import com.bogie.common.lib.vo.Stat;
import com.bogie.skill.lib.vo.Skill;

import static org.junit.Assert.*;

public class SkillServiceTest extends CommonTestCase
{
    SkillService    skillService;
    CommonService   commonService;
    
    @Before
    public void init()
    {
        skillService = (SkillService)applicationContext.getBean("skillService");
        commonService = (CommonService)applicationContext.getBean("commonService");
    }
    
    @Test
    public void testSaveSkill()
    {
        Skill   skill = createSkill("skill");
        
        skillService.saveSkill(skill);
        
        List<Skill>  skills = skillService.findAllSkills();
        
        assertTrue(skills.size() == 1);
        
        skillService.deleteSkill(skill);
    }
    
    @Test
    public void testDeleteSkill()
    {
        Skill   skill = createSkill("skill");
        
        skillService.saveSkill(skill);
        
        List<Skill>  skills = skillService.findAllSkills();
        
        assertTrue(skills.size() == 1);
        
        skillService.deleteSkill(skill);

        skills = skillService.findAllSkills();
        
        assertTrue(skills.size() == 0);
    }

    @Test
    public void testFindSkills()
    {
        Skill   skill = createSkill("skill");
        Skill   childSkill = createSkill("childSkill");
        
        skill.addChild(childSkill);
        skillService.saveSkill(skill);
        
        List<Skill>  skills = skillService.findSkills((Skill)null);

        assertTrue(skills.size() == 1);
        assertEquals(skills.get(0).getId(), skill.getId());
        
        skills = skillService.findSkills(skill);

        assertTrue(skills.size() == 1);
        assertEquals(skills.get(0).getId(), childSkill.getId());
        
        skillService.deleteSkill(skill);
    }
    
    @Test
    public void testFindAllSkills()
    {
        Skill   skill = createSkill("skill");
        Skill   childSkill = createSkill("childSkill");
        
        skill.addChild(childSkill);
        skillService.saveSkill(skill);
        
        List<Skill>  allSkills = skillService.findAllSkills();

        assertTrue(allSkills.size() == 2);

        skillService.deleteSkill(skill);
    }

    private Skill createSkill(String skillName)
    {
        Stat            primaryStat = new Stat();
        Skill           skill = new Skill();

        primaryStat.setCode('P');
        primaryStat.setShortForm("PRI");
        primaryStat.setLongForm("Primary");
        primaryStat.setMultiplier(1);
        
        commonService.saveStat(primaryStat);
        
        skill.setName(skillName);
        skill.setPrimaryStat(primaryStat);
        
        return skill;
    }
}
