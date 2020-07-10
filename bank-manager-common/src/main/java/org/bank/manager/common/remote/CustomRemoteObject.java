package org.bank.manager.common.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;

import org.bank.manager.common.interfaces.IDataOperations;

import com.ontimize.db.Entity;
import com.ontimize.db.EntityResult;
import com.ontimize.locator.EntityReferenceLocator;
import com.ontimize.locator.SecureReferenceLocator;

public class CustomRemoteObject extends UnicastRemoteObject implements IDataOperations {
	
	public static final String REMOTE_NAME = "DataOperations";
	 protected EntityReferenceLocator locator;
	 

	    public CustomRemoteObject(int port, EntityReferenceLocator locator, Hashtable parameters) throws RemoteException {
	        super(port);
	        this.locator = locator;
	    }


		public EntityResult duplicateRecord(String entityName, Hashtable values, int sessionId) throws Exception {
			Entity ent = null;
	        if (this.locator instanceof SecureReferenceLocator) {
	            ent = ((SecureReferenceLocator) this.locator).getEntityReferenceFromServer(entityName);
	        } else {
	            ent = this.locator.getEntityReference(entityName);
	        }

	        return ent.insert(values, sessionId);
		}
	 
}
