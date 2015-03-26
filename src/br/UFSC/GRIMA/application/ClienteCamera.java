package br.UFSC.GRIMA.application;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import br.UFSC.GRIMA.application.visual.BeginWindow;




public class ClienteCamera
{
	private int nCameras;
	private ArrayList<String> listaPadrao  = new ArrayList<String>();
	private String ipCamera; 

   public ClienteCamera(String ipCamera)
   {
	   this.ipCamera = ipCamera;
	   startClientCamera();
	   
   }
	public void startClientCamera()
	{
		Socket cliente;
		try 
		{
			cliente = new Socket(ipCamera, 12345);
			Scanner scanner = new Scanner(cliente.getInputStream());
			System.out.println("Cliente se conectou ao servidor!");
			while (scanner.hasNextLine()) 
			{
				listaPadrao.add(scanner.nextLine());
			}
			nCameras = Integer.parseInt(listaPadrao.get(listaPadrao.size() - 1));
			scanner.close();
			cliente.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Was not possible the connection with the WEBCAM server!");
		}
		
	}
	public int getnCameras() 
	{
		return nCameras;
	}
	public ArrayList <String> getlistaPadrao()
	{
		return listaPadrao;
	}
	public static void main(String[] args) throws UnknownHostException, IOException 
	{
		Socket cliente = new Socket("150.162.105.71", 12345);
		System.out.println("O cliente se conectou ao servidor!");
		
		Scanner s = new Scanner(cliente.getInputStream());
		while (s.hasNextLine()) {
			System.out.println(s.nextLine());
		}
		s.close();
		cliente.close();
	}
 }