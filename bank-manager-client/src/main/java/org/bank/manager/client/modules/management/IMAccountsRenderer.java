package org.bank.manager.client.modules.management;


import org.bank.manager.client.utils.AccountTreeRenderer;

import com.ontimize.gui.Form;
import com.ontimize.gui.FormManager;
import com.ontimize.gui.manager.IFormManager;
import com.ontimize.gui.tree.BasicTreeCellRenderer;

public class IMAccountsRenderer extends IMAccounts {
	
 protected static BasicTreeCellRenderer cellRenderer = null;
 static {
  try {
   IMAccountsRenderer.cellRenderer = new AccountTreeRenderer();
  } catch (Exception ex) {
   ex.printStackTrace();
  }
 }
 
 @Override
 public void registerInteractionManager(Form form, IFormManager formManager) {
	  super.registerInteractionManager(form, formManager);
	  IMAccountsRenderer.cellRenderer = new AccountTreeRenderer();
	  if (((FormManager) formManager).getTree() != null)
	  {
	   ((FormManager) formManager).getTree().setCellRenderer(IMAccountsRenderer.cellRenderer);
	  }
	 }

}