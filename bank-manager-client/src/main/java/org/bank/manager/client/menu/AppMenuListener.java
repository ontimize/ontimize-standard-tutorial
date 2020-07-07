package org.bank.manager.client.menu;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

import com.ontimize.db.Entity;
import com.ontimize.db.EntityResult;
import com.ontimize.gui.ApplicationManager;
import com.ontimize.gui.DefaultActionMenuListener;
import com.ontimize.gui.Form;
import com.ontimize.gui.button.Button;
import com.ontimize.locator.ReferenceLocator;
import com.ontimize.locator.UtilReferenceLocator;

/**
 * 
 * The extension of the Default Menu Listener, in which it is implemented a
 * feature to change the password It is shown too how to implement non standard
 * extensions of the menu
 * 
 */
public class AppMenuListener extends DefaultActionMenuListener{

    public static final String MANAGER_ABOUT="Managerabout";
    public static final String ACCEPT_KEY="accept";
 
    JDialog dChangePassword = null;

    Form fChangePassword = null;

    public AppMenuListener() {

    }

    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        if (e.getActionCommand().equalsIgnoreCase("ChangePassword")) {
            if (dChangePassword == null) {
                fChangePassword = this.application.getFormManager(MANAGER_ABOUT).getFormCopy("formchangepass.xml");
                dChangePassword = fChangePassword.putInModalDialog("ChangePassword");
                dChangePassword.pack();
                Button b = fChangePassword.getButton(ACCEPT_KEY);
                if (b != null) {
                    b.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (fChangePassword.isEmpty("Password") || fChangePassword.isEmpty("ConfirmPassword")) {
                                fChangePassword.message(dChangePassword, "insert_new_pass_and_confirm", Form.ERROR_MESSAGE);
                                return;
                            }
                            else {
                                if (fChangePassword.getDataFieldValue("Password").equals( fChangePassword.getDataFieldValue("ConfirmPassword")) == false) {
                                    fChangePassword.message(dChangePassword, "Password don't match", Form.ERROR_MESSAGE);
                                    return;
                                }
                                try {
                                    String loginEntity = ((UtilReferenceLocator)application.getReferenceLocator()).getLoginEntityName(application.getReferenceLocator().getSessionId());
                                    Entity eUsu = application.getReferenceLocator().getEntityReference(loginEntity);
                                    Hashtable av = new Hashtable();
                                    av.put("Password", fChangePassword.getDataFieldValue("Password"));
                                    Hashtable cv = new Hashtable();
                                    cv.put("User_", ((ReferenceLocator) application.getReferenceLocator()).getUser());
                                    EntityResult res = eUsu.update(av, cv, application.getReferenceLocator().getSessionId());
                                    System.out.println(res);
                                    if (res.getCode() == EntityResult.OPERATION_WRONG) {
                                        fChangePassword.message(dChangePassword, res.getMessage(), Form.ERROR_MESSAGE);
                                    }
                                    else {
                                        fChangePassword.message(dChangePassword, "Password changed successfully", Form.INFORMATION_MESSAGE);
                                        dChangePassword.setVisible(false);
                                    }
                                }
                                catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    });
                }

                Button bc = fChangePassword.getButton("cancel");
                if (bc != null) {
                    bc.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            dChangePassword.setVisible(false);
                        }
                    });
                }
            }

            fChangePassword.setDataFieldValue("User_", ((ReferenceLocator) application.getReferenceLocator()).getUser());
            fChangePassword.enableDataFields();
            fChangePassword.enableButtons();
            fChangePassword.disableDataField("User_");
            ApplicationManager.center(dChangePassword);
            dChangePassword.setVisible(true);
        }
        else if (command != null) {
            super.actionPerformed(e);
        }
    }
}