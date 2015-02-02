/*
 * Created by JFormDesigner on Mon Feb 02 16:00:25 BRST 2015
 */

package br.UFSC.GRIMA.application.visual;

import java.awt.*;
import javax.swing.*;

/**
 * @author jc
 */
public class AboutWindow extends JDialog
{
	private BeginWindow beginWindow;
	public AboutWindow(BeginWindow beginWindow) 
	{
		this.beginWindow = beginWindow;
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();

		//======== this ========
		setTitle("About");
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0};
		((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
		((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
		((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

		//---- label1 ----
		label1.setIcon(new ImageIcon(getClass().getResource("/images/3_EMC_logo.gif")));
		contentPane.add(label1, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 0), 0, 0));

		//---- label2 ----
		label2.setText("Desenvolvedor: Igor Beninc\u00e1 - Metido Engenheiro de Controle e Automa\u00e7\u00e3o.");
		contentPane.add(label2, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 0), 0, 0));

		//---- label3 ----
		label3.setText("P\u00f3s Doutorando: Julio - Engenheiro Mec\u00e2nico.");
		contentPane.add(label3, new GridBagConstraints(3, 5, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 0, 0), 0, 0));
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
