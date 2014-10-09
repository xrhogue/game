package com.bogie.server;

import java.util.List;

import com.bogie.client.GameService;
import com.bogie.common.bus.CommonService;
import com.bogie.common.lib.dto.PhysicalAppearanceDTO;
import com.bogie.common.lib.dto.RaceDTO;
import com.bogie.common.lib.dto.SkillDTO;
import com.bogie.common.lib.dto.StatDTO;
import com.bogie.common.lib.vo.Complexion;
import com.bogie.common.lib.vo.EyeColor;
import com.bogie.common.lib.vo.Gender;
import com.bogie.common.lib.vo.HairColor;
import com.bogie.common.lib.vo.SkinColor;
import com.bogie.common.lib.vo.Stat;
import com.bogie.race.bus.PhysicalAppearanceService;
import com.bogie.race.bus.RaceService;
import com.bogie.race.lib.vo.Race;
import com.bogie.skill.bus.SkillService;
import com.bogie.skill.lib.vo.Skill;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * GameServiceImpl 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class GameServiceImpl extends RemoteServiceServlet implements GameService
{
	private static final long serialVersionUID = -4276918278515541547L;
	
	private CommonService               commonService;
    private SkillService                skillService;
    private RaceService                 raceService;
    private PhysicalAppearanceService   physicalAppearanceService;
    
	/**
	 * Default constructor
	 */
	public GameServiceImpl()
    {
	}

    public void setCommonService(CommonService x) { commonService = x; }
    public void setSkillService(SkillService x) { skillService = x; }
    public void setRaceService(RaceService x) { raceService = x; }
    public void setPhysicalAppearanceService(PhysicalAppearanceService x) { physicalAppearanceService = x; }

    /**
     * @see com.bogie.client.GameService#deleteStat(com.bogie.common.lib.dto.StatDTO)
     */
    public void deleteStat(StatDTO stat)
    {
        if (stat == null || stat.getId() == null)
        {
            return;
        }
        
        commonService.deleteStat(stat.getId());
    }

    /**
     * @see com.bogie.client.GameService#findStats()
     */
    public List<Stat> findStats()
    {
        return commonService.findStats();
    }

    /**
     * @see com.bogie.client.GameService#saveStat(com.bogie.common.lib.dto.StatDTO)
     */
    public StatDTO saveStat(StatDTO stat)
    {
        return commonService.saveStat(stat);
    }

    /**
     * @see com.bogie.client.GameService#deleteComplexion(java.lang.Long)
     */
    public void deleteComplexion(Long complexionId)
    {
        physicalAppearanceService.deleteComplexion(complexionId);
    }

    /**
     * @see com.bogie.client.GameService#deleteEyeColor(java.lang.Long)
     */
    public void deleteEyeColor(Long eyeColorId)
    {
        physicalAppearanceService.deleteEyeColor(eyeColorId);
    }

    /**
     * @see com.bogie.client.GameService#deleteGender(java.lang.Long)
     */
    public void deleteGender(Long genderId)
    {
        physicalAppearanceService.deleteGender(genderId);
    }

    /**
     * @see com.bogie.client.GameService#deleteHairColor(java.lang.Long)
     */
    public void deleteHairColor(Long hairColorId)
    {
        physicalAppearanceService.deleteHairColor(hairColorId);
    }

    /**
     * @see com.bogie.client.GameService#deleteSkinColor(java.lang.Long)
     */
    public void deleteSkinColor(Long skinColorId)
    {
        physicalAppearanceService.deleteSkinColor(skinColorId);
    }

    /**
     * @see com.bogie.client.GameService#findComplexions()
     */
    public List<Complexion> findComplexions()
    {
        return physicalAppearanceService.findComplexions();
    }

    /**
     * @see com.bogie.client.GameService#findEyeColors()
     */
    public List<EyeColor> findEyeColors()
    {
        return physicalAppearanceService.findEyeColors();
    }

    /**
     * @see com.bogie.client.GameService#findGenders()
     */
    public List<Gender> findGenders()
    {
        return physicalAppearanceService.findGenders();
    }

    /**
     * @see com.bogie.client.GameService#findHairColors()
     */
    public List<HairColor> findHairColors()
    {
        return physicalAppearanceService.findHairColors();
    }

    /**
     * @see com.bogie.client.GameService#findSkinColors()
     */
    public List<SkinColor> findSkinColors()
    {
    	return physicalAppearanceService.findSkinColors();
    }

    /**
     * @see com.bogie.client.GameService#getComplexion(java.lang.Long)
     */
    public PhysicalAppearanceDTO getComplexion(Long complexionId)
    {
        return physicalAppearanceService.getComplexion(complexionId).getDTO();
    }

    /**
     * @see com.bogie.client.GameService#getEyeColor(java.lang.Long)
     */
    public PhysicalAppearanceDTO getEyeColor(Long eyeColorId)
    {
        return physicalAppearanceService.getEyeColor(eyeColorId).getDTO();
    }

    /**
     * @see com.bogie.client.GameService#getGender(java.lang.Long)
     */
    public PhysicalAppearanceDTO getGender(Long genderId)
    {
        return physicalAppearanceService.getGender(genderId).getDTO();
    }

    /**
     * @see com.bogie.client.GameService#getHairColor(java.lang.Long)
     */
    public PhysicalAppearanceDTO getHairColor(Long hairColorId)
    {
        return physicalAppearanceService.getHairColor(hairColorId).getDTO();
    }

    /**
     * @see com.bogie.client.GameService#getSkinColor(java.lang.Long)
     */
    public PhysicalAppearanceDTO getSkinColor(Long skinColorId)
    {
        return physicalAppearanceService.getSkinColor(skinColorId).getDTO();
    }

    /**
     * @see com.bogie.client.GameService#saveComplexion(com.bogie.common.lib.dto.PhysicalAppearanceDTO)
     */
    public PhysicalAppearanceDTO saveComplexion(PhysicalAppearanceDTO complexion)
    {
        return physicalAppearanceService.saveComplexion(complexion);
    }

    /**
     * @see com.bogie.client.GameService#saveEyeColor(com.bogie.common.lib.dto.PhysicalAppearanceDTO)
     */
    public PhysicalAppearanceDTO saveEyeColor(PhysicalAppearanceDTO eyeColor)
    {
        return physicalAppearanceService.saveEyeColor(eyeColor);
    }

    /**
     * @see com.bogie.client.GameService#saveGender(com.bogie.common.lib.dto.PhysicalAppearanceDTO)
     */
    public PhysicalAppearanceDTO saveGender(PhysicalAppearanceDTO gender)
    {
        return physicalAppearanceService.saveGender(gender);
    }

    /**
     * @see com.bogie.client.GameService#saveHairColor(com.bogie.common.lib.dto.PhysicalAppearanceDTO)
     */
    public PhysicalAppearanceDTO saveHairColor(PhysicalAppearanceDTO hairColor)
    {
        return physicalAppearanceService.saveHairColor(hairColor);
    }

    /**
     * @see com.bogie.client.GameService#saveSkinColor(com.bogie.common.lib.dto.PhysicalAppearanceDTO)
     */
    public PhysicalAppearanceDTO saveSkinColor(PhysicalAppearanceDTO skinColor)
    {
        return physicalAppearanceService.saveSkinColor(skinColor);
    }

    /**
     * @see com.bogie.client.GameService#deleteRace(java.lang.Long)
     */
    public void deleteRace(Long raceId)
    {
        raceService.deleteRace(raceId);
    }

    /**
     * @see com.bogie.client.GameService#findAllRaces()
     */
    public List<Race> findAllRaces()
    {
        return null;
    }

    /**
     * @see com.bogie.client.GameService#findRaces(java.lang.Long)
     */
    public List<Race> findRaces(Long parentId)
    {
        return null;
    }

    /**
     * @see com.bogie.client.GameService#getRace(java.lang.Long)
     */
    public RaceDTO getRace(Long raceId)
    {
        return null;
    }

    /**
     * @see com.bogie.client.GameService#saveRace(com.bogie.common.lib.dto.RaceDTO)
     */
    public RaceDTO saveRace(RaceDTO race)
    {
        return null;
    }

    /**
     * @see com.bogie.client.GameService#deleteSkill(java.lang.Long)
     */
    public void deleteSkill(Long skillId)
    {
        skillService.deleteSkill(skillId);
    }

    /**
     * @see com.bogie.client.GameService#findAllSkills()
     */
    public List<Skill> findAllSkills()
    {
        return skillService.findAllSkills();
    }

    /**
     * @see com.bogie.client.GameService#findSkills(java.lang.Long)
     */
    public List<Skill> findSkills(Long parentId)
    {
        return skillService.findSkills(parentId);
    }

    /**
     * @see com.bogie.client.GameService#getSkill(java.lang.Long)
     */
    public SkillDTO getSkill(Long skillId)
    {
        return skillService.getSkill(skillId).getDTO();
    }

    /**
     * @see com.bogie.client.GameService#saveSkill(com.bogie.common.lib.dto.SkillDTO)
     */
    public SkillDTO saveSkill(SkillDTO skill)
    {
        return skillService.saveSkill(skill);
    }
}
