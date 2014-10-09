/**
 * PhysicalAppearanceServiceTest.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.race.bus;

import java.util.List;

import org.junit.Test;

import com.bogie.common.CommonTestCase;
import com.bogie.common.lib.vo.SkinColor;

public class PhysicalAppearanceServiceTest extends CommonTestCase
{
    @Test
    public void testSkinColors()
    {
        PhysicalAppearanceService   physicalAppearanceService = (PhysicalAppearanceService)applicationContext.getBean("physicalAppearanceService");
        SkinColor                   skinColor = new SkinColor();

        skinColor.setName("skinColor");
        
        physicalAppearanceService.saveSkinColor(skinColor);
        
        List<SkinColor>    skinColors = physicalAppearanceService.findSkinColors();
        
        for (SkinColor skinColorTmp : skinColors)
        {
            physicalAppearanceService.deleteSkinColor(skinColorTmp);
        }
    }
}
