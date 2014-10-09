/**
 * StatDlg.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.client;

import com.bogie.common.lib.dto.StatDTO;
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
 * StatDlg 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class StatDlg extends PopupPanel
{
    private DockPanel           dockPanel;
    private StatDetailsGrid     statDetailsGrid;
    private Button              okButton;
    private Button              cancelButton;
    private Long                id;
    private Long                version;
    private StatDTO             newStat;
    
    private GameServiceAsync    gameService = (GameServiceAsync)GWT.create(GameService.class);

    /**
     * Default constructor
     * @param stat 
     */
    public StatDlg(StatDTO stat)
    {
        HorizontalPanel buttonPanel = new HorizontalPanel();
        
        ((ServiceDefTarget)gameService).setServiceEntryPoint(GWT.getModuleBaseURL() + "services/gameService");
        
        newStat = stat;
        
        if (stat != null)
        {
            id = stat.getId();
            version = stat.getVersion();
        }
        
        dockPanel = new DockPanel();
        statDetailsGrid = new StatDetailsGrid(stat);
        okButton = new Button("OK");
        cancelButton = new Button("Cancel");
        
        setTitle("Edit Stat");
        setWidget(dockPanel);

        okButton.addClickListener(new ClickListener()
        {
            /**
             * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
             */
            public void onClick(Widget sender)
            {
                gameService.saveStat(statDetailsGrid.getStat(id, version), new AsyncCallback()
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
                        newStat = (StatDTO)result;
                        StatDlg.this.hide();
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
                StatDlg.this.hide();
            }
        });
        
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        
        dockPanel.add(statDetailsGrid, DockPanel.CENTER);
        dockPanel.add(buttonPanel, DockPanel.SOUTH);
    }
    
    public StatDTO getStat()
    {
        return newStat;
    }
}
