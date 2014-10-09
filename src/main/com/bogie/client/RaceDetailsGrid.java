/**
 * RaceDetailsGrid.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.client;

import com.bogie.common.lib.dto.RaceDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

/**
 * RaceDetailsGrid 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class RaceDetailsGrid extends Grid
{
    private TextBox     nameTextBox = new TextBox();
    private CheckBox    selectableCheckBox = new CheckBox();
    
    private GameServiceAsync    gameService = (GameServiceAsync)GWT.create(GameService.class);

    /**
     * Default constructor
     * @param race the race to initialize with
     */
    public RaceDetailsGrid(RaceDTO race)
    {
        super(7, 2);
        
        ((ServiceDefTarget)gameService).setServiceEntryPoint(GWT.getModuleBaseURL() + "services/gameService");

        setWidget(0, 0, new Label("Name: "));
        setWidget(2, 0, new Label("Selectable:"));
        
        setWidget(0, 1, nameTextBox);
        setWidget(2, 1, selectableCheckBox);
        
        setRace(race);
    }
    
    public void setRace(RaceDTO race)
    {
        nameTextBox.setText("");
        selectableCheckBox.setChecked(true);
        
        if (race != null)
        {
            nameTextBox.setText(race.getName());
            selectableCheckBox.setChecked(race.isSelectable());
        }
    }
    
    public RaceDTO getRace()
    {
        return getRace(null, null);
    }
    
    public RaceDTO getRace(Long id, Long version)
    {
        RaceDTO race = new RaceDTO();
        
        race.setId(id);
        race.setVersion(version);
        race.setName(nameTextBox.getText());
        race.setSelectable(selectableCheckBox.isChecked());
        
        return race;
    }
}
