/**
 * CharacterPanel.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.client;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabPanel;

/**
 * CharacterPanel 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class CharacterPanel extends TabPanel
{
    public CharacterPanel()
    {
        add(new Label("Background"), "Background");
        add(new Label("Race"), "Race");
        add(new Label("Skills"), "Skills");
        add(new Label("Magic"), "Magic");
        
        
    }
}
