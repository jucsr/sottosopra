package br.UFSC.GRIMA.application;

import org.junit.Test;



public class Main
{
	@Test
	public void test()
	{
		JanelaEventos janela = new JanelaEventos();
		janela.setVisible(true);
		janela.setSize(400, 200);
		while(true);
	}
}
