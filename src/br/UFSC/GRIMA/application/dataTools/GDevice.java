package br.UFSC.GRIMA.application.dataTools;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EtchedBorder;
import javax.xml.datatype.XMLGregorianCalendar;

import org.hamcrest.core.IsAnything;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYStepAreaRenderer;
import org.jfree.chart.renderer.xy.XYStepRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import com.sun.codemodel.JLabel;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;



public class GDevice 
{
	private String name;
	private String uuid;
	public ArrayList<GComponent> componentStreamList = new ArrayList<GComponent>();
	private XMLGregorianCalendar lastTimestamp;
	
	public JPanel buttonPanel = new JPanel();
	public JPanel informationPanel = new JPanel();
	public JPanel checkBoxPanel = new JPanel();
	public JPanel graphPanel = new JPanel();
	
	private TimeSeriesCollection numDataset = new TimeSeriesCollection();
	private TimeSeriesCollection symbolDataset = new TimeSeriesCollection();
	private JPanel numPanel;
	private JPanel symbolPanel;
	public String[] categoryAxesValues = new String[1000];
	public String[] symbolParameter;
	public int categoryAxesValuesLenght;
	
	public ArrayList<GSubComponent> valuesToUpdate = new ArrayList<GSubComponent>();
	public ArrayList<GDataserie> sampleRequestList = new ArrayList<GDataserie>();
	public ArrayList<GSubComponent> graphsToUpdate = new ArrayList<GSubComponent>();
	public ArrayList<GDataserie> seriesToUpdate = new ArrayList<GDataserie>();
	
	SQLConnection conn = null;
	java.sql.Statement statement = null;
	
	///gambiarra
	public JToggleButton button = new JToggleButton();
	public double energia = 0;
	public String[] lista = new String[]{};
	public JComboBox julio;
	public javax.swing.JTextField result = new javax.swing.JTextField();
	
 	public GDevice (String name, String uuid)
	{
		this.name = name;
		this.uuid = uuid;
		buttonPanel.setBorder(new EtchedBorder());
		buttonPanel.setLayout(new GridBagLayout());
		((GridBagLayout)buttonPanel.getLayout()).columnWidths = new int[] {10, 0, 0};
		((GridBagLayout)buttonPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
		((GridBagLayout)buttonPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
		((GridBagLayout)buttonPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
		checkBoxPanel.setBorder(new EtchedBorder());
		checkBoxPanel.setLayout(new GridBagLayout());
		((GridBagLayout)checkBoxPanel.getLayout()).columnWidths = new int[] {10, 0, 0};
		((GridBagLayout)checkBoxPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
		((GridBagLayout)checkBoxPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
		((GridBagLayout)checkBoxPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
		
		informationPanel.setBorder(new EtchedBorder());
		informationPanel.setAlignmentY(0.0F);
		informationPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		// SQL Set
		conn = new SQLConnection();
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn.setConn("150.162.105.1", "mtcTest", "webcad", "julio123");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			statement = conn.getConn().createStatement();
			statement.executeUpdate(
					   "CREATE TABLE Registers ("+
					    "	 	Id  int NULL AUTO_INCREMENT,"+
						"	    PRIMARY KEY (Id),"+
						" 	    value  varchar(255) NULL ,"+
						" 	    subcomponent  varchar(255) NULL ,"+
						"  	    component  varchar(255) NULL ,"+
						"	    device  varchar(255) NULL,"+
						"		year  int NULL ,"+
						"		month  int NULL ,"+
						"		day  int NULL ,"+
						"		hour  int NULL ,"+
						"		minute  int NULL ,"+
						"		second int NULL ,"+
						"		milisecond  double NULL"+ 
						"	   )"+
						"	   ;");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}	
	public String getName() 
	{
		return name;
	}
	public String getUuid() {
		return uuid;
	}
	public TimeSeriesCollection getNumDataset() {
		return numDataset;
	}
	public void setNumDataset(TimeSeriesCollection numDataset) {
		this.numDataset = numDataset;
	}
	public TimeSeriesCollection getSymbolDataset() {
		return symbolDataset;
	}
	public void setSymbolDataset(TimeSeriesCollection symbolDataset) {
		this.symbolDataset = symbolDataset;
	}
	public void addToDatasetAll()
	{
		for (int i=0; i < graphsToUpdate.size();i++)
		{
			GSubComponent subComponent = graphsToUpdate.get(i);
			if (!subComponent.getDataserie().isAxesOutOfRange())
			{
				if (subComponent.getDataserie().isNumericChart())
				{
					numDataset.addSeries(subComponent.getDataserie().getSerie());
				}
				else if (subComponent.getDataserie().isCategoryChart())
				{
					symbolDataset.addSeries(subComponent.getDataserie().getSerie());
				}
				
			}
		}
	}
	public void addtoDataset (GSubComponent subComponent)
	{
		if (!subComponent.getDataserie().isAxesOutOfRange())
		{
			if (subComponent.getDataserie().isNumericChart())
			{
				numDataset.addSeries(subComponent.getDataserie().getSerie());
			}
			else if (subComponent.getDataserie().isCategoryChart())
			{
				symbolDataset.addSeries(subComponent.getDataserie().getSerie());
			}
			
		}
	}
	public void removeFromDataset(GSubComponent subComponent)
	{
		if(subComponent.getDataserie().isNumericChart())
		{
			numDataset.removeSeries(subComponent.getDataserie().getSerie());
		}
		else if (subComponent.getDataserie().isCategoryChart())
		{
			symbolDataset.removeSeries(subComponent.getDataserie().getSerie());
		}
	}
	public void setGraphCharts ()
	{
		graphPanel.setLayout(new GridBagLayout());
		((GridBagLayout)graphPanel.getLayout()).columnWidths = new int[] {10, 0, 0};
		((GridBagLayout)graphPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
		((GridBagLayout)graphPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
		((GridBagLayout)graphPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
		JFreeChart numericChart = ChartFactory.createTimeSeriesChart("Numeric Chart", "Time", "Values", numDataset, true, false, false);
		numPanel = new ChartPanel(numericChart);
		numPanel.setVisible(true);
		
		DateAxis xAxis = new DateAxis();
		ValueAxis yAxis = new SymbolAxis("Status", categoryAxesValues);
		XYItemRenderer renderer = new XYStepAreaRenderer();
		XYPlot plot = new XYPlot(symbolDataset, xAxis, yAxis, renderer);
		JFreeChart categoryChart = new JFreeChart("Category Chart", new Font("Tahoma", 0, 18), plot, true);
		symbolPanel = new ChartPanel(categoryChart);
		
		graphPanel.add(numPanel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,5,0), 0, 0));
		graphPanel.add(symbolPanel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,5,0), 0, 0));
		
		///gambiarra
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		((GridBagLayout)panel.getLayout()).columnWidths = new int[] {10, 0, 0};
		((GridBagLayout)panel.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
		((GridBagLayout)panel.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
		((GridBagLayout)panel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
		graphPanel.add(panel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,5,0), 0, 0));
		panel.add(button, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,5,0), 0, 0));
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				JToggleButton button = (JToggleButton) e.getSource();
				if (button.isSelected())
				{
					energia = 0;
				}
			}
		});
		result = new javax.swing.JTextField();
		panel.add(result, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,5,0), 0, 0));
		julio = new JComboBox();
		panel.add(julio, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,5,0), 0, 0));
	}
	public void defineSymbolAxis()
	{
		int i = 0;
		for(i = 0; i<categoryAxesValues.length && categoryAxesValues[i]!= null; i++);
		symbolParameter = new String[i];
		for(int j = 0; j < i; j++)
		{
			symbolParameter[j] = categoryAxesValues[j];
		}
		graphPanel.remove(symbolPanel);
		DateAxis xAxis = new DateAxis();
		ValueAxis yAxis = new SymbolAxis("Status", symbolParameter);
		XYStepRenderer renderer = new XYStepRenderer();
		XYPlot plot = new XYPlot(symbolDataset, xAxis, yAxis, renderer);
		JFreeChart categoryChart = new JFreeChart("Category Chart", new Font("Tahoma", 0, 18), plot, true);
		symbolPanel = new ChartPanel(categoryChart);
		graphPanel.add(symbolPanel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,5,0), 0, 0));
	}
	public XMLGregorianCalendar getLastTimestamp() {
		return lastTimestamp;
	}
	public void setLastTimestamp(XMLGregorianCalendar lastTimestamp) {
		this.lastTimestamp = lastTimestamp;
	}
	public SQLConnection getConn()
	{
		return this.conn;
	}
	public java.sql.Statement getStatement()
	{
		return statement;
	}
}
