package org.bank.manager.client.utils;

import java.util.Hashtable;

import com.ontimize.gui.DetailForm;
import com.ontimize.gui.table.Table;

public class AccountTable extends Table {
	
	protected Hashtable formsCache = new Hashtable();

	public AccountTable(Hashtable params) throws Exception {
		super(params);
	}
	
	@Override
	public void openDetailForm(int rowIndex) {
		this.detailForm = null;
		
		Number balance = (Number) this.getRowValue(rowIndex, "BALANCE");
		
		if ((balance != null) && (balance.doubleValue() < 0)) {
            this.formName = "formnegativeaccounts.xml";
        } else {
            this.formName = "formaccounts.xml";
        }
		
		if (!this.formsCache.containsKey(this.formName)) {
            this.createDetailForm();
            this.formsCache.put(this.formName, this.detailForm);
        } else {
            this.detailForm = (DetailForm) this.formsCache.get(this.formName);
        }
		
		super.openDetailForm(rowIndex);
	}

}
