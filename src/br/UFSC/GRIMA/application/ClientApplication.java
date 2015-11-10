package br.UFSC.GRIMA.application;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingWorker;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;

import org.jboss.ejb.plugins.JaccAuthorizationInterceptor;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import main.java.us.sosia.video.stream.agent.StreamClient;
import br.UFSC.GRIMA.application.entities.devices.ComponentType;
import br.UFSC.GRIMA.application.entities.devices.MTConnectDevicesType;
import br.UFSC.GRIMA.application.entities.streams.ComponentStreamType;
import br.UFSC.GRIMA.application.entities.streams.MTConnectStreamsType;
import br.UFSC.GRIMA.application.visual.AboutWindow;
import br.UFSC.GRIMA.application.visual.BeginWindow;
import br.UFSC.GRIMA.application.dataTools.GButton;
import br.UFSC.GRIMA.application.dataTools.GCheckbox;
import br.UFSC.GRIMA.application.dataTools.GComponent;
import br.UFSC.GRIMA.application.dataTools.GCondition;
import br.UFSC.GRIMA.application.dataTools.GDataserie;
import br.UFSC.GRIMA.application.dataTools.GDevice;
import br.UFSC.GRIMA.application.dataTools.GEvent;
import br.UFSC.GRIMA.application.dataTools.GSample;
import br.UFSC.GRIMA.application.dataTools.GSubComponent;
import br.UFSC.GRIMA.application.dataTools.SQLConnection;


public class ClientApplication extends BeginWindow implements ActionListener
{
	int conterC = 0; // --> Coluna, Column
	boolean flag;
	boolean b = true;
	boolean s = true;
	boolean c = true;
	boolean e = true;
	
	public ArrayList<JMenuItem> menuList = new ArrayList<JMenuItem>();
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
	
	private GDevice device;
	
	public ClientApplication(final Agent agent)
	{
////--------ajusta configurações da janela e suas açoes----------------------------------------------------------
		this.comboBox1.addActionListener(this);
		this.menuItem1.addActionListener(this);
		this.button1.addActionListener(this);
		this.toggleAbaValues.addActionListener(this);
		this.toggleAbaGraph.addActionListener(this);
		this.menuItem2.addActionListener(this);
		this.menuItem3.addActionListener(this);
		this.menuItem4.addActionListener(this);
		
		this.mainPanel.setVisible(false);
		this.panel9.setVisible(false);
		this.adjustJFrame();
		this.setVisible(true);
		textPane1.setText("History:");
///// ------------Ajusta configuracao da camera e afins------------------------------------------------------------		
		this.setAgent(agent);
		if(agent.getnCameras() > 0)
			
		{
			textPane1.setText(textPane1.getText() + "\n" + "*Client-Webcam Connected With Success: ");
			textPane1.setText(textPane1.getText() + "\n  Cams List: ");
			for(int i = 0; i < agent.getlistaPadrao().size() - 1; i++)
			{
				textPane1.setText(textPane1.getText() + "\n    " + (i + 1) + "  -->  " + agent.getlistaPadrao().get(i).substring(0, agent.getlistaPadrao().get(i).length() - 1));
			}
		}
		try
		{
			JAXBContext jc = JAXBContext.newInstance(MTConnectStreamsType.class);
			Unmarshaller u = jc.createUnmarshaller();
			URL url = new URL(agent.getIP() + "/current" );
			JAXBElement<MTConnectStreamsType> element =(JAXBElement<MTConnectStreamsType>)u.unmarshal(url);
			final MTConnectStreamsType teste = element.getValue();
			textPane1.setText(textPane1.getText() + "\n*" + agent.getName()+ "  Agent Connected With Success:");
			textPane1.setText(textPane1.getText() + "\n  " + agent.getIP());
			textPane1.setText(textPane1.getText() + "\n  ==============================================");
			System.out.println(element.getValue().getHeader().getCreationTime().getClass());
		}
		catch (Exception validate)
		{
			textPane1.setText(textPane1.getText() + "\n" + agent.getName()+ " Not Connected With Success");
		}
		for(int i = 0; i < this.agent.getnCameras(); i++)
		{
			JMenuItem menuItemTmp = new JMenuItem("" + agent.getlistaPadrao().get(i));
			menuList.add(menuItemTmp);
			this.menuItem4.add(menuItemTmp);
			menuItemTmp.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent event1) 
				{
					Object source = event1.getSource();
					/////////////////////////////////////
					int  index = menuList.indexOf(source);
					System.out.println(index);
					int port = 20000 - 10*index;
					System.out.println(port);
					menuList.get(index).setEnabled(false);
					new StreamClient(agent.getIpCamera(), port, menuList.get(index));
					
				}
			});
		}
		this.setDefaultCloseOperation(ClientApplication.EXIT_ON_CLOSE); 
		worker.execute();
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
	public void panelDestructor()
	{
		b = false;
		textFieldList.removeAll(textFieldList);
		mainPanel.removeAll();
		infoPanel.removeAll();
		try
		{
			panel5.remove(device.buttonPanel);
		}
		catch(Exception e)
		{
			System.out.println("no vpanel");
		}
		try
		{
			panel5.remove(device.checkBoxPanel);
		}
		catch(Exception e)
		{
			System.out.println("no vpanel");
		}
		buttons.removeAll(buttons);
		for (int i=0 ; i< panelList.size();i++)
		{
			panelList.get(i).removeAll();
			infoPanel.remove(panelList.get(i));
		}
		panelList.removeAll(panelList);

		panel9.setVisible(false);
		mainPanel.setVisible(false);
		infoPanel.setVisible(false);
		System.out.println("destrutor\n");
	}	
	public void addComponentLayout(Container container, Component component, int coluna, int linha, int colunasOcupadas, int linhasOcupadas, Insets insets)
	{
		container.add(component, new GridBagConstraints(coluna, linha, colunasOcupadas, linhasOcupadas, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
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
		else if(source == toggleAbaValues)
		{
			this.toggleAbaGraph.setSelected(!this.toggleAbaValues.isSelected());
			System.out.println("start");
			if (toggleAbaValues.isSelected())
			{
				try 
				{
					setCurrentValues();
					
				} catch (MalformedURLException e) 
				{
					e.printStackTrace();
					
				} catch (JAXBException e) 
				{
					e.printStackTrace();
				}
			}
			else
			{
				try {
					setCurrentGraphs();
				} catch (MalformedURLException | JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else if(source == toggleAbaGraph) 
		{
			this.toggleAbaValues.setSelected(!this.toggleAbaGraph.isSelected());
			System.out.println("start");
			if (toggleAbaGraph.isSelected())
			{
				try {
					setCurrentGraphs();
				} catch (MalformedURLException | JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				try 
				{
					setCurrentValues();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else if(source == comboBox1)
		{
			if ( 2 == index) // Current 
			{
				panelDestructor();
				panel9.setVisible(true);
				if (toggleAbaValues.isSelected())
				{
					try 
					{
						setCurrentValues();
					} catch (MalformedURLException | JAXBException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if (toggleAbaGraph.isSelected())
				{
					try {
						setCurrentGraphs();
					} catch (MalformedURLException | JAXBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if (3 == index) // Probe
			{
				try {
					panelDestructor();
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
				panelDestructor();
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
		else if (source == menuItem1)
		{
			AboutWindow nojaJanela = new AboutWindow(this);
			nojaJanela.setVisible(true);
		}
		else if(source == menuItem2)
		{
			System.exit(EXIT_ON_CLOSE);
		} 
		else if(source == menuItem3)
		{
			new ConfigureAgentWindow(this, agent);
		}
//		else if(source == menuItem4)
//		{
//			new StreamClient(agent.getIpCamera());
//			textPane1.setText(textPane1.getText() + "\n" + "Webcam ON!");
//			
//			
//		}
	}
	private void setCurrentValues() throws JAXBException, MalformedURLException
	{

		if (device == null)
		{
			System.out.println("criando novo device");
			JAXBContext jc = JAXBContext.newInstance(MTConnectStreamsType.class);
			Unmarshaller u = jc.createUnmarshaller();
			URL url = new URL(agent.getIP() + "/current" );
			JAXBElement<MTConnectStreamsType> element =(JAXBElement<MTConnectStreamsType>)u.unmarshal(url);
			final MTConnectStreamsType current = element.getValue();
			this.device = new GDevice(current.getStreams().getDeviceStream().get(0).getName(),
									  current.getStreams().getDeviceStream().get(0).getUuid());
			textField1.setText(device.getName());
			textField2.setText(device.getUuid());
			try
			{
				mainPanel.remove(device.checkBoxPanel);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			infoPanel.removeAll();
			System.out.println(device);
			addComponentLayout(panel5, device.buttonPanel, 0, 0, 1, 1, new Insets(0, 0, 5, 0));
			addComponentLayout(infoPanel, device.informationPanel, 0, 0, 1, 1, new Insets(0, 0, 5, 0));
			
			
			for (int i = 0; i< current.getStreams().getDeviceStream().get(0).getComponentStream().size(); i++)
			{
				System.out.println("criando botão " + i);
				final GButton toggleTemp = new GButton();
				toggleTemp.setFont(buttonsFont);
				toggleTemp.setText(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(i).getComponent()+"-"+ current.getStreams().getDeviceStream().get(0).getComponentStream().get(i).getName());
				addComponentLayout(device.buttonPanel, toggleTemp, 1, i, 1, 1, new Insets(0, 0, 5, 0));
				System.out.println("Criando component");
				GComponent component = new GComponent(current.getStreams().getDeviceStream().get(0).getComponentStream().get(i).getComponent(),
													  current.getStreams().getDeviceStream().get(0).getComponentStream().get(i).getName(), 
													  current.getStreams().getDeviceStream().get(0).getComponentStream().get(i).getComponentId());
				device.componentStreamList.add(component);
				component.setButton(toggleTemp);
				toggleTemp.setIndex(i);
				toggleTemp.addActionListener(new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent event) 
					{
						System.out.println("criando action listener");
						GButton button = (GButton) event.getSource();
						GComponent component = device.componentStreamList.get(button.getIndex());
						if (button.isSelected())
						{
							
							if (component.getComponentInfoPanel() == null)
							{
								JPanel componentPanel = new JPanel();
								component.setComponentInfoPanel(componentPanel);
								componentPanel.setLayout(new GridBagLayout());
								((GridBagLayout)componentPanel.getLayout()).columnWidths = new int[] {0, 0, 0};
								((GridBagLayout)componentPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
								((GridBagLayout)componentPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
								((GridBagLayout)componentPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
								
								componentPanel.setBorder(new CompoundBorder(
										new TitledBorder(null,component.getComponent()+" - "+ component.getName(), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, subtittlesFont),
										null));
								
								addComponentLayout(device.informationPanel, componentPanel, component.getButton().getIndex(), 0, 1, 1, new Insets(0, 0, 5, 5));
								ComponentStreamType jaxComponent = current.getStreams().getDeviceStream().get(0).getComponentStream().get(component.getButton().getIndex());
								int panelLine = 0;
								try
								{
									if(!jaxComponent.getSamples().getSample().isEmpty())
									{
										System.out.println("creating sample"); /////////////criando sample
										GSample componentSample = new GSample();
										System.out.println(componentSample);
										JLabel tittleSample = new JLabel();
										tittleSample.setFont(tittlesFont);
										tittleSample.setText("Samples:");
										tittleSample.setForeground(new Color(25, 25, 112));
										addComponentLayout(component.getComponentInfoPanel(), tittleSample, 0, panelLine, 1, 1, new Insets(0, 0, 5, 0));
										panelLine++;
										System.out.println("creating sample for: " + jaxComponent.getSamples().getSample().size());
										for(int i = 0; i < jaxComponent.getSamples().getSample().size();i++)
										{
											System.out.println(i +"o loop, name:" + jaxComponent.getSamples().getSample().get(i).getValue().getName() + " id: " + jaxComponent.getSamples().getSample().get(i).getValue().getDataItemId() + " componentIndex: " + button.getIndex() );
											GDataserie dataserie = new GDataserie(jaxComponent.getSamples().getSample().get(i).getValue().getName(), 
																				  jaxComponent.getSamples().getSample().get(i).getValue().getDataItemId(),
																				  button.getIndex(), 0, i, agent, device.categoryAxesValues);
											GSubComponent subComponent = new GSubComponent(jaxComponent.getSamples().getSample().get(i).getValue().getName(), 
																						   jaxComponent.getSamples().getSample().get(i).getValue().getDataItemId());
											System.out.println("criados ds e subcomponent" + dataserie + " " + subComponent);
											subComponent.setDataserie(dataserie);
											JLabel fieldName = new JLabel();
											fieldName.setFont(subtittlesFont);
											if (subComponent.getName() != null)
											{
												fieldName.setText(subComponent.getName());
											}
											else
											{
												fieldName.setText(subComponent.getID());
											}
											System.out.println("setting field name: " + fieldName);
											addComponentLayout(component.getComponentInfoPanel(), fieldName, 0, panelLine, 1, 1, new Insets(0, 2, 5, 0));
											JTextField field = new  JTextField();
											field.setFont(dataFont);
											
											
											String value = (String)jaxComponent.getSamples().getSample().get(i).getValue().getValue(); //provisorio
											System.out.println("setando valor default: " + value);
											if (value.toUpperCase().equals("AVAILABLE") || value.toUpperCase().equals("ACTIVE"))
											{
												field.setForeground(new Color(0,128,0));
											}
												else if(value.toUpperCase().equals("UNAVAILABLE"))
											{
												field.setForeground(Color.RED);	
											}
											field.setText(value);
											field.setEditable(false);
											subComponent.settField(field);
											addComponentLayout(component.getComponentInfoPanel(), field, 1, panelLine, 1, 1, new Insets(0, 0, 5, 0));
											panelLine++;
											component.setgSample(componentSample);
											componentSample.subComponentList.add(subComponent);
											device.valuesToUpdate.add(subComponent);
											device.seriesToUpdate.add(dataserie);
											System.out.println("Adding to values and seriestoupdate: " + device.valuesToUpdate.size() + " " + device.seriesToUpdate.size());
										}
									}
								}
								catch(Exception sampleError)
								{
									System.out.println(sampleError);
									System.out.println("No Samples");
								}
								try
								{
									if(!jaxComponent.getEvents().getEvent().isEmpty())
									{
										System.out.println("creating event");
										GEvent componentEvent = new GEvent();
										System.out.println(componentEvent);
										JLabel tittleEvent = new JLabel();
										tittleEvent.setFont(tittlesFont);
										tittleEvent.setText("Events:");
										tittleEvent.setForeground(new Color(25,25,112));
										addComponentLayout(component.getComponentInfoPanel(), tittleEvent, 0, panelLine, 1, 1, new Insets(0, 0, 5, 0));
										panelLine++;
										System.out.println("creating event for: " + jaxComponent.getEvents().getEvent().size());
										for(int i=0; i < jaxComponent.getEvents().getEvent().size(); i++)
										{
											
											System.out.println(i +"o loop, name:" + jaxComponent.getEvents().getEvent().get(i).getValue().getName() + " id: " + jaxComponent.getEvents().getEvent().get(i).getValue().getDataItemId() + " componentIndex: " + button.getIndex() );
											GDataserie dataserie = new GDataserie(jaxComponent.getEvents().getEvent().get(i).getValue().getName(), 
																				  jaxComponent.getEvents().getEvent().get(i).getValue().getDataItemId(),
													  							  button.getIndex(), 1, i, agent, device.categoryAxesValues);
											GSubComponent subComponent = new GSubComponent(jaxComponent.getEvents().getEvent().get(i).getValue().getName(), 
																						   jaxComponent.getEvents().getEvent().get(i).getValue().getDataItemId());
											subComponent.setDataserie(dataserie);
											System.out.println("criados ds e subcomponent" + dataserie + " " + subComponent);
											JLabel fieldName = new JLabel();
											fieldName.setFont(subtittlesFont);
											if (subComponent.getName() != null)
											{
												fieldName.setText(subComponent.getName());
											}
											else
											{
												fieldName.setText(subComponent.getID());
											}
											System.out.println("setting field name: " + fieldName);
											addComponentLayout(component.getComponentInfoPanel(), fieldName, 0, panelLine, 1, 1, new Insets(0, 2, 5, 0));
											JTextField field = new JTextField();
											field.setFont(dataFont);
											String value = (String)jaxComponent.getEvents().getEvent().get(i).getValue().getValue(); //provisorio
											System.out.println("setando valor default: " + value);
											if (value.toUpperCase().equals("AVAILABLE") || value.toUpperCase().equals("ACTIVE"))
											{
												field.setForeground(new Color(0,128,0));
											}
												else if(value.toUpperCase().equals("UNAVAILABLE"))
											{
												field.setForeground(Color.RED);	
											}
											field.setText(value);
											field.setEditable(false);
											subComponent.settField(field);
											addComponentLayout(component.getComponentInfoPanel(), field, 1, panelLine, 1, 1, new Insets(0, 0, 5, 0));
											panelLine++;
											componentEvent.subComponentList.add(subComponent);
											component.setgEvent(componentEvent);
											device.valuesToUpdate.add(subComponent);
											device.seriesToUpdate.add(dataserie);
											System.out.println("Adding to values and seriestoupdate: " + device.valuesToUpdate.size() + " " + device.seriesToUpdate.size());
										}
									}
								}
								catch(Exception eventsError)
								{
									System.out.println(eventsError);
									System.out.println("No Events");
//									componentPanel.remove(panelLine);
//									panelLine--;
								}
								try
								{
									if(!jaxComponent.getCondition().getCondition().isEmpty())
									{
										System.out.println("creating conditions");
										GCondition componentCondition = new GCondition();
										System.out.println(componentCondition);
										JLabel tittleCondition = new JLabel();
										tittleCondition.setFont(tittlesFont);
										tittleCondition.setText("Conditions:");
										tittleCondition.setForeground(new Color(25,25,112));
										addComponentLayout(component.getComponentInfoPanel(), tittleCondition, 0, panelLine, 1, 1, new Insets(0, 0, 5, 0));
										panelLine++;
										System.out.println("creating condition for: " + jaxComponent.getCondition().getCondition().size());
										for(int i=0; i < jaxComponent.getCondition().getCondition().size(); i++)
										{
											System.out.println(i +"o loop, name:" + jaxComponent.getCondition().getCondition().get(i).getValue().getName() + " id: " + jaxComponent.getCondition().getCondition().get(i).getValue().getDataItemId() + " componentIndex: " + button.getIndex() );
											GDataserie dataserie = new GDataserie(jaxComponent.getCondition().getCondition().get(i).getValue().getName(),  
																				  jaxComponent.getCondition().getCondition().get(i).getValue().getDataItemId(),
																				  button.getIndex(), 2, i, agent, device.categoryAxesValues);
											GSubComponent subComponent = new GSubComponent(jaxComponent.getCondition().getCondition().get(i).getValue().getName(), 
															   							   jaxComponent.getCondition().getCondition().get(i).getValue().getDataItemId());
											subComponent.setDataserie(dataserie);
											System.out.println("criados ds e subcomponent" + dataserie + " " + subComponent);
											JLabel fieldName = new JLabel();
											fieldName.setFont(subtittlesFont);
											if (subComponent.getName() != null)
											{
												fieldName.setText(subComponent.getName());
											}
											else
											{
												fieldName.setText(subComponent.getID());
											}
											System.out.println("setting field name: " + fieldName);
											addComponentLayout(component.getComponentInfoPanel(), fieldName, 0, panelLine, 1, 1, new Insets(0, 2, 5, 0));
											JTextField field = new JTextField();
											field.setFont(dataFont);
											String value = (String)jaxComponent.getCondition().getCondition().get(i).getName().getLocalPart(); //provisorio
											if (value.toUpperCase().equals("AVAILABLE") || value.toUpperCase().equals("ACTIVE"))
											{
												field.setForeground(new Color(0,128,0));
											}
												else if(value.toUpperCase().equals("UNAVAILABLE"))
											{
												field.setForeground(Color.RED);	
											}
											field.setText(value);
											field.setEditable(false);
											subComponent.settField(field);
											System.out.println("setando valor default: " + value);
											addComponentLayout(component.getComponentInfoPanel(), field, 1, panelLine, 1, 1, new Insets(0, 0, 5, 0));
											panelLine++;
											componentCondition.subComponentList.add(subComponent);
											component.setgCondition(componentCondition);
											device.valuesToUpdate.add(subComponent);
											device.seriesToUpdate.add(dataserie);
											System.out.println("Adding to values and seriestoupdate: " + device.valuesToUpdate.size() + " " + device.seriesToUpdate.size());
										}
									}
								}
								catch(Exception conditionsError)
								{
									System.out.println(conditionsError);
									System.out.println("No Conditions");
								}
								component.getComponentInfoPanel().setVisible(true);
								revalidate();
								repaint();
							}
							
							else
							{
								component.getComponentInfoPanel().setVisible(true);
								//remove campos do valuestoupdate
								if (component.getgSample()!= null)device.valuesToUpdate.addAll(component.getgSample().subComponentList);
								if (component.getgEvent()!= null)device.valuesToUpdate.addAll(component.getgEvent().subComponentList);
								if (component.getgCondition()!= null)device.valuesToUpdate.addAll(component.getgCondition().subComponentList);
								System.out.println("showing existing panel and adding to valuestoupdate: " + device.valuesToUpdate.size());
							}
						}
						else
						{
							component.getComponentInfoPanel().setVisible(false);
							//remove campos do valuestoupdate
							if (component.getgSample()!= null)device.valuesToUpdate.removeAll(component.getgSample().subComponentList);
							if (component.getgEvent()!= null)device.valuesToUpdate.removeAll(component.getgEvent().subComponentList);
							if (component.getgCondition()!= null)device.valuesToUpdate.removeAll(component.getgCondition().subComponentList);
							System.out.println("showing hiding existing panel and removing the valuestoupdate: " + device.valuesToUpdate.size());
						}	
					}
				});
				toggleTemp.setVisible(true);	
			}
		}
		else if (device.buttonPanel.getComponentCount() == 0)
		{
			for(int i=0; i<device.componentStreamList.size();i++)
			{
				GButton button = new GButton();
				button.setFont(buttonsFont);
				button.setText(device.componentStreamList.get(i).getComponent()+"-"+ device.componentStreamList.get(i).getName());
				addComponentLayout(device.buttonPanel, button, 1, i, 1, 1, new Insets(0, 0, 5, 0));
				device.componentStreamList.get(i).setButton(button);
				button.setIndex(i);
				button.addActionListener(new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent event) 
					{
						System.out.println("criando action listener");
						GButton button = (GButton) event.getSource();
						GComponent component = device.componentStreamList.get(button.getIndex());
						if (button.isSelected())
						{
							if (component.getComponentInfoPanel() == null)
							{
								JPanel componentPanel = new JPanel();
								component.setComponentInfoPanel(componentPanel);
								componentPanel.setLayout(new GridBagLayout());
								((GridBagLayout)componentPanel.getLayout()).columnWidths = new int[] {0, 0, 0};
								((GridBagLayout)componentPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
								((GridBagLayout)componentPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
								((GridBagLayout)componentPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
								
								componentPanel.setBorder(new CompoundBorder(
										new TitledBorder(null,component.getComponent()+" - "+ component.getName(), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, subtittlesFont),
										null));
								
								addComponentLayout(device.informationPanel, componentPanel, component.getButton().getIndex(), 0, 1, 1, new Insets(0, 0, 5, 5));
								GComponent jaxComponent = device.componentStreamList.get(button.getIndex());
								int panelLine = 0;
//								try
//								{
									if(!jaxComponent.getgSample().subComponentList.isEmpty())
									{
										System.out.println("creating sample"); /////////////criando sample
										JLabel tittleSample = new JLabel();
										tittleSample.setFont(tittlesFont);
										tittleSample.setText("Samples:");
										tittleSample.setForeground(new Color(25, 25, 112));
										addComponentLayout(component.getComponentInfoPanel(), tittleSample, 0, panelLine, 1, 1, new Insets(0, 0, 5, 0));
										panelLine++;
										for(int i = 0; i < jaxComponent.getgSample().subComponentList.size();i++)
										{
											System.out.println(i +"o loop, name:" + jaxComponent.getgSample().subComponentList.get(i).getName() + " id: " + jaxComponent.getgSample().subComponentList.get(i).getID() + " componentIndex: " + button.getIndex() );
											JLabel fieldName = new JLabel();
											fieldName.setFont(subtittlesFont);
											if (jaxComponent.getgSample().subComponentList.get(i).getName() != null)
											{
												fieldName.setText(jaxComponent.getgSample().subComponentList.get(i).getName());
											}
											else
											{
												fieldName.setText(jaxComponent.getgSample().subComponentList.get(i).getID());
											}
											System.out.println("setting field name: " + fieldName);
											addComponentLayout(component.getComponentInfoPanel(), fieldName, 0, panelLine, 1, 1, new Insets(0, 2, 5, 0));
											JTextField field = new  JTextField();
											field.setFont(dataFont);
											
											String value = jaxComponent.getgSample().subComponentList.get(i).getDataserie().getLastValue(); //provisorio
											System.out.println("setando valor default: " + value);
											if (value.toUpperCase().equals("AVAILABLE") || value.toUpperCase().equals("ACTIVE"))
											{
												field.setForeground(new Color(0,128,0));
											}
												else if(value.toUpperCase().equals("UNAVAILABLE"))
											{
												field.setForeground(Color.RED);	
											}
											field.setText(value);
											field.setEditable(false);
											jaxComponent.getgSample().subComponentList.get(i).settField(field);
											addComponentLayout(component.getComponentInfoPanel(), field, 1, panelLine, 1, 1, new Insets(0, 0, 5, 0));
											panelLine++;
											device.valuesToUpdate.add(jaxComponent.getgSample().subComponentList.get(i));
											if (!device.seriesToUpdate.contains(jaxComponent.getgSample().subComponentList.get(i).getDataserie()))
											{
												device.seriesToUpdate.add(jaxComponent.getgSample().subComponentList.get(i).getDataserie());
											}
											System.out.println("Adding to values and seriestoupdate: " + device.valuesToUpdate.size() + " " + device.seriesToUpdate.size());
										}
									}
//								}
//								catch(Exception sampleError)
//								{
//									System.out.println(sampleError);
//									System.out.println("No Samples");
//								}
								try
								{
									if(!jaxComponent.getgEvent().subComponentList.isEmpty())
									{
										System.out.println("creating Event"); /////////////criando sample
										JLabel tittleEvent = new JLabel();
										tittleEvent.setFont(tittlesFont);
										tittleEvent.setText("Events:");
										tittleEvent.setForeground(new Color(25, 25, 112));
										addComponentLayout(component.getComponentInfoPanel(), tittleEvent, 0, panelLine, 1, 1, new Insets(0, 0, 5, 0));
										panelLine++;
										for(int i = 0; i < jaxComponent.getgEvent().subComponentList.size();i++)
										{
											System.out.println(i +"o loop, name:" + jaxComponent.getgEvent().subComponentList.get(i).getName() + " id: " + jaxComponent.getgEvent().subComponentList.get(i).getID() + " componentIndex: " + button.getIndex() );
											JLabel fieldName = new JLabel();
											fieldName.setFont(subtittlesFont);
											if (jaxComponent.getgEvent().subComponentList.get(i).getName() != null)
											{
												fieldName.setText(jaxComponent.getgEvent().subComponentList.get(i).getName());
											}
											else
											{
												fieldName.setText(jaxComponent.getgEvent().subComponentList.get(i).getID());
											}
											System.out.println("setting field name: " + fieldName);
											addComponentLayout(component.getComponentInfoPanel(), fieldName, 0, panelLine, 1, 1, new Insets(0, 2, 5, 0));
											JTextField field = new  JTextField();
											field.setFont(dataFont);
											
											String value = jaxComponent.getgEvent().subComponentList.get(i).getDataserie().getLastValue(); //provisorio
											System.out.println("setando valor default: " + value);
											if (value.toUpperCase().equals("AVAILABLE") || value.toUpperCase().equals("ACTIVE"))
											{
												field.setForeground(new Color(0,128,0));
											}
											else if(value.toUpperCase().equals("UNAVAILABLE"))
											{
												field.setForeground(Color.RED);	
											}
											field.setText(value);
											field.setEditable(false);
											jaxComponent.getgEvent().subComponentList.get(i).settField(field);
											addComponentLayout(component.getComponentInfoPanel(), field, 1, panelLine, 1, 1, new Insets(0, 0, 5, 0));
											panelLine++;
											device.valuesToUpdate.add(jaxComponent.getgEvent().subComponentList.get(i));
											if (!device.seriesToUpdate.contains(jaxComponent.getgEvent().subComponentList.get(i).getDataserie()))
											{
												device.seriesToUpdate.add(jaxComponent.getgEvent().subComponentList.get(i).getDataserie());
											}
											System.out.println("Adding to values and seriestoupdate: " + device.valuesToUpdate.size() + " " + device.seriesToUpdate.size());
										}
									}
								}
								catch(Exception eventsError)
								{
									System.out.println(eventsError);
									System.out.println("No Events");
								}
								try
								{
									if(!jaxComponent.getgEvent().subComponentList.isEmpty())
									{
										System.out.println("creating Condition"); /////////////criando sample
										JLabel tittleCondition = new JLabel();
										tittleCondition.setFont(tittlesFont);
										tittleCondition.setText("Events:");
										tittleCondition.setForeground(new Color(25, 25, 112));
										addComponentLayout(component.getComponentInfoPanel(), tittleCondition, 0, panelLine, 1, 1, new Insets(0, 0, 5, 0));
										panelLine++;
										for(int i = 0; i < jaxComponent.getgCondition().subComponentList.size();i++)
										{
											System.out.println(i +"o loop, name:" + jaxComponent.getgCondition().subComponentList.get(i).getName() + " id: " + jaxComponent.getgCondition().subComponentList.get(i).getID() + " componentIndex: " + button.getIndex() );
											JLabel fieldName = new JLabel();
											fieldName.setFont(subtittlesFont);
											if (jaxComponent.getgCondition().subComponentList.get(i).getName() != null)
											{
												fieldName.setText(jaxComponent.getgCondition().subComponentList.get(i).getName());
											}
											else
											{
												fieldName.setText(jaxComponent.getgCondition().subComponentList.get(i).getID());
											}
											System.out.println("setting field name: " + fieldName);
											addComponentLayout(component.getComponentInfoPanel(), fieldName, 0, panelLine, 1, 1, new Insets(0, 2, 5, 0));
											JTextField field = new  JTextField();
											field.setFont(dataFont);
											
											String value = jaxComponent.getgCondition().subComponentList.get(i).getDataserie().getLastValue(); //provisorio
											System.out.println("setando valor default: " + value);
											if (value.toUpperCase().equals("AVAILABLE") || value.toUpperCase().equals("ACTIVE"))
											{
												field.setForeground(new Color(0,128,0));
											}
											else if(value.toUpperCase().equals("UNAVAILABLE"))
											{
												field.setForeground(Color.RED);	
											}
											field.setText(value);
											field.setEditable(false);
											jaxComponent.getgCondition().subComponentList.get(i).settField(field);
											addComponentLayout(component.getComponentInfoPanel(), field, 1, panelLine, 1, 1, new Insets(0, 0, 5, 0));
											panelLine++;
											device.valuesToUpdate.add(jaxComponent.getgCondition().subComponentList.get(i));
											if (!device.seriesToUpdate.contains(jaxComponent.getgCondition().subComponentList.get(i).getDataserie()))
											{
												device.seriesToUpdate.add(jaxComponent.getgCondition().subComponentList.get(i).getDataserie());
											}
											System.out.println("Adding to values and seriestoupdate: " + device.valuesToUpdate.size() + " " + device.seriesToUpdate.size());
										}
									}
								}
								catch(Exception conditionsError)
								{
									System.out.println(conditionsError);
									System.out.println("No Conditions");
								}
								component.getComponentInfoPanel().setVisible(true);
								revalidate();
								repaint();
							}
//							
							else
							{
								component.getComponentInfoPanel().setVisible(true);
								//add campos do valuestoupdate
								if (component.getgSample()!= null)device.valuesToUpdate.addAll(component.getgSample().subComponentList);
								if (component.getgEvent()!= null)device.valuesToUpdate.addAll(component.getgEvent().subComponentList);
								if (component.getgCondition()!= null)device.valuesToUpdate.addAll(component.getgCondition().subComponentList);
								System.out.println("showing existing panel and adding to valuestoupdate: " + device.valuesToUpdate.size());
							}
						}
						else
						{
							component.getComponentInfoPanel().setVisible(false);
							//remove campos do valuestoupdate
							if (component.getgSample()!= null)device.valuesToUpdate.removeAll(component.getgSample().subComponentList);
							if (component.getgEvent()!= null)device.valuesToUpdate.removeAll(component.getgEvent().subComponentList);
							if (component.getgCondition()!= null)device.valuesToUpdate.removeAll(component.getgCondition().subComponentList);
							System.out.println("showing hiding existing panel and removing the valuestoupdate: " + device.valuesToUpdate.size());
						}	
					}
				});
				button.setVisible(true);
			}
			infoPanel.removeAll();
			panel5.removeAll();
			addComponentLayout(panel5, device.buttonPanel, 0, 1, 1, 1, new Insets(0, 0, 5, 5));
			addComponentLayout(infoPanel, device.informationPanel, 0, 0, 1, 1, new Insets(0, 0, 5, 5));
		}
		else
		{
			infoPanel.removeAll();
			panel5.removeAll();
			addComponentLayout(panel5, device.buttonPanel, 0, 1, 1, 1, new Insets(0, 0, 5, 5));
			addComponentLayout(infoPanel, device.informationPanel, 0, 0, 1, 1, new Insets(0, 0, 5, 5));
		}
		panel5.setVisible(true);
		infoPanel.setVisible(true);
		device.buttonPanel.setVisible(true);
		device.informationPanel.setVisible(true);
		System.out.println("ajustando paineis");
		revalidate();
		repaint();
	}
	private void setCurrentGraphs() throws JAXBException, MalformedURLException
	{
		
		if (device == null)
		{
			System.out.println("criando novo device");
			JAXBContext jc = JAXBContext.newInstance(MTConnectStreamsType.class);
			Unmarshaller u = jc.createUnmarshaller();
			URL url = new URL(agent.getIP() + "/current" );
			JAXBElement<MTConnectStreamsType> element =(JAXBElement<MTConnectStreamsType>)u.unmarshal(url);
			final MTConnectStreamsType current = element.getValue();
			this.device = new GDevice(current.getStreams().getDeviceStream().get(0).getName(),
									  current.getStreams().getDeviceStream().get(0).getUuid());
			textField1.setText(device.getName());
			textField2.setText(device.getUuid());
			infoPanel.removeAll();
			try
			{
				mainPanel.remove(device.buttonPanel);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			addComponentLayout(panel5, device.checkBoxPanel, 0, 0, 1, 1, new Insets(0, 0, 5, 0));
			addComponentLayout(infoPanel, device.graphPanel, 0, 0, 1, 1, new Insets(0, 0, 5, 0));
			
			for (int i = 0; i< current.getStreams().getDeviceStream().get(0).getComponentStream().size(); i++)
			{
				System.out.println("criando checkbox " + i);
				final GCheckbox componentCheckbox = new GCheckbox();
				componentCheckbox.setFont(buttonsFont);
				componentCheckbox.setText(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(i).getComponent()+"-"+ current.getStreams().getDeviceStream().get(0).getComponentStream().get(i).getName());
				final JPanel painelMenuCheckbox = new JPanel();
				painelMenuCheckbox.setBorder(new CompoundBorder(new TitledBorder(null, null , TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, subtittlesFont),null));
				painelMenuCheckbox.setLayout(new GridBagLayout());
				((GridBagLayout)painelMenuCheckbox.getLayout()).columnWidths = new int[] {105, 0, 0, 15, 0, 0, 0};
				((GridBagLayout)painelMenuCheckbox.getLayout()).rowHeights = new int[] {0, 30, 0};
				((GridBagLayout)painelMenuCheckbox.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0E-4};
				((GridBagLayout)painelMenuCheckbox.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

				addComponentLayout(painelMenuCheckbox, componentCheckbox, 0, 0, 3, 1, new Insets(0, 0, 5, 0));
				addComponentLayout(device.checkBoxPanel, painelMenuCheckbox, 1, i, 1, 1, new Insets(0, 0, 5, 0));
				System.out.println("Criando component");
				GComponent component = new GComponent(current.getStreams().getDeviceStream().get(0).getComponentStream().get(i).getComponent(),
													  current.getStreams().getDeviceStream().get(0).getComponentStream().get(i).getName(), 
													  current.getStreams().getDeviceStream().get(0).getComponentStream().get(i).getComponentId());
				device.componentStreamList.add(component);
				component.setComponentCheckbox(componentCheckbox);
				component.setPainelMenuCheckbox(painelMenuCheckbox);
				componentCheckbox.setIndex(i);
				componentCheckbox.addActionListener(new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent event) 
					{
						System.out.println("criando action listener");
						GCheckbox checkbox = (GCheckbox) event.getSource();
						GComponent component = device.componentStreamList.get(checkbox.getIndex());
						if (checkbox.isSelected())
						{
							if (component.getgSample() == null &&
								component.getgEvent() == null &&
								component.getgCondition() == null)
								
							{
								ComponentStreamType jaxComponent = current.getStreams().getDeviceStream().get(0).getComponentStream().get(component.getComponentCheckbox().getIndex());
								int panelLine = 1;
								try
								{
									if(!jaxComponent.getSamples().getSample().isEmpty())
									{
										System.out.println("creating sample"); /////////////criando sample
										GSample componentSample = new GSample();
										System.out.println(componentSample);
										System.out.println("creating sample for: " + jaxComponent.getSamples().getSample().size());
										component.setgSample(componentSample);
										for(int j = 0; j < jaxComponent.getSamples().getSample().size();j++)
										{
											System.out.println(j +"o loop, name:" + jaxComponent.getSamples().getSample().get(j).getValue().getName() + " id: " + jaxComponent.getSamples().getSample().get(j).getValue().getDataItemId() + " componentIndex: " + checkbox.getIndex() );
											GDataserie dataserie = new GDataserie(jaxComponent.getSamples().getSample().get(j).getValue().getName(), 
																				  jaxComponent.getSamples().getSample().get(j).getValue().getDataItemId(),
																				  checkbox.getIndex(), 0, j, agent, device.categoryAxesValues);
											GSubComponent subComponent = new GSubComponent(jaxComponent.getSamples().getSample().get(j).getValue().getName(), 
																						   jaxComponent.getSamples().getSample().get(j).getValue().getDataItemId());
											System.out.println("criados ds e subcomponent" + dataserie + " " + subComponent);
											subComponent.setDataserie(dataserie);
											final GCheckbox subCheckbox = new GCheckbox();
											if (subComponent.getName() != null)
											{
												subCheckbox.setText(subComponent.getName());
											}
											else
											{
												subCheckbox.setText(subComponent.getID());
											}
											subCheckbox.setFont(subtittlesFont);
											addComponentLayout(component.getPainelMenuCheckbox(), subCheckbox, 1, panelLine, 2, 1, new Insets(0, 5, 5, 0));
											panelLine++;
											subCheckbox.setIndex(component.getComponentCheckbox().getIndex());
											subCheckbox.setSubComponentIndex(j);
											subCheckbox.addActionListener(new ActionListener() 
											{
												
												@Override
												public void actionPerformed(ActionEvent e) 
												{
													GSubComponent subComponent = device.componentStreamList.get(subCheckbox.getIndex()).getgSample().subComponentList.get(subCheckbox.getSubComponentIndex());
													
													if (subComponent.getSubComponentCheckbox().isSelected())
													{
														device.graphsToUpdate.add(subComponent);
														device.addtoDataset(subComponent);
													}
													else
													{
														device.graphsToUpdate.remove(subComponent);
														device.removeFromDataset(subComponent);
													}
												}
											});
											componentSample.subComponentList.add(subComponent);
											componentSample.subComponentList.get(j).setSubComponentCheckbox(subCheckbox);
											device.seriesToUpdate.add(dataserie);
										}
									}
									
								}
								catch(Exception sampleError)
								{
									System.out.println("//////////***" + sampleError);
									System.out.println("No Samples");
								}
								try
								{
									if(!jaxComponent.getEvents().getEvent().isEmpty())
									{
										System.out.println("creating event"); /////////////criando sample
										GEvent componentEvent = new GEvent();
										System.out.println(componentEvent);
										System.out.println("creating sample for: " + jaxComponent.getEvents().getEvent().size());
										component.setgEvent(componentEvent);
										for(int j = 0; j < jaxComponent.getEvents().getEvent().size();j++)
										{
											System.out.println(j +"o loop, name:" + jaxComponent.getEvents().getEvent().get(j).getValue().getName() + " id: " + jaxComponent.getEvents().getEvent().get(j).getValue().getDataItemId() + " componentIndex: " + checkbox.getIndex() );
											GDataserie dataserie = new GDataserie(jaxComponent.getEvents().getEvent().get(j).getValue().getName(), 
																				  jaxComponent.getEvents().getEvent().get(j).getValue().getDataItemId(),
																				  checkbox.getIndex(), 1, j, agent, device.categoryAxesValues);
											GSubComponent subComponent = new GSubComponent(jaxComponent.getEvents().getEvent().get(j).getValue().getName(), 
																						   jaxComponent.getEvents().getEvent().get(j).getValue().getDataItemId());
											System.out.println("criados ds e subcomponent" + dataserie + " " + subComponent);
											subComponent.setDataserie(dataserie);
											final GCheckbox subCheckbox = new GCheckbox();
											if (subComponent.getName() != null)
											{
												subCheckbox.setText(subComponent.getName());
											}
											else
											{
												subCheckbox.setText(subComponent.getID());
											}
											subCheckbox.setFont(subtittlesFont);
											addComponentLayout(component.getPainelMenuCheckbox(), subCheckbox, 1, panelLine, 2, 1, new Insets(0, 5, 5, 0));
											panelLine++;
											subCheckbox.setIndex(component.getComponentCheckbox().getIndex());
											subCheckbox.setSubComponentIndex(j);
											subCheckbox.addActionListener(new ActionListener() 
											{
												@Override
												public void actionPerformed(ActionEvent e) 
												{
													GSubComponent subComponent = device.componentStreamList.get(subCheckbox.getIndex()).getgEvent().subComponentList.get(subCheckbox.getSubComponentIndex());
													
													if (subComponent.getSubComponentCheckbox().isSelected())
													{
														device.graphsToUpdate.add(subComponent);
														device.addtoDataset(subComponent);
													}
													else
													{
														device.graphsToUpdate.remove(subComponent);
														device.removeFromDataset(subComponent);
													}
												}
											});
											componentEvent.subComponentList.add(subComponent);
											componentEvent.subComponentList.get(j).setSubComponentCheckbox(subCheckbox);
											device.seriesToUpdate.add(dataserie);
										}
									}
								}
								catch(Exception eventError)
								{
									System.out.println("No Events");
								}
								try
								{
									if (!jaxComponent.getCondition().getCondition().isEmpty())
									{
										System.out.println("creating Conditions"); /////////////criando Conditions
										GCondition componentCondition = new GCondition();
										System.out.println(componentCondition);
										System.out.println("creating event for: " + jaxComponent.getCondition().getCondition().size());
										component.setgCondition(componentCondition);
										for(int i=0; i<jaxComponent.getCondition().getCondition().size(); i++)
										{
											System.out.println(i +"o loop, name:" + jaxComponent.getCondition().getCondition().get(i).getValue().getName() + " id: " + jaxComponent.getCondition().getCondition().get(i).getValue().getDataItemId() + " componentIndex: " + checkbox.getIndex() );
											GSubComponent subComponent = new GSubComponent(jaxComponent.getCondition().getCondition().get(i).getValue().getName(),
																						   jaxComponent.getCondition().getCondition().get(i).getValue().getDataItemId());
											GDataserie dataserie = new GDataserie(jaxComponent.getCondition().getCondition().get(i).getValue().getName(), 
																				  jaxComponent.getCondition().getCondition().get(i).getValue().getDataItemId(), 
																				  checkbox.getIndex(), 2, i, agent, device.categoryAxesValues);
											System.out.println("criados ds e subcomponent" + dataserie + " " + subComponent);
											subComponent.setDataserie(dataserie);
											final GCheckbox subCheckbox = new GCheckbox();
											if (subComponent.getName() != null)
											{
												subCheckbox.setText(subComponent.getName());
											}
											else
											{
												subCheckbox.setText(subComponent.getID());
											}
											subCheckbox.setFont(subtittlesFont);
											addComponentLayout(component.getPainelMenuCheckbox(), subCheckbox, 1, panelLine, 2, 1, new Insets(0, 5, 5, 0));
											panelLine++;
											subCheckbox.setIndex(component.getComponentCheckbox().getIndex());
											subCheckbox.setSubComponentIndex(i);
											subCheckbox.addActionListener(new ActionListener()
											{
												@Override
												public void actionPerformed(ActionEvent e) 
												{
													GSubComponent subComponent = device.componentStreamList.get(subCheckbox.getIndex()).getgCondition().subComponentList.get(subCheckbox.getSubComponentIndex());
													
													if (subComponent.getSubComponentCheckbox().isSelected())
													{
														device.graphsToUpdate.add(subComponent);
														device.addtoDataset(subComponent);
													}
													else
													{
														device.graphsToUpdate.remove(subComponent);
														device.removeFromDataset(subComponent);
													}
												}
											});
											componentCondition.subComponentList.add(subComponent);
											componentCondition.subComponentList.get(i).setSubComponentCheckbox(subCheckbox);
											device.seriesToUpdate.add(dataserie);
										}
										
									}
								}
								catch(Exception conditionError)
								{
									System.out.println("No Conditions");
								}
								revalidate();
								repaint();
							}
							else
							{
								if (component.getgSample() != null)
								{
									for(int i=0; i<component.getgSample().subComponentList.size(); i++)
									{
										component.getgSample().subComponentList.get(i).getSubComponentCheckbox().setVisible(true);
									}
								}
								if (component.getgEvent()!= null)
								{
									for(int i=0; i<component.getgEvent().subComponentList.size(); i++)
									{
										component.getgEvent().subComponentList.get(i).getSubComponentCheckbox().setVisible(true);
									}
								}
								if (component.getgCondition() != null)
								{
									for(int i=0; i<component.getgCondition().subComponentList.size(); i++)
									{
										component.getgCondition().subComponentList.get(i).getSubComponentCheckbox().setVisible(true);
									}
								}
							}
						}
						else if (!checkbox.isSelected())
						{
							if (component.getgSample() != null)
							{
								for(int i=0; i<component.getgSample().subComponentList.size(); i++)
								{
									component.getgSample().subComponentList.get(i).getSubComponentCheckbox().setVisible(false);
									component.getgSample().subComponentList.get(i).getSubComponentCheckbox().setSelected(false);
									device.graphsToUpdate.remove(component.getgSample().subComponentList.get(i));
									device.removeFromDataset(component.getgSample().subComponentList.get(i));
								}
							}
							if (component.getgEvent()!= null)
							{
								for(int i=0; i<component.getgEvent().subComponentList.size(); i++)
								{
									component.getgEvent().subComponentList.get(i).getSubComponentCheckbox().setVisible(false);
									component.getgEvent().subComponentList.get(i).getSubComponentCheckbox().setSelected(false);
									device.graphsToUpdate.remove(component.getgEvent().subComponentList.get(i));
									device.removeFromDataset(component.getgEvent().subComponentList.get(i));
								}
							}
							if (component.getgCondition() != null)
							{
								for(int i=0; i<component.getgCondition().subComponentList.size(); i++)
								{
									component.getgCondition().subComponentList.get(i).getSubComponentCheckbox().setVisible(false);
									component.getgCondition().subComponentList.get(i).getSubComponentCheckbox().setSelected(false);
									device.graphsToUpdate.remove(component.getgCondition().subComponentList.get(i));
									device.removeFromDataset(component.getgCondition().subComponentList.get(i));
								}
							}
						}
						
					}
				});
				componentCheckbox.setVisible(true);
				
			}
			device.setGraphCharts();
		}
		else
		{
			infoPanel.removeAll();
			panel5.removeAll();
			addComponentLayout(panel5, device.checkBoxPanel, 0, 1, 1, 1, new Insets(0, 0, 5, 5));
			addComponentLayout(infoPanel, device.graphPanel, 0, 0, 1, 1, new Insets(0, 0, 5, 5));
		}
		panel5.setVisible(true);
		infoPanel.setVisible(true);
		device.checkBoxPanel.setVisible(true);
		device.graphPanel.setVisible(true);
		System.out.println("ajustando paineis");
		revalidate();
		repaint();
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
		mainPanel.setVisible(true);
		infoPanel.setVisible(true);
		int cont  = probe.getDevices().getDevice().get(0).getComponents().getComponent().size();
		for (int i = 0; i < cont ; i++) //---> Quantos Botï¿½es deve colocar devido aos dados de ComponentStream
		{
			final JToggleButton toggleTemp = new JToggleButton();
			//---- toggleButton1 ----
			toggleTemp.setFont(buttonsFont);
			toggleTemp.setName(""+i);
			toggleTemp.setText(""+ probe.getDevices().getDevice().get(0).getComponents().getComponent().get(i).getName().getLocalPart());
			JPanel panel5 = new JPanel();
			panelList.add(panel5);
			mainPanel.add(toggleTemp, new GridBagConstraints(1, i, 1, 1, 0.0, 0.0,
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
							infoPanel.add(panelList.get(j), new GridBagConstraints(0+ conterC, 0, 1, 1, 0.0, 0.0,
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
		URL url = new URL(agent.getIP() + "/sample" );
		JAXBElement<MTConnectStreamsType> element =(JAXBElement<MTConnectStreamsType>)u.unmarshal(url);
		final MTConnectStreamsType sample = element.getValue();
		textField1.setText(""+sample.getStreams().getDeviceStream().get(0).getName());
		textField2.setText(""+sample.getStreams().getDeviceStream().get(0).getUuid());
		mainPanel.setVisible(true);
		infoPanel.setVisible(true);
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
			mainPanel.add(toggleTemp, new GridBagConstraints(1, i, 1, 1, 0.0, 0.0,
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
					infoPanel.add(panelList.get(j), new GridBagConstraints(0+ conterC, 0, 1, 1, 0.0, 0.0,
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
					infoPanel.remove(panelList.get(j));
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
	
	SwingWorker worker = new SwingWorker() 
	{
		protected Object doInBackground() throws Exception
		{
			while(true)
			{
				try
				{
					JAXBContext jct = JAXBContext.newInstance(MTConnectStreamsType.class);
					Unmarshaller ut = jct.createUnmarshaller();
					URL urlt = new URL(agent.getIP() + "/current" );
					JAXBElement<MTConnectStreamsType> elementt =(JAXBElement<MTConnectStreamsType>)ut.unmarshal(urlt);
					final MTConnectStreamsType currentt = elementt.getValue();
					if (currentt!= null)
					{
						textField7.setText(""+currentt.getHeader().getCreationTime());
						if (comboBox1.getSelectedItem().toString().equals("Current") && device!=null) 
	 					{
							//atualiza Series de dados
							device.setLastTimestamp(currentt.getHeader().getCreationTime());
							try
							{
								System.out.println("atualizando series:" + device.seriesToUpdate.size());
								for (int i=0; i<device.seriesToUpdate.size();i++)
								{
									System.out.print("    " + i + "o loop: ");
									GDataserie serie = device.seriesToUpdate.get(i);
									XMLGregorianCalendar time = null;
									String value = null;
									String subName = null;
									String name = device.componentStreamList.get(serie.componentIndex).getComponent() + "-" + device.componentStreamList.get(serie.componentIndex).getName();
									if (serie.SEC == 0) //Sample
									{
										time = currentt.getStreams().getDeviceStream().get(0).getComponentStream().get(serie.componentIndex).getSamples().getSample().get(serie.subComponentIndex).getValue().getTimestamp();
										value = currentt.getStreams().getDeviceStream().get(0).getComponentStream().get(serie.componentIndex).getSamples().getSample().get(serie.subComponentIndex).getValue().getValue();
										if (device.componentStreamList.get(serie.componentIndex).getgSample().subComponentList.get(serie.subComponentIndex).getName() != null)
											subName = device.componentStreamList.get(serie.componentIndex).getgSample().subComponentList.get(serie.subComponentIndex).getName();
										else 
											subName = device.componentStreamList.get(serie.componentIndex).getgSample().subComponentList.get(serie.subComponentIndex).getID();
									}
									if (serie.SEC == 1) //Event
									{
										time = currentt.getStreams().getDeviceStream().get(0).getComponentStream().get(serie.componentIndex).getEvents().getEvent().get(serie.subComponentIndex).getValue().getTimestamp();
										value = currentt.getStreams().getDeviceStream().get(0).getComponentStream().get(serie.componentIndex).getEvents().getEvent().get(serie.subComponentIndex).getValue().getValue();
										if (device.componentStreamList.get(serie.componentIndex).getgEvent().subComponentList.get(serie.subComponentIndex).getName() != null)
											subName = device.componentStreamList.get(serie.componentIndex).getgEvent().subComponentList.get(serie.subComponentIndex).getName();
										else 
											subName = device.componentStreamList.get(serie.componentIndex).getgEvent().subComponentList.get(serie.subComponentIndex).getID();
									}
									if (serie.SEC == 2) // Condition
									{
										time = currentt.getStreams().getDeviceStream().get(0).getComponentStream().get(serie.componentIndex).getCondition().getCondition().get(serie.subComponentIndex).getValue().getTimestamp();
										value = currentt.getStreams().getDeviceStream().get(0).getComponentStream().get(serie.componentIndex).getCondition().getCondition().get(serie.subComponentIndex).getName().getLocalPart();
										if (device.componentStreamList.get(serie.componentIndex).getgCondition().subComponentList.get(serie.subComponentIndex).getName() != null)
											subName = device.componentStreamList.get(serie.componentIndex).getgCondition().subComponentList.get(serie.subComponentIndex).getName();
										else 
											subName = device.componentStreamList.get(serie.componentIndex).getgCondition().subComponentList.get(serie.subComponentIndex).getID();
									}
									if (serie.getSerie().getItemCount() != 0)
									{
										if (value.toUpperCase().equals("UNAVAILABLE"))
										{
											if (serie.getSerie().getValue(serie.getSerie().getItemCount() - 1) != null)
											{
												addToDataBase(name, subName, value, time);
											}
										}
										else if (serie.isNumericChart())
										{
											double numValue =  ((Double)(Double.parseDouble(value.replace(',', '.')))).doubleValue();
											if (!(numValue == (Double) serie.getSerie().getValue(serie.getSerie().getItemCount() - 1)))
											{
												addToDataBase(name, subName, value, time);
											}
										}
										else if (serie.isCategoryChart())
										{
											String string = device.categoryAxesValues[Math.round(Float.parseFloat(serie.getLastValue()))];
											if (!value.equals(string))
											{
												addToDataBase(name, subName, value, time);
											}
										}
										else
										{
											addToDataBase(name, subName, value, time);
										}
									}
									else
									{
										addToDataBase(name, subName, value, time);
									}
									serie.addToSerie(time, value, device.getLastTimestamp(), device.categoryAxesValues);
								}
							}
							catch (Exception e)
							{
								System.out.println("seriesToUpdate range changed in execution\n" + e);
							}
							if (toggleAbaValues.isSelected())
							{
								System.out.println("atualizando valores: " + device.valuesToUpdate.size());
								try
								{
									for (int i=0; i<device.valuesToUpdate.size();i++)
									{
										GSubComponent subComponent = device.valuesToUpdate.get(i);
										System.out.println("    " + i + "o loop, vis.painel:" + device.componentStreamList.get(subComponent.getDataserie().componentIndex).getComponentInfoPanel().isVisible() + ", categ.chart: " + subComponent.getDataserie().isCategoryChart() + ", num.chart: " + subComponent.getDataserie().isNumericChart());

										if (device.componentStreamList.get(subComponent.getDataserie().componentIndex).getComponentInfoPanel().isVisible())
										{
											String value = null;
											if (subComponent.getDataserie().getLastValue() == null)
											{
												value = "UNAVAILABLE";
												System.out.println("       (NULL)Defining value to update on field, value: UNAVAILABLE");
											}
											else if (subComponent.getDataserie().isCategoryChart() )
											{
												value = device.categoryAxesValues[(int) Double.parseDouble(subComponent.getDataserie().getLastValue())];
											}
											else if (subComponent.getDataserie().isNumericChart())
											{
												value = subComponent.getDataserie().getLastValue();
											}
											if (value.toUpperCase().equals("AVAILABLE") || value.toUpperCase().equals("ACTIVE") || value.toUpperCase().equals("NORMAL"))
											{
												subComponent.gettField().setForeground(new Color(0,128,0));
											}
											else if(value.toUpperCase().equals("UNAVAILABLE"))
											{
													subComponent.gettField().setForeground(Color.RED);	
											}
											subComponent.gettField().setText(value);
										}
									}
									
								}
								catch (Exception e)
								{
									System.out.println(e);
									System.out.println("valuesToUpdate  range changed in execution");
								}
							}
							else if (toggleAbaGraph.isSelected())
							{
								System.out.println("Iniciando atualizador grafico");
								boolean resetAxis = false;
								int limit = device.seriesToUpdate.size();
								for (int j = 0; j < limit; j++)
								{
									if (device.seriesToUpdate.get(j).isAxesOutOfRange())
									{
										resetAxis = true;
										device.getSymbolDataset().removeSeries(device.seriesToUpdate.get(j).getSerie());
										for (int i = 0; i< device.graphsToUpdate.size(); i++)
										{
											if (device.graphsToUpdate.get(i).getDataserie().equals(device.seriesToUpdate.get(j)))
											{
												device.graphsToUpdate.remove(i);
												break;
											}
										}
										j--;
										limit--;
									}
								}
								if (resetAxis)
								{
									device.categoryAxesValues = new String[1000];
									for(int i = 0; i< device.graphsToUpdate.size(); i++)
									{
										if (device.graphsToUpdate.get(i).getDataserie().isCategoryChart())
										{
											device.graphsToUpdate.get(i).getDataserie().redefineAllRegisters(device.getLastTimestamp(), device.categoryAxesValues);
										}
									}
								}
								int i;
								for(i = 0; i < device.categoryAxesValues.length && device.categoryAxesValues[i] != null; i++);
								if (i != device.categoryAxesValuesLenght)
								{
									device.defineSymbolAxis();
									device.categoryAxesValuesLenght = i;
								}
								
							}
							
							//////////gambiarra///////////////////
							String[] lista = new String[device.graphsToUpdate.size()];
							for (int i = 0; i < device.graphsToUpdate.size(); i++)
							{
								lista[i] = new String(device.graphsToUpdate.get(i).getDataserie().getDataItemId());
							}
							if (device.lista.length != lista.length) 
							{
								device.lista = lista;
								device.julio.setModel(new DefaultComboBoxModel(lista));
							}
							System.out.println(device.lista.length != lista.length);
							if (device.button.isSelected())
							{
								String name = device.lista[device.julio.getSelectedIndex()];
								TimeSeries serie = null;
								for (int i= 0; i < device.graphsToUpdate.size(); i++)
								{
									if (device.graphsToUpdate.get(i).getDataserie().getDataItemId().equals(name))
									{
										serie = device.graphsToUpdate.get(i).getDataserie().getSerie();
										break;
									}
								}
								System.out.println("achou a serie: " + serie.getKey());
								long deltat = serie.getTimePeriod(serie.getItemCount() - 1).getFirstMillisecond() - serie.getTimePeriod(serie.getItemCount() - 2).getFirstMillisecond();
								device.energia = device.energia + (deltat*(double)serie.getValue(serie.getItemCount() - 1))/1000.0;
								device.result.setVisible(true);
								device.result.setText("" + device.energia);
							}
							
						}
						revalidate();
						repaint();
					}
				}
				catch(Exception connectionError)
				{
					System.out.println(connectionError);
					connectionError.printStackTrace();
				    JOptionPane.showMessageDialog(null, "Connection Lost", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		protected void done()
		{
			System.out.println("Saiu do atualizador");
		}
	};
	{	
/////////////////////////////////////////- Teste
//	SwingWorker worker = new SwingWorker()  // Atualizador em Tempo Real!
//	{
//		@Override
//		protected Object doInBackground() throws Exception 
//		{
//			while(true)
//			{
//				try
//				{
//					JAXBContext jct = JAXBContext.newInstance(MTConnectStreamsType.class);
//					Unmarshaller ut = jct.createUnmarshaller();
//	//				URL urlt = new URL( "http://agent.mtconnect.org/current" );
//					URL urlt = new URL(agent.getIP() + "/current" );
//					JAXBElement<MTConnectStreamsType> elementt =(JAXBElement<MTConnectStreamsType>)ut.unmarshal(urlt);
//					final MTConnectStreamsType currentt = elementt.getValue();
//					textField7.setText(""+currentt.getHeader().getCreationTime());
//				}
//				catch(Exception connectionError)
//				{
//				    JOptionPane.showMessageDialog(null, "Connection Lost", "Error", JOptionPane.ERROR_MESSAGE);
//				}
//				while(b)
//				{
//					MTConnectStreamsType current = null;
//					try
//					{
//						JAXBContext jc = JAXBContext.newInstance(MTConnectStreamsType.class);
//						Unmarshaller u = jc.createUnmarshaller();
//						URL url = new URL(agent.getIP() +  "/current");
//						JAXBElement<MTConnectStreamsType> element =(JAXBElement<MTConnectStreamsType>)u.unmarshal(url);
//						current = element.getValue();
//						textField7.setText(""+current.getHeader().getCreationTime());
//						flag = true;
//					}
//					catch(Exception connectionError)
//					{
//						JOptionPane.showMessageDialog(null, "Connection Lost", "Error", JOptionPane.ERROR_MESSAGE);
//						
//						flag = false;
//					}
//					int k = 0;
//					while(k < textFieldList.size() && flag)
//					{
//						for(int z = 0 ; z < buttons.size() ; z++)
//						{	
//							int j = buttons.get(z);
//							c = true;
//							s = true;
//							e = true;
//							try
//							{
//								if(s)
//								{
//									for (int i = 0 ; i < current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getSamples().getSample().size(); i++)
//									{
//										String string = (String)current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getSamples().getSample().get(i).getValue().getValue();
//										if (string.toUpperCase().equals("AVAILABLE") || string.toUpperCase().equals("ACTIVE"))
//										{
//											textFieldList.get(k).setForeground(new Color(0,128,0));
//										}
//										else if(string.toUpperCase().equals("UNAVAILABLE"))
//										{
//												textFieldList.get(k).setForeground(Color.RED);	
//										}
//										textFieldList.get(k).setText(string);
//										k++;
//									}
//								}
//							}
//							catch (Exception error)
//							{
//								s = false;
//								System.out.println("Estive aqui S");
//							}
//							try
//							{
//								if (c)
//								{
//									for (int i = 0 ; i < current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().size(); i++)
//									{
//										
//										if(current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getName().getLocalPart() == "Normal" |
//										   current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getName().getLocalPart().equals("AVAILABLE"))
//										{	
//											textFieldList.get(k).setForeground(new Color(0,128,0));
//										}
//										else if(current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getName().getLocalPart() == "Warning" | 
//												current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getName().getLocalPart() == "Unavailable" | 
//												current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getName().getLocalPart().equals("UNAVAILABLE"))
//										{	
//											textFieldList.get(k).setForeground(Color.RED);
//											if(!textPane1.getText().contains(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getValue().getTimestamp()) && current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getName().getLocalPart() == "Warning" )
//											{
//												//textPane1.setForeground(Color.RED);
//												textPane1.setText(textPane1.getText() + "\n"+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getName().getLocalPart()+" --> " + current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getValue().getValue()+ "---"+"Time:"+ current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getValue().getTimestamp());
//											}
//										}
//										textFieldList.get(k).setText(current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getName().getLocalPart());
////										if(current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getName().getLocalPart().equals("Warning") && war == true)
////										{
////											textPane1.setForeground(Color.RED);
////											textPane1.setText(textPane1.getText() + "\n" + current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getValue().getValue()+ "---"+"Time:"+ current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getValue().getTimestamp()+ "\n");
////										}
//										k++;
//									}
//								}
//							}
//							catch(Exception error2)
//							{
//							c = false;
//							System.out.println("Estive aqui C");
//							}
//							try
//							{
//								if(e)
//								{
//									
//									for (int i = 0 ; i < current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().size(); i++)
//									{
//										if(current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue() == "Normal" | current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().equals("AVAILABLE") | current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().equals("ON") || current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().equals("ARMED") ||current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().equals("ACTIVE"))
//											textFieldList.get(k).setForeground(new Color(0,128,0));
//										else if(current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue() == "Warning" | current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue() == "Unavailable" | current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().equals("UNAVAILABLE") || current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().equals("OFF"))
//											textFieldList.get(k).setForeground(Color.RED);
//										textFieldList.get(k).setText(current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue());
//										if(current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().toUpperCase().equals("STOPPED") || current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().equals("OFF") )
//										{
//											//textPane1.setForeground(Color.RED);
//											String value = current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue();
//											if(!textPane1.getText().contains(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getTimestamp()))
//												textPane1.setText(textPane1.getText() + "\n" + current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getName().getLocalPart()+" --> "+value + "---"+"Time:"+ current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getTimestamp());
//										}
//										else if(current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().toUpperCase().equals("ON")||current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().equals("ACTIVE") || current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().equals("READY"))
//										{
//											//textPane1.setForeground(Color.GREEN);
//											String value = current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue();
//											if(!textPane1.getText().contains(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getTimestamp()))
//												textPane1.setText(textPane1.getText() + "\n"+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getName().getLocalPart()+" --> " + value + "---"+"Time:"+ current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getTimestamp());
//										}
////										if(current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue().toUpperCase().equals("ON"))
////										{
////											String name = current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue();
////											if(!textPane1.getText().contains(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getTimestamp()))
////												textPane1.setText(textPane1.getText() + "\n" + name + "---"+"Time:"+ current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getTimestamp());
////										}
//										k++;
//									}
//								}
//							}
//							catch(Exception error3)
//							{
//								e = false;
//								System.out.println("Estive aqui E");
//							}
//						}
//					}
//				}
//			}
//		}
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
	}		
	public Agent getAgent() 
	{
		return agent;
	}
	public void setAgent(Agent agent) 
	{
		this.agent = agent;
	}
	public void addToDataBase(String component, String subcomponent, String value, XMLGregorianCalendar time)
	{
		try
		{
			java.sql.Statement statement = device.getConn().getConn().createStatement();
			statement.executeUpdate(
				   "INSERT INTO Registers (value, subcomponent, component, device, year, month, day, hour, minute, second, milisecond)" +
				   "VALUES ('" + value + "','" + subcomponent + "','" + component + "','" +  device.getName() + "'," + time.getYear() + "," +   time.getMonth() + "," + time.getDay() + "," +  time.getHour() + "," + time.getMinute() + ","  + time.getSecond() + "," + time.getMillisecond() + ");"
				   );
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
