/**
 * SkillTree.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.client;

import java.util.List;

import com.bogie.common.lib.dto.SkillDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.TreeListener;
import com.google.gwt.user.client.ui.Widget;


/**
 * SkillTree 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class SkillTree extends Tree
{
    private TreeItem    rootSkillTreeItem = new TreeItem("Skills");
    private TreeItem    selectedSkillTreeItem;

    private GameServiceAsync    gameService = (GameServiceAsync)GWT.create(GameService.class);

    /**
     * Default constructor
     */
    public SkillTree()
    {
        ((ServiceDefTarget)gameService).setServiceEntryPoint(GWT.getModuleBaseURL() + "services/gameService");
        
        rootSkillTreeItem.setUserObject(new SkillDTO());
        
        addItem(rootSkillTreeItem);
        
        update(rootSkillTreeItem);
        
        addMouseListener(new MouseListener() {

            /**
             * @see com.google.gwt.user.client.ui.MouseListener#onMouseDown(com.google.gwt.user.client.ui.Widget, int, int)
             */
            public void onMouseDown(Widget sender, int x, int y)
            {
            }

            /**
             * @see com.google.gwt.user.client.ui.MouseListener#onMouseEnter(com.google.gwt.user.client.ui.Widget)
             */
            public void onMouseEnter(Widget sender)
            {
            }

            /**
             * @see com.google.gwt.user.client.ui.MouseListener#onMouseLeave(com.google.gwt.user.client.ui.Widget)
             */
            public void onMouseLeave(Widget sender)
            {
            }

            /**
             * @see com.google.gwt.user.client.ui.MouseListener#onMouseMove(com.google.gwt.user.client.ui.Widget, int, int)
             */
            public void onMouseMove(Widget sender, int x, int y)
            {
            }

            /**
             * @see com.google.gwt.user.client.ui.MouseListener#onMouseUp(com.google.gwt.user.client.ui.Widget, int, int)
             */
            public void onMouseUp(Widget sender, int x, int y)
            {
            }
            
        });
        
        addTreeListener(new TreeListener()
        {
            /**
             * @see com.google.gwt.user.client.ui.TreeListener#onTreeItemSelected(com.google.gwt.user.client.ui.TreeItem)
             */
            public void onTreeItemSelected(TreeItem item)
            {
                selectedSkillTreeItem = item;
            }

            /**
             * @see com.google.gwt.user.client.ui.TreeListener#onTreeItemStateChanged(com.google.gwt.user.client.ui.TreeItem)
             */
            public void onTreeItemStateChanged(TreeItem item)
            {
            }
        });
    }
    
    /**
     * @return the rootSkillTreeItem
     */
    public TreeItem getRootSkillTreeItem()
    {
        return rootSkillTreeItem;
    }

    /**
     * @return the selectedSkillTreeItem
     */
    public TreeItem getSelectedSkillTreeItem()
    {
        return selectedSkillTreeItem;
    }

    /**
     * updates the skill tree
     */
    public void update()
    {
        update(rootSkillTreeItem);
    }
    
    /**
     * updates the skill tree
     * @param parent the parent tree item to update
     */
    public void update(final TreeItem parent)
    {
        gameService.findSkills(((SkillDTO)parent.getUserObject()).getId(), new AsyncCallback()
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
            @SuppressWarnings("unchecked")
			public void onSuccess(Object result)
            {
                List<SkillDTO> skills = (List<SkillDTO>)result;
                
                for (SkillDTO skill : skills)
                {
                    TreeItem    child = new TreeItem();
                    
                    child.setUserObject(skill);
                    child.setText(skill.toString());
                    
                    parent.addItem(child);
                    
                    update(child);
                }
            }
        });
    }
}
