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
	private boolean axesOutOfRange = false;
	private TimeSeries serie;
	private int[] range = new int[4];
	public String[] categoryAxesValues = new String[30];
	public Integer[] categoryAxesIndexs = new Integer[30];
	
	
	
 	public GDataserie(String Name, String dataItemId, int componentIndex, int SEC, int subComponentIndex, Agent agent)
	{
		System.out.println("GDS: iniciando criação do ds");
		setName(Name);
		setDataItemId(dataItemId);
		this.componentIndex = componentIndex;
		this.subComponentIndex = subComponentIndex;
		this.SEC = SEC;
		range[2] = 1;
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
//			if (!element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getSamples().getSample().isEmpty())
//			{
//				
//				for(int i=0;i<element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getSamples().getSample().size();i++)
//				{
//					addToSerie(element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getSamples().getSample().get(i).getValue().getTimestamp(), 
//							   element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getSamples().getSample().get(i).getValue().getValue());
//				}
//			}
//			else if (!element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getEvents().getEvent().isEmpty())
//			{
//				for (int i=0; i<element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getEvents().getEvent().size();i++)
//				{
//					addToSerie(element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getEvents().getEvent().get(i).getValue().getTimestamp(),
//							   element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getEvents().getEvent().get(i).getValue().getValue());
//				}
//			}
//			else if (!element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getCondition().getCondition().isEmpty())
//			{
//				for (int i=0; i<element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getCondition().getCondition().size();i++)
//				{
//					addToSerie(element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getCondition().getCondition().get(i).getValue().getTimestamp(),
//							   element.getValue().getStreams().getDeviceStream().get(0).getComponentStream().get(0).getCondition().getCondition().get(i).getValue().getValue());
//				}
//			}
//			else
//			{
//				System.out.println("Empty Sample request. " + dataItemId);
//			}
		}
		catch(Exception connectionError)
		{
			System.out.println(connectionError);
		    JOptionPane.showMessageDialog(null, "Connection Lost ds", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	public GDataserie(String Name, String dataItemId,  int componentIndex, int SEC, int subComponentIndex)
	{
		System.out.println("GDS: iniciando criação do ds");
		setName(Name);
		setDataItemId(dataItemId);
		this.componentIndex = componentIndex;
		this.subComponentIndex = subComponentIndex;
		this.SEC = SEC;
		range[2] = 1;
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
	public void addToSerie(XMLGregorianCalendar xtime, String yValue, XMLGregorianCalendar creationTime, String[] deviceList)
	{
		Millisecond time = new Millisecond(xtime.getMillisecond(), xtime.getSecond(), xtime.getMinute(), xtime.getHour(), xtime.getDay(), xtime.getMonth(), xtime.getYear());
		Millisecond inicialTime = new Millisecond(0, creationTime.getSecond(), creationTime.getMinute(), creationTime.getHour(), creationTime.getDay(), creationTime.getMonth(), creationTime.getYear());
		if (!categoryChart && !numericChart)
		{
			time = inicialTime;
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
				serie.addOrUpdate(time, getCategoryPosition(yValue, deviceList));
			}
		}
		else if (categoryChart)
		{
			System.out.println("GDS:      categoryChart pre definido");
			if(serie.getItemCount()>0)
			{
				if (yValue.equals(categoryAxesValues[Math.round(Float.parseFloat(getLastValue()))]))
				{
					time = inicialTime;
				}
			}
			if (yValue.equals(categoryAxesValues[Math.round(Float.parseFloat(getLastValue()))]))
			{
				time = inicialTime;
			}
			serie.addOrUpdate(time, getCategoryPosition(yValue, deviceList));
		}
		else if (numericChart)
		{
			System.out.print("GDS:     numchart pre efinido. adicionando ");
			if(serie.getItemCount()>0)
			{
				if (serie.getValue(serie.getItemCount()-1).equals(Double.parseDouble(yValue)))
				{
					time = inicialTime;
				}
			}
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
		////Descarta valores intermediarios////
		if (serie.getItemCount() > 2)
		{
			if (serie.getValue(serie.getItemCount()-1) == null && serie.getValue(serie.getItemCount()-2) == null && serie.getValue(serie.getItemCount()-3) == null)
			{
				serie.delete(serie.getItemCount()-2, serie.getItemCount()-2); /// deleta o penúltimo registro
			}
			else if (serie.getValue(serie.getItemCount() - 1).equals(serie.getValue(serie.getItemCount()-2)) && serie.getValue(serie.getItemCount()-1).equals(serie.getValue(serie.getItemCount()-3)))
			{
				serie.delete(serie.getItemCount()-2, serie.getItemCount()-2); /// deleta o penúltimo registro
			}
		}
		System.out.println("###################tamanho da lista: " + serie.getItemCount());
		/////Descarta registros antigos
		Millisecond time0 = new Millisecond(0, inicialTime.getSecond().getSecond() - range[3], inicialTime.getSecond().getMinute().getMinute() - range[2], inicialTime.getSecond().getMinute().getHour().getHour() - range[1], inicialTime.getSecond().getMinute().getHour().getDay().getDayOfMonth() - range[0], inicialTime.getSecond().getMinute().getHour().getDay().getMonth(), inicialTime.getSecond().getMinute().getHour().getDay().getYear());
		if(serie.getItemCount() > 1)
		{
			for (int i=0; i < serie.getItemCount() - 1;i++)
			{
				if (time0.compareTo(serie.getTimePeriod(i)) <=0)
					break;
				else if (time0.compareTo(serie.getTimePeriod(i)) > 0 && time0.compareTo(serie.getTimePeriod(i+1)) < 0)
				{
					if (!axesOutOfRange)
					{
						if (!numericChart)
						{
							serie.addOrUpdate(time0, serie.getValue(i));
						}
						if (numericChart)
						{
							//faz uma aproximacao linear em t0
							double y1 = serie.getValue(i).doubleValue();
							double y2 = serie.getValue(i+1).doubleValue();
							long x0 = time0.getLastMillisecond();
							long x1 = serie.getTimePeriod(i).getLastMillisecond();
							long x2 = serie.getTimePeriod(i+1).getLastMillisecond();
							double a =  (double) ((y2 - y1 )/(x2 - x1));
							double b = (double) (y1 - a*x1);
							double yn = a*x0 + b;
							serie.addOrUpdate(time0, yn);
						}
						if (categoryChart)
						{
							serie.addOrUpdate(time0, serie.getValue(i - 1));
						}
						serie.delete(0, i);
					}
				}
			}
		}
	}
	public int getCategoryPosition (String string, String[] deviceList)
	{
		System.out.println("GDS: Inicializando getCategoryPosition, iniciando for: "  + this.categoryAxesValues.length + ", string: " + string);
		if (axesOutOfRange)
		{
			categoryAxesValues[0] = string;
			return 0;
		}
		int i;
		for ( i=0; i< this.categoryAxesValues.length && categoryAxesValues[i]!= null;i++)
		{
			System.out.println("GDS:       " + i + "o loop. valor corrente na lista de strings: " + categoryAxesValues[i]);
			if (string.equals(categoryAxesValues[i]))
			{
				System.out.println("GDS:      returning index " + i + " com string: " + categoryAxesValues[i]);
				return categoryAxesIndexs[i];
			}
		}
		if (i == 30)
		{
			axesOutOfRange = true;
			categoryAxesValues[0] = string;
			return 0;
		}
		System.out.println("GDS:       Creating new string on axis: " + string + " na posição " + i);
		categoryAxesValues[i]= string;
		int j;
		for (j = 0; i< deviceList.length && deviceList[j]!= null;j++)
		{
			if(string.equals(deviceList[j]))
			{
				categoryAxesIndexs[i] = j;
				return j;
			}
		}
		deviceList[j] = string;
		categoryAxesValues[i] = string;
		categoryAxesIndexs[i] = j;
		return j;
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
	public boolean isAxesOutOfRange()
	{
		return this.axesOutOfRange;
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
	public TimeSeries getSerie()
	{
		return this.serie;
	}
}
