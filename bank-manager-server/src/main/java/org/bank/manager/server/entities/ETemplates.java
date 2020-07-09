package org.bank.manager.server.entities;

import com.ontimize.db.DatabaseConnectionManager;
import com.ontimize.db.PrintTemplateEntity;
import com.ontimize.locator.EntityReferenceLocator;

public class ETemplates extends PrintTemplateEntity{

    public ETemplates(EntityReferenceLocator referenceLocator,
            DatabaseConnectionManager dbConnectionManager, int port)
            throws Exception {
        super(referenceLocator, dbConnectionManager, port);
    }
}