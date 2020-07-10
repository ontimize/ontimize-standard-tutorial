package org.bank.manager.common.interfaces;

import java.rmi.Remote;
import java.util.Hashtable;

import com.ontimize.db.EntityResult;

public interface IDataOperations extends Remote {

    public static final String REMOTE_NAME = "DataOperations";
    
    public EntityResult duplicateRecord(String entityName, Hashtable values, int sessionId) throws Exception;

}