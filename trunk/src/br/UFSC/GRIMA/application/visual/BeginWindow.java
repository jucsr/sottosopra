/*
 * Created by JFormDesigner on Wed Jan 28 13:52:44 BRST 2015
 */

package br.UFSC.GRIMA.application.visual;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author jc
 */
public class BeginWindow extends JFrame {
	public BeginWindow() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		label5 = new JLabel();
		panel1 = new JPanel();
		label1 = new JLabel();
		comboBox1 = new JComboBox<>();
		splitPane1 = new JSplitPane();
		scrollPane1 = new JScrollPane();
		panel3 = new JPanel();
		label2 = new JLabel();
		textField1 = new JTextField();
		label3 = new JLabel();
		textField2 = new JTextField();
		label4 = new JLabel();
		scrollPane2 = new JScrollPane();
		panel6 = new JPanel();

		//======== this ========
		setTitle("Applet");
		setIconImage(null);
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {6, 125, 0, 1, 0};
		((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {6, 7, 0, 125, 1, 0};
		((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 0.0, 1.0E-4};
		((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0, 0.0, 1.0E-4};

		//---- label5 ----
		label5.setIcon(new ImageIcon(getClass().getResource("/images/3_EMC_logo.gif")));
		label5.setOpaque(true);
		contentPane.add(label5, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));

		//======== panel1 ========
		{
			panel1.setLayout(new GridBagLayout());
			((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {100, 0};
			((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0};
			((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
			((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 1.0, 1.0E-4};

			//---- label1 ----
			label1.setText("Requests:");
			panel1.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 0), 0, 0));

			//---- comboBox1 ----
			comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
				" ",
				"Assets",
				"Current",
				"Probe",
				"Sample"
			}));
			panel1.add(comboBox1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel1, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));

		//======== splitPane1 ========
		{
			splitPane1.setOneTouchExpandable(true);
			splitPane1.setAutoscrolls(true);

			//======== scrollPane1 ========
			{

				//======== panel3 ========
				{
					panel3.setBorder(new EtchedBorder());
					panel3.setLayout(new GridBagLayout());
					((GridBagLayout)panel3.getLayout()).columnWidths = new int[] {0, 200, 0};
					((GridBagLayout)panel3.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
					((GridBagLayout)panel3.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
					((GridBagLayout)panel3.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

					//---- label2 ----
					label2.setText("Name:");
					panel3.add(label2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 5), 0, 0));

					//---- textField1 ----
					textField1.setEditable(false);
					panel3.add(textField1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 0), 0, 0));

					//---- label3 ----
					label3.setText("ID:");
					panel3.add(label3, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 5), 0, 0));

					//---- textField2 ----
					textField2.setEditable(false);
					panel3.add(textField2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 0), 0, 0));

					//---- label4 ----
					label4.setText("Status:");
					panel3.add(label4, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 5), 0, 0));
				}
				scrollPane1.setViewportView(panel3);
			}
			splitPane1.setLeftComponent(scrollPane1);

			//======== scrollPane2 ========
			{

				//======== panel6 ========
				{
					panel6.setBorder(new EtchedBorder());
					panel6.setLayout(new GridBagLayout());
					((GridBagLayout)panel6.getLayout()).columnWidths = new int[] {35, 75, 0, 75, 0, 75, 0, 75, 0, 75, 0, 75, 0, 70, 0};
					((GridBagLayout)panel6.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0};
					((GridBagLayout)panel6.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
					((GridBagLayout)panel6.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
				}
				scrollPane2.setViewportView(panel6);
			}
			splitPane1.setRightComponent(scrollPane2);
		}
		contentPane.add(splitPane1, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label5;
	private JPanel panel1;
	private JLabel label1;
	public JComboBox<String> comboBox1;
	private JSplitPane splitPane1;
	private JScrollPane scrollPane1;
	public JPanel panel3;
	private JLabel label2;
	public JTextField textField1;
	private JLabel label3;
	public JTextField textField2;
	private JLabel label4;
	private JScrollPane scrollPane2;
	public JPanel panel6;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
