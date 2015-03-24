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
	private int nCameras;

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
		if (o == okButton) 
		{
			this.name = this.textFieldName.getText();
			this.ip = this.textFieldIP.getText();
			this.ipCamera = this.textFieldName2.getText();

			new Thread() 
			{
				public void run() 
				{
					Agent agent = new Agent(name, ip, ipCamera);
					ClienteCamera cc = new ClienteCamera(ipCamera);
					nCameras = cc.getnCameras();
					agent.setnCameras(nCameras);
					new ClientApplication(agent);
//					System.out.println("cameras = " + nCameras);
				}
			}.start();

			this.dispose();

		} else if (o == cancelButton) 
		{
			this.dispose();
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

	}

	public int getnCameras() 
	{
		return nCameras;
	}
}
