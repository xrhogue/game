/**
 * CommonServiceTest.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.common.bus;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.orm.hibernate3.HibernateOptimisticLockingFailureException;

import com.bogie.common.CommonTestCase;
import com.bogie.common.bus.CommonService;
import com.bogie.common.lib.vo.Stat;

import static org.junit.Assert.*;

public class CommonServiceTest extends CommonTestCase
{
    CommonService   commonService;
    
    @Before
    public void init()
    {
        commonService = (CommonService)applicationContext.getBean("commonService");
    }
    
    @Test
    public void testSaveStat()
    {
        Stat    stat = createStat("stat");
        
        commonService.saveStat(stat);
        
        List<Stat>  stats = commonService.findStats();
        
        assertTrue(stats.size() == 1);
        
        commonService.deleteStat(stat);
    }
    
    @Test
    public void testDeleteStat()
    {
        Stat   stat = createStat("stat");
        
        commonService.saveStat(stat);
        
        List<Stat>  stats = commonService.findStats();
        
        assertTrue(stats.size() == 1);
        
        commonService.deleteStat(stat);

        stats = commonService.findStats();
        
        assertTrue(stats.size() == 0);
    }

    @Test
    public void testFindStats()
    {
        Stat   stat = createStat("stat");
        
        commonService.saveStat(stat);
        
        List<Stat>  stats = commonService.findStats();

        assertTrue(stats.size() == 1);
        assertEquals(stats.get(0).getId(), stat.getId());
        
        stats = commonService.findStats();

        assertTrue(stats.size() == 1);
        
        commonService.deleteStat(stat);
    }

    @Test
    public void testStaleStat()
    {
        Stat   stat = createStat("stat");
        
        commonService.saveStat(stat);
        
        List<Stat>  stats = commonService.findStats();

        assertTrue(stats.size() == 1);
        assertEquals(stats.get(0).getId(), stat.getId());
        
        Stat    statVersion1 = commonService.getStat(stat.getId());
        Stat    statModifyVersion1 = commonService.getStat(stat.getId());
        
        statModifyVersion1.setLongForm(statModifyVersion1.getLongForm() + " modified");
        
        commonService.saveStat(statModifyVersion1);
        
        statVersion1.setLongForm(statVersion1.getLongForm() + "failure");
        
        try
        {
            commonService.saveStat(statVersion1);
            fail("should have thrown an error here");
        }
        catch (HibernateOptimisticLockingFailureException e)
        {
            // success!
        }
        
        commonService.deleteStat(statModifyVersion1);
    }

    private Stat createStat(String statName)
    {
        Stat    stat = new Stat();

        stat.setCode('P');
        stat.setShortForm("PRI");
        stat.setLongForm(statName);
        stat.setMultiplier(1);
        
        return stat;
    }
}
