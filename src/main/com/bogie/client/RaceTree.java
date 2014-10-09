/**
 * RaceTree.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.client;

import java.util.List;

import com.bogie.common.lib.dto.RaceDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.TreeListener;
import com.google.gwt.user.client.ui.Widget;


/**
 * RaceTree 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class RaceTree extends Tree
{
    private TreeItem    rootRaceTreeItem = new TreeItem("Races");
    private TreeItem    selectedRaceTreeItem;

    private GameServiceAsync    gameService = (GameServiceAsync)GWT.create(GameService.class);

    /**
     * Default constructor
     */
    public RaceTree()
    {
        ((ServiceDefTarget)gameService).setServiceEntryPoint(GWT.getModuleBaseURL() + "services/gameService");
        
        rootRaceTreeItem.setUserObject(new RaceDTO());
        
        addItem(rootRaceTreeItem);
        
        update(rootRaceTreeItem);
        
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
                selectedRaceTreeItem = item;
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
     * @return the rootRaceTreeItem
     */
    public TreeItem getRootRaceTreeItem()
    {
        return rootRaceTreeItem;
    }

    /**
     * @return the selectedRaceTreeItem
     */
    public TreeItem getSelectedRaceTreeItem()
    {
        return selectedRaceTreeItem;
    }

    /**
     * updates the race tree
     */
    public void update()
    {
        update(rootRaceTreeItem);
    }
    
    /**
     * updates the race tree
     * @param parent the parent tree item to update
     */
    public void update(final TreeItem parent)
    {
        gameService.findRaces(((RaceDTO)parent.getUserObject()).getId(), new AsyncCallback()
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
                List<RaceDTO> races = (List<RaceDTO>)result;
                
                for (RaceDTO race : races)
                {
                    TreeItem    child = new TreeItem();
                    
                    child.setUserObject(race);
                    child.setText(race.toString());
                    
                    parent.addItem(child);
                    
                    update(child);
                }
            }
        });
    }
}
