/**
 * CharacterStat.java
 */
package com.bogie.character.lib.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bogie.common.lib.vo.Stat;

/**
 * CharacterStat 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Entity
@Table(name="CHARACTER_STAT")
public class CharacterStat implements Serializable
{
	private static final long serialVersionUID = 6331455076203219419L;

	@Id
    @GeneratedValue
    @Column(nullable=false)
    private int id;
    
    @ManyToOne(optional=false)
    private CharacterBase   character;
    
    @ManyToOne
    private Stat  stat;
    
    @Column(nullable=false)
    private int value;
    
    /**
     * Default constructor
     */
    public CharacterStat()
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
     * @return the stat
     */
    public Stat getStat()
    {
        return stat;
    }

    /**
     * @param stat the stat to set
     */
    public void setStat(Stat stat)
    {
        this.stat = stat;
    }

    /**
     * @return Returns the value.
     */
    public int getValue()
    {
        return value;
    }

    /**
     * @param value The value to set.
     */
    public void setValue(int value)
    {
        this.value = value;
    }
}
