/**
 * StatDTO.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.common.lib.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * StatDTO 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class StatDTO implements IsSerializable
{
    private Long    id;
    private Long    version;
    private char    code;
    private String  shortForm;
    private String  longForm;
    private int     multiplier;
    
    /**
     * Default constructor
     */
    public StatDTO()
    {
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
    public Long getId()
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
     * @return the version
     */
    public Long getVersion()
    {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(Long version)
    {
        this.version = version;
    }
}
