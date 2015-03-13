package br.UFSC.GRIMA.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import br.UFSC.GRIMA.application.visual.BeginAgentWindow;

public class SetAgentFrame extends BeginAgentWindow implements ActionListener
{
	private String name;
	private String ip;
	private String ipCamera;


public SetAgentFrame()
{
	this.setVisible(true);
	this.textFieldName.selectAll();
	this.okButton.addActionListener(this);
	this.cancelButton.addActionListener(this);
}
@Override
public void actionPerformed(ActionEvent e) 
{
	Object o = e.getSource();
	if(o == okButton)
	{
		this.name = this.textFieldName.getText();
		this.ip = this.textFieldIP.getText();
		this.ipCamera = this.textFieldName2.getText();
		Agent agent = new Agent(name, ip, ipCamera);
		new ClientApplication(agent);
		this.dispose();
		
	} else if(o == cancelButton)
	{
		this.dispose();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
}
