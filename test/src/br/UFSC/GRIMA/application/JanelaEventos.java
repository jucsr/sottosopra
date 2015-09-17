package br.UFSC.GRIMA.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.JOptionPane;


public class JanelaEventos extends Janela implements ActionListener, MouseMotionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8280638275312575640L;
	public double numero;
	public JanelaEventos()
	{
		this.botaoOk.addActionListener(this);
		this.botaoCancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println("clicou");
	}

	
	public void mouseMoved(MouseEvent arg0) 
	{
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) 
	{
		
		
	}

}
