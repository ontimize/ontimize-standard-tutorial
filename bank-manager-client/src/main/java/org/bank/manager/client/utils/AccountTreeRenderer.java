package org.bank.manager.client.utils;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTree;

import com.ontimize.db.NullValue;
import com.ontimize.gui.tree.BasicTreeCellRenderer;
import com.ontimize.gui.tree.OTreeNode;

public class AccountTreeRenderer extends BasicTreeCellRenderer {

	@Override
	public Component getTreeCellRendererComponent(JTree jTree, Object value, boolean selected, boolean expanded,
			boolean leaf, int row, boolean hasFocus) {
		// TODO Auto-generated method stub
		Component comp = super.getTreeCellRendererComponent(jTree, value, selected, expanded, leaf, row, hasFocus);
		if (value instanceof OTreeNode) {
			Object enddate = ((OTreeNode) value).getValueForAttribute("ENDDATE");
			if ((enddate != null) && !(enddate instanceof NullValue) && (enddate.toString().trim().length() > 0)) {
				comp.setForeground(Color.DARK_GRAY);
			}
		}
		return comp;

	}

}
