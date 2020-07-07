package org.bank.manager.server.entities;

import com.ontimize.db.DatabaseConnectionManager;
import com.ontimize.db.UserEntity;
import com.ontimize.locator.EntityReferenceLocator;

public class EUsers extends UserEntity {
 
 static {
  UserEntity.encrypt = true;
 }
 
    public EUsers(EntityReferenceLocator locator, DatabaseConnectionManager databaseConnectionManager, int port) throws Exception {
        super(locator, databaseConnectionManager, port);
    }
}