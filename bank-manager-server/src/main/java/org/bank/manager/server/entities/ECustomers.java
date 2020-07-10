package org.bank.manager.server.entities;

import java.sql.Connection;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import org.bank.manager.common.interfaces.IUpdateCustomerTypes;

import com.ontimize.db.DatabaseConnectionManager;
import com.ontimize.db.EntityResult;
import com.ontimize.db.OntimizeConnection;
import com.ontimize.db.TableEntity;
import com.ontimize.locator.EntityReferenceLocator;

public class ECustomers extends TableEntity implements IUpdateCustomerTypes{

//	private static final Logger logger = LoggerFactory.getLogger(ECustomers.class);
	
	public ECustomers(EntityReferenceLocator locator, DatabaseConnectionManager dbConnectionManager, int port)
			throws Exception {
		super(locator, dbConnectionManager, port);
	}

	@Override
	public EntityResult query(Hashtable keysValues, Vector attributes, int sessionId, Connection con) throws Exception {
		
//		logger.debug("ECustomers: Executing query.");
		System.out.println("ECustomers: Executing query.");
		
		return super.query(keysValues, attributes, sessionId, con);
	}

	@Override
	public EntityResult insert(Hashtable attributesValues, int sessionId, Connection con) throws Exception {
		// TODO Auto-generated method stub
		EntityResult insertResult = super.insert(attributesValues, sessionId, con);
		if (insertResult.getCode() != EntityResult.OPERATION_WRONG) {
			TableEntity EArchive = (TableEntity) this.getEntityReference("EArchive");
			Hashtable archiveValues = new Hashtable();
			archiveValues.put("TABLE_NAME", this.getTable());
			archiveValues.put("RECORD_IDENTIFIER", insertResult.get(this.keys.get(0)));
			archiveValues.put("INSERT_DATE", new Date());

			EArchive.insert(archiveValues, sessionId, con);
		}

		return insertResult;
	}

	@Override
	public EntityResult delete(Hashtable keysValues, int sessionId, Connection con) throws Exception {

		TableEntity EAccounts = (TableEntity) this.getEntityReference("ECustomerAccounts");
		Hashtable queryFilter = new Hashtable();
		queryFilter.put("CUSTOMERID", keysValues.get("CUSTOMERID"));
		EntityResult accountsResult = EAccounts.query(queryFilter, new Vector(), sessionId, con);

		if (accountsResult.calculateRecordNumber() > 0) {
			EntityResult result = this.createEntityResultForSessionId(sessionId);
			result.setCode(EntityResult.OPERATION_WRONG);
			result.setMessage("M_ACOUNTS_EXIST");
			return result;
		} else {
			return super.delete(keysValues, sessionId, con);
		}

	}

	@Override
	public EntityResult updateCustomerTypes(List customerIds, Object typeId, int sessionId) throws Exception {
		this.checkFinishedSession(sessionId);

        Hashtable valuesToUpdate = new Hashtable();
        valuesToUpdate.put("CUSTOMERTYPEID", typeId);

        Hashtable keys = new Hashtable();

        OntimizeConnection oConnection = this.connect();
        Connection con = oConnection.getConnection();

        try {
            con.setAutoCommit(false);
            
            for (int i = 0; i < customerIds.size(); i++) {
                keys.put("CUSTOMERID", customerIds.get(i));
                super.update(valuesToUpdate, keys, sessionId, con);
            }
            
            con.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
            EntityResult result = createEntityResultForSessionId(sessionId);
            result.setCode(EntityResult.OPERATION_WRONG);
            result.setMessage(e.getMessage());
            con.rollback();
            return result;
        } finally {
            con.setAutoCommit(true);
            disconnect(oConnection);
        }

        return createEntityResultForSessionId(sessionId);
    }
}