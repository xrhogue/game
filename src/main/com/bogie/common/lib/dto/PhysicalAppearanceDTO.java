/**
 * PhysicalAppearanceDTO.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.common.lib.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * PhysicalAppearanceDTO 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class PhysicalAppearanceDTO implements IsSerializable
{
    public static final int GENDER = 1;
    public static final int SKIN_COLOR = 2;
    public static final int EYE_COLOR = 3;
    public static final int HAIR_COLOR = 4;
    public static final int COMPLEXION = 5;
    
    private Long    id;
    private Long    version;
    private String  name;
    
    /**
     * Default constructor
     */
    public PhysicalAppearanceDTO()
    {
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

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }
}    
