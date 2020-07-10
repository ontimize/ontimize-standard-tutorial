package org.bank.manager.common.interfaces;

import java.rmi.Remote;
import java.util.List;

import com.ontimize.db.EntityResult;

public interface IUpdateCustomerTypes extends Remote {
	
	public EntityResult updateCustomerTypes(List customerIds, Object typeId, int sessionId) throws Exception;

}
