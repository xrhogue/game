/**
 * RaceDTO.java
 */
package com.bogie.common.lib.dto;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * RaceDTO 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class RaceDTO implements IsSerializable
{
    private Long    		id;
    private Long    		version;
    private Long    		parentId;
    private String  		name;
    private boolean 		selectable;
    private Set<Long>		children = new HashSet<Long>();
    private Set<Long>     	skinColors = new HashSet<Long>();
    private Set<Long>     	hairColors = new HashSet<Long>();
    private Set<Long>     	eyeColors = new HashSet<Long>();
    private Set<Long>     	complexions = new HashSet<Long>();
    private Set<RaceStatDTO> raceStats = new HashSet<RaceStatDTO>();
    
    /**
     * Default constructor
     */
    public RaceDTO()
    {
        super();
    }

    /**
     * @return Returns the id.
     */
    public Long getId()
    {
        return id;
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
     * @param id The id to set.
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * @return Returns the parentId.
     */
    public Long getParentId()
    {
        return parentId;
    }

    /**
     * @param parentId The parentid to set.
     */
    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return Returns the selectable.
     */
    public boolean isSelectable()
    {
        return selectable;
    }

    /**
     * @param selectable The selectable to set.
     */
    public void setSelectable(boolean selectable)
    {
        this.selectable = selectable;
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
     * @param child the child to add
     */
    public void addChild(Long child)
    {
        children.add(child);
    }

    /**
     * @return the skinColors
     */
    public Set<Long> getSkinColors()
    {
        return skinColors;
    }

    /**
     * @param skinColors the skinColors to set
     */
    public void setSkinColors(Set<Long> skinColors)
    {
        this.skinColors = skinColors;
    }

    /**
     * @param skinColor the skinColor to add
     */
    public void addSkinColor(Long skinColor)
    {
        skinColors.add(skinColor);
    }

    /**
     * @return the hairColors
     */
    public Set<Long> getHairColors()
    {
        return hairColors;
    }

    /**
     * @param hairColors the hairColors to set
     */
    public void setHairColors(Set<Long> hairColors)
    {
        this.hairColors = hairColors;
    }

    /**
     * @param hairColor the hairColor to add
     */
    public void addHairColor(Long hairColor)
    {
        hairColors.add(hairColor);
    }

    /**
     * @return the eyeColors
     */
    public Set<Long> getEyeColors()
    {
        return eyeColors;
    }

    /**
     * @param eyeColors the eyeColors to set
     */
    public void setEyeColors(Set<Long> eyeColors)
    {
        this.eyeColors = eyeColors;
    }

    /**
     * @param eyeColor the eyeColor to add
     */
    public void addEyeColor(Long eyeColor)
    {
        eyeColors.add(eyeColor);
    }
    
    /**
     * @return the complexions
     */
    public Set<Long> getComplexions()
    {
        return complexions;
    }

    /**
     * @param complexions the complexions to set
     */
    public void setComplexions(Set<Long> complexions)
    {
        this.complexions = complexions;
    }

    /**
     * @param complexion the complexion to add
     */
    public void addComplexion(Long complexion)
    {
        complexions.add(complexion);
    }

    /**
     * @return the raceStats
     */
    public Set<RaceStatDTO> getRaceStats()
    {
        return raceStats;
    }

    /**
     * @param raceStats the raceStats to set
     */
    public void setRaceStats(Set<RaceStatDTO> raceStats)
    {
        this.raceStats = raceStats;
    }

    /**
     * @param raceStat the raceStat to add
     */
    public void addRaceStat(RaceStatDTO raceStat)
    {
        raceStats.add(raceStat);
    }
}
