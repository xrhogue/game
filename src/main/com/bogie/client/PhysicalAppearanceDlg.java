/**
 * PhysicalAppearanceDlg.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.client;

import com.bogie.common.lib.dto.PhysicalAppearanceDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * PhysicalAppearanceDlg 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class PhysicalAppearanceDlg extends PopupPanel
{
    private DockPanel                       dockPanel;
    private PhysicalAppearanceDetailsGrid   physicalAppearanceDetailsGrid;
    private Button                          okButton;
    private Button                          cancelButton;
    private Long                            id;
    private Long                            version;
    private PhysicalAppearanceDTO           newPhysicalAppearance;
    private int                             type;
    
    private GameServiceAsync                gameService = (GameServiceAsync)GWT.create(GameService.class);

    /**
     * Default constructor
     * @param physicalAppearance 
     * @param type the physical appearance type
     */
    public PhysicalAppearanceDlg(PhysicalAppearanceDTO physicalAppearance, int type)
    {
        this.type = type;
        
        HorizontalPanel buttonPanel = new HorizontalPanel();
        
        ((ServiceDefTarget)gameService).setServiceEntryPoint(GWT.getModuleBaseURL() + "services/gameService");
        
        newPhysicalAppearance = physicalAppearance;
        
        if (physicalAppearance != null)
        {
            id = physicalAppearance.getId();
            version = physicalAppearance.getVersion();
        }
        
        dockPanel = new DockPanel();
        physicalAppearanceDetailsGrid = new PhysicalAppearanceDetailsGrid(type);
        okButton = new Button("Add");
        cancelButton = new Button("Close");
        
        setTitle("Edit Stat");
        setWidget(dockPanel);

        okButton.addClickListener(new ClickListener()
        {
            /**
             * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
             */
            public void onClick(Widget sender)
            {
                switch (PhysicalAppearanceDlg.this.type)
                {
                    case PhysicalAppearanceDTO.GENDER:
                        gameService.saveGender(physicalAppearanceDetailsGrid.getPhysicalAppearance(id, version), new SavePhysicalAppearanceAsyncCallback());
                        break;
                    case PhysicalAppearanceDTO.SKIN_COLOR:
                        gameService.saveSkinColor(physicalAppearanceDetailsGrid.getPhysicalAppearance(id, version), new SavePhysicalAppearanceAsyncCallback());
                        break;
                    case PhysicalAppearanceDTO.HAIR_COLOR:
                        gameService.saveHairColor(physicalAppearanceDetailsGrid.getPhysicalAppearance(id, version), new SavePhysicalAppearanceAsyncCallback());
                        break;
                    case PhysicalAppearanceDTO.EYE_COLOR:
                        gameService.saveEyeColor(physicalAppearanceDetailsGrid.getPhysicalAppearance(id, version), new SavePhysicalAppearanceAsyncCallback());
                        break;
                    case PhysicalAppearanceDTO.COMPLEXION:
                        gameService.saveComplexion(physicalAppearanceDetailsGrid.getPhysicalAppearance(id, version), new SavePhysicalAppearanceAsyncCallback());
                        break;
                }
            }
        });
        
        cancelButton.addClickListener(new ClickListener()
        {
            /**
             * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
             */
            public void onClick(Widget sender)
            {
                PhysicalAppearanceDlg.this.hide();
            }
        });
        
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        
        dockPanel.add(physicalAppearanceDetailsGrid, DockPanel.CENTER);
        dockPanel.add(buttonPanel, DockPanel.SOUTH);
    }

    public PhysicalAppearanceDTO getPhysicalAppearance()
    {
        return newPhysicalAppearance;
    }
    
    private class SavePhysicalAppearanceAsyncCallback implements AsyncCallback
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
        public void onSuccess(Object result)
        {
            newPhysicalAppearance = (PhysicalAppearanceDTO)result;
            physicalAppearanceDetailsGrid.addPhysicalAppearance(newPhysicalAppearance.getName());
            //PhysicalAppearanceDlg.this.hide();
        }
    }

}
