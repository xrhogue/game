/**
 * SkillDlg.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.client;

import com.bogie.common.lib.dto.SkillDTO;
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
 * SkillDlg 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class SkillDlg extends PopupPanel
{
    private DockPanel           dockPanel;
    private SkillDetailsGrid    skillDetailsGrid;
    private Button              okButton;
    private Button              cancelButton;
    private Long                id;
    private Long                version;
    private SkillDTO            newSkill;
    
    private GameServiceAsync    gameService = (GameServiceAsync)GWT.create(GameService.class);

    /**
     * Default constructor
     * @param skill 
     */
    public SkillDlg(SkillDTO skill)
    {
        HorizontalPanel buttonPanel = new HorizontalPanel();
        
        ((ServiceDefTarget)gameService).setServiceEntryPoint(GWT.getModuleBaseURL() + "services/gameService");
        
        newSkill = skill;
        
        if (skill != null)
        {
            id = skill.getId();
            version = skill.getVersion();
        }
        
        dockPanel = new DockPanel();
        skillDetailsGrid = new SkillDetailsGrid(skill);
        okButton = new Button("OK");
        cancelButton = new Button("Cancel");
        
        setTitle("Edit Skill");
        setWidget(dockPanel);

        okButton.addClickListener(new ClickListener()
        {
            /**
             * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
             */
            public void onClick(Widget sender)
            {
                gameService.saveSkill(skillDetailsGrid.getSkill(id, version), new AsyncCallback()
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
                        newSkill = (SkillDTO)result;
                        SkillDlg.this.hide();
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
                SkillDlg.this.hide();
            }
        });
        
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        
        dockPanel.add(skillDetailsGrid, DockPanel.CENTER);
        dockPanel.add(buttonPanel, DockPanel.SOUTH);
    }
    
    public SkillDTO getSkill()
    {
        return newSkill;
    }
}
