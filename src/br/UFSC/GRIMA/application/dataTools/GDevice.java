package br.UFSC.GRIMA.application.dataTools;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;



public class GDevice 
{
	private String name;
	private String uuid;
	public ArrayList<GComponent> componentStreamList = new ArrayList<GComponent>();
	private int lastTimestamp;
	
	public JPanel buttonPanel = new JPanel();
	public JPanel informationPanel = new JPanel();
	public JPanel checkBoxPanel = new JPanel();
	public JPanel GraphPanel = new JPanel();
	
	public ArrayList<GSubComponent> valuesToUpdate = new ArrayList<GSubComponent>();
	public ArrayList<GDataserie> sampleRequestList = new ArrayList<GDataserie>();
	public ArrayList<GSubComponent> graphsToUpdate = new ArrayList<GSubComponent>();
	public ArrayList<GDataserie> seriesToUpdate = new ArrayList<GDataserie>();
	public String[] categoryAxesValues = new String[1000];
	
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
	}
	
	public String getName() 
	{
		return name;
	}
	public String getUuid() {
		return uuid;
	}
}
