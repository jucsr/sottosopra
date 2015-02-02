package br.UFSC.GRIMA.application;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import br.UFSC.GRIMA.application.entities.streams.MTConnectStreamsType;
import br.UFSC.GRIMA.application.visual.AboutWindow;
import br.UFSC.GRIMA.application.visual.BeginWindow;

public class ClientApplication extends BeginWindow implements ActionListener
{
	int conterC = 0; // --> Coluna, Column
	public ArrayList<JPanel> panelList = new ArrayList<JPanel>();
	public ClientApplication()
	{
		this.comboBox1.addActionListener(this);
		this.menuItem1.addActionListener(this);
		this.adjustJFrame();
		//////Visibles
		this.setVisible(true);
		
	}
	public void adjustJFrame() {
		// this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Toolkit toolkit = this.getToolkit();

//		this.setResizable(false);
		this.pack();

		Dimension windowDimension = toolkit.getScreenSize();
		Dimension thisDimension = this.getPreferredSize();
		this.setSize(new Dimension((int)(windowDimension.width/2), (int)(windowDimension.height / 2)));
		this.setLocation((int) (windowDimension.getWidth() - thisDimension.getWidth()) / 2, (int) (windowDimension.getHeight() - thisDimension.getHeight()) / 2);

	}

	public void actionPerformed(ActionEvent event)
	{
		Object source = event.getSource();
		if ( 2 == comboBox1.getSelectedIndex()) // Modo Current 
		{
			try {
				current();
				this.revalidate();
				this.repaint();
			} catch (MalformedURLException | JAXBException e) {
				e.printStackTrace();
			}
		}
		if (source == menuItem1)
		{
			AboutWindow nojaJanela = new AboutWindow(this);
			nojaJanela.setVisible(true);
		}
	}
		
	
	public void current() throws JAXBException, MalformedURLException //--> O Que deve aparecer para o usuário em relação á máquina selecionada.
	{
		JAXBContext jc = JAXBContext.newInstance(MTConnectStreamsType.class);
		Unmarshaller u = jc.createUnmarshaller();
		URL url = new URL( "http://agent.mtconnect.org/current" );
		JAXBElement<MTConnectStreamsType> element =(JAXBElement<MTConnectStreamsType>)u.unmarshal(url);
		final MTConnectStreamsType current = element.getValue();
		textField1.setText(current.getStreams().getDeviceStream().get(0).getName()); // -- Nome da Machine
		textField2.setText(current.getStreams().getDeviceStream().get(0).getUuid()); // -- ID ---> padrão pra todas
		int cont = current.getStreams().getDeviceStream().get(0).getComponentStream().size(); // --> Numero de Components Streams!
		for (int i = 0; i < cont ; i++) //---> Quantos Botões deve colocar devido aos dados de ComponentStream
		{
			final JToggleButton toggleTemp = new JToggleButton();
			//---- toggleButton1 ----
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
						panelList.get(j).setBorder(new CompoundBorder(
								new TitledBorder(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getComponent()+"-"+ current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getName()),
								new EmptyBorder(5, 5, 5, 5)));
						
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
							label7.setText("Samples:");
							panelList.get(j).add(label7, new GridBagConstraints(1 + conterC, 1, 1, 1, 0.0, 0.0,
								GridBagConstraints.CENTER, GridBagConstraints.BOTH,
								new Insets(0, 0, 5, 0), 0, 0));
							for (int i = 0 ; i < current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getSamples().getSample().size(); i++)
							{ 
								//---- label8 ----
								JLabel label8 = new JLabel();
								JTextField textField3 = new JTextField();
								label8.setText(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getSamples().getSample().get(i).getValue().getName());
								textField3.setText(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getSamples().getSample().get(i).getValue().getValue());
								textField3.setEditable(false);
								panelList.get(j).add(label8, new GridBagConstraints(0 + conterC, 2+ conterR, 1, 1, 0.0, 0.0,
									GridBagConstraints.CENTER, GridBagConstraints.BOTH,
									new Insets(0, 0, 5, 5), 0, 0));
								panelList.get(j).add(textField3, new GridBagConstraints(1 + conterC, 2+ conterR, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
									new Insets(0, 0, 5, 0), 0, 0));
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
							label7.setText("Condition:");
							panelList.get(j).add(label7, new GridBagConstraints(1+ conterC, 2+conterR , 1, 1, 0.0, 0.0,
								GridBagConstraints.CENTER, GridBagConstraints.BOTH,
								new Insets(0, 0, 5, 0), 0, 0));
							conterR++;
							for (int i = 0 ; i < current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().size(); i++)
							{
								//---- label8 ----
								JLabel label8 = new JLabel();
								JTextField textField3 = new JTextField();
								label8.setText(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getValue().getDataItemId());
								textField3.setText(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getCondition().getCondition().get(i).getValue().getType());
								textField3.setEditable(false);
								panelList.get(j).add(label8, new GridBagConstraints(0 + conterC, 2+ conterR, 1, 1, 0.0, 0.0,
									GridBagConstraints.CENTER, GridBagConstraints.BOTH,
									new Insets(0, 0, 5, 5), 0, 0));
								panelList.get(j).add(textField3, new GridBagConstraints(1 + conterC, 2+ conterR, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
									new Insets(0, 0, 5, 0), 0, 0));
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
							label7.setText("Events:");
							panelList.get(j).add(label7, new GridBagConstraints(1+ conterC, 2+conterR , 1, 1, 0.0, 0.0,
								GridBagConstraints.CENTER, GridBagConstraints.BOTH,
								new Insets(0, 0, 5, 0), 0, 0));
							conterR++;
							for (int i = 0 ; i < current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().size(); i++)
							{
								//---- label8 ----
								JLabel label8 = new JLabel();
								JTextField textField3 = new JTextField();
								label8.setText(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getDataItemId());
								textField3.setText(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(j).getEvents().getEvent().get(i).getValue().getValue());
								textField3.setEditable(false);
								panelList.get(j).add(label8, new GridBagConstraints(0 + conterC, 2+ conterR, 1, 1, 0.0, 0.0,
									GridBagConstraints.CENTER, GridBagConstraints.BOTH,
									new Insets(0, 0, 5, 5), 0, 0));
								panelList.get(j).add(textField3, new GridBagConstraints(1 + conterC, 2+ conterR, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
									new Insets(0, 0, 5, 0), 0, 0));
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
}
