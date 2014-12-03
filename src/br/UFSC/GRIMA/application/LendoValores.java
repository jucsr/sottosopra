package br.UFSC.GRIMA.application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.vecmath.Point3d;

import br.UFSC.GRIMA.application.visual.TesteValores;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class LendoValores extends TesteValores
{
	private double x;
	private double y;
	private double z;
	public LendoValores() throws Exception
	{
//		System.out.println("Your Fisrt Hello World Program" + "\n");

		// URL MTConnect = new URL("http://agent.MTConnect.org/probe");
		URL MTConnect = new URL("http://agent.mtconnect.org/current?path=//Axes//Linear//DataItem[@subType='ACTUAL']");
		BufferedReader in = new BufferedReader(new InputStreamReader(MTConnect.openStream()));
		String inputLine;
		String ler = "";
//		while ((inputLine = in.readLine()) != null) {
//			//System.out.println(inputLine);
//			ler = ler + "\n" + inputLine;
//		}
//		in.close();
		System.out.println(ler);
//		System.out.println("\n" + "XML from probe command printed above");
////////////////////////////////////////////////////////////////////////////////////////////	
	
		XStream xStream2 = new XStream(new DomDriver());
		xStream2.alias("general", Point3d.class);
		Point3d general2 = (Point3d)xStream2.fromXML(ler);
//		
	}
	
		
	

}

