/**
 * SkillServiceImpl.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.skill.bus.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.StaleObjectStateException;

import com.bogie.common.bus.CommonService;
import com.bogie.common.lib.dto.SkillDTO;
import com.bogie.skill.bus.SkillService;
import com.bogie.skill.dao.SkillDao;
import com.bogie.skill.lib.vo.Skill;

/**
 * SkillServiceImpl 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@org.springframework.transaction.annotation.Transactional
public class SkillServiceImpl implements SkillService
{
    private CommonService   commonService;
    private SkillDao        skillDao;
    
    public void setCommonService(CommonService x) { commonService = x; }
    public void setSkillDao(SkillDao x) { skillDao = x; }

    /**
     * @see com.bogie.skill.bus.SkillService#getSkill(java.lang.Long)
     */
    public Skill getSkill(Long skillId)
    {
        return skillDao.getSkill(skillId);
    }
    
    /**
     * @see com.bogie.skill.bus.SkillService#deleteSkill(com.bogie.skill.lib.vo.Skill)
     */
    public void deleteSkill(Skill skill)
    {
        skillDao.deleteSkill(skill);
    }

    /**
     * @see com.bogie.skill.bus.SkillService#deleteSkill(java.lang.Long)
     */
    public void deleteSkill(Long skillId)
    {
        skillDao.deleteSkill(skillId);
    }

    /**
     * @see com.bogie.skill.bus.SkillService#findSkills(com.bogie.skill.lib.vo.Skill)
     */
    public List<Skill> findSkills(Skill parent)
    {
        return skillDao.findSkills(parent);
    }

    /**
     * @see com.bogie.skill.bus.SkillService#findSkills(java.lang.Long)
     */
    public List<Skill> findSkills(Long parentId)
    {
        return skillDao.findSkills(parentId);
    }

    /**
     * @see com.bogie.skill.bus.SkillService#findAllSkills()
     */
    public List<Skill> findAllSkills()
    {
        return skillDao.findAllSkills();
    }

    /**
     * @see com.bogie.skill.bus.SkillService#saveSkill(com.bogie.skill.lib.vo.Skill)
     */
    public Skill saveSkill(Skill skill)
    {
        return skillDao.saveSkill(skill);
    }

    /**
     * @see com.bogie.skill.bus.SkillService#saveSkill(com.bogie.common.lib.dto.SkillDTO)
     */
    public SkillDTO saveSkill(SkillDTO skill)
    {
        if (skill.getId() != null)
        {
            Skill   mergeSkill = skillDao.getSkill(skill.getId());
            
            if (mergeSkill.getVersion().equals(skill.getVersion()))
            {
                mergeSkill = mergeSkill.update(skill);
                
                mergeSkill.setPrimaryStat(commonService.getStat(skill.getPrimaryStatId()));
                mergeSkill.getSecondaryStats().clear();
                mergeSkill.getPrerequisites().clear();
                
                Set<Long>   secondaryStatIds = skill.getSecondaryStats();
                
                for (Long secondaryStatId : secondaryStatIds)
                {
                    mergeSkill.getSecondaryStats().add(commonService.getStat(secondaryStatId));
                }
                
                Set<Long>   prerequisiteIds = skill.getPrerequisites();
                
                for (Long prerequisiteId : prerequisiteIds)
                {
                    mergeSkill.getPrerequisites().add(getSkill(prerequisiteId));
                }
                
                mergeSkill = skillDao.saveSkill(mergeSkill);
            }
            else
            {
                throw new StaleObjectStateException("Skill has been updated already!", mergeSkill);
            }
            
            return mergeSkill.getDTO();
        }

        Skill   newSkill = new Skill(skill);
            
        newSkill.setPrimaryStat(commonService.getStat(skill.getPrimaryStatId()));

        return saveSkill(newSkill).getDTO();
    }
}
