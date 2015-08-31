package br.UFSC.GRIMA.application.dataTools;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class JIndexedButton extends JToggleButton 
{
	private int componentStreamIndex;
	public ArrayList<SerieIdentifier> identifierList = new ArrayList<SerieIdentifier>();
	public JPanel dataPanel;
	
	public void setComponentStreamIndex(int componentStreamIndex)
	{
		this.componentStreamIndex = componentStreamIndex;
	}
	public int getComponentStreamIndex()
	{
		return this.componentStreamIndex;
	}
}

