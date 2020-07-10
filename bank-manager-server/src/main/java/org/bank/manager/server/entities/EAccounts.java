package org.bank.manager.server.entities;

import java.sql.Connection;
import java.util.Hashtable;
import java.util.Vector;

import org.bank.manager.server.InsertUserDataEntity;

import com.ontimize.db.DatabaseConnectionManager;
import com.ontimize.db.EntityResult;
import com.ontimize.locator.EntityReferenceLocator;

public class EAccounts extends InsertUserDataEntity {

    public EAccounts(EntityReferenceLocator locator, DatabaseConnectionManager dbManager, int port) throws Exception {
        super(locator, dbManager, port, "org/bank/manager/server/entities/prop/EAccounts.properties");
    }

    @Override
    public EntityResult query(Hashtable keysValues, Vector attributes, int sessionId, Connection con) throws Exception {
        System.out.println("EAccounts: Executing query.");
        return super.query(keysValues, attributes, sessionId, con);
    }
}


