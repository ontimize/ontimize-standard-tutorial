package org.bank.manager.client;

import com.ontimize.gui.ApplicationLauncher;
import com.ontimize.gui.ApplicationToolBar;
import com.ontimize.util.math.MathExpressionParser;
import com.ontimize.util.math.MathExpressionParserFactory;

public class ClientLauncher {

	public static void main(String[] args) {
		ApplicationToolBar.DEFAULT_BUTTON_SIZE=30;
		System.setProperty(MathExpressionParserFactory.MATH_EXPRESSION_PARSER_PROPERTY, MathExpressionParser.MESP);
		ApplicationLauncher.main(args);
	}
	
}
