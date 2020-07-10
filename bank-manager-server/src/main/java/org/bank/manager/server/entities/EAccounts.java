package org.bank.manager.server.entities;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Vector;

import org.bank.manager.server.InsertUserDataEntity;

import com.ontimize.db.DatabaseConnectionManager;
import com.ontimize.db.EntityResult;
import com.ontimize.db.SQLStatementBuilder.BasicExpression;
import com.ontimize.db.SQLStatementBuilder.BasicField;
import com.ontimize.db.SQLStatementBuilder.BasicOperator;
import com.ontimize.db.SQLStatementBuilder.ExtendedSQLConditionValuesProcessor;
import com.ontimize.locator.EntityReferenceLocator;

public class EAccounts extends InsertUserDataEntity {

    public EAccounts(EntityReferenceLocator locator, DatabaseConnectionManager dbManager, int port) throws Exception {
        super(locator, dbManager, port, "org/bank/manager/server/entities/prop/EAccounts.properties");
    }

    @Override
    public EntityResult query(Hashtable keysValues, Vector attributes, int sessionId, Connection con) throws Exception {
        System.out.println("EAccounts: Executing query.");
        
        Calendar cal = Calendar.getInstance();
//        Date now = new Date();
//        cal.setTime(now);
        cal.set(cal.get(Calendar.YEAR), 0, 1, 0, 0, 0);
        
        BasicField startDateField = new BasicField("STARTDATE");
        BasicField endDateField = new BasicField("ENDDATE");
        
        BasicExpression be1 = new BasicExpression(startDateField, BasicOperator.MORE_EQUAL_OP, cal.getTime());
        BasicExpression be2 = new BasicExpression(endDateField, BasicOperator.NULL_OP, null);
        BasicExpression be3 = new BasicExpression(endDateField, BasicOperator.EQUAL_OP, "");
        BasicExpression be2OR3 = new BasicExpression(be2, BasicOperator.OR_OP, be3);
        BasicExpression be1OR2OR3 = new BasicExpression(be1, BasicOperator.OR_OP, be2OR3);
        
        keysValues.put(ExtendedSQLConditionValuesProcessor.EXPRESSION_KEY, be1OR2OR3);

        return super.query(keysValues, attributes, sessionId, con);
    }
}


