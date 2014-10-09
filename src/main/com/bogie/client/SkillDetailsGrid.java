/**
 * SkillDetailsGrid.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.client;

import java.util.Iterator;
import java.util.List;

import com.bogie.common.lib.dto.SkillDTO;
import com.bogie.common.lib.dto.StatDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

/**
 * SkillDetailsGrid 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class SkillDetailsGrid extends Grid
{
    private TextBox     nameTextBox = new TextBox();
    private TextBox     shortNameTextBox = new TextBox();
    private CheckBox    selectableCheckBox = new CheckBox();
    private TextBox     baseCostTextBox = new TextBox();
    private TextBox     levelCostTextBox = new TextBox();
    private ListBox     primaryStatCB = new ListBox();
    private ListBox     secondaryStatList = new ListBox();
    
    private GameServiceAsync    gameService = (GameServiceAsync)GWT.create(GameService.class);

    /**
     * Default constructor
     * @param skill the skill to initialize with
     */
    public SkillDetailsGrid(SkillDTO skill)
    {
        super(7, 2);
        
        ((ServiceDefTarget)gameService).setServiceEntryPoint(GWT.getModuleBaseURL() + "services/gameService");

        setWidget(0, 0, new Label("Name: "));
        setWidget(1, 0, new Label("Short Name:"));
        setWidget(2, 0, new Label("Selectable:"));
        setWidget(3, 0, new Label("Base Cost:"));
        setWidget(4, 0, new Label("Level Cost:"));
        setWidget(5, 0, new Label("Primary Stat: "));
        setWidget(6, 0, new Label("Secondary Stats: "));
        
        setWidget(0, 1, nameTextBox);
        setWidget(1, 1, shortNameTextBox);
        setWidget(2, 1, selectableCheckBox);
        setWidget(3, 1, baseCostTextBox);
        setWidget(4, 1, levelCostTextBox);
        setWidget(5, 1, primaryStatCB);
        setWidget(6, 1, secondaryStatList);
        
        initStats(skill);
        setSkill(skill);
    }
    
    private void initStats(final SkillDTO skill)
    {
        primaryStatCB.setVisibleItemCount(1); // for CB functionality
        secondaryStatList.setVisibleItemCount(3);

        gameService.findStats(new AsyncCallback()
        {
            /**
             * @see com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.Throwable)
             */
            public void onFailure(Throwable caught)
            {
                caught.printStackTrace();
            }

            /**
             * @see com.google.gwt.user.client.rpc.AsyncCallback#onSuccess(java.lang.Object)
             */
            @SuppressWarnings("unchecked")
			public void onSuccess(Object result)
            {
                List<StatDTO>	stats = (List<StatDTO>)result;
                
                for (StatDTO stat : stats)
                {                    
                    primaryStatCB.addItem(stat.getShortForm(), stat.getId().toString());
                    secondaryStatList.addItem(stat.getShortForm(), stat.getId().toString());
                }
                
                if (skill != null)
                {
                    for (int i = 0; i < primaryStatCB.getItemCount(); i++)
                    {
                        if (primaryStatCB.getValue(i).equals(skill.getPrimaryStatId().toString()))
                        {
                            primaryStatCB.setSelectedIndex(i);
                            break;
                        }
                    }
                    
                    Iterator<Long>    secondaryStatsIterator = skill.getSecondaryStats().iterator();
                    
                    while(secondaryStatsIterator.hasNext())
                    {
                        Long    secondaryStatId = secondaryStatsIterator.next();
                        
                        for (int i = 0; i < secondaryStatList.getItemCount(); i++)
                        {
                            if (secondaryStatList.getValue(i).equals(secondaryStatId.toString()))
                            {
                                secondaryStatList.setItemSelected(i, true);
                                break;
                            }
                        }
                    }
                }
            }
        });
    }
    
    public void setSkill(SkillDTO skill)
    {
        nameTextBox.setText("");
        shortNameTextBox.setText("");
        selectableCheckBox.setChecked(true);
        baseCostTextBox.setText("");
        levelCostTextBox.setText("");
        primaryStatCB.setSelectedIndex(0);
        secondaryStatList.setSelectedIndex(-1);
        secondaryStatList.setMultipleSelect(true);
        
        if (skill != null)
        {
            nameTextBox.setText(skill.getName());
            shortNameTextBox.setText(skill.getShortName());
            selectableCheckBox.setChecked(skill.isSelectable());
            baseCostTextBox.setText(new Integer(skill.getBaseCost()).toString());
            levelCostTextBox.setText(new Integer(skill.getBaseCost()).toString());
            
//            for (int i = 0; i < primaryStatCB.getItemCount(); i++)
//            {
//                if (primaryStatCB.getValue(i).equals(skill.getPrimaryStatId().toString()))
//                {
//                    primaryStatCB.setSelectedIndex(i);
//                    break;
//                }
//            }
//            
//            Iterator    secondaryStatsIterator = skill.getSecondaryStats().iterator();
//            
//            while(secondaryStatsIterator.hasNext())
//            {
//                Long    secondaryStatId = (Long)secondaryStatsIterator.next();
//                
//                for (int i = 0; i < secondaryStatList.getItemCount(); i++)
//                {
//                    if (secondaryStatList.getValue(i).equals(secondaryStatId.toString()))
//                    {
//                        secondaryStatList.setItemSelected(i, true);
//                        break;
//                    }
//                }
//            }
        }
    }
    
    public SkillDTO getSkill()
    {
        return getSkill(null, null);
    }
    
    public SkillDTO getSkill(Long id, Long version)
    {
        SkillDTO skill = new SkillDTO();
        
        skill.setId(id);
        skill.setVersion(version);
        skill.setName(nameTextBox.getText());
        skill.setShortName(shortNameTextBox.getText());
        skill.setSelectable(selectableCheckBox.isChecked());
        skill.setBaseCost(new Integer(baseCostTextBox.getText()).intValue());
        skill.setLevelCost(new Integer(levelCostTextBox.getText()).intValue());
        skill.setPrimaryStatId(new Long(primaryStatCB.getValue(primaryStatCB.getSelectedIndex())));
        skill.getSecondaryStats().clear();
        
        for (int i = 0; i < secondaryStatList.getItemCount(); i++)
        {
            if (secondaryStatList.isItemSelected(i))
            {
                skill.getSecondaryStats().add(new Long(secondaryStatList.getValue(i)));
            }
        }
        
        return skill;
    }
}
