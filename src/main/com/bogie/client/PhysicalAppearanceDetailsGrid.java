/**
 * PhysicalAppearanceDetailsGrid.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.client;

import java.util.List;

import com.bogie.common.lib.dto.PhysicalAppearanceDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

/**
 * PhysicalAppearanceDetailsGrid 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class PhysicalAppearanceDetailsGrid extends Grid
{
    private ListBox physicalAppearanceListBox = new ListBox();
    private TextBox nameTextBox = new TextBox();
    
    private GameServiceAsync    gameService = (GameServiceAsync)GWT.create(GameService.class);

    public PhysicalAppearanceDetailsGrid(int type)
    {
        super(2, 2);
        
        ((ServiceDefTarget)gameService).setServiceEntryPoint(GWT.getModuleBaseURL() + "services/gameService");

        setWidget(0, 0, new Label("List: "));
        setWidget(1, 0, new Label("New Name: "));
        
        setWidget(0, 1, physicalAppearanceListBox);
        setWidget(1, 1, nameTextBox);
        
        setPhysicalAppearance(type);
    }
    
    private void setPhysicalAppearance(int type)
    {
        physicalAppearanceListBox.clear();
        
        nameTextBox.setText("");
        
        switch (type)
        {
            case PhysicalAppearanceDTO.GENDER:
                gameService.findGenders(new PhysicalAppearancesAsyncCallback());
                break;
            case PhysicalAppearanceDTO.SKIN_COLOR:
                gameService.findSkinColors(new PhysicalAppearancesAsyncCallback());
                break;
            case PhysicalAppearanceDTO.HAIR_COLOR:
                gameService.findHairColors(new PhysicalAppearancesAsyncCallback());
                break;
            case PhysicalAppearanceDTO.EYE_COLOR:
                gameService.findEyeColors(new PhysicalAppearancesAsyncCallback());
                break;
            case PhysicalAppearanceDTO.COMPLEXION:
                gameService.findComplexions(new PhysicalAppearancesAsyncCallback());
                break;
        }
    }
    
    public void addPhysicalAppearance(String physicalAppearance)
    {
        physicalAppearanceListBox.addItem(physicalAppearance);
    }
    
    private void setPhysicalAppearance(List<PhysicalAppearanceDTO> physicalAppearances)
    {
        if (physicalAppearances != null)
        {
            for (PhysicalAppearanceDTO physicalAppearance : physicalAppearances)
            {
                physicalAppearanceListBox.addItem(physicalAppearance.getName());
            }
            
            physicalAppearanceListBox.setVisibleItemCount(physicalAppearances.size());
        }
    }
    
    public PhysicalAppearanceDTO getPhysicalAppearance()
    {
        return getPhysicalAppearance(null, null);
    }
    
    public PhysicalAppearanceDTO getPhysicalAppearance(Long id, Long version)
    {
        PhysicalAppearanceDTO physicalAppearance = new PhysicalAppearanceDTO();
        
        physicalAppearance.setId(id);
        physicalAppearance.setVersion(version);
        physicalAppearance.setName(nameTextBox.getText());
        
        return physicalAppearance;
    }
    
    private class PhysicalAppearancesAsyncCallback implements AsyncCallback
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
            setPhysicalAppearance((List<PhysicalAppearanceDTO>)result);
        }
    }
}
