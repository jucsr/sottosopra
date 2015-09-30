package br.UFSC.GRIMA.application.dataTools;

import javax.swing.JCheckBox;

public class GCheckbox extends JCheckBox 
{
	private int componentIndex;
	private int subComponentIndex;
	public int getIndex() 
	{
		return componentIndex;
	}

	public void setIndex(int index) 
	{
		this.componentIndex = index;
	}

	public int getSubComponentIndex() {
		return subComponentIndex;
	}

	public void setSubComponentIndex(int subComponentIndex) {
		this.subComponentIndex = subComponentIndex;
	}

}
