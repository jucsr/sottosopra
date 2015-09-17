package br.UFSC.GRIMA.application.dataTools;

import java.net.URL;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;

import org.jfree.data.time.Millisecond;
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
	private String dataItemId;
	public int componentIndex;
	public int SEC; //Sample(0) or Event(1) or Condition(2)
	public int subComponentIndex;
	private boolean numericChart = false;
	private boolean categoryChart = false;
	public TimeSeries serie;
	private Millisecond range = new Millisecond();
	public String[] categoryAxesValues;
	
	
	
	public GDataserie(String Name, String dataItemId, int componentIndex, int SEC, int subComponentIndex, Agent agent, String[] string)
	{
		System.out.println("GDS: iniciando criação do ds");
		setName(Name);
		setDataItemId(dataItemId);
		this.componentIndex = componentIndex;
		this.subComponentIndex = subComponentIndex;
		this.SEC = SEC;
		this.categoryAxesValues = string;
		System.out.println("GDS: setando valores. nome, id, comp.index, sc.index , sec, categoryAxes.: " + Name + " " + this.dataItemId + " " + this.componentIndex + " " + this.subComponentIndex + " " + this.SEC + " " + this.categoryAxesValues);
		if (this.getName() != null)
		{
			this.serie = new TimeSeries(this.name);
		}
		else
		{
			this.serie = new TimeSeries(dataItemId);
		}
		System.out.println("GDS: setando nome na dataserie: " + this.serie.getKey());
		try
		{
			JAXBContext jc = JAXBContext.newInstance(MTConnectStreamsType.class);
			Unmarshaller u = jc.createUnmarshaller();
			URL url = new URL(agent.getIP() + "/sample?path=//DataItem[@id='" + dataItemId + "']" );
			JAXBElement<MTConnectStreamsType> element =(JAXBElement<MTConnectStreamsType>)u.unmarshal(url);
			if (!element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getSamples().getSample().isEmpty())
			{
				
				for(int i=0;i<element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getSamples().getSample().size();i++)
				{
					addToSerie(element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getSamples().getSample().get(i).getValue().getTimestamp(), 
							   element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getSamples().getSample().get(i).getValue().getValue());
				}
			}
			else if (!element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getEvents().getEvent().isEmpty())
			{
				for (int i=0; i<element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getEvents().getEvent().size();i++)
				{
					addToSerie(element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getEvents().getEvent().get(i).getValue().getTimestamp(),
							   element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getEvents().getEvent().get(i).getValue().getValue());
				}
			}
			else if (!element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getCondition().getCondition().isEmpty())
			{
				for (int i=0; i<element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getCondition().getCondition().size();i++)
				{
					addToSerie(element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getCondition().getCondition().get(i).getValue().getTimestamp(),
							   element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getCondition().getCondition().get(i).getValue().getValue());
				}
			}
			else
			{
				System.out.println("Empty Sample request. " + dataItemId);
			}
		}
		catch(Exception connectionError)
		{
			System.out.println(connectionError);
		    JOptionPane.showMessageDialog(null, "Connection Lost ds", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	public GDataserie(String Name, String dataItemId,  int componentIndex, int SEC, int subComponentIndex, String[] string)
	{
		System.out.println("GDS: iniciando criação do ds");
		setName(Name);
		setDataItemId(dataItemId);
		this.componentIndex = componentIndex;
		this.subComponentIndex = subComponentIndex;
		this.SEC = SEC;
		this.categoryAxesValues = string;
		System.out.println("GDS: setando valores. nome, id, comp.index, sc.index , sec, categoryAxes.: " + Name + " " + this.dataItemId + " " + this.componentIndex + " " + this.subComponentIndex + " " + this.SEC + " " + this.categoryAxesValues);
		if (this.getName() != null)
		{
			this.serie = new TimeSeries(this.name);
		}
		else
		{
			this.serie = new TimeSeries(dataItemId);
		}
		System.out.println("GDS: setando nome na dataserie: " + this.serie.getKey());
	}
	public void setRange (Millisecond range)
	{																		/////////////////setar tamanho maximo
		this.range = range;
		this.serie.setMaximumItemAge(range.getMillisecond());
	}
	public void addToSerie(XMLGregorianCalendar xtime, String yValue)
	{
		System.out.println("GDS:    Iniciando addToSerie. tempo, valor: " + xtime.toString() + " " + yValue);
		Millisecond time = new Millisecond(xtime.getMillisecond(), xtime.getSecond(), xtime.getMinute(), xtime.getHour(), xtime.getDay(), xtime.getMonth(), xtime.getYear());
		System.out.println("GDS:         tempo convertido: " + time.toString());
		System.out.println("GDS:         verificando tipo de serie. catChart, numchart" + categoryChart + " " + numericChart);
		if (!categoryChart && !numericChart)
		{
			try
			{
				if(yValue.toUpperCase().equals("UNAVAILABLE"))
				{
					System.out.println("GDS:             null identificado");
					serie.addOrUpdate(time, null);
				}
				else
				{
					double numValue =  Double.parseDouble(yValue);
					this.numericChart = true;
					System.out.println("GDS:            numeric chart identificado: " + numValue);
					serie.addOrUpdate(time, numValue);
				}
			}
			catch (Exception e)
			{
				this.categoryChart = true;
				System.out.println("GDS:             category chart identificado, chamando getCategoryPosition.");
				serie.addOrUpdate(time, getCategoryPosition(yValue));
			}
		}
		else if (categoryChart)
		{
			System.out.println("GDS:      categoryChart pre definido");
			serie.addOrUpdate(time, getCategoryPosition(yValue));
		}
		else if (numericChart)
		{
			System.out.print("GDS:     numchart pre efinido. adicionando ");
			if(yValue.toUpperCase().equals("UNAVAILABLE"))
			{
				serie.addOrUpdate(time, null);
				System.out.println("nulo");
			}
			else
			{
				double numValue =  Double.parseDouble(yValue);
				System.out.println("GDS:      valor, tempo" + numValue + " " + time.toString());
				serie.addOrUpdate(time, numValue);
			}
		}
	}
	public int getCategoryPosition (String string)
	{
		System.out.println("GDS: Inicializando getCategoryPosition, iniciando for: "  + this.categoryAxesValues.length + ", string: " + string);
		int i;
		
		for ( i=0; i< this.categoryAxesValues.length && categoryAxesValues[i]!= null;i++)
		{
			System.out.println("GDS:       " + i + "o loop. valor corrente na lista de strings: " + categoryAxesValues[i]);
			if (string.equals(categoryAxesValues[i]))
			{
				System.out.println("GDS:      returning index " + i + " com string: " + categoryAxesValues[i]);
				return i;
			}
		}
		System.out.println("GDS:       Creating new string on axis: " + string + " na posição " + i);
		categoryAxesValues[i]= string;
		return i;
	}
	
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
		public String getDataItemId() {
		return dataItemId;
	}
	public void setDataItemId(String dataItemId) {
		this.dataItemId = dataItemId;
	}
	public boolean isCategoryChart()
	{
		return this.categoryChart;
	}
	public boolean isNumericChart()
	{
		return this.numericChart;
	}
	public String getLastValue()
	{
		System.out.println("GDS: iniciando getLastValue. tamanho da serie: " + this.serie.getItemCount() + " com ultimo valor: " + this.serie.getValue(this.serie.getItemCount() - 1));
		if (this.serie.getValue(this.serie.getItemCount() - 1) != null)
		{
			return this.serie.getValue(this.serie.getItemCount() - 1).toString();
		}
		else return null;
	}
}
