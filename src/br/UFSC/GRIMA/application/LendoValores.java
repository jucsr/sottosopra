package br.UFSC.GRIMA.application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import br.UFSC.GRIMA.application.entities.streams.StreamsType;
import br.UFSC.GRIMA.application.visual.TesteValores;

public class LendoValores extends TesteValores
{
	private double x;
	private double y;
	private double z;
	public LendoValores() throws Exception
	{
//		System.out.println("Your Fisrt Hello World Program" + "\n");

//		 URL MTConnect = new URL("http://agent.MTConnect.org/probe");
		URL MTConnect = new URL("http://agent.mtconnect.org/current?path=//Axes//Linear//DataItem[@subType='ACTUAL']");
		BufferedReader in = new BufferedReader(new InputStreamReader(MTConnect.openStream()));
		String inputLine;
		String ler = "";
		while ((inputLine = in.readLine()) != null) {
		//System.out.println(inputLine);
			ler = ler + "\n" + inputLine;
		}
		in.close();
		System.out.println(ler);
		System.out.println("\n" + "XML from probe command printed above");
		//////////////
//		JAXBContext jc = JAXBContext.newInstance( "br.UFSC.GRIMA.application.entities.streams.MTConnectStreamsType.class" );
		JAXBContext jc = JAXBContext.newInstance(StreamsType.class);
	    Unmarshaller u = jc.createUnmarshaller();
	    URL url = new URL( "http://agent.mtconnect.org/current?path=//Axes//Linear//DataItem[@subType='ACTUAL']" );
	    StreamsType mt = (StreamsType)u.unmarshal(url);
	}
}

