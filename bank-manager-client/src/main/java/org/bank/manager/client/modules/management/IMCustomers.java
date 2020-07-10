package org.bank.manager.client.modules.management;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JTable;

import org.bank.manager.common.interfaces.IDataOperations;
import org.bank.manager.common.interfaces.IUpdateCustomerTypes;

import com.ontimize.db.Entity;
import com.ontimize.db.EntityResult;
import com.ontimize.gui.BasicInteractionManager;
import com.ontimize.gui.Form;
import com.ontimize.gui.InteractionManager;
import com.ontimize.gui.ValueChangeListener;
import com.ontimize.gui.ValueEvent;
import com.ontimize.gui.button.Button;
import com.ontimize.gui.field.DataComponent;
import com.ontimize.gui.field.FormComponent;
import com.ontimize.gui.field.ReferenceComboDataField;
import com.ontimize.gui.manager.IFormManager;
import com.ontimize.gui.table.CellEditor;
import com.ontimize.gui.table.CurrencyCellRenderer;
import com.ontimize.gui.table.DateCellEditor;
import com.ontimize.gui.table.DateCellRenderer;
import com.ontimize.gui.table.Table;
import com.ontimize.locator.UtilReferenceLocator;

public class IMCustomers extends BasicInteractionManager {
	
	 protected Button updateTypeButton;
	
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
       
       DataComponent typeComponent = this.managedForm.getDataFieldReference("CUSTOMERTYPEID");
       if (typeComponent != null) {
           ((ReferenceComboDataField) typeComponent).addValueChangeListener(new ValueChangeListener() {

               public void valueChanged(ValueEvent arg0) {
                   IMCustomers.this.updateTypeButtonStatus();
               }
           });

           this.updateTypeButton = form.getButton("updateType");
           this.updateTypeButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   IMCustomers.this.updateCustomerTypes();
               }
           });
       }
       
       Button buttonDuplicateRecord = this.managedForm.getButton("duplicateRecord");
       if (buttonDuplicateRecord != null) {
           buttonDuplicateRecord.addActionListener(new ActionListener() {

               public void actionPerformed(ActionEvent e) {
                   IMCustomers.this.duplicateRecord();
               }

           });
       }
       
    }
	
	 protected void duplicateRecord() {
		 if (this.formManager.getReferenceLocator() instanceof UtilReferenceLocator) {
			 try {
				 int sessionId = this.formManager.getReferenceLocator().getSessionId();
				 Object remoteObject = ((UtilReferenceLocator) this.formManager.getReferenceLocator()).getRemoteReference(IDataOperations.REMOTE_NAME, sessionId);
				 
				 if ((remoteObject != null) && (remoteObject instanceof IDataOperations)) {
					 EntityResult result = ((IDataOperations) remoteObject).duplicateRecord(this.managedForm.getEntityName(),  this.managedForm.getDataFieldValues(false), sessionId);
					 if (result.getCode() == EntityResult.OPERATION_WRONG) {
	                        this.managedForm.message(result.getMessage(), Form.ERROR_MESSAGE);
	                    }
				 }
				 
			} catch (Exception e) {
				 e.printStackTrace();
			}
			 
		 }
		
	}

	protected void updateCustomerTypes() {
		 Object typeId = this.managedForm.getDataFieldValue("CUSTOMERTYPEID");
		 if (typeId != null) {
			 List customerIds = (List) this.managedForm.getDataList().get("CUSTOMERID");	 
			 try {
				Entity eCustomers = this.formManager.getReferenceLocator().getEntityReference("ECustomers");
				((IUpdateCustomerTypes)eCustomers).updateCustomerTypes(customerIds, typeId, this.formManager.getReferenceLocator().getSessionId());
				 this.managedForm.setDataFieldValue("CUSTOMERTYPEID", typeId);
				 this.setUpdateMode();
			} catch (Exception e) {
				e.printStackTrace();
			}
			 
		 }
		
	}

	protected void updateTypeButtonStatus() {
	        if (this.currentMode == InteractionManager.UPDATE) {
	            this.updateTypeButton.setEnabled(true);
	        }
	    }
	
	 @Override
	    public void setUpdateMode() {
	        super.setUpdateMode();
	        this.managedForm.enableButton("duplicateRecord");
	    }
}