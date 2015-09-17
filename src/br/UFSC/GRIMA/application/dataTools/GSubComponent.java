package br.UFSC.GRIMA.application.dataTools;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import com.sun.codemodel.JLabel;

public class GSubComponent 
{
	private String name;
	private String id;
	private GDataserie dataserie;
	private JTextField tField = new JTextField();
	private JCheckBox subComponentCheckbox = new JCheckBox();
	
	public GSubComponent(String name, String id)
	{
		this.name = name;
		this.id = id;
	}
	public String getName()
	{
		return this.name;
	}
	public String getID()
	{
		return this.id;
	}
	public JTextField gettField() {
		return tField;
	}
	public void settField(JTextField tField) {
		this.tField = tField;
	}
	public JCheckBox getSubComponentCheckbox() {
		return subComponentCheckbox;
	}
	public void setSubComponentCheckbox(JCheckBox subComponentCheckbox) {
		this.subComponentCheckbox = subComponentCheckbox;
	}
	public GDataserie getDataserie() {
		return dataserie;
	}
	public void setDataserie(GDataserie dataserie) {
		this.dataserie = dataserie;
	}
}
