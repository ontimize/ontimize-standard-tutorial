package org.bank.manager.client;

import com.ontimize.db.DatabaseConnectionManager;
import com.ontimize.gui.ApplicationLauncher;
import com.ontimize.gui.ApplicationToolBar;
import com.ontimize.gui.Form;
import com.ontimize.gui.preferences.BasicApplicationPreferences;
import com.ontimize.permission.PermissionButton;
import com.ontimize.util.math.MathExpressionParser;
import com.ontimize.util.math.MathExpressionParserFactory;

public class ClientLauncher {

	public static void main(String[] args) {
		DatabaseConnectionManager.offset = 5;
		DatabaseConnectionManager.offsetString = "quickstart";
		ApplicationToolBar.DEFAULT_BUTTON_SIZE = 30;
		Form.WORD_TEMPLATES = 1;
		BasicApplicationPreferences.remoteUserPreferences = true;
		BasicApplicationPreferences.checkOldPreferences = false;
		PermissionButton.setPropertyFile("org/bank/manager/client/permission/PermissionButton.properties");
		System.setProperty(MathExpressionParserFactory.MATH_EXPRESSION_PARSER_PROPERTY, MathExpressionParser.MESP);
		ApplicationLauncher.main(args);
	}

}
