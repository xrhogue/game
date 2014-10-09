package com.bogie.common.lib.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bogie.common.lib.dto.PhysicalAppearanceDTO;

/**
 * Gender 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Entity
@Table(name="GENDER")
public class Gender extends PhysicalAppearance<Gender> implements PhysicalAttribute, Serializable
{
	private static final long serialVersionUID = -6686704240777294612L;

	/**
     * Default constructor
     */
    public Gender()
    {
    }

    /**
     * Default constructor
     * @param name the gender name
     */
    public Gender(String name)
    {
        super(name);
    }

    /**
     * Default constructor
     * @param gender
     */
    public Gender(PhysicalAppearanceDTO gender)
    {
        super(gender);
    }
}
