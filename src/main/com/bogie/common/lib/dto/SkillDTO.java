/**
 * SkillDTO.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.common.lib.dto;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * SkillDTO 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class SkillDTO implements IsSerializable
{
    private Long    	id;
    private Long    	version;
    private String  	name;
    private String  	shortName;
    private Long    	parentId;
    private Long    	raceId;
    private boolean 	selectable = true;
    private int    		baseCost = 3;
    private int     	levelCost = 3;
    private Long    	primaryStatId;
    private Set<Long>   children = new HashSet<Long>();
    private Set<Long>   secondaryStats = new HashSet<Long>();
    private Set<Long>   prerequisites = new HashSet<Long>();
    
    /**
     * Default constructor
     */
    public SkillDTO()
    {
        super();
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
     * @return the baseCost
     */
    public int getBaseCost()
    {
        return baseCost;
    }

    /**
     * @param baseCost the baseCost to set
     */
    public void setBaseCost(int baseCost)
    {
        this.baseCost = baseCost;
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
     * @return the levelCost
     */
    public int getLevelCost()
    {
        return levelCost;
    }

    /**
     * @param levelCost the levelCost to set
     */
    public void setLevelCost(int levelCost)
    {
        this.levelCost = levelCost;
    }

    /**
     * @return the selectable
     */
    public boolean isSelectable()
    {
        return selectable;
    }

    /**
     * @param selectable the selectable to set
     */
    public void setSelectable(boolean selectable)
    {
        this.selectable = selectable;
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

    /**
     * @return the parentId
     */
    public Long getParentId()
    {
        return parentId;
    }

    /**
     * @param parentId the parentId to set
     */
    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    /**
     * @return the raceId
     */
    public Long getRaceId()
    {
        return raceId;
    }

    /**
     * @param raceId the raceId to set
     */
    public void setRaceId(Long raceId)
    {
        this.raceId = raceId;
    }

    /**
     * @return the primaryStat
     */
    public Long getPrimaryStatId()
    {
        return primaryStatId;
    }

    /**
     * @param primaryStatId the primaryStatId to set
     */
    public void setPrimaryStatId(Long primaryStatId)
    {
        this.primaryStatId = primaryStatId;
    }

    /**
     * @return the secondaryStats
     */
    public Set<Long> getSecondaryStats()
    {
        return secondaryStats;
    }

    /**
     * @param secondaryStats the secondaryStats to set
     */
    public void setSecondaryStats(Set<Long> secondaryStats)
    {
        this.secondaryStats = secondaryStats;
    }

    /**
     * @param secondaryStatId the secondaryStatId to add
     */
    public void addSecondaryStatId(Long secondaryStatId)
    {
        secondaryStats.add(secondaryStatId);
    }

    /**
     * @return the shortName
     */
    public String getShortName()
    {
        return shortName;
    }

    /**
     * @param shortName the shortName to set
     */
    public void setShortName(String shortName)
    {
        this.shortName = shortName;
    }

    /**
     * @return the children
     */
    public Set<Long> getChildren()
    {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(Set<Long> children)
    {
        this.children = children;
    }

    /**
     * @param childId the child to add
     */
    public void addChildId(Long childId)
    {
        children.add(childId);
    }

    /**
     * @return the prerequisites
     */
    public Set<Long> getPrerequisites()
    {
        return prerequisites;
    }

    /**
     * @param prerequisites the prerequisites to set
     */
    public void setPrerequisites(Set<Long> prerequisites)
    {
        this.prerequisites = prerequisites;
    }
    
    /**
     * @param prerequisiteId the prerequisiteId to add
     */
    public void addPrerequisiteId(Long prerequisiteId)
    {
        prerequisites.add(prerequisiteId);
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return getName();
    }
}
