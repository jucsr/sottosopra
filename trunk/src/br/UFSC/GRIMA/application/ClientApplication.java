package br.UFSC.GRIMA.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.Action;
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
		this.toggleButton1.addActionListener(this);
		this.toggleButton4.addActionListener(this);
		this.toggleButton5.addActionListener(this);
		//////Visibles
		this.setVisible(true);
		this.toggleButton4.setVisible(false);
		this.toggleButton5.setVisible(false);
		label6.setVisible(false);
		label7.setVisible(false);
		label8.setVisible(false);
		label9.setVisible(false);
		textField3.setVisible(false);
		textField4.setVisible(false);
		textField5.setVisible(false);
		textField6.setVisible(false);
	}
	public void actionPerformed(ActionEvent event)
	{
		Object source = event.getSource();
		if (source == toggleButton1)
		{
			this.toggleButton4.setVisible(true);
			this.toggleButton5.setVisible(true);
		}
		if (toggleButton4.isSelected())
		{	
			label6.setVisible(true);
			label7.setVisible(true);
			label8.setVisible(true);
			textField3.setVisible(true);
			textField4.setVisible(true);
			textField5.setVisible(true);
			try {
				linear();
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
			else if(!toggleButton4.isSelected())
			{
				label6.setVisible(false);
				label7.setVisible(false);
				label8.setVisible(false);
				textField3.setVisible(false);
				textField4.setVisible(false);
				textField5.setVisible(false);
			}
		if (toggleButton5.isSelected())
		{
			label9.setVisible(true);
			textField6.setVisible(true);
			try {
				rotary();
			} catch (JAXBException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(!toggleButton5.isSelected())
		{
			label9.setVisible(false);
			textField6.setVisible(false);
		}
		
		
		}
	public void linear() throws Exception
	{
		if (1 == comboBox1.getSelectedIndex()) // Modo Current
		{
			JAXBContext jc = JAXBContext.newInstance(MTConnectStreamsType.class);
			Unmarshaller u = jc.createUnmarshaller();
			URL url = new URL( "http://agent.mtconnect.org/current?path=//Axes//Linear//DataItem[@subType='ACTUAL']" );
			JAXBElement<MTConnectStreamsType> element =(JAXBElement<MTConnectStreamsType>)u.unmarshal(url);
			MTConnectStreamsType linear1 = element.getValue();
			textField1.setText(linear1.getStreams().getDeviceStream().get(0).getName());
			textField2.setText(linear1.getStreams().getDeviceStream().get(0).getUuid());
			textField3.setText(linear1.getStreams().getDeviceStream().get(0).getComponentStream().get(0).getSamples().getSample().get(0).getValue().getValue());
			textField4.setText(linear1.getStreams().getDeviceStream().get(0).getComponentStream().get(1).getSamples().getSample().get(0).getValue().getValue());
			textField5.setText(linear1.getStreams().getDeviceStream().get(0).getComponentStream().get(2).getSamples().getSample().get(0).getValue().getValue());
			
		}
	}
	public void rotary() throws JAXBException, Exception
	{
		if (1 == comboBox1.getSelectedIndex()) // Modo Current
		{
			JAXBContext jc = JAXBContext.newInstance(MTConnectStreamsType.class);
			Unmarshaller u = jc.createUnmarshaller();
			URL url = new URL ( "http://agent.mtconnect.org/current?path=//Axes//Rotary//DataItem[@subType='ACTUAL']");
			JAXBElement<MTConnectStreamsType> element =(JAXBElement<MTConnectStreamsType>)u.unmarshal(url);
			MTConnectStreamsType rotary1 = element.getValue();
			textField1.setText(rotary1.getStreams().getDeviceStream().get(0).getName());
			textField2.setText(rotary1.getStreams().getDeviceStream().get(0).getUuid());
			textField6.setText(rotary1.getStreams().getDeviceStream().get(0).getComponentStream().get(0).getSamples().getSample().get(0).getValue().getValue());
		}
	}

}
