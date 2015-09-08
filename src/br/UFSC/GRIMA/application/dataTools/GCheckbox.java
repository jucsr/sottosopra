package br.UFSC.GRIMA.application.dataTools;

import javax.swing.JCheckBox;

public class GCheckbox extends JCheckBox 
{
	private int componentIndex;

	public int getIndex() 
	{
		return componentIndex;
	}

	public void setIndex(int index) 
	{
		this.componentIndex = index;
	}
}
