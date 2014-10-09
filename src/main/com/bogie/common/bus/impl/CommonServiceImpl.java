/**
 * CommonServiceImpl.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.common.bus.impl;

import java.util.List;

import org.hibernate.StaleObjectStateException;

import com.bogie.common.bus.CommonService;
import com.bogie.common.dao.GenericDao;
import com.bogie.common.lib.dto.StatDTO;
import com.bogie.common.lib.vo.Stat;

/**
 * CommonServiceImpl 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@org.springframework.transaction.annotation.Transactional
public class CommonServiceImpl implements CommonService
{
    GenericDao<Long, Stat>   statDao;
    
    public void setStatDao(GenericDao<Long, Stat> x) { statDao = x; }
    
    /**
     * Default constructor
     */
    public CommonServiceImpl()
    {
    }

    /**
     * @see com.bogie.common.bus.CommonService#getStat(java.lang.Long)
     */
    public Stat getStat(Long id)
    {
        return statDao.get(Stat.class, id);
    }

    /**
     * @see com.bogie.common.bus.CommonService#deleteStat(java.lang.Long)
     */
    public void deleteStat(Long id)
    {
        statDao.delete(getStat(id));
    }

    /**
     * @see com.bogie.common.bus.CommonService#deleteStat(com.bogie.common.lib.vo.Stat)
     */
    public void deleteStat(Stat stat)
    {
        statDao.delete(stat);
    }

    /**
     * @see com.bogie.common.bus.CommonService#findStats()
     */
    public List<Stat> findStats()
    {
        return statDao.find("from Stat");
    }

    /**
     * @see com.bogie.common.bus.CommonService#saveStat(com.bogie.common.lib.vo.Stat)
     */
    public Stat saveStat(Stat stat)
    {
        statDao.saveOrUpdate(stat);
        
        return stat;
    }

    /**
     * @see com.bogie.common.bus.CommonService#saveStat(com.bogie.common.lib.dto.StatDTO)
     */
    public StatDTO saveStat(StatDTO stat)
    {
        if (stat.getId() != null)
        {
            Stat    mergeStat = statDao.get(Stat.class, stat.getId());
            
            if (mergeStat.getVersion().equals(stat.getVersion()))
            {
                mergeStat = mergeStat.update(stat);
                
                statDao.saveOrUpdate(mergeStat);
            }
            else
            {
                throw new StaleObjectStateException("Stat has been updated already!", mergeStat);
            }
            
            return mergeStat.getDTO();
        }

        return saveStat(new Stat(stat)).getDTO();
    }
}
