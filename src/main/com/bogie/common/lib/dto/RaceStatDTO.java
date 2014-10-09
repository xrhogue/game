/**
 * RaceStatTO.java
 */
package com.bogie.common.lib.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * RaceStat 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class RaceStatDTO implements IsSerializable
{
    private int     id;
    private Long    genderId;
    private Long    raceId;
    private Long    statId;
    private int     minimum;
    private int     maximum;
    
    /**
     * Default constructor
     */
    public RaceStatDTO()
    {
    }
    
    /**
     * @return Returns the id.
     */
    public int getId()
    {
        return id;
    }

    /**
     * @param id The id to set.
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @return the genderId
     */
    public Long getGenderId()
    {
        return genderId;
    }

    /**
     * @param genderId the genderId to set
     */
    public void setGenderId(Long genderId)
    {
        this.genderId = genderId;
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
     * @return the statId
     */
    public Long getStatId()
    {
        return statId;
    }

    /**
     * @param statId the statId to set
     */
    public void setStatId(Long statId)
    {
        this.statId = statId;
    }

    /**
     * @return the maximum
     */
    public int getMaximum()
    {
        return maximum;
    }

    /**
     * @param maximum the maximum to set
     */
    public void setMaximum(int maximum)
    {
        this.maximum = maximum;
    }

    /**
     * @return the minimum
     */
    public int getMinimum()
    {
        return minimum;
    }

    /**
     * @param minimum the minimum to set
     */
    public void setMinimum(int minimum)
    {
        this.minimum = minimum;
    }
    
}
