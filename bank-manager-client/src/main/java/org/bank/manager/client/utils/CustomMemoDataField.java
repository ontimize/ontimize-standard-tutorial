package org.bank.manager.client.utils;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Hashtable;

import com.ontimize.gui.field.MemoDataField;

public class CustomMemoDataField extends MemoDataField{

    private String fontName;

	public CustomMemoDataField(Hashtable params) {
        super(params);
        this.getDataField().addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                // Scape --> Lost focus
                if (e.getKeyChar() == '\t') {
                    CustomMemoDataField.this.dataField.transferFocus();
                }
            }
        });
    }
    
    @Override
    public void init(Hashtable params) {
    	// TODO Auto-generated method stub
    	super.init(params);
    	this.fontName = (String) params.get("font");
    	if (this.fontName != null) {
    		this.dataField.setFont(new Font(this.fontName, Font.PLAIN, this.dataField.getFont().getSize()));
    	}
    }
    
};