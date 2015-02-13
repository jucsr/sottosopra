package br.UFSC.GRIMA.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.UFSC.GRIMA.application.visual.sourceAgentFrame;

public class ConfigureAgentWindow extends sourceAgentFrame implements ActionListener
{
	public ConfigureAgentWindow(ClientApplication owner)
	{
		super(owner);
		this.setVisible(true);
		this.okButton.addActionListener(this);
		this.cancelButton.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();
		if(o == cancelButton)
		{
			this.dispose();
		}
		else if(o == okButton)
		{
			this.dispose();
		}
	}
}
