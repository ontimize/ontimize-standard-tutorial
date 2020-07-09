package org.bank.manager.client.modules.management;

import java.util.Calendar;
import java.util.Date;

import com.ontimize.gui.BasicInteractionManager;
import com.ontimize.gui.Form;
import com.ontimize.gui.manager.IFormManager;

public class IMMovements extends BasicInteractionManager{
	
	@Override
	public void registerInteractionManager(Form form, IFormManager formManager) {
		super.registerInteractionManager(form, formManager);
		
	}
	
	@Override
	public void setInsertMode() {
		super.setInsertMode();
		managedForm.setDataFieldValue("DATE_", new Date());
		
	}
	
	
	@Override
	public boolean checkInsert() {
	
		Object dateValue = managedForm.getDataFieldValue("DATE_");
		if (dateValue != null && dateValue instanceof Date) {
			Calendar calFormValue = Calendar.getInstance();
			calFormValue.setTime((Date) dateValue);
			Calendar calCurrentValue = Calendar.getInstance();
			if (calCurrentValue.get(Calendar.YEAR) != calFormValue.get(Calendar.YEAR)) {
				managedForm.message("M_DATE_MUST_BE_CURRENT_YEAR", Form.WARNING_MESSAGE);
				return false;
			}
			
		}
		
		return super.checkInsert();
	}

}
