package com.bogie.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GameApp implements EntryPoint
{
    /**
     * This is the entry point method.
     */
    public void onModuleLoad()
    {
        final GameServiceAsync	service = (GameServiceAsync)GWT.create(GameService.class);
        ServiceDefTarget        endpoint = (ServiceDefTarget)service;
        final TabPanel          gameTabPanel = new TabPanel();
        
        endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "/gameService");

        gameTabPanel.add(new CharacterPanel(), "Character");
        gameTabPanel.add(new AdminPanel(), "Admin");
        
        RootPanel.get("widget").add(gameTabPanel);
    }
}
