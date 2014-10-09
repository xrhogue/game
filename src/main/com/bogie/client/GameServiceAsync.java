package com.bogie.client;

import com.bogie.common.lib.dto.PhysicalAppearanceDTO;
import com.bogie.common.lib.dto.RaceDTO;
import com.bogie.common.lib.dto.SkillDTO;
import com.bogie.common.lib.dto.StatDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GameServiceAsync
{
    void saveStat(StatDTO stat, AsyncCallback callback);
    void deleteStat(StatDTO stat, AsyncCallback callback);
    void findStats(AsyncCallback callback);
    
    void getGender(Long genderId, AsyncCallback callback);
    void saveGender(PhysicalAppearanceDTO gender, AsyncCallback callback);
    void deleteGender(Long genderId, AsyncCallback callback);
    void findGenders(AsyncCallback callback);
    
    void getSkinColor(Long skinColorId, AsyncCallback callback);
    void saveSkinColor(PhysicalAppearanceDTO skinColor, AsyncCallback callback);
    void deleteSkinColor(Long skinColorId, AsyncCallback callback);
    void findSkinColors(AsyncCallback callback);

    void getHairColor(Long hairColorId, AsyncCallback callback);
    void saveHairColor(PhysicalAppearanceDTO hairColor, AsyncCallback callback);
    void deleteHairColor(Long hairColorId, AsyncCallback callback);
    void findHairColors(AsyncCallback callback);

    void getEyeColor(Long eyeColorId, AsyncCallback callback);
    void saveEyeColor(PhysicalAppearanceDTO eyeColor, AsyncCallback callback);
    void deleteEyeColor(Long eyeColorId, AsyncCallback callback);
    void findEyeColors(AsyncCallback callback);

    void getComplexion(Long complexionId, AsyncCallback callback);
    void saveComplexion(PhysicalAppearanceDTO complexion, AsyncCallback callback);
    void deleteComplexion(Long complexionId, AsyncCallback callback);
    void findComplexions(AsyncCallback callback);

    void getRace(Long raceId, AsyncCallback callback);
    void saveRace(RaceDTO race, AsyncCallback callback);
    void deleteRace(Long raceId, AsyncCallback callback);
    void findRaces(Long parentId, AsyncCallback callback);
    void findAllRaces(AsyncCallback callback);
    
    void getSkill(Long skillId, AsyncCallback callback);
    void saveSkill(SkillDTO skill, AsyncCallback callback);
    void deleteSkill(Long skillId, AsyncCallback callback);
    void findSkills(Long parentId, AsyncCallback callback);
    void findAllSkills(AsyncCallback callback);
}
