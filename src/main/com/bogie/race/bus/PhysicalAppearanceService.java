/**
 * PhysicalAppearanceService.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.race.bus;

import java.util.List;

import com.bogie.common.lib.dto.PhysicalAppearanceDTO;
import com.bogie.common.lib.vo.Complexion;
import com.bogie.common.lib.vo.EyeColor;
import com.bogie.common.lib.vo.Gender;
import com.bogie.common.lib.vo.HairColor;
import com.bogie.common.lib.vo.SkinColor;

/**
 * PhysicalAppearanceService 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public interface PhysicalAppearanceService
{
    Gender getGender(Long genderId);
    PhysicalAppearanceDTO saveGender(PhysicalAppearanceDTO gender);
    Gender saveGender(Gender gender);
    void deleteGender(Long genderId);
    void deleteGender(Gender gender);
    List<Gender> findGenders();
    
    SkinColor getSkinColor(Long skinColorId);
    PhysicalAppearanceDTO saveSkinColor(PhysicalAppearanceDTO skinColor);
    SkinColor saveSkinColor(SkinColor skinColor);
    void deleteSkinColor(Long skinColorId);
    void deleteSkinColor(SkinColor skinColor);
    List<SkinColor> findSkinColors();

    HairColor getHairColor(Long hairColorId);
    PhysicalAppearanceDTO saveHairColor(PhysicalAppearanceDTO hairColor);
    HairColor saveHairColor(HairColor hairColor);
    void deleteHairColor(Long hairColorId);
    void deleteHairColor(HairColor hairColor);
    List<HairColor> findHairColors();

    EyeColor getEyeColor(Long eyeColorId);
    PhysicalAppearanceDTO saveEyeColor(PhysicalAppearanceDTO eyeColor);
    EyeColor saveEyeColor(EyeColor eyeColor);
    void deleteEyeColor(Long eyeColorId);
    void deleteEyeColor(EyeColor eyeColor);
    List<EyeColor> findEyeColors();

    Complexion getComplexion(Long complexionId);
    PhysicalAppearanceDTO saveComplexion(PhysicalAppearanceDTO complexion);
    Complexion saveComplexion(Complexion complexion);
    void deleteComplexion(Long complexionId);
    void deleteComplexion(Complexion complexion);
    List<Complexion> findComplexions();
}
