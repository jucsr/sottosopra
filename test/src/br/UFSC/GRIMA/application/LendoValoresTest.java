package br.UFSC.GRIMA.application;

import org.junit.Test;

public class LendoValoresTest 
{
	@Test
	public void lendoValoresTest()
	{
		try {
			LendoValores p1 = new LendoValores();
			p1.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		while(true);
	}
}
