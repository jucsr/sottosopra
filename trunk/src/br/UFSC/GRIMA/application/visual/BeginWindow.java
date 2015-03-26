package br.UFSC.GRIMA.application.visual;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class BeginWindow extends JFrame {
	public BeginWindow() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		menuBar1 = new JMenuBar();
		menu1 = new JMenu();
		menuItem2 = new JMenuItem();
		menu3 = new JMenu();
		menuItem3 = new JMenuItem();
		menuItem4 = new JMenu();
		menu2 = new JMenu();
		menuItem1 = new JMenuItem();
		label5 = new JLabel();
		panel1 = new JPanel();
		label1 = new JLabel();
		comboBox1 = new JComboBox<>();
		button1 = new JToggleButton();
		panel7 = new JPanel();
		label6 = new JLabel();
		textField7 = new JTextField();
		splitPane2 = new JSplitPane();
		splitPane1 = new JSplitPane();
		scrollPane1 = new JScrollPane();
		panel2 = new JPanel();
		panel3 = new JPanel();
		label2 = new JLabel();
		textField1 = new JTextField();
		label3 = new JLabel();
		textField2 = new JTextField();
		label4 = new JLabel();
		panel4 = new JPanel();
		scrollPane2 = new JScrollPane();
		panel6 = new JPanel();
		scrollPane3 = new JScrollPane();
		textPane1 = new JTextPane();

		//======== this ========
		setTitle("Applet");
		setIconImage(new ImageIcon(getClass().getResource("/images/iconeLogo.png")).getImage());
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {6, 25, 0, 1, 0};
		((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {7, 6, 31, 125, 1, 0};
		((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 0.0, 1.0E-4};
		((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0, 0.0, 1.0E-4};

		//======== menuBar1 ========
		{

			//======== menu1 ========
			{
				menu1.setText("File");
				menu1.setFont(new Font("Verdana", Font.PLAIN, 12));

				//---- menuItem2 ----
				menuItem2.setText("exit");
				menuItem2.setIcon(new ImageIcon(getClass().getResource("/images/process-stop.png")));
				menuItem2.setFont(new Font("Verdana", Font.PLAIN, 12));
				menu1.add(menuItem2);
			}
			menuBar1.add(menu1);

			//======== menu3 ========
			{
				menu3.setText("Configure");
				menu3.setFont(new Font("Verdana", Font.PLAIN, 12));

				//---- menuItem3 ----
				menuItem3.setText("Set source agent");
				menuItem3.setFont(new Font("Verdana", Font.PLAIN, 12));
				menu3.add(menuItem3);

				//======== menuItem4 ========
				{
					menuItem4.setText("Webcam");
					menuItem4.setFont(new Font("Verdana", Font.PLAIN, 12));
				}
				menu3.add(menuItem4);
			}
			menuBar1.add(menu3);

			//======== menu2 ========
			{
				menu2.setText("Help");
				menu2.setFont(new Font("Verdana", Font.PLAIN, 12));

				//---- menuItem1 ----
				menuItem1.setText("About");
				menuItem1.setIcon(null);
				menuItem1.setFont(new Font("Verdana", Font.PLAIN, 12));
				menu2.add(menuItem1);
			}
			menuBar1.add(menu2);
		}
		setJMenuBar(menuBar1);

		//---- label5 ----
		label5.setIcon(new ImageIcon(getClass().getResource("/images/logofinal.png")));
		label5.setOpaque(true);
		contentPane.add(label5, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));

		//======== panel1 ========
		{
			panel1.setLayout(new GridBagLayout());
			((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {105, 0, 0, 0, 0, 0, 0};
			((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 2, 0};
			((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
			((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

			//---- label1 ----
			label1.setText("Requests:");
			label1.setFont(new Font("Verdana", Font.PLAIN, 12));
			panel1.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- comboBox1 ----
			comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
				" ",
				"Assets",
				"Current",
				"Probe",
				"Sample"
			}));
			comboBox1.setFont(new Font("Verdana", Font.PLAIN, 12));
			panel1.add(comboBox1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 5), 0, 0));

			//---- button1 ----
			button1.setIcon(new ImageIcon(getClass().getResource("/images/pause.png")));
			button1.setVisible(false);
			button1.setFont(new Font("Verdana", Font.PLAIN, 12));
			panel1.add(button1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 5), 0, 0));

			//======== panel7 ========
			{
				panel7.setLayout(new GridBagLayout());
				((GridBagLayout)panel7.getLayout()).columnWidths = new int[] {0, 160, 0};
				((GridBagLayout)panel7.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
				((GridBagLayout)panel7.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
				((GridBagLayout)panel7.getLayout()).rowWeights = new double[] {1.0, 0.0, 0.0, 1.0E-4};

				//---- label6 ----
				label6.setText("Time:");
				label6.setFont(new Font("Verdana", Font.PLAIN, 12));
				panel7.add(label6, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

				//---- textField7 ----
				textField7.setEditable(false);
				textField7.setFont(new Font("Verdana", Font.PLAIN, 12));
				panel7.add(textField7, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));
			}
			panel1.add(panel7, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 5), 0, 0));
		}
		contentPane.add(panel1, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));

		//======== splitPane2 ========
		{
			splitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPane2.setResizeWeight(0.9);
			splitPane2.setOneTouchExpandable(true);

			//======== splitPane1 ========
			{
				splitPane1.setOneTouchExpandable(true);
				splitPane1.setAutoscrolls(true);
				splitPane1.setResizeWeight(0.1);

				//======== scrollPane1 ========
				{

					//======== panel2 ========
					{
						panel2.setLayout(new GridBagLayout());
						((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {0, 0, 0};
						((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {0, 0, 0};
						((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {0.0, 1.0, 1.0E-4};
						((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

						//======== panel3 ========
						{
							panel3.setBorder(new EtchedBorder());
							panel3.setLayout(new GridBagLayout());
							((GridBagLayout)panel3.getLayout()).columnWidths = new int[] {8, 0, 110, 0};
							((GridBagLayout)panel3.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
							((GridBagLayout)panel3.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0, 1.0E-4};
							((GridBagLayout)panel3.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

							//---- label2 ----
							label2.setText("Name:");
							label2.setFont(new Font("Verdana", Font.PLAIN, 12));
							panel3.add(label2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
								GridBagConstraints.CENTER, GridBagConstraints.BOTH,
								new Insets(0, 0, 5, 5), 0, 0));

							//---- textField1 ----
							textField1.setEditable(false);
							textField1.setFont(new Font("Verdana", Font.PLAIN, 12));
							panel3.add(textField1, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
								GridBagConstraints.CENTER, GridBagConstraints.BOTH,
								new Insets(0, 0, 5, 0), 0, 0));

							//---- label3 ----
							label3.setText("ID:");
							label3.setFont(new Font("Verdana", Font.PLAIN, 12));
							panel3.add(label3, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
								GridBagConstraints.CENTER, GridBagConstraints.BOTH,
								new Insets(0, 0, 5, 5), 0, 0));

							//---- textField2 ----
							textField2.setEditable(false);
							textField2.setFont(new Font("Verdana", Font.PLAIN, 12));
							panel3.add(textField2, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
								GridBagConstraints.CENTER, GridBagConstraints.BOTH,
								new Insets(0, 0, 5, 0), 0, 0));

							//---- label4 ----
							label4.setText("Status:");
							label4.setFont(new Font("Verdana", Font.PLAIN, 12));
							panel3.add(label4, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
								GridBagConstraints.CENTER, GridBagConstraints.BOTH,
								new Insets(0, 0, 0, 5), 0, 0));
						}
						panel2.add(panel3, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 5, 0), 0, 0));

						//======== panel4 ========
						{
							panel4.setBorder(new EtchedBorder());
							panel4.setLayout(new GridBagLayout());
							((GridBagLayout)panel4.getLayout()).columnWidths = new int[] {10, 0, 0};
							((GridBagLayout)panel4.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
							((GridBagLayout)panel4.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
							((GridBagLayout)panel4.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
						}
						panel2.add(panel4, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 0, 0), 0, 0));
					}
					scrollPane1.setViewportView(panel2);
				}
				splitPane1.setLeftComponent(scrollPane1);

				//======== scrollPane2 ========
				{

					//======== panel6 ========
					{
						panel6.setBorder(new EtchedBorder());
						panel6.setAlignmentY(0.0F);
						panel6.setLayout(new FlowLayout(FlowLayout.LEFT));
					}
					scrollPane2.setViewportView(panel6);
				}
				splitPane1.setRightComponent(scrollPane2);
			}
			splitPane2.setTopComponent(splitPane1);

			//======== scrollPane3 ========
			{

				//---- textPane1 ----
				textPane1.setEditable(false);
				textPane1.setFont(new Font("Verdana", Font.PLAIN, 12));
				scrollPane3.setViewportView(textPane1);
			}
			splitPane2.setBottomComponent(scrollPane3);
		}
		contentPane.add(splitPane2, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JMenuBar menuBar1;
	private JMenu menu1;
	protected JMenuItem menuItem2;
	private JMenu menu3;
	protected JMenuItem menuItem3;
	protected JMenu menuItem4;
	private JMenu menu2;
	protected JMenuItem menuItem1;
	private JLabel label5;
	private JPanel panel1;
	private JLabel label1;
	public JComboBox<String> comboBox1;
	public JToggleButton button1;
	private JPanel panel7;
	private JLabel label6;
	public JTextField textField7;
	private JSplitPane splitPane2;
	private JSplitPane splitPane1;
	private JScrollPane scrollPane1;
	private JPanel panel2;
	public JPanel panel3;
	private JLabel label2;
	public JTextField textField1;
	private JLabel label3;
	public JTextField textField2;
	private JLabel label4;
	protected JPanel panel4;
	protected JScrollPane scrollPane2;
	public JPanel panel6;
	private JScrollPane scrollPane3;
	public JTextPane textPane1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
