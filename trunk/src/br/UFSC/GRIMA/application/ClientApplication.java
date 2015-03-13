package br.UFSC.GRIMA.application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingWorker;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import br.UFSC.GRIMA.application.entities.devices.MTConnectDevicesType;
import br.UFSC.GRIMA.application.entities.streams.MTConnectStreamsType;
import br.UFSC.GRIMA.application.visual.AboutWindow;
import br.UFSC.GRIMA.application.visual.BeginWindow;
import main.java.us.sosia.video.stream.agent.StreamClient;


public class ClientApplication extends BeginWindow implements ActionListener
{
	int conterC = 0; // --> Coluna, Column
	boolean flag;
	boolean b = true;
	boolean s = true;
	boolean c = true;
	boolean e = true;
	public ArrayList<JPanel> panelList = new ArrayList<JPanel>();
	public ArrayList<JTextField> textFieldList = new ArrayList<JTextField>();
	public ArrayList<Integer> buttons = new ArrayList<Integer>();
	public ArrayList<JTextField> textFieldTimeList = new ArrayList<JTextField>();
	public Agent agent = null; // new Agent("MTConnect", "http://agent.mtconnect.org/" , "150.162.105.71");
	private Font buttonsFont = new Font("Verdana", Font.PLAIN, 12);
	private Font tittlesFont = new Font("Khmer UI", Font.BOLD, 12);
	private Font subtittlesFont = new Font("Euphemia", Font.BOLD, 12);
	private Font dataFont = new Font("Verdana", Font.PLAIN, 12);
	private Font textPaneFont = new Font("Verdana", Font.PLAIN, 12);
	
	public ClientApplication(Agent agent)
	{
		this.comboBox1.addActionListener(this);
		this.menuItem1.addActionListener(this);
		this.button1.addActionListener(this);
		this.menuItem2.addActionListener(this);
		this.menuItem3.addActionListener(this);
		this.menuItem4.addActionListener(this);
		this.panel4.setVisible(false);
		this.adjustJFrame();
		this.setVisible(true);
		this.textPane1.setText("History:");
		this.agent = agent;
	}
	public void adjustJFrame()
	{
		Toolkit toolkit = this.getToolkit();
		this.pack();
		Dimension windowDimension = toolkit.getScreenSize();
		this.setSize(new Dimension((int)(windowDimension.width/2), (int)(windowDimension.height / 2)));
		Dimension thisDimension = this.getSize();
		this.setLocation((int) (windowDimension.getWidth() - thisDimension.getWidth()) / 2, (int) (windowDimension.getHeight() - thisDimension.getHeight()) / 2);
	}

	public void actionPerformed(ActionEvent event)
	{
		int index = comboBox1.getSelectedIndex();
		Object source = event.getSource();
		if (source == button1)
		{
			if(comboBox1.getSelectedIndex() == 2)
			{
				if(button1.isSelected())
				{
					b = !b;
//					button1.setText("Play");
					button1.setText("");
					button1.setIcon(new ImageIcon(getClass().getResource("/images/play.png")));
					button1.setToolTipText("Play");
				}
				else if(!button1.isSelected())
				{
					b = !b;
//					button1.setText("Stop");
					button1.setText("");
					button1.setIcon(new ImageIcon(getClass().getResource("/images/pause.png")));
					button1.setToolTipText("Pause");
				}
			}
		}
		if(source == comboBox1)
		{
			if ( 2 == index) // Current 
			{
				try {
					buttons.removeAll(buttons);
					textFieldList.removeAll(textFieldList);
					panel4.removeAll();
					button1.setVisible(true);
					button1.setIcon(new ImageIcon(getClass().getResource("/images/pause.png")));
					button1.setToolTipText("Pause");
					for (int i = 0 ; i < panelList.size(); i++)
					{
						panelList.get(i).removeAll();
						panel6.remove(panelList.get(i));
					}
					panelList.removeAll(panelList);
					System.out.println(agent.getIP());
					current();
					worker.execute();
					b = true;
					this.revalidate();
					this.repaint();
				} catch (MalformedURLException | JAXBException e) {
					e.printStackTrace();
				}
			}
			if (3 == index) // Probe
			{
				try {
					b = false;
					textFieldList.removeAll(textFieldList);
					button1.setVisible(false);
					buttons.removeAll(buttons);
					panel4.removeAll();
					for (int i = 0 ; i < panelList.size(); i++)
					{
						panelList.get(i).removeAll();
						panel6.remove(panelList.get(i));
					}
					panelList.removeAll(panelList);
					probe();
					worker.execute();
					this.revalidate();
					this.repaint();
				} catch (MalformedURLException | JAXBException e) {
					e.printStackTrace();
				}
			}
			if (4 == index) // Sample
			{
				b = false;
				textFieldList.removeAll(textFieldList);
				button1.setVisible(false);
				buttons.removeAll(buttons);
				panel4.removeAll();
				for (int i = 0 ; i < panelList.size(); i++)
				{
					panelList.get(i).removeAll();
					panel6.remove(panelList.get(i));
				}
				panelList.removeAll(panelList);
				try {
					sample();
					worker.execute();
//					a = true;
				} catch (MalformedURLException | JAXBException e) {
					e.printStackTrace();
				}
				this.revalidate();
				this.repaint();
			}
		}
		if (source == menuItem1)
		{
			AboutWindow nojaJanela = new AboutWindow(this);
			nojaJanela.setVisible(true);
		}
		else if(source == menuItem2)
		{
			System.exit(EXIT_ON_CLOSE);
		} else if(source == menuItem3)
		{
			new ConfigureAgentWindow(this, agent);
		}
		else if(source == menuItem4)
		{
			new StreamClient(agent.getIpCamera());
			textPane1.setText(textPane1.getText() + "\n" + "Webcam ON!");
			
			
		}
	}
	
	public void current() throws JAXBException, MalformedURLException //--> O Que deve aparecer para o usu�rio em rela��o � m�quina selecionada.
	{
		JAXBContext jc = JAXBContext.newInstance(MTConnectStreamsType.class);
		Unmarshaller u = jc.createUnmarshaller();
//		URL url = new URL( "http://agent.mtconnect.org/current" );
		URL url = new URL(agent.getIP() + "/current" );
		JAXBElement<MTConnectStreamsType> element =(JAXBElement<MTConnectStreamsType>)u.unmarshal(url);
		final MTConnectStreamsType current = element.getValue();
		System.out.println(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(7).getSamples().getSample().get(1).getValue().getValue());
		textField1.setText(current.getStreams().getDeviceStream().get(0).getName()); // -- Nome da Machine
		textField2.setText(current.getStreams().getDeviceStream().get(0).getUuid()); // -- ID ---> padr�o pra todas
		int cont = current.getStreams().getDeviceStream().get(0).getComponentStream().size(); // --> Numero de Components Streams!
		panel4.setVisible(true);
		for (int i = 0; i < cont ; i++) //---> Quantos Botoes deve colocar devido aos dados de ComponentStream
		{
			final JToggleButton toggleTemp = new JToggleButton();
			//---- toggleButton1 ----
			toggleTemp.setFont(buttonsFont);
			toggleTemp.setName(""+i);
			toggleTemp.setText(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(i).getComponent()+"-"+ current.getStreams().getDeviceStream().get(0).getComponentStream().get(i).getName());
			JPanel panel5 = new JPanel();
			panelList.add(panel5);
			panel4.add(toggleTemp, new GridBagConstraints(1, i, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 0), 0, 0));
			toggleTemp.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					if(toggleTemp.isSelected())
					{
						int conterR = 0; // --> Linha, Row
						int j = Integer.parseInt(toggleTemp.getName());
						if(!buttons.contains(j))
						{
						//JLabel label6 = new JLabel();
						///////////////////////////////////
						{
							panelList.get(j).setLayout(new GridBagLayout());
							((GridBagLayout)panelList.get(j).getLayout()).columnWidths = new int[] {0, 0, 0};
							((GridBagLayout)panelList.get(j).getLayout()).rowHeights = new int[] {0, 0, 0, 0};
							((GridBagLayout)panelList.get(j).getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
							((GridBagLayout)panelList.get(j).getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
						}
						panel6.add(panelList.get(j), new GridBagConstraints(0+ conterC, 0, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 5, 5), 0, 0));
//						panelList.get(j).setBorder(new CompoundBorder(
//								new TitledBorder(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getComponent()+"-"+ current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getName()),
//								new EmptyBorder(5, 5, 5, 5)));
						
						//===========
						panelList.get(j).setBorder(new CompoundBorder(
								new TitledBorder(null, "" + ""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getComponent()+" - "+ current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getName(), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, subtittlesFont),
								null));
						//===========
						
						buttons.add(j);
						//////////////////////////
						//label6.setText(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getComponent()+"-"+ current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getName());
						//panel5.add(label6, new GridBagConstraints(1 + conterC, 0, 1, 1, 0.0, 0.0,
						//	GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						//	new Insets(0, 0, 5, 0), 0, 0));
						try
						{
						if (current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getSamples().getSample().size() >= 1)
						{
							//---- label7 ----
							JLabel label7 = new JLabel();
							label7.setFont(tittlesFont);
							label7.setText("Samples:");
							label7.setForeground(new Color(25, 25, 112));
							panelList.get(j).add(label7, new GridBagConstraints(1 + conterC, 1, 1, 1, 0.0, 0.0,
								GridBagConstraints.CENTER, GridBagConstraints.BOTH,
								new Insets(0, 0, 5, 0), 0, 0));
							
							for (int i = 0 ; i < current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getSamples().getSample().size(); i++)
							{ 
								//---- label8 ----
								JLabel label8 = new JLabel();
								label8.setFont(subtittlesFont);
								JTextField textField3 = new JTextField();
								textField3.setFont(dataFont);
								if(current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getSamples().getSample().get(i).getValue().getName()!=null)
									label8.setText(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getSamples().getSample().get(i).getValue().getName());
								else
									label8.setText(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getSamples().getSample().get(i).getValue().getDataItemId());
								String string = (String)current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getSamples().getSample().get(i).getValue().getValue();
								if (string.toUpperCase().equals("AVAILABLE") || string.toUpperCase().equals("ACTIVE"))
								{
									textField3.setForeground(new Color(0,128,0));
								}
									else if(string.toUpperCase().equals("UNAVAILABLE"))
								{
									textField3.setForeground(Color.RED);	
								}
								textField3.setText(string);
								textField3.setEditable(false);
								panelList.get(j).add(label8, new GridBagConstraints(0 + conterC, 2+ conterR, 1, 1, 0.0, 0.0,
									GridBagConstraints.CENTER, GridBagConstraints.BOTH,
									new Insets(0, 0, 5, 5), 0, 0));
								panelList.get(j).add(textField3, new GridBagConstraints(1 + conterC, 2+ conterR, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
									new Insets(0, 0, 5, 0), 0, 0));
								textFieldList.add(textField3);
								conterR++;
							}
							revalidate();
							repaint();
						}
						}
						catch(Exception sampleError)
						{
							System.out.println("No Samples");
							conterR= conterR -1;
						}
						try
						{
						if (current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().size() >= 1)
						{
							//---- label7 ----
							JLabel label7 = new JLabel();
							label7.setFont(tittlesFont);
							label7.setText("Conditions:");
							label7.setForeground(new Color(25, 25, 112));
							panelList.get(j).add(label7, new GridBagConstraints(1+ conterC, 2+conterR , 1, 1, 0.0, 0.0,
								GridBagConstraints.CENTER, GridBagConstraints.BOTH,
								new Insets(0, 0, 5, 0), 0, 0));
							conterR++;
							for (int i = 0 ; i < current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().size(); i++)
							{
								//---- label8 ----
								JLabel label8 = new JLabel();
								label8.setFont(subtittlesFont);
								JTextField textField3 = new JTextField();
								textField3.setFont(buttonsFont);
								label8.setText(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getValue().getDataItemId());
								if(current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getName().getLocalPart() == "Normal" | current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getName().getLocalPart().equals("AVAILABLE"))
									textField3.setForeground(new Color(0,128,0));
								else if(current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getName().getLocalPart() == "Warning" | current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getName().getLocalPart() == "Unavailable" | current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getName().getLocalPart().equals("UNAVAILABLE"))
									textField3.setForeground(Color.RED);
//								if(current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getName().getLocalPart().equals("Warning"))
//								{
//									textPane1.setText("Warning Type: "+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getValue().getType()+""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getValue().getValue());
//								} 

								textField3.setText(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getName().getLocalPart());
								textField3.setEditable(false);
								panelList.get(j).add(label8, new GridBagConstraints(0 + conterC, 2+ conterR, 1, 1, 0.0, 0.0,
										GridBagConstraints.CENTER, GridBagConstraints.BOTH,
									new Insets(0, 0, 5, 5), 0, 0));
								panelList.get(j).add(textField3, new GridBagConstraints(1 + conterC, 2+ conterR, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
									new Insets(0, 0, 5, 0), 0, 0));
								textFieldList.add(textField3);
								conterR++;
							}
							revalidate();
							repaint();
						}
						}
						catch(Exception conditionsError)
						{
							System.out.println("No Conditions");
						}
						try
						{
						if (current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().size() >= 1)
						{
							//---- label7 ----
							JLabel label7 = new JLabel();
							label7.setFont(tittlesFont);
							label7.setText("Events:");
							label7.setForeground(new Color(25, 25, 112));
							panelList.get(j).add(label7, new GridBagConstraints(1+ conterC, 2+conterR , 1, 1, 0.0, 0.0,
								GridBagConstraints.CENTER, GridBagConstraints.BOTH,
								new Insets(0, 0, 5, 0), 0, 0));
							conterR++;
							for (int i = 0 ; i < current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().size(); i++)
							{
								//---- label8 ----
								JLabel label8 = new JLabel();
								label8.setFont(subtittlesFont);
								JTextField textField3 = new JTextField();
								textField3.setFont(buttonsFont);
								String name = current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getName();
								if(name == null)
									label8.setText(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getDataItemId());
								else
									label8.setText(""+name);	
								if(current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue() == "Normal" | current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().equals("AVAILABLE") | current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().equals("ON") || current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().equals("ARMED") ||current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().equals("ACTIVE"))
									textField3.setForeground(new Color(0,128,0));
								else if(current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue() == "Warning" | current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue() == "Unavailable" | current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().equals("UNAVAILABLE") || current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().equals("OFF"))
									textField3.setForeground(Color.RED);
							
								textField3.setText(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue());
								textField3.setEditable(false);
								panelList.get(j).add(label8, new GridBagConstraints(0 + conterC, 2+ conterR, 1, 1, 0.0, 0.0,
									GridBagConstraints.CENTER, GridBagConstraints.BOTH,
									new Insets(0, 0, 5, 5), 0, 0));
								panelList.get(j).add(textField3, new GridBagConstraints(1 + conterC, 2+ conterR, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
									new Insets(0, 0, 5, 0), 0, 0));
								textFieldList.add(textField3);
								conterR++;
							}
							revalidate();
							repaint();
						}
						}
						catch(Exception conditionsError)
						{
							System.out.println("No Events");
						}
						conterC = conterC+1;
					}
						else
						{
							panelList.get(j).setVisible(true);
						}
					}
					else if (!toggleTemp.isSelected())
					{
						int j = Integer.parseInt(toggleTemp.getName());
						panelList.get(j).setVisible(false);
						//panel6.remove(panelList.get(j));
						//conterC = conterC -1;
						revalidate();
						repaint();
					}
				}
			});
		}
	}
	
	public void probe() throws JAXBException, MalformedURLException
	{
		JAXBContext jc = JAXBContext.newInstance(MTConnectDevicesType.class);
		Unmarshaller u = jc.createUnmarshaller();
//		URL url = new URL( "http://agent.mtconnect.org/probe" );
		URL url = new URL(agent.getIP() + "/probe");
		JAXBElement<MTConnectDevicesType> element = extracted(u, url);
		final MTConnectDevicesType probe = element.getValue();
		textField1.setText(""+probe.getDevices().getDevice().get(0).getName());
		textField2.setText(""+ probe.getDevices().getDevice().get(0).getUuid());
		panel4.setVisible(true);
		int cont  = probe.getDevices().getDevice().get(0).getComponents().getComponent().size();
		for (int i = 0; i < cont ; i++) //---> Quantos Bot�es deve colocar devido aos dados de ComponentStream
		{
			final JToggleButton toggleTemp = new JToggleButton();
			//---- toggleButton1 ----
			toggleTemp.setFont(buttonsFont);
			toggleTemp.setName(""+i);
			toggleTemp.setText(""+ probe.getDevices().getDevice().get(0).getComponents().getComponent().get(i).getName().getLocalPart());
			JPanel panel5 = new JPanel();
			panelList.add(panel5);
			panel4.add(toggleTemp, new GridBagConstraints(1, i, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 0), 0, 0));
		
			toggleTemp.addActionListener(new ActionListener() 
			{
				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					if(toggleTemp.isSelected())
					{
						int conterR = 0; // --> Linha, Row
						int j = Integer.parseInt(toggleTemp.getName());
						if(!buttons.contains(j))
						{
						buttons.add(j);
						//JLabel label6 = new JLabel();
						///////////////////////////////////
						{
							panelList.get(j).setLayout(new GridBagLayout());
							((GridBagLayout)panelList.get(j).getLayout()).columnWidths = new int[] {0, 0, 0};
							((GridBagLayout)panelList.get(j).getLayout()).rowHeights = new int[] {0, 0, 0, 0};
							((GridBagLayout)panelList.get(j).getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
							((GridBagLayout)panelList.get(j).getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
						}
						panel6.add(panelList.get(j), new GridBagConstraints(0+ conterC, 0, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 5, 5), 0, 0));
//						panelList.get(j).setBorder(new CompoundBorder(
//								new TitledBorder(""+probe.getDevices().getDevice().get(0).getComponents().getComponent().get(j).getName().getLocalPart()),
//								new EmptyBorder(5, 5, 5, 5)));
						
						//===========
						panelList.get(j).setBorder(new CompoundBorder(
								new TitledBorder(null, "" + probe.getDevices().getDevice().get(0).getComponents().getComponent().get(j).getName().getLocalPart(), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, subtittlesFont),
								null));
						//===========
						
						for (int i = 0; i < probe.getDevices().getDevice().get(0).getComponents().getComponent().get(j).getValue().getComponents().getComponent().size(); i++)
						{
							//---- label7 ----
							JLabel label7 = new JLabel();
							label7.setFont(tittlesFont);
							label7.setText(""+ probe.getDevices().getDevice().get(0).getComponents().getComponent().get(j).getValue().getComponents().getComponent().get(i).getName().getLocalPart()+"-"+probe.getDevices().getDevice().get(0).getComponents().getComponent().get(j).getValue().getComponents().getComponent().get(i).getValue().getId());
							label7.setForeground(new Color(25, 25, 112));
							panelList.get(j).add(label7, new GridBagConstraints(1 + conterC, 2+conterR, 1, 1, 0.0, 0.0,
								GridBagConstraints.CENTER, GridBagConstraints.BOTH,
								new Insets(0, 0, 5, 0), 0, 0));
							conterR++;
							for (int z = 0; z < probe.getDevices().getDevice().get(0).getComponents().getComponent().get(j).getValue().getComponents().getComponent().get(i).getValue().getDataItems().getDataItem().size(); z++)
							{
								//---- label8 ----
								JLabel label8 = new JLabel();
								label8.setFont(subtittlesFont);
								JTextField textField3 = new JTextField();
								textField3.setFont(dataFont);
								if (probe.getDevices().getDevice().get(0).getComponents().getComponent().get(j).getValue().getComponents().getComponent().get(i).getValue().getDataItems().getDataItem().get(z).getName() != null)
									label8.setText(""+probe.getDevices().getDevice().get(0).getComponents().getComponent().get(j).getValue().getComponents().getComponent().get(i).getValue().getDataItems().getDataItem().get(z).getName());
								else
									label8.setText(""+probe.getDevices().getDevice().get(0).getComponents().getComponent().get(j).getValue().getComponents().getComponent().get(i).getValue().getDataItems().getDataItem().get(z).getId());
								if(probe.getDevices().getDevice().get(0).getComponents().getComponent().get(j).getValue().getComponents().getComponent().get(i).getValue().getDataItems().getDataItem().get(z).getUnits() != null)
									textField3.setText(""+probe.getDevices().getDevice().get(0).getComponents().getComponent().get(j).getValue().getComponents().getComponent().get(i).getValue().getDataItems().getDataItem().get(z).getUnits());
								else
									textField3.setText(""+probe.getDevices().getDevice().get(0).getComponents().getComponent().get(j).getValue().getComponents().getComponent().get(i).getValue().getDataItems().getDataItem().get(z).getType());
								textField3.setEditable(false);
								panelList.get(j).add(label8, new GridBagConstraints(conterC, 2+ conterR, 1, 1, 0.0, 0.0,
									GridBagConstraints.CENTER, GridBagConstraints.BOTH,
									new Insets(0, 0, 5, 5), 0, 0));
								panelList.get(j).add(textField3, new GridBagConstraints(1 + conterC, 2+ conterR, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
									new Insets(0, 0, 5, 0), 0, 0));
								conterR++;
							}	
						}
						conterC++;
						revalidate();
						repaint();
						}
						else
						{
							panelList.get(j).setVisible(true);
						}
						
					}
					else if (!toggleTemp.isSelected())
					{
						int j = Integer.parseInt(toggleTemp.getName());
						panelList.get(j).setVisible(false);
						//panel6.remove(panelList.get(j));
						//conterC = conterC -1;
						revalidate();
						repaint();
					}
				}
			});
		}
	}
	
	public void sample() throws JAXBException, MalformedURLException
	{
		JAXBContext jc = JAXBContext.newInstance(MTConnectStreamsType.class);
		Unmarshaller u = jc.createUnmarshaller();
//		URL url = new URL( "http://agent.mtconnect.org/sample" );
		URL url = new URL(agent.getIP() + "/sample" );
		JAXBElement<MTConnectStreamsType> element =(JAXBElement<MTConnectStreamsType>)u.unmarshal(url);
		final MTConnectStreamsType sample = element.getValue();
		textField1.setText(""+sample.getStreams().getDeviceStream().get(0).getName());
		textField2.setText(""+sample.getStreams().getDeviceStream().get(0).getUuid());
		panel4.setVisible(true);
		int cont = sample.getStreams().getDeviceStream().get(0).getComponentStream().size();
		for (int i = 0 ; i < cont ; i++)
		{
			final JToggleButton toggleTemp = new JToggleButton();
			//---- toggleButton1 ----
			toggleTemp.setFont(buttonsFont);
			toggleTemp.setName(""+i);
			toggleTemp.setText(""+ sample.getStreams().getDeviceStream().get(0).getComponentStream().get(i).getComponent()+ "-"+sample.getStreams().getDeviceStream().get(0).getComponentStream().get(i).getName() );
			JPanel panel5 = new JPanel();
			panelList.add(panel5);
			panel4.add(toggleTemp, new GridBagConstraints(1, i, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 0), 0, 0));
			toggleTemp.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					if(toggleTemp.isSelected())
					{
						int conterR = 0; // --> Linha, Row
						int j = Integer.parseInt(toggleTemp.getName());
						///////////////////////////////////
					{
					panelList.get(j).setLayout(new GridBagLayout());
					((GridBagLayout)panelList.get(j).getLayout()).columnWidths = new int[] {0, 0, 0};
					((GridBagLayout)panelList.get(j).getLayout()).rowHeights = new int[] {0, 0, 0, 0};
					((GridBagLayout)panelList.get(j).getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
					((GridBagLayout)panelList.get(j).getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
					}
				panel6.add(panelList.get(j), new GridBagConstraints(0+ conterC, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
//				panelList.get(j).setBorder(new CompoundBorder(
//						new TitledBorder(""+ sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getComponent()+ "-"+sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getName()),
//						new EmptyBorder(5, 5, 5, 5)));
				
				//===========
				panelList.get(j).setBorder(new CompoundBorder(
						new TitledBorder(null, "" + sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getComponent()+ "-"+sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getName(), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, subtittlesFont),
						null));
				//===========
				
				try
				{
				JLabel label7 = new JLabel();
				label7.setFont(tittlesFont);
				label7.setText("Samples:");
				label7.setForeground(new Color(25, 25, 112));
				panelList.get(j).add(label7, new GridBagConstraints(1 + conterC, 2+conterR, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));
				conterR++;
				for (int i = 0 ; i < sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getSamples().getSample().size() ; i++)
				{
					//---- label8 ----
					JLabel label8 = new JLabel();
					label8.setFont(subtittlesFont);
					JLabel label9 = new JLabel();
					JTextField textField4 = new JTextField();
					JTextField textField3 = new JTextField();
					if (sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getSamples().getSample().get(i).getValue().getName()!= null)
						label8.setText(""+sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getSamples().getSample().get(i).getValue().getName());
					else
						label8.setText(""+sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getSamples().getSample().get(i).getValue().getDataItemId());
					label9.setText("Time: ");
					String string = sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getSamples().getSample().get(i).getValue().getValue();
					textField3.setText(string);
					textField4.setText(""+sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getSamples().getSample().get(i).getValue().getTimestamp());
					textField3.setEditable(false);
					textField4.setEditable(false);
					panelList.get(j).add(label8, new GridBagConstraints(0 + conterC, 2+ conterR, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 5), 0, 0));
					panelList.get(j).add(label9, new GridBagConstraints(2 + conterC, 2+ conterR, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 5, 5), 0, 0));
					panelList.get(j).add(textField3, new GridBagConstraints(1 + conterC, 2+ conterR, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 0), 0, 0));
					panelList.get(j).add(textField4, new GridBagConstraints(3 + conterC, 2+ conterR, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 5, 0), 0, 0));
					conterR++;
//					textFieldList.add(textField3);
//					textFieldTimeList.add(textField4);
					
				}
				revalidate();
				repaint();
				}
				catch(Exception sampleError)
				{
					System.out.println("No Samples");
				}
				
				try
				{
					int conter = sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().size();
					JLabel label7 = new JLabel();
					label7.setText("Events:");
					label7.setForeground(new Color(25, 25, 112));
					panelList.get(j).add(label7, new GridBagConstraints(1 + conterC, 3+conterR, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 0), 0, 0));
					conterR++;
				for (int i = 0 ; i < conter ; i++)
				{
					//---- label8 ----
					JLabel label8 = new JLabel();
					JLabel label9 = new JLabel();
					JTextField textField4 = new JTextField();
					JTextField textField3 = new JTextField();
					if (sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getName()!= null)
						label8.setText(""+sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getName());
					else
						label8.setText(""+sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getDataItemId());
					label9.setText("Time: ");
					String string = sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue();
					textField3.setText(string);
					textField4.setText(""+sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getTimestamp());
					textField3.setEditable(false);
					textField4.setEditable(false);
					panelList.get(j).add(label8, new GridBagConstraints(0 + conterC, 4+ conterR, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 5), 0, 0));
					panelList.get(j).add(label9, new GridBagConstraints(2 + conterC, 4+ conterR, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 5, 5), 0, 0));
					panelList.get(j).add(textField3, new GridBagConstraints(1 + conterC, 4+ conterR, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 0), 0, 0));
					panelList.get(j).add(textField4, new GridBagConstraints(3 + conterC, 4+ conterR, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 5, 0), 0, 0));
					conterR++;
//					textFieldList.add(textField3);
//					textFieldTimeList.add(textField4);
				}
				
				revalidate();
				repaint();
				}
				catch (Exception NoEvents)
				{
					System.out.println("No Events");
				}
				}
				else if (!toggleTemp.isSelected())
				{
					int j = Integer.parseInt(toggleTemp.getName());
					panelList.get(j).removeAll();
					panel6.remove(panelList.get(j));
					//conterC = conterC -1;
					revalidate();
					repaint();
				}
				}
			});
		}
	}
	
	private JAXBElement<MTConnectDevicesType> extracted(Unmarshaller u, URL url) throws JAXBException 
	{
		return (JAXBElement<MTConnectDevicesType>)u.unmarshal(url);
	}
/////////////////////////////////////////- Teste
	SwingWorker worker = new SwingWorker()  // Atualizador em Tempo Real!
	{
		@Override
		protected Object doInBackground() throws Exception 
		{
			while(true)
			{
				try
				{
					JAXBContext jct = JAXBContext.newInstance(MTConnectStreamsType.class);
					Unmarshaller ut = jct.createUnmarshaller();
	//				URL urlt = new URL( "http://agent.mtconnect.org/current" );
					URL urlt = new URL(agent.getIP() + "/current" );
					JAXBElement<MTConnectStreamsType> elementt =(JAXBElement<MTConnectStreamsType>)ut.unmarshal(urlt);
					final MTConnectStreamsType currentt = elementt.getValue();
					textField7.setText(""+currentt.getHeader().getCreationTime());
				}
				catch(Exception connectionError)
				{
				    JOptionPane.showMessageDialog(null, "Connection Lost", "Error", JOptionPane.ERROR_MESSAGE);

				}
				while(b)
				{
					MTConnectStreamsType current = null;
					try
					{
						JAXBContext jc = JAXBContext.newInstance(MTConnectStreamsType.class);
						Unmarshaller u = jc.createUnmarshaller();
						URL url = new URL(agent.getIP() +  "/current");
						JAXBElement<MTConnectStreamsType> element =(JAXBElement<MTConnectStreamsType>)u.unmarshal(url);
						current = element.getValue();
						textField7.setText(""+current.getHeader().getCreationTime());
						flag = true;
					}
					catch(Exception connectionError)
					{
						JOptionPane.showMessageDialog(null, "Connection Lost", "Error", JOptionPane.ERROR_MESSAGE);
						
						flag = false;
					}
					int k = 0;
					while(k < textFieldList.size() && flag)
					{
						for(int z = 0 ; z < buttons.size() ; z++)
						{	
							int j = buttons.get(z);
							c = true;
							s = true;
							e = true;
							try
							{
								if(s)
								{
									for (int i = 0 ; i < current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getSamples().getSample().size(); i++)
									{
										String string = (String)current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getSamples().getSample().get(i).getValue().getValue();
										if (string.toUpperCase().equals("AVAILABLE") || string.toUpperCase().equals("ACTIVE"))
										{
											textFieldList.get(k).setForeground(new Color(0,128,0));
										}
										else if(string.toUpperCase().equals("UNAVAILABLE"))
										{
												textFieldList.get(k).setForeground(Color.RED);	
										}
										textFieldList.get(k).setText(string);
										k++;
									}
								}
							}
							catch (Exception error)
							{
								s = false;
								System.out.println("Estive aqui S");
							}
							try
							{
								if (c)
								{
									for (int i = 0 ; i < current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().size(); i++)
									{
										
										if(current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getName().getLocalPart() == "Normal" | current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getName().getLocalPart().equals("AVAILABLE"))
										{	
											textFieldList.get(k).setForeground(new Color(0,128,0));
										}
										else if(current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getName().getLocalPart() == "Warning" | current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getName().getLocalPart() == "Unavailable" | current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getName().getLocalPart().equals("UNAVAILABLE"))
										{	
											textFieldList.get(k).setForeground(Color.RED);
											if(!textPane1.getText().contains(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getValue().getTimestamp()) && current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getName().getLocalPart() == "Warning" )
											{
												//textPane1.setForeground(Color.RED);
												textPane1.setText(textPane1.getText() + "\n"+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getName().getLocalPart()+" --> " + current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getValue().getValue()+ "---"+"Time:"+ current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getValue().getTimestamp());
											}
										}
										textFieldList.get(k).setText(current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getName().getLocalPart());
//										if(current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getName().getLocalPart().equals("Warning") && war == true)
//										{
//											textPane1.setForeground(Color.RED);
//											textPane1.setText(textPane1.getText() + "\n" + current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getValue().getValue()+ "---"+"Time:"+ current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getValue().getTimestamp()+ "\n");
//										}
										k++;
									}
								}
							}
							catch(Exception error2)
							{
							c = false;
							System.out.println("Estive aqui C");
							}
							try
							{
								if(e)
								{
									for (int i = 0 ; i < current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().size(); i++)
									{
										if(current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue() == "Normal" | current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().equals("AVAILABLE") | current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().equals("ON") || current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().equals("ARMED") ||current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().equals("ACTIVE"))
											textFieldList.get(k).setForeground(new Color(0,128,0));
										else if(current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue() == "Warning" | current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue() == "Unavailable" | current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().equals("UNAVAILABLE") || current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().equals("OFF"))
											textFieldList.get(k).setForeground(Color.RED);
										textFieldList.get(k).setText(current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue());
										if(current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().toUpperCase().equals("STOPPED") || current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().equals("OFF") )
										{
											//textPane1.setForeground(Color.RED);
											String value = current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue();
											if(!textPane1.getText().contains(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getTimestamp()))
												textPane1.setText(textPane1.getText() + "\n" + current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getName().getLocalPart()+" --> "+value + "---"+"Time:"+ current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getTimestamp());
										}
										else if(current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().toUpperCase().equals("ON")||current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().equals("ACTIVE") || current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().equals("READY"))
										{
											//textPane1.setForeground(Color.GREEN);
											String value = current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue();
											if(!textPane1.getText().contains(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getTimestamp()))
												textPane1.setText(textPane1.getText() + "\n"+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getName().getLocalPart()+" --> " + value + "---"+"Time:"+ current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getTimestamp());
										}
//										if(current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().toUpperCase().equals("ON"))
//										{
//											String name = current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue();
//											if(!textPane1.getText().contains(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getTimestamp()))
//												textPane1.setText(textPane1.getText() + "\n" + name + "---"+"Time:"+ current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getTimestamp());
//										}
										k++;
									}
								}
							}
							catch(Exception error3)
							{
								e = false;
								System.out.println("Estive aqui E");
							}
						}
					}
				}
			}
		}
//				while(a)
//				{
//					JAXBContext jc = JAXBContext.newInstance(MTConnectStreamsType.class);
//					Unmarshaller u = jc.createUnmarshaller();
//					URL url = new URL( "http://agent.mtconnect.org/sample" );
//					JAXBElement<MTConnectStreamsType> element =(JAXBElement<MTConnectStreamsType>)u.unmarshal(url);
//					final MTConnectStreamsType sample = element.getValue();
//								
//						for(int j = 0 ; j < buttons.size(); j++)
//								{
//									panelList.get(j).removeAll();
//									panel6.remove(panelList.get(j));
//									revalidate();
//									repaint();
//									int conterR = 0;
//									if(s)
//									{
//										try
//										{
//										for (int i = 0 ; i < sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getSamples().getSample().size(); i++)
//										{
//											//---- label8 ----
//											JLabel label8 = new JLabel();
//											JLabel label9 = new JLabel();
//											JTextField textField4 = new JTextField();
//											JTextField textField3 = new JTextField();
//											if (sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getSamples().getSample().get(i).getValue().getName()!= null)
//												label8.setText(""+sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getSamples().getSample().get(i).getValue().getName());
//											else
//												label8.setText(""+sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getSamples().getSample().get(i).getValue().getDataItemId());
//											label9.setText("Time: ");
//											String string = sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getSamples().getSample().get(i).getValue().getValue();
//											textField3.setText(string);
//											textField4.setText(""+sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getSamples().getSample().get(i).getValue().getTimestamp());
//											textField3.setEditable(false);
//											textField4.setEditable(false);
//											panelList.get(j).add(label8, new GridBagConstraints(0 + conterC, 2+ conterR, 1, 1, 0.0, 0.0,
//												GridBagConstraints.CENTER, GridBagConstraints.BOTH,
//												new Insets(0, 0, 5, 5), 0, 0));
//											panelList.get(j).add(label9, new GridBagConstraints(2 + conterC, 2+ conterR, 1, 1, 0.0, 0.0,
//													GridBagConstraints.CENTER, GridBagConstraints.BOTH,
//													new Insets(0, 0, 5, 5), 0, 0));
//											panelList.get(j).add(textField3, new GridBagConstraints(1 + conterC, 2+ conterR, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
//												new Insets(0, 0, 5, 0), 0, 0));
//											panelList.get(j).add(textField4, new GridBagConstraints(3 + conterC, 2+ conterR, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
//													new Insets(0, 0, 5, 0), 0, 0));
//											conterR++;
//											textFieldList.add(textField3);
//											textFieldTimeList.add(textField4);
//										}
//									}
//								
//								catch (Exception error)
//								{
//									s = false;
//									System.out.println("Estive aqui S");
//								}
//									}
//								if(e)	
//								{
//									try
//									{
//									for (int i = 0 ; i < sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().size(); i++)
//										{
//											//---- label8 ----
//											JLabel label8 = new JLabel();
//											JLabel label9 = new JLabel();
//											JTextField textField4 = new JTextField();
//											JTextField textField3 = new JTextField();
//											if (sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getName()!= null)
//												label8.setText(""+sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getName());
//											else
//												label8.setText(""+sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getDataItemId());
//											label9.setText("Time: ");
//											String string = sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue();
//											textField3.setText(string);
//											textField4.setText(""+sample.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getTimestamp());
//											textField3.setEditable(false);
//											textField4.setEditable(false);
//											panelList.get(j).add(label8, new GridBagConstraints(0 + conterC, 4+ conterR, 1, 1, 0.0, 0.0,
//												GridBagConstraints.CENTER, GridBagConstraints.BOTH,
//												new Insets(0, 0, 5, 5), 0, 0));
//											panelList.get(j).add(label9, new GridBagConstraints(2 + conterC, 4+ conterR, 1, 1, 0.0, 0.0,
//													GridBagConstraints.CENTER, GridBagConstraints.BOTH,
//													new Insets(0, 0, 5, 5), 0, 0));
//											panelList.get(j).add(textField3, new GridBagConstraints(1 + conterC, 4+ conterR, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
//												new Insets(0, 0, 5, 0), 0, 0));
//											panelList.get(j).add(textField4, new GridBagConstraints(3 + conterC, 4+ conterR, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
//													new Insets(0, 0, 5, 0), 0, 0));
//											conterR++;
//											textFieldList.add(textField3);
//											textFieldTimeList.add(textField4);
//										}
//									}
//								catch(Exception error3)
//								{
//									e = false;
//									System.out.println("Estive aqui E");
//								}
//							}
//						}
//				}
//			}				
//		}						
		protected void done()
		{
			System.out.println("Saiu");
		}
	};
	public Agent getAgent() 
	{
		return agent;
	}
	public void setAgent(Agent agent) 
	{
		this.agent = agent;
	}
////////////////////////////////////////////////////////////	
}
