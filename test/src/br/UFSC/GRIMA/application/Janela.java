package br.UFSC.GRIMA.application;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.PlotState;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;
import org.jfree.data.xml.CategorySeriesHandler;
import org.jfree.data.xml.RootHandler;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.orsoncharts.data.category.CategoryDataset3D;



public class Janela extends JFrame
{
	public JButton botaoOk = new JButton();
	public JButton botaoCancel = new JButton();
	

	public JPanel painelBotoes = new JPanel();
	public JPanel painelFundo = new JPanel();
	
	
	
	

	public CategoryDataset ds = createData();
//	public JFreeChart grafico = ChartFactory.createTimeSeriesChart("Linear", "valores", "Altura", ds);
	public JFreeChart grafico = ChartFactory.createLineChart("linear", "x", "y", ds);
	public ChartPanel painelGrafico = new ChartPanel(grafico);
	
	public Janela()
	{
		botaoOk.setText("Ok");
		botaoCancel.setText("Cancel");
		painelBotoes.add(botaoOk);
		painelBotoes.add(botaoCancel);
		
		painelFundo.setLayout(new BorderLayout());
		painelFundo.add(BorderLayout.CENTER, painelGrafico);
		painelFundo.add(BorderLayout.SOUTH, painelBotoes);
	
		this.getContentPane().add(painelFundo);
		
		
	}
	public CategoryDataset createData()
	{
		 
	     return null;   
	}
}
