package com.bogie.common.lib.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bogie.common.lib.dto.PhysicalAppearanceDTO;

/**
 * HairColor 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Entity
@Table(name="HAIR_COLOR")
public class HairColor extends PhysicalAppearance<HairColor> implements PhysicalAttribute, Serializable
{
	private static final long serialVersionUID = -7754051533487068528L;

	/**
     * Default constructor
     */
    public HairColor()
    {
    }

    /**
     * Default constructor
     * @param name
     */
    public HairColor(String name)
    {
        super(name);
    }

    /**
     * Default constructor
     * @param hairColor
     */
    public HairColor(PhysicalAppearanceDTO hairColor)
    {
        super(hairColor);
    }
}
