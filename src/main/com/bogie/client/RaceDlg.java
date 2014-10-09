/**
 * RaceDlg.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.client;

import com.bogie.common.lib.dto.RaceDTO;
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
 * RaceDlg 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class RaceDlg extends PopupPanel
{
    private DockPanel       dockPanel;
    private RaceDetailsGrid raceDetailsGrid;
    private Button          okButton;
    private Button          cancelButton;
    private Long            id;
    private Long            version;
    private RaceDTO         newRace;
    
    private GameServiceAsync    gameService = (GameServiceAsync)GWT.create(GameService.class);

    /**
     * Default constructor
     * @param race 
     */
    public RaceDlg(RaceDTO race)
    {
        HorizontalPanel buttonPanel = new HorizontalPanel();
        
        ((ServiceDefTarget)gameService).setServiceEntryPoint(GWT.getModuleBaseURL() + "services/gameService");
        
        newRace = race;
        
        if (race != null)
        {
            id = race.getId();
            version = race.getVersion();
        }
        
        dockPanel = new DockPanel();
        raceDetailsGrid = new RaceDetailsGrid(race);
        okButton = new Button("OK");
        cancelButton = new Button("Cancel");
        
        setTitle("Edit Race");
        setWidget(dockPanel);

        okButton.addClickListener(new ClickListener()
        {
            /**
             * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
             */
            public void onClick(Widget sender)
            {
                gameService.saveRace(raceDetailsGrid.getRace(id, version), new AsyncCallback()
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
                        newRace = (RaceDTO)result;
                        RaceDlg.this.hide();
                    }
                });
            }
        });
        
        cancelButton.addClickListener(new ClickListener()
        {
            /**
             * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
             */
            public void onClick(Widget sender)
            {
                RaceDlg.this.hide();
            }
        });
        
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        
        dockPanel.add(raceDetailsGrid, DockPanel.CENTER);
        dockPanel.add(buttonPanel, DockPanel.SOUTH);
    }
    
    public RaceDTO getRace()
    {
        return newRace;
    }
}
