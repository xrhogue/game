package com.bogie.common.lib.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.bogie.common.lib.dto.PhysicalAppearanceDTO;

/**
 * PhysicalAppearance 
 * 
 * @author Richard Hogue
 * @version 1.0
 * @param <T> the type of physical appearance
 */
@MappedSuperclass
public class PhysicalAppearance<T> implements PhysicalAttribute, Serializable
{
	private static final long serialVersionUID = 4768861523162303083L;

	@Id
    @GeneratedValue
    @Column(nullable=false)
    private Long id;
    
    @Version
    @Column
    private Long version;
    
    @Column
    private String name;

    /**
     * Default constructor
     */
    public PhysicalAppearance()
    {
    }

    /**
     * Default constructor
     * @param physicalAppearance
     */
    public PhysicalAppearance(PhysicalAppearanceDTO physicalAppearance)
    {
        update(physicalAppearance);
    }
    
    /**
     * Default constructor
     * @param name
     */
    public PhysicalAppearance(String name)
    {
        this.name = name;
    }

    /**
     * Updates the physical appearance with the new data
     * @param physicalAppearance
     * @return the updated physical appearance
     */
    @SuppressWarnings("unchecked")
	public T update(PhysicalAppearanceDTO physicalAppearance)
    {
        setName(physicalAppearance.getName());
        
        return (T)this;
    }
    
    /**
     * @return Returns the id.
     */
    public Long getId()
    {
        return id;
    }

    /**
     * @param id The id to set.
     */
    public void setId(Long id)
    {
        this.id = id;
    }
    
    /**
     * @return the version
     */
    public Long getVersion()
    {
        return version;
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
    
    public PhysicalAppearanceDTO getDTO()
    {
        PhysicalAppearanceDTO   physicalAppearanceDTO = new PhysicalAppearanceDTO();
        
        physicalAppearanceDTO.setId(getId());
        physicalAppearanceDTO.setVersion(getVersion());
        physicalAppearanceDTO.setName(getName());
        
        return physicalAppearanceDTO;
    }
}
