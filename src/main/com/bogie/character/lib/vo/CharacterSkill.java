/**
 * CharacterSkill.java
 */
package com.bogie.character.lib.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bogie.common.lib.vo.Stat;
import com.bogie.skill.lib.vo.Skill;

/**
 * CharacterSkill 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Entity
@Table(name="CHARACTER_SKILL")
public class CharacterSkill implements Serializable
{
	private static final long serialVersionUID = 4481538481575266506L;

	@Id
    @GeneratedValue
    @Column(nullable=false)
    private int id;
    
    @ManyToOne(optional=false)
    private CharacterBase   character;

    @ManyToOne
    private Skill  skill;
    
    @Column(nullable=false)
    private int baseCost;
    
    @Column(nullable=false)
    private int levelCost;
    
    @ManyToOne(optional=false)
    private Stat primaryStat;
    
    @OneToMany
    private Set<Stat>   secondaryStats = new HashSet<Stat>();
    
    @Column(nullable=false)
    private int level;
    
    /**
     * Default constructor
     */
    public CharacterSkill()
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
     * @return the skill
     */
    public Skill getSkill()
    {
        return skill;
    }

    /**
     * @param skill the skill to set
     */
    public void setSkill(Skill skill)
    {
        this.skill = skill;
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
     * @return the primaryStat
     */
    public Stat getPrimaryStat()
    {
        return primaryStat;
    }

    /**
     * @param primaryStat the primaryStat to set
     */
    public void setPrimaryStat(Stat primaryStat)
    {
        this.primaryStat = primaryStat;
    }

    /**
     * @return the secondaryStats
     */
    public Set<Stat> getSecondaryStats()
    {
        return secondaryStats;
    }

    /**
     * @param secondaryStats the secondaryStats to set
     */
    public void setSecondaryStats(Set<Stat> secondaryStats)
    {
        this.secondaryStats = secondaryStats;
    }

    /**
     * @return the level
     */
    public int getLevel()
    {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level)
    {
        this.level = level;
    }
    
}
