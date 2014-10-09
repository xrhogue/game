/**
 * AdminPanel.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.client;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabPanel;

/**
 * AdminPanel 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class AdminPanel extends TabPanel
{
    public AdminPanel()
    {
        add(new StatPanel(), "Stats");
        add(new RacePanel(), "Race");
        add(new SkillPanel(), "Skills");
        add(new Label("Magic"), "Magic");
    }
}
