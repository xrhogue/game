/**
 * CharacterRace.java
 */
package com.bogie.character.lib.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bogie.race.lib.vo.Race;

/**
 * CharacterRace 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Entity
@Table(name="CHARACTER_RACE")
public class CharacterRace implements Serializable
{
	private static final long serialVersionUID = -3933335861483917500L;

	@Id
    @GeneratedValue
    @Column
    private int id;
    
    @ManyToOne(optional=false)
    private CharacterBase   character;
    
    @ManyToOne(optional=false)
    private Race    race;
    
    @Column
    private int percent;
    
    /**
     * Default constructor
     */
    public CharacterRace()
    {
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @return the percent
     */
    public int getPercent()
    {
        return percent;
    }

    /**
     * @param percent the percent to set
     */
    public void setPercent(int percent)
    {
        this.percent = percent;
    }

    /**
     * @return the character
     */
    public CharacterBase getCharacter()
    {
        return character;
    }

    /**
     * @param character the character to set
     */
    public void setCharacter(CharacterBase character)
    {
        this.character = character;
    }

    /**
     * @return the race
     */
    public Race getRace()
    {
        return race;
    }

    /**
     * @param race the race to set
     */
    public void setRace(Race race)
    {
        this.race = race;
    }

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return getRace().getName();
    }
}
