package br.UFSC.GRIMA.application;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JToggleButton;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import br.UFSC.GRIMA.application.entities.streams.MTConnectStreamsType;
import br.UFSC.GRIMA.application.visual.BeginWindow;

public class ClientApplication extends BeginWindow implements ActionListener
{
	private double x;
	private double y;
	private double z;
	private double speed;
	public ClientApplication()
	{
		this.comboBox1.addActionListener(this);
		//////Visibles
		this.setVisible(true);
		
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
	}
		
	
	public void current() throws JAXBException, MalformedURLException //--> O Que deve aparecer para o usuário em relação á máquina selecionada.
	{
		JAXBContext jc = JAXBContext.newInstance(MTConnectStreamsType.class);
		Unmarshaller u = jc.createUnmarshaller();
		URL url = new URL( "http://agent.mtconnect.org/current" );
		JAXBElement<MTConnectStreamsType> element =(JAXBElement<MTConnectStreamsType>)u.unmarshal(url);
		MTConnectStreamsType current = element.getValue();
		textField1.setText(current.getStreams().getDeviceStream().get(0).getName()); // -- Nome da Machine
		textField2.setText(current.getStreams().getDeviceStream().get(0).getUuid()); // -- ID ---> padrão pra todas
		int cont = current.getStreams().getDeviceStream().get(0).getComponentStream().size(); // --> Numero de Components Streams!
		for (int i = 0; i < cont ; i++) //---> Quantos Botões deve colocar devido aos dados de ComponentStream
		{
			final JToggleButton toggleTemp = new JToggleButton();
			//---- toggleButton1 ----
			toggleTemp.setName("i");
			toggleTemp.setText(""+current.getStreams().getDeviceStream().get(0).getComponentStream().get(i).getComponent()+"-"+ current.getStreams().getDeviceStream().get(0).getComponentStream().get(i).getName());
			panel3.add(toggleTemp, new GridBagConstraints(1, 3+i, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 0), 0, 0));
			toggleTemp.addActionListener(new ActionListener() 
			{
				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					if(toggleTemp.isSelected())
					{
						
					}
				}
				
			});
			
		}
		panel3.repaint();
	}
	
	
	
	
	
	
	
	
	//	public void linear() throws Exception
//	{
//		if (1 == comboBox1.getSelectedIndex()) // Modo Current
//		{
//			int cont;
//			JAXBContext jc = JAXBContext.newInstance(MTConnectStreamsType.class);
//			Unmarshaller u = jc.createUnmarshaller();
//			URL url = new URL( "http://agent.mtconnect.org/current?path=//Axes//Linear//DataItem[@subType='ACTUAL']" );
//			JAXBElement<MTConnectStreamsType> element =(JAXBElement<MTConnectStreamsType>)u.unmarshal(url);
//			MTConnectStreamsType linear1 = element.getValue();
//			cont = linear1.getStreams().getDeviceStream().get(0).getComponentStream().size();
//			if (cont == 2)
//			{
//				label6.setText("Position "+ linear1.getStreams().getDeviceStream().get(0).getComponentStream().get(0).getName()+ ":");
//				label7.setText("Position "+ linear1.getStreams().getDeviceStream().get(0).getComponentStream().get(1).getName()+ ":");
//				textField3.setText(linear1.getStreams().getDeviceStream().get(0).getComponentStream().get(0).getSamples().getSample().get(0).getValue().getValue());
//				textField4.setText(linear1.getStreams().getDeviceStream().get(0).getComponentStream().get(1).getSamples().getSample().get(0).getValue().getValue());
//			}
//			if (cont == 3)
//			{
//				label6.setText("Position "+ linear1.getStreams().getDeviceStream().get(0).getComponentStream().get(0).getName()+ ":");
//				label7.setText("Position "+ linear1.getStreams().getDeviceStream().get(0).getComponentStream().get(1).getName()+ ":");
//				label8.setText("Position "+ linear1.getStreams().getDeviceStream().get(0).getComponentStream().get(2).getName()+ ":");
//				textField3.setText(linear1.getStreams().getDeviceStream().get(0).getComponentStream().get(0).getSamples().getSample().get(0).getValue().getValue());
//				textField4.setText(linear1.getStreams().getDeviceStream().get(0).getComponentStream().get(1).getSamples().getSample().get(0).getValue().getValue());
//				textField5.setText(linear1.getStreams().getDeviceStream().get(0).getComponentStream().get(2).getSamples().getSample().get(0).getValue().getValue());
//			}
//			textField1.setText(linear1.getStreams().getDeviceStream().get(0).getName());
//			textField2.setText(linear1.getStreams().getDeviceStream().get(0).getUuid());
//			
//			
//		}
//	}
//	public void rotary() throws JAXBException, Exception
//	{
//		if (1 == comboBox1.getSelectedIndex()) // Modo Current
//		{
//			JAXBContext jc = JAXBContext.newInstance(MTConnectStreamsType.class);
//			Unmarshaller u = jc.createUnmarshaller();
//			URL url = new URL ( "http://agent.mtconnect.org/current?path=//Axes//Rotary//DataItem[@subType='ACTUAL']");
//			JAXBElement<MTConnectStreamsType> element =(JAXBElement<MTConnectStreamsType>)u.unmarshal(url);
//			MTConnectStreamsType rotary1 = element.getValue();
//			textField1.setText(rotary1.getStreams().getDeviceStream().get(0).getName());
//			textField2.setText(rotary1.getStreams().getDeviceStream().get(0).getUuid());
//			label9.setText(""+ rotary1.getStreams().getDeviceStream().get(0).getComponentStream().get(0).getSamples().getSample().get(0).getValue().getName()+":");
//			textField6.setText(rotary1.getStreams().getDeviceStream().get(0).getComponentStream().get(0).getSamples().getSample().get(0).getValue().getValue());
//		}
//	}
}
