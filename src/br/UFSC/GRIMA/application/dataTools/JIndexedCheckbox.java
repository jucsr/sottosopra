package br.UFSC.GRIMA.application.dataTools;

import java.util.ArrayList;

import javax.swing.JCheckBox;

public class JIndexedCheckbox extends JCheckBox 
{
	private int componentStreamIndex;
	private int componentIndex;
	public ArrayList<JIndexedCheckbox> subComponents = new ArrayList<JIndexedCheckbox>();
	public SerieIdentifier serieIdentifier;
	
	public int getComponentStreamIndex() 
	{
		return this.componentStreamIndex;
	}
	public void setComponentStreamIndex(int componentStreamIndex) 
	{
		this.componentStreamIndex = componentStreamIndex;
	}
	public int getComponentIndex() 
	{
		return this.componentIndex;
	}
	public void setComponentIndex(int components) 
	{
		this.componentIndex = components;
	}
	public void clearCheckboxList()
	{
		for(int i=0 ; i < subComponents.size() ; i++)
		{
			subComponents.get(i).removeAll();
			subComponents.remove(i);
		}
	}
}