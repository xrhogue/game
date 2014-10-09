/**
 * Stat.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.common.lib.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.bogie.common.lib.dto.StatDTO;

/**
 * Stat 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Entity
@Table(name="STAT")
public class Stat implements Serializable
{
	private static final long serialVersionUID = -3308122051355560773L;

	@Id
    @GeneratedValue
    @Column(nullable=false)
    private Long id;
    
    @Version
    @Column
    private Long version;
    
    @Column
    private char code;
    
    @Column
    private String  shortForm;
    
    @Column
    private String  longForm;
    
    @Column
    private int multiplier;
    
    /**
     * Default constructor
     */
    public Stat()
    {
    }

    /**
     * Default constructor
     * @param stat the stat DTO
     */
    public Stat(StatDTO stat)
    {
        update(stat);
    }
    
    /**
     * Updates the stat with the DTO info
     * @param stat the update info
     * @return itself
     */
    public Stat update(StatDTO stat)
    {
        code = stat.getCode();
        shortForm = stat.getShortForm();
        longForm = stat.getLongForm();
        multiplier = stat.getMultiplier();
        
        return this;
    }
    
    /**
     * @return the version
     */
    public Long getVersion()
    {
        return version;
    }

    /**
     * @return the code
     */
    public char getCode()
    {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(char code)
    {
        this.code = code;
    }

    /**
     * @return the id
     */
    public long getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * @return the longForm
     */
    public String getLongForm()
    {
        return longForm;
    }

    /**
     * @param longForm the longForm to set
     */
    public void setLongForm(String longForm)
    {
        this.longForm = longForm;
    }

    /**
     * @return the shortForm
     */
    public String getShortForm()
    {
        return shortForm;
    }

    /**
     * @param shortForm the shortForm to set
     */
    public void setShortForm(String shortForm)
    {
        this.shortForm = shortForm;
    }

    /**
     * @return the multiplier
     */
    public int getMultiplier()
    {
        return multiplier;
    }

    /**
     * @param multiplier the multiplier to set
     */
    public void setMultiplier(int multiplier)
    {
        this.multiplier = multiplier;
    }
    
    /**
     * @return the stat DTO
     */
    public StatDTO getDTO()
    {
        StatDTO stat = new StatDTO();
        
        stat.setId(getId());
        stat.setVersion(getVersion());
        stat.setCode(getCode());
        stat.setLongForm(getLongForm());
        stat.setShortForm(getShortForm());
        stat.setMultiplier(getMultiplier());
        
        return stat;
    }
}
