/**
 * RacePanel.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.client;

import com.bogie.common.lib.dto.PhysicalAppearanceDTO;
import com.bogie.common.lib.dto.RaceDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PopupListener;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * RacePanel 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class RacePanel extends DockPanel
{
    private RaceTree    raceTree;
    private Button      newButton;
    private Button      editButton;
    private Button      deleteButton;
    private Button      editGenderButton;
    private Button      editSkinColorButton;
    private Button      editEyeColorButton;
    private Button      editHairColorButton;
    private Button      editComplexionButton;
    
    private GameServiceAsync    gameService = (GameServiceAsync)GWT.create(GameService.class);
    
    /**
     * Default constructor
     */
    public RacePanel()
    {
        HorizontalPanel buttonPanel = new HorizontalPanel();
        VerticalPanel   physicalAppearancePanel = new VerticalPanel();
        GameConstants   gameConstants = (GameConstants)GWT.create(GameConstants.class);

        ((ServiceDefTarget)gameService).setServiceEntryPoint(GWT.getModuleBaseURL() + "services/gameService");

        raceTree = new RaceTree();
        newButton = new Button(gameConstants.newLabel());
        editButton = new Button(gameConstants.edit());
        deleteButton = new Button(gameConstants.delete());
        
        buttonPanel.add(newButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        
        editGenderButton = new Button("Edit Gender...");
        editSkinColorButton = new Button("Edit Skin Color...");
        editEyeColorButton = new Button("Edit Eye Color...");
        editHairColorButton = new Button("Edit Hair Color...");
        editComplexionButton = new Button("Edit Complexion...");

        physicalAppearancePanel.add(editGenderButton);
        physicalAppearancePanel.add(editSkinColorButton);
        physicalAppearancePanel.add(editEyeColorButton);
        physicalAppearancePanel.add(editHairColorButton);
        physicalAppearancePanel.add(editComplexionButton);
        
        editGenderButton.addClickListener(new PhysicalAppearanceClickListener(PhysicalAppearanceDTO.GENDER));
        
        newButton.addClickListener(new ClickListener()
        {
            /**
             * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
             */
            public void onClick(Widget sender)
            {
                RaceDlg raceDlg = new RaceDlg(null);
                
                raceDlg.addPopupListener(new PopupListener()
                {
                    /**
                     * @see com.google.gwt.user.client.ui.PopupListener#onPopupClosed(com.google.gwt.user.client.ui.PopupPanel, boolean)
                     */
                    public void onPopupClosed(PopupPanel sender, boolean autoClosed)
                    {
                        RaceDTO     race = ((RaceDlg)sender).getRace();
                        TreeItem    raceTreeItem = new TreeItem(race.getName());
                        
                        raceTreeItem.setUserObject(race);
                        
                        if (raceTree.getSelectedRaceTreeItem() != null)
                        {
                            raceTree.getSelectedRaceTreeItem().addItem(raceTreeItem);
                        }
                        else
                        {
                            raceTree.getRootRaceTreeItem().addItem(raceTreeItem);
                        }
                    }
                });
                
                raceDlg.show();
            }
        });
        
        editButton.addClickListener(new ClickListener()
        {
            /**
             * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
             */
            public void onClick(Widget sender)
            {
                if (raceTree.getSelectedRaceTreeItem() == null)
                {
                    return;
                }
                
                RaceDlg raceDlg = new RaceDlg((RaceDTO)raceTree.getSelectedRaceTreeItem().getUserObject());
                
                raceDlg.addPopupListener(new PopupListener()
                {
                    /**
                     * @see com.google.gwt.user.client.ui.PopupListener#onPopupClosed(com.google.gwt.user.client.ui.PopupPanel, boolean)
                     */
                    public void onPopupClosed(PopupPanel sender, boolean autoClosed)
                    {
                        RaceDTO race = ((RaceDlg)sender).getRace();
                        
                        raceTree.getSelectedRaceTreeItem().setUserObject(race);
                        raceTree.getSelectedRaceTreeItem().setText(race.getName());
                    }
                });
                
                raceDlg.show();
            }
        });
        
        deleteButton.addClickListener(new ClickListener()
        {
            /**
             * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
             */
            public void onClick(Widget sender)
            {
                if (raceTree.getSelectedRaceTreeItem() == null)
                {
                    return;
                }
                
                gameService.deleteRace(((RaceDTO)raceTree.getSelectedRaceTreeItem().getUserObject()).getId(), new AsyncCallback()
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
                        raceTree.getSelectedRaceTreeItem().remove();
                    }
                });
            }
        });
        
        add(raceTree, DockPanel.CENTER);
        add(buttonPanel, DockPanel.SOUTH);
        add(physicalAppearancePanel, DockPanel.EAST);
    }
    
    private class PhysicalAppearanceClickListener implements ClickListener
    {
        private int type;
        
        public PhysicalAppearanceClickListener(int type)
        {
            this.type = type;
        }
        /**
         * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
         */
        public void onClick(Widget sender)
        {
            PhysicalAppearanceDlg   physicalAppearanceDlg = new PhysicalAppearanceDlg(null, type);
            
            physicalAppearanceDlg.addPopupListener(new PopupListener()
            {
                /**
                 * @see com.google.gwt.user.client.ui.PopupListener#onPopupClosed(com.google.gwt.user.client.ui.PopupPanel, boolean)
                 */
                public void onPopupClosed(PopupPanel sender, boolean autoClosed)
                {
                    //PhysicalAppearanceDTO   physicalAppearance = ((PhysicalAppearanceDlg)sender).getPhysicalAppearance();
                }
            });
            
            physicalAppearanceDlg.show();
        }
    }
}
