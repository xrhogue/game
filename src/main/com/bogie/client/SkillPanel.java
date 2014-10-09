/**
 * SkillPanel.java
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
import com.google.gwt.user.client.ui.PopupListener;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

/**
 * SkillPanel 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class SkillPanel extends DockPanel
{
    private SkillTree   skillTree;
    private Button      newButton;
    private Button      editButton;
    private Button      deleteButton;
    
    private GameServiceAsync    gameService = (GameServiceAsync)GWT.create(GameService.class);
    
    /**
     * Default constructor
     */
    public SkillPanel()
    {
        HorizontalPanel buttonPanel = new HorizontalPanel();
        GameConstants   gameConstants = (GameConstants)GWT.create(GameConstants.class);

        ((ServiceDefTarget)gameService).setServiceEntryPoint(GWT.getModuleBaseURL() + "services/gameService");

        skillTree = new SkillTree();
        newButton = new Button(gameConstants.newLabel());
        editButton = new Button(gameConstants.edit());
        deleteButton = new Button(gameConstants.delete());
        
        buttonPanel.add(newButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        
        newButton.addClickListener(new ClickListener()
        {
            /**
             * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
             */
            public void onClick(Widget sender)
            {
                SkillDlg    skillDlg = new SkillDlg(null);
                
                skillDlg.addPopupListener(new PopupListener()
                {
                    /**
                     * @see com.google.gwt.user.client.ui.PopupListener#onPopupClosed(com.google.gwt.user.client.ui.PopupPanel, boolean)
                     */
                    public void onPopupClosed(PopupPanel sender, boolean autoClosed)
                    {
                        SkillDTO    skill = ((SkillDlg)sender).getSkill();
                        TreeItem    skillTreeItem = new TreeItem(skill.getName());
                        
                        skillTreeItem.setUserObject(skill);
                        
                        if (skillTree.getSelectedSkillTreeItem() != null)
                        {
                            skillTree.getSelectedSkillTreeItem().addItem(skillTreeItem);
                        }
                        else
                        {
                            skillTree.getRootSkillTreeItem().addItem(skillTreeItem);
                        }
                    }
                });
                
                skillDlg.show();
            }
        });
        
        editButton.addClickListener(new ClickListener()
        {
            /**
             * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
             */
            public void onClick(Widget sender)
            {
                if (skillTree.getSelectedSkillTreeItem() == null)
                {
                    return;
                }
                
                SkillDlg    skillDlg = new SkillDlg((SkillDTO)skillTree.getSelectedSkillTreeItem().getUserObject());
                
                skillDlg.addPopupListener(new PopupListener()
                {
                    /**
                     * @see com.google.gwt.user.client.ui.PopupListener#onPopupClosed(com.google.gwt.user.client.ui.PopupPanel, boolean)
                     */
                    public void onPopupClosed(PopupPanel sender, boolean autoClosed)
                    {
                        SkillDTO skill = ((SkillDlg)sender).getSkill();
                        
                        skillTree.getSelectedSkillTreeItem().setUserObject(skill);
                        skillTree.getSelectedSkillTreeItem().setText(skill.getName());
                    }
                });
                
                skillDlg.show();
            }
        });
        
        deleteButton.addClickListener(new ClickListener()
        {
            /**
             * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
             */
            public void onClick(Widget sender)
            {
                if (skillTree.getSelectedSkillTreeItem() == null)
                {
                    return;
                }
                
                gameService.deleteSkill(((SkillDTO)skillTree.getSelectedSkillTreeItem().getUserObject()).getId(), new AsyncCallback()
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
                        skillTree.getSelectedSkillTreeItem().remove();
                    }
                });
            }
        });
        
        add(skillTree, DockPanel.CENTER);
        add(buttonPanel, DockPanel.SOUTH);
    }
}
