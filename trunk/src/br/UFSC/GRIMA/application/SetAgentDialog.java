package br.UFSC.GRIMA.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import br.UFSC.GRIMA.application.visual.SetAgentFrame;
/**
 * 
 * @author Jc
 *
 */
public class SetAgentDialog extends SetAgentFrame implements ActionListener, MouseListener
{
	private String name;
	private String ip;
	private String ipCamera;
	private ConfigureAgentWindow configure;
	public SetAgentDialog(ConfigureAgentWindow configure) 
	{
		super(configure);
		this.configure = configure;
		this.setVisible(true);
		this.textFieldName.selectAll();
		this.textFieldName.addMouseListener(this);
		this.textFieldIP.addMouseListener(this);
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
			this.ip = this.textFieldName.getText();
			this.ipCamera = this.textFieldName2.getText();
			Agent agent = new Agent(name, ip, ipCamera);
			configure.comboBox1.addItem(agent.getIP());
			configure.comboBox1.setSelectedIndex(configure.comboBox1.getItemCount() - 1);
			
			
			this.dispose();
		} else if(o == cancelButton)
		{
			this.dispose();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		this.textFieldName.selectAll();
		this.textFieldIP.selectAll();
		this.textFieldName2.selectAll();
	}
	@Override
	public void mouseEntered(MouseEvent e) 
	{
	}
	@Override
	public void mouseExited(MouseEvent e) 
	{
	}
	@Override
	public void mousePressed(MouseEvent e) 
	{
	}
	@Override
	public void mouseReleased(MouseEvent e) 
	{
	}
}
