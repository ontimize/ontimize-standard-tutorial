package org.bank.manager.client.modules.management;

import com.ontimize.gui.BasicInteractionManager;
import com.ontimize.gui.Form;
import com.ontimize.gui.field.DataComponent;
import com.ontimize.gui.manager.IFormManager;

public class IMCustomers extends BasicInteractionManager {
    @Override
    public void registerInteractionManager(Form form, IFormManager formManager) {
        super.registerInteractionManager(form, formManager);
        DataComponent component = managedForm.getDataFieldReference("CUSTOMERID");
        if (component != null)
        {
            component.setVisible(false);
        }
    }
}