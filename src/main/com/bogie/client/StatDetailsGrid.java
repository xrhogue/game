/**
 * StatDetailsGrid.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.client;

import com.bogie.common.lib.dto.StatDTO;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

/**
 * StatDetailsGrid 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class StatDetailsGrid extends Grid
{
    private TextBox codeTextBox = new TextBox();
    private TextBox shortFormTextBox = new TextBox();
    private TextBox longFormTextBox = new TextBox();
    private TextBox multiplierTextBox = new TextBox();
    
    public StatDetailsGrid()
    {
        this(null);
    }
    
    public StatDetailsGrid(StatDTO stat)
    {
        super(4, 2);
        
        setWidget(0, 0, new Label("Code: "));
        setWidget(1, 0, new Label("Short Name: "));
        setWidget(2, 0, new Label("Long Name: "));
        setWidget(3, 0, new Label("Multiplier: "));
        
        setWidget(0, 1, codeTextBox);
        setWidget(1, 1, shortFormTextBox);
        setWidget(2, 1, longFormTextBox);
        setWidget(3, 1, multiplierTextBox);
        
        setStat(stat);
    }
    
    public void setStat(StatDTO stat)
    {
        if (stat == null)
        {
            codeTextBox.setText("");
            shortFormTextBox.setText("");
            longFormTextBox.setText("");
            multiplierTextBox.setText("");
        }
        else
        {
            codeTextBox.setText(Character.toString(stat.getCode()));
            shortFormTextBox.setText(stat.getShortForm());
            longFormTextBox.setText(stat.getLongForm());
            multiplierTextBox.setText(Integer.toString(stat.getMultiplier()));
        }
    }
    
    public StatDTO getStat()
    {
        return getStat(null, null);
    }
    
    public StatDTO getStat(Long id, Long version)
    {
        StatDTO stat = new StatDTO();
        
        stat.setId(id);
        stat.setVersion(version);
        stat.setCode(codeTextBox.getText().charAt(0));
        stat.setShortForm(shortFormTextBox.getText());
        stat.setLongForm(longFormTextBox.getText());
        stat.setMultiplier(Integer.valueOf(multiplierTextBox.getText()).intValue());
        
        return stat;
    }
}
