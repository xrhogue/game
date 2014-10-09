/**
 * CharacterBase.java
 */
package com.bogie.character.lib.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bogie.common.lib.vo.Complexion;
import com.bogie.common.lib.vo.EyeColor;
import com.bogie.common.lib.vo.Gender;
import com.bogie.common.lib.vo.HairColor;
import com.bogie.common.lib.vo.SkinColor;

/**
 * CharacterBase 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Entity
@Table(name="CHARACTER_BASE")
public class CharacterBase implements Serializable
{
	private static final long serialVersionUID = 5282645033532061243L;

	@Id
    @GeneratedValue
    @Column(nullable=false)
    private int id;
    
    @Column(nullable=false)
    private String  name;
    
    @Column
    private String  player;
    
    @Column
    private int basePoints;
    
    @Column
    private int experienceEarned;
    
    @Column
    private int magicPoints;
    
    @Column
    private int hitPoints;
    
    @ManyToOne(optional=false)
    private Gender  gender;
    
    @ManyToOne(optional=false)
    private HairColor   hairColor;
    
    @ManyToOne(optional=false)
    private SkinColor   skinColor;
    
    @ManyToOne(optional=false)
    private EyeColor    eyeColor;
    
    @ManyToOne(optional=false)
    private Complexion  complexion;
    
    @Column
    private int age;
    
    @Column
    private int height;
    
    @Column
    private int weight;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="character")
    private Set<CharacterRace> races = new HashSet<CharacterRace>();
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="character")
    private Set<CharacterStat> stats = new HashSet<CharacterStat>();
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="character")
    private Set<CharacterSkill> skills = new HashSet<CharacterSkill>();
    
    /**
     * Default constructor
     */
    public CharacterBase()
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
     * @return Returns the basePoints.
     */
    public int getBasePoints()
    {
        return basePoints;
    }

    /**
     * @param basePoints The basePoints to set.
     */
    public void setBasePoints(int basePoints)
    {
        this.basePoints = basePoints;
    }

    /**
     * @return Returns the experienceEarned.
     */
    public int getExperienceEarned()
    {
        return experienceEarned;
    }

    /**
     * @param experienceEarned The experienceEarned to set.
     */
    public void setExperienceEarned(int experienceEarned)
    {
        this.experienceEarned = experienceEarned;
    }

    /**
     * @return Returns the hitPoints.
     */
    public int getHitPoints()
    {
        return hitPoints;
    }

    /**
     * @param hitPoints The hitPoints to set.
     */
    public void setHitPoints(int hitPoints)
    {
        this.hitPoints = hitPoints;
    }

    /**
     * @return Returns the magicPoints.
     */
    public int getMagicPoints()
    {
        return magicPoints;
    }

    /**
     * @param magicPoints The magicPoints to set.
     */
    public void setMagicPoints(int magicPoints)
    {
        this.magicPoints = magicPoints;
    }

    /**
     * @return Returns the player.
     */
    public String getPlayer()
    {
        return player;
    }

    /**
     * @param player The player to set.
     */
    public void setPlayer(String player)
    {
        this.player = player;
    }

    /**
     * @return the complexion
     */
    public Complexion getComplexion()
    {
        return complexion;
    }

    /**
     * @param complexion the complexion to set
     */
    public void setComplexion(Complexion complexion)
    {
        this.complexion = complexion;
    }

    /**
     * @return the eyeColor
     */
    public EyeColor getEyeColor()
    {
        return eyeColor;
    }

    /**
     * @param eyeColor the eyeColor to set
     */
    public void setEyeColor(EyeColor eyeColor)
    {
        this.eyeColor = eyeColor;
    }

    /**
     * @return the gender
     */
    public Gender getGender()
    {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    /**
     * @return the hairColor
     */
    public HairColor getHairColor()
    {
        return hairColor;
    }

    /**
     * @param hairColor the hairColor to set
     */
    public void setHairColor(HairColor hairColor)
    {
        this.hairColor = hairColor;
    }

    /**
     * @return the skinColor
     */
    public SkinColor getSkinColor()
    {
        return skinColor;
    }

    /**
     * @param skinColor the skinColor to set
     */
    public void setSkinColor(SkinColor skinColor)
    {
        this.skinColor = skinColor;
    }

    /**
     * @return Returns the age.
     */
    public int getAge()
    {
        return age;
    }

    /**
     * @param age The age to set.
     */
    public void setAge(int age)
    {
        this.age = age;
    }

    /**
     * @return Returns the height.
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * @param height The height to set.
     */
    public void setHeight(int height)
    {
        this.height = height;
    }

    /**
     * @return Returns the weight.
     */
    public int getWeight()
    {
        return weight;
    }

    /**
     * @param weight The weight to set.
     */
    public void setWeight(int weight)
    {
        this.weight = weight;
    }

    /**
     * @return the races
     */
    public Set<CharacterRace> getRaces()
    {
        return races;
    }

    /**
     * @param races the races to set
     */
    public void setRaces(Set<CharacterRace> races)
    {
        this.races = races;
    }

    /**
     * @return the stats
     */
    public Set<CharacterStat> getStats()
    {
        return stats;
    }

    /**
     * @param stats the stats to set
     */
    public void setStats(Set<CharacterStat> stats)
    {
        this.stats = stats;
    }

    /**
     * @return the skills
     */
    public Set<CharacterSkill> getSkills()
    {
        return skills;
    }

    /**
     * @param skills the skills to set
     */
    public void setSkills(Set<CharacterSkill> skills)
    {
        this.skills = skills;
    }
}
