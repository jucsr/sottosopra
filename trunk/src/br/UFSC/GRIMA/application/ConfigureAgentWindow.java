package br.UFSC.GRIMA.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.UFSC.GRIMA.application.visual.SourceAgentFrame;
/**
 * 
 * @author Jc
 *
 */
public class ConfigureAgentWindow extends SourceAgentFrame implements ActionListener
{
	public ClientApplication application;
	private Agent agent;
	public ConfigureAgentWindow(ClientApplication application, Agent agent)
	{
		super(application);
		this.application = application;
		this.agent = agent;
		this.setVisible(true);
		this.menuItem1.addActionListener(this);
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
		} else if(o == menuItem1)
		{
			new SetAgentDialog(this);
		}
	}

	public Agent getAgent() 
	{
		return agent;
	}
}
