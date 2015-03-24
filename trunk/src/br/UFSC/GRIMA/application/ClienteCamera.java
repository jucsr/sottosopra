package br.UFSC.GRIMA.application;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteCamera 
{
	private int nCameras;
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
			while (scanner.hasNextLine()) 
			{
				nCameras = Integer.parseInt(scanner.nextLine());
//				System.out.println(nCameras);
				//break;
			}
			scanner.close();
			cliente.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		System.out.println("O cliente se conectou ao servidor!");
	}
	public int getnCameras() 
	{
		return nCameras;
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