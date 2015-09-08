package br.UFSC.GRIMA.application.dataTools;

import java.net.URL;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;

import org.jfree.data.time.TimeSeries;
import org.jfree.data.xy.XYSeries;

import com.sun.tools.xjc.reader.gbind.Element;

import br.UFSC.GRIMA.application.Agent;
import br.UFSC.GRIMA.application.entities.streams.ComponentStreamType;
import br.UFSC.GRIMA.application.entities.streams.ConditionType;
import br.UFSC.GRIMA.application.entities.streams.EventType;
import br.UFSC.GRIMA.application.entities.streams.MTConnectStreamsType;
import br.UFSC.GRIMA.application.entities.streams.SampleType;

public class GDataserie 
{
	private String name;
	public int componentIndex;
	public int subComponentIndex;
	private boolean chartType;
	public TimeSeries numericSerie;
	
	
	
	
	public GDataserie(String Name, String dataItemId, int componentIndex, int SEC, int subComponentIndex, Agent agent)
	{
		setName(Name);
		this.componentIndex = componentIndex;
		this.subComponentIndex = subComponentIndex;
		this.numericSerie = new TimeSeries(this.name);
		try
		{
			JAXBContext jc = JAXBContext.newInstance(MTConnectStreamsType.class);
			Unmarshaller u = jc.createUnmarshaller();
			URL url = new URL(agent.getIP() + "/sample?path=//Axes//DataItem[@id='" + dataItemId + "']" );
			JAXBElement<MTConnectStreamsType> element =(JAXBElement<MTConnectStreamsType>)u.unmarshal(url);
			if (!element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getSamples().getSample().isEmpty())
			{
				final List<JAXBElement<? extends SampleType>> samples = (List<JAXBElement<? extends SampleType>>)element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getSamples().getSample();
				setChartType(false);  //string
				for(int i=0;i<samples.size();i++)
				{
					addToSerie(samples.get(i).getValue().getTimestamp(), samples.get(i).getValue().getValue());
				}
				
			}
			else if (!element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getEvents().getEvent().isEmpty())
			{
				final List<JAXBElement<? extends EventType>> samples = element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getEvents().getEvent();
			}
			else if (!element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getCondition().getCondition().isEmpty())
			{
				final List<JAXBElement<? extends ConditionType>> samples = element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getCondition().getCondition();
			}
		}
		catch(Exception connectionError)
		{
		    JOptionPane.showMessageDialog(null, "Connection Lost", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	public GDataserie(String Name,  int componentIndex, int SEC, int subComponentIndex)
	{
		setName(Name);
		this.componentIndex = componentIndex;
		this.subComponentIndex = subComponentIndex;
		
	}
	public void addToSerie(XMLGregorianCalendar time, int yValue)
	{
		
	}
	public void addToSerie(XMLGregorianCalendar time, String yValue)
	{
		
	}
	
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public boolean isChartType() 
	{
		return chartType;
	}
	public void setChartType(boolean chartType) 
	{
		this.chartType = chartType;
	}
}
