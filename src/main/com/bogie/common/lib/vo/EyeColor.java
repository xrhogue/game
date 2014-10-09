package com.bogie.common.lib.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bogie.common.lib.dto.PhysicalAppearanceDTO;

/**
 * EyeColor 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Entity
@Table(name="EYE_COLOR")
public class EyeColor extends PhysicalAppearance<EyeColor> implements PhysicalAttribute, Serializable
{
	private static final long serialVersionUID = -166537206050585725L;

	/**
     * Default constructor
     */
    public EyeColor()
    {
    }

    /**
     * Default constructor
     * @param name
     */
    public EyeColor(String name)
    {
        super(name);
    }

    /**
     * Default constructor
     * @param eyeColor
     */
    public EyeColor(PhysicalAppearanceDTO eyeColor)
    {
        super(eyeColor);
    }
}
