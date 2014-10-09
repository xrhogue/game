package com.bogie.client;

import java.util.List;

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
import com.bogie.race.lib.vo.Race;
import com.bogie.skill.lib.vo.Skill;
import com.google.gwt.user.client.rpc.RemoteService;

public interface GameService extends RemoteService
{
    StatDTO saveStat(StatDTO stat);
    void deleteStat(StatDTO stat);
    List<Stat> findStats();

    PhysicalAppearanceDTO getGender(Long genderId);
    PhysicalAppearanceDTO saveGender(PhysicalAppearanceDTO gender);
    void deleteGender(Long genderId);
    List<Gender> findGenders();
    
    PhysicalAppearanceDTO getSkinColor(Long skinColorId);
    PhysicalAppearanceDTO saveSkinColor(PhysicalAppearanceDTO skinColor);
    void deleteSkinColor(Long skinColorId);
    List<SkinColor> findSkinColors();

    PhysicalAppearanceDTO getHairColor(Long hairColorId);
    PhysicalAppearanceDTO saveHairColor(PhysicalAppearanceDTO hairColor);
    void deleteHairColor(Long hairColorId);
    List<HairColor> findHairColors();

    PhysicalAppearanceDTO getEyeColor(Long eyeColorId);
    PhysicalAppearanceDTO saveEyeColor(PhysicalAppearanceDTO eyeColor);
    void deleteEyeColor(Long eyeColorId);
    List<EyeColor> findEyeColors();

    PhysicalAppearanceDTO getComplexion(Long complexionId);
    PhysicalAppearanceDTO saveComplexion(PhysicalAppearanceDTO complexion);
    void deleteComplexion(Long complexionId);
    List<Complexion> findComplexions();

    RaceDTO getRace(Long raceId);
    RaceDTO saveRace(RaceDTO race);
    void deleteRace(Long raceId);
    List<Race> findRaces(Long parentId);
    List<Race> findAllRaces();

    SkillDTO getSkill(Long skillId);
    SkillDTO saveSkill(SkillDTO skill);
    void deleteSkill(Long skillId);
    List<Skill> findSkills(Long parentId);
    List<Skill> findAllSkills();
}
