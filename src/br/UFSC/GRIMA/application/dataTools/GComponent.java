package br.UFSC.GRIMA.application.dataTools;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class GComponent 
{
	private String component;
	private String name;
	private String componentID;
	
	private GSample gSample = new GSample();
	private GEvent gEvent= new GEvent();
	private GCondition gCondition = new GCondition();
	
	private GButton button = new GButton();
	private JPanel componentInfoPanel;
	
	private GCheckbox componentCheckbox = new GCheckbox();
	private JPanel painelMenuCheckbox = new JPanel();
	
	public GComponent(String component,String name, String componentID)
	{
		this.name = name;
		this.component = component;
		this.componentID = componentID;
	}
	
	public String getName()
	{
		return this.name;
	}
	public String getComponent()
	{
		return this.component;
	}
	public String getComponentID()
	{
		return this.componentID;
	}

	public GButton getButton() 
	{
		return button;
	}

	public void setButton(GButton button) 
	{
		this.button = button;
	}

	public GEvent getgEvent() {
		return gEvent;
	}

	public void setgEvent(GEvent gEvent) {
		this.gEvent = gEvent;
	}

	public GSample getgSample() {
		return gSample;
	}

	public void setgSample(GSample gSample) {
		this.gSample = gSample;
	}

	public GCondition getgCondition() {
		return gCondition;
	}

	public void setgCondition(GCondition gCondition) {
		this.gCondition = gCondition;
	}

	public JPanel getComponentInfoPanel() 
	{
		return componentInfoPanel;
	}

	public void setComponentInfoPanel(JPanel componentInfoPanel) 
	{
		this.componentInfoPanel = componentInfoPanel;
	}
}
