package br.UFSC.GRIMA.application;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

import br.UFSC.GRIMA.application.entities.streams.MTConnectStreamsType;
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
		salvar(ler);
//		JAXBContext context = JAXBContext.newInstance("br.ufsc.GRIMA.application.entities.streams");
//		Unmarshaller unmarshaller = context.createUnmarshaller();
//		JAXBElement<MTConnectStreamsType> element = (JAXBElement<MTConnectStreamsType>) unmarshaller.unmarshal(new File());
//		MTConnectStreamsType p1 = element.getValue();
	}
	
	public void salvar(String entrada) {
		FileDialog fd = new FileDialog(this, "Salvar", FileDialog.SAVE);
		
		fd.setFile(entrada);
		fd.setVisible(true);
		String dir = fd.getDirectory();
		String file = fd.getFile();
		String filePath = dir + file + ".xml";
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(filePath, false));
			out.writeObject(entrada);
			out.flush();
			out.close();

			// arquivo vai estar salvo
			// this.salvo = true;//global
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}

