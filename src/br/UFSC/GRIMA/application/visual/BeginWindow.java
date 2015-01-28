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
		panel2 = new JPanel();
		panel3 = new JPanel();
		label2 = new JLabel();
		textField1 = new JTextField();
		label3 = new JLabel();
		textField2 = new JTextField();
		label4 = new JLabel();
		toggleButton1 = new JButton();
		toggleButton2 = new JButton();
		toggleButton3 = new JButton();
		panel4 = new JPanel();
		panel5 = new JPanel();
		toggleButton4 = new JToggleButton();
		toggleButton5 = new JToggleButton();
		panel6 = new JPanel();
		label6 = new JLabel();
		textField3 = new JTextField();
		label7 = new JLabel();
		textField4 = new JTextField();
		label8 = new JLabel();
		textField5 = new JTextField();
		label9 = new JLabel();
		textField6 = new JTextField();

		//======== this ========
		setTitle("Applet");
		setIconImage(new ImageIcon("C:\\Users\\Sonir\\Desktop\\3_EMC_logo.gif").getImage());
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {6, 0, 0, 0, 0, 1, 0};
		((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {105, 0, 0, 1, 0};
		((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
		((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};

		//---- label5 ----
		label5.setIcon(new ImageIcon("C:\\Users\\Sonir\\Desktop\\3_EMC_logo.gif"));
		contentPane.add(label5, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
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
				"Assets",
				"Current",
				"Probe",
				"Sample"
			}));
			panel1.add(comboBox1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));

		//======== panel2 ========
		{
			panel2.setLayout(new GridBagLayout());
			((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {0, 0, 0};
			((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
			((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
			((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
		}
		contentPane.add(panel2, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));

		//======== panel3 ========
		{
			panel3.setBorder(new EtchedBorder());
			panel3.setLayout(new GridBagLayout());
			((GridBagLayout)panel3.getLayout()).columnWidths = new int[] {0, 140, 0};
			((GridBagLayout)panel3.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
			((GridBagLayout)panel3.getLayout()).columnWeights = new double[] {0.0, 1.0, 1.0E-4};
			((GridBagLayout)panel3.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

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
				new Insets(0, 0, 5, 5), 0, 0));

			//---- toggleButton1 ----
			toggleButton1.setText("AXIS");
			panel3.add(toggleButton1, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 0), 0, 0));

			//---- toggleButton2 ----
			toggleButton2.setText("MAGAZINE");
			panel3.add(toggleButton2, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 0), 0, 0));

			//---- toggleButton3 ----
			toggleButton3.setText("FEED");
			panel3.add(toggleButton3, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel3, new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));

		//======== panel4 ========
		{
			panel4.setLayout(new GridBagLayout());
			((GridBagLayout)panel4.getLayout()).columnWidths = new int[] {0, 0, 0};
			((GridBagLayout)panel4.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
			((GridBagLayout)panel4.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
			((GridBagLayout)panel4.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
		}
		contentPane.add(panel4, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));

		//======== panel5 ========
		{
			panel5.setBorder(new EtchedBorder());
			panel5.setLayout(new GridBagLayout());
			((GridBagLayout)panel5.getLayout()).columnWidths = new int[] {105, 0, 0};
			((GridBagLayout)panel5.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
			((GridBagLayout)panel5.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
			((GridBagLayout)panel5.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

			//---- toggleButton4 ----
			toggleButton4.setText("LINEAR");
			panel5.add(toggleButton4, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- toggleButton5 ----
			toggleButton5.setText("ROTARY");
			panel5.add(toggleButton5, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 5), 0, 0));
		}
		contentPane.add(panel5, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));

		//======== panel6 ========
		{
			panel6.setBorder(new EtchedBorder());
			panel6.setLayout(new GridBagLayout());
			((GridBagLayout)panel6.getLayout()).columnWidths = new int[] {55, 120, 0};
			((GridBagLayout)panel6.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0};
			((GridBagLayout)panel6.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
			((GridBagLayout)panel6.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};

			//---- label6 ----
			label6.setText("Position X:");
			panel6.add(label6, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- textField3 ----
			textField3.setEditable(false);
			panel6.add(textField3, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 0), 0, 0));

			//---- label7 ----
			label7.setText("Position Y:");
			panel6.add(label7, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- textField4 ----
			textField4.setEditable(false);
			panel6.add(textField4, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 0), 0, 0));

			//---- label8 ----
			label8.setText("Position Z:");
			panel6.add(label8, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- textField5 ----
			textField5.setEditable(false);
			panel6.add(textField5, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 0), 0, 0));

			//---- label9 ----
			label9.setText("Speed:");
			panel6.add(label9, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 5), 0, 0));

			//---- textField6 ----
			textField6.setEditable(false);
			panel6.add(textField6, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel6, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0,
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
	private JPanel panel2;
	public JPanel panel3;
	private JLabel label2;
	public JTextField textField1;
	private JLabel label3;
	public JTextField textField2;
	private JLabel label4;
	public JButton toggleButton1;
	public JButton toggleButton2;
	public JButton toggleButton3;
	private JPanel panel4;
	private JPanel panel5;
	public JToggleButton toggleButton4;
	public JToggleButton toggleButton5;
	private JPanel panel6;
	public JLabel label6;
	public JTextField textField3;
	public JLabel label7;
	protected JTextField textField4;
	public JLabel label8;
	protected JTextField textField5;
	public JLabel label9;
	protected JTextField textField6;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
