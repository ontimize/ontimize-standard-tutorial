package org.bank.manager.client.modules.administration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;

import com.ontimize.gui.ApplicationManager;
import com.ontimize.gui.BasicInteractionManager;
import com.ontimize.gui.Form;
import com.ontimize.gui.button.Button;
import com.ontimize.gui.manager.IFormManager;
import com.ontimize.locator.EntityReferenceLocator;
import com.ontimize.locator.UtilReferenceLocator;

public class IMUsers extends BasicInteractionManager{
	
	private Button checkConnectionButton;
	protected Form usersInfoForm;
	protected JDialog usersInfoDialog;

	@Override
	public void registerInteractionManager(Form form, IFormManager formManager) {
		// TODO Auto-generated method stub
		super.registerInteractionManager(form, formManager);
		this.checkConnectionButton = managedForm.getButton("checkUserConnection");
		if (this.checkConnectionButton != null) {
			this.checkConnectionButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					IMUsers.this.showConnectedUsers();
					
				}
			});
			
		}
	}

	protected void showConnectedUsers() {
		String connectedUsers = this.getConnectedUsers();
		if (connectedUsers != null) {
			 if (this.usersInfoForm == null) {
				 this.usersInfoForm = formManager.getFormCopy("formusersinfo.xml");
				 this.usersInfoDialog = this.usersInfoForm.putInModalDialog();
				 this.usersInfoDialog.setTitle(ApplicationManager.getTranslation("Users"));
				 this.usersInfoDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
				 this.usersInfoForm.setDataFieldValue("usersList", connectedUsers);
				 usersInfoDialog.pack();
			 }else {
				 this.usersInfoForm.setDataFieldValue("usersList", connectedUsers); 
			 }
			 this.usersInfoDialog.setVisible(true);
		}
		
	}

	private String getConnectedUsers() {
		
		EntityReferenceLocator locator = ApplicationManager.getApplication().getReferenceLocator();
		if(locator != null && locator instanceof UtilReferenceLocator) {
			try {
				int sessionId = locator.getSessionId();
				List<String> connectedUsers = ((UtilReferenceLocator)locator).getConnectedUsers(sessionId);
				
				if (connectedUsers != null) {
					StringBuilder builder = new StringBuilder();
					for (String user: connectedUsers) {
						builder.append(user);
						builder.append("\n");
					}
					return builder.toString();
				}
			}catch (Exception e) {
				managedForm.message("M_USERS_CONNECTED_NOT_AVAILABLE", Form.ERROR_MESSAGE);
			}
		}
		
		return null;
	}
	
	@Override
	public void setUpdateMode() {
		super.setUpdateMode();
		 managedForm.enableButton("checkUserConnection");
	}

}
