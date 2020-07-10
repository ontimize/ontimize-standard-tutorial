package org.bank.manager.server;

import java.sql.Connection;
import java.util.Date;
import java.util.Hashtable;

import com.ontimize.db.DatabaseConnectionManager;
import com.ontimize.db.DefaultTableEntity;
import com.ontimize.db.EntityResult;
import com.ontimize.locator.EntityReferenceLocator;
import com.ontimize.locator.ReferenceLocator;
import com.ontimize.locator.SecureReferenceLocator;

public class InsertUserDataEntity extends DefaultTableEntity {

	public InsertUserDataEntity(EntityReferenceLocator locator, DatabaseConnectionManager dbManager, int port,
			String properties) throws Exception {
		super(locator, dbManager, port, properties);
	}

	public static String insertUserColumn = "INSERTUSER";
	public static String insertDateColumn = "INSERTDATE";

	@Override
	public EntityResult insert(Hashtable attributesValues, int sessionId, Connection con) throws Exception {

		String user = null;
		// Get the name of the user with the specified sessionId
		if (this.locator instanceof SecureReferenceLocator) {
			user = ((SecureReferenceLocator) this.locator).getUser(sessionId);
		} else if (this.locator instanceof ReferenceLocator) {
			user = (String) ((ReferenceLocator) this.locator).getUserId(sessionId);
		}

		// Put the name of the user in the attributes to insert
		if (user != null) {
			attributesValues.put(InsertUserDataEntity.insertUserColumn, user);
		}

		// Put the current date in the attributes to insert
		attributesValues.put(InsertUserDataEntity.insertDateColumn, new Date());

		return super.insert(attributesValues, sessionId, con);
	}

}
