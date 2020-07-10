package org.bank.manager.client.modules.management;

import java.awt.Color;
import java.awt.Component;
import java.util.Hashtable;

import javax.swing.JTable;

import com.ontimize.gui.BasicInteractionManager;
import com.ontimize.gui.Form;
import com.ontimize.gui.field.FormComponent;
import com.ontimize.gui.manager.IFormManager;
import com.ontimize.gui.table.CellEditor;
import com.ontimize.gui.table.CurrencyCellRenderer;
import com.ontimize.gui.table.DateCellEditor;
import com.ontimize.gui.table.DateCellRenderer;
import com.ontimize.gui.table.Table;

public class IMCustomers extends BasicInteractionManager {
	
	@Override
    public void registerInteractionManager(Form form, IFormManager formManager) {
        super.registerInteractionManager(form, formManager);
        
       FormComponent comp = this.managedForm.getElementReference("ECustomerAccountBalance");
       
       DateCellEditor.SHOW_CALENDAR = true;
       
       if ((comp != null) && (comp instanceof Table)) {
    	   Hashtable<String, String> hStart = new Hashtable<String, String>();
    	   Hashtable<String, String> hEnd = new Hashtable<String, String>();
    	   hStart.put(CellEditor.COLUMN_PARAMETER, "STARTDATE");
           hEnd.put(CellEditor.COLUMN_PARAMETER, "ENDDATE");
    	   ((Table)comp).setColumnEditor("STARTDATE", new DateCellEditor(hStart));
    	   ((Table)comp).setColumnEditor("ENDDATE", new DateCellEditor(hEnd));
    	   
    	   DateCellRenderer rendererWithoutHour = new DateCellRenderer(false);
    	   ((Table) comp).setRendererForColumn("ENDDATE", rendererWithoutHour);
           ((Table) comp).setRendererForColumn("STARTDATE", rendererWithoutHour);
           
           ((Table) comp).setRendererForColumn("BALANCE", new CurrencyCellRenderer() {
        	   @Override
        	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean hasFocus, int row, int column) {
        		// TODO Auto-generated method stub
        		   Component comp = super.getTableCellRendererComponent(table, value, selected, hasFocus, row, column);
        		   if ((value != null) && (value instanceof Number)) {
        			   double dValue = ((Number) value).doubleValue();
        			   if (dValue > 0) {
                           comp.setForeground(Color.GREEN);
                       } else if (dValue < 0) {
                           comp.setForeground(Color.RED);
                       }
        		   }
        		   
        		   return comp;
        	}
           });
    	   
       }
       
    }
}