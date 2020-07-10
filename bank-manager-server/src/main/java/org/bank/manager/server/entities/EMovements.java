package org.bank.manager.server.entities;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Vector;

import com.ontimize.db.DatabaseConnectionManager;
import com.ontimize.db.EntityResult;
import com.ontimize.db.TableEntity;
import com.ontimize.gui.SearchValue;
import com.ontimize.locator.EntityReferenceLocator;

public class EMovements extends TableEntity {

    public EMovements(EntityReferenceLocator locator, DatabaseConnectionManager dbConnectionManager, int port) throws Exception {
        super(locator, dbConnectionManager, port);
    }

    @Override
    public EntityResult query(Hashtable keysValues, Vector attributes, int sessionId, Connection con) throws Exception {
    	
    	Calendar cal = Calendar.getInstance();
//        Date now = new Date();
//        cal.setTime(now);
    	cal.set(cal.get(Calendar.YEAR), 0, 1, 0, 0, 0);
    	keysValues.put("DATE_", new SearchValue(SearchValue.MORE_EQUAL, cal.getTime()));
    	
    	return super.query(keysValues, attributes, sessionId, con);
    	
    }

}
