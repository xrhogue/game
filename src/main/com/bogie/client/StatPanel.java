/**
 * StatPanel.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.client;

import java.util.List;

import com.bogie.common.lib.dto.StatDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PopupListener;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.Widget;

/**
 * StatPanel 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class StatPanel extends DockPanel
{
    private StatGrid            statGrid;
    private Button              newButton;
    private Button              editButton;
    private Button              deleteButton;
    private int                 selectedRow;
    private List<StatDTO>       stats;
    
    private GameServiceAsync    gameService = (GameServiceAsync)GWT.create(GameService.class);
    
    /**
     * Default constructor
     */
    public StatPanel()
    {
        HorizontalPanel buttonPanel = new HorizontalPanel();
        GameConstants   gameConstants = (GameConstants)GWT.create(GameConstants.class);
        
        ((ServiceDefTarget)gameService).setServiceEntryPoint(GWT.getModuleBaseURL() + "services/gameService");

        statGrid = new StatGrid();
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
                StatDlg statDlg = new StatDlg(null);
                
                statDlg.addPopupListener(new PopupListener()
                {
                    /**
                     * @see com.google.gwt.user.client.ui.PopupListener#onPopupClosed(com.google.gwt.user.client.ui.PopupPanel, boolean)
                     */
                    public void onPopupClosed(PopupPanel sender, boolean autoClosed)
                    {
                        statGrid.update();
                    }
                });
                
                statDlg.show();
            }
        });
        
        editButton.addClickListener(new ClickListener()
        {
            /**
             * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
             */
            public void onClick(Widget sender)
            {
                if (selectedRow < 1 || stats == null)
                {
                    return;
                }
                
                StatDlg statDlg = new StatDlg((StatDTO)stats.get(selectedRow - 1));
                
                statDlg.addPopupListener(new PopupListener()
                {
                    /**
                     * @see com.google.gwt.user.client.ui.PopupListener#onPopupClosed(com.google.gwt.user.client.ui.PopupPanel, boolean)
                     */
                    public void onPopupClosed(PopupPanel sender, boolean autoClosed)
                    {
                        StatDTO stat = ((StatDlg)sender).getStat();
                        
                        statGrid.update(selectedRow, stat);
                    }
                });
                
                statDlg.show();
            }
        });
        
        deleteButton.addClickListener(new ClickListener()
        {
            /**
             * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
             */
            public void onClick(Widget sender)
            {
                if (selectedRow < 1 || stats == null)
                {
                    return;
                }
                
                gameService.deleteStat((StatDTO)stats.get(selectedRow - 1), new AsyncCallback()
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
                        if (selectedRow >= statGrid.getRowCount() - 1)
                        {
                            selectedRow--;
                        }
                        
                        statGrid.update();
                    }
                });
            }
        });
        
        add(statGrid, DockPanel.CENTER);
        add(buttonPanel, DockPanel.SOUTH);
    }
    
    /**
     * set the enabled state for the components
     */
    public void updateComponents()
    {
        if (selectedRow > 0)
        {
            editButton.setEnabled(true);
            deleteButton.setEnabled(true);
        }
        else
        {
            editButton.setEnabled(false);
            deleteButton.setEnabled(false);
        }
    }
    
    /**
     * StatGrid 
     * 
     * @author Richard Hogue
     * @version 1.0
     */
    private class StatGrid extends Grid
    {
        /**
         * Default constructor
         */
        public StatGrid()
        {
            super(1, 4);
            
            setText(0, 0, "Short Name");
            setText(0, 1, "Code");
            setText(0, 2, "Long Name");
            setText(0, 3, "Multiplier");
            
            getRowFormatter().addStyleName(0, "sg-header");
            
            addTableListener(new TableListener()
            {
                public void onCellClicked(SourcesTableEvents sender,  int row, int cell)
                {
                    StatGrid.this.getRowFormatter().removeStyleName(selectedRow, "sg-selected");
                    
                    if (row > 0)
                    {
                        StatGrid.this.getRowFormatter().addStyleName(row, "sg-selected");
                        selectedRow = row;
                    }
                    
                    updateComponents();
                }
            });
            
            update();
        }
        
        /**
         * updates the stat list
         */
        public void update()
        {
            gameService.findStats(new AsyncCallback()
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
                    stats = (List<StatDTO>)result;

                    StatGrid.this.resizeRows(stats.size() + 1);
                    
                    for (int i = 0; i < stats.size(); i++)
                    {
                        StatDTO stat = stats.get(i);
                        
                        update(i + 1, stat);

                        if (i % 2 == 1)
                        {
                            getRowFormatter().addStyleName(i + 1, "sg-oddrow");
                        }
                        
                        if (i + 1 == selectedRow)
                        {
                            getRowFormatter().addStyleName(i + 1, "sg-selected");
                        }
                    }
                    
                    updateComponents();
                }
            });
        }
        
        public void update(int index, StatDTO stat)
        {
            setText(index, 0, stat.getShortForm());
            setText(index, 1, Character.toString(stat.getCode()));
            setText(index, 2, stat.getLongForm());
            setText(index, 3, Integer.toString(stat.getMultiplier()));
        }
    }
}
