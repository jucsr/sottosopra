package br.UFSC.GRIMA.application;

import java.util.ArrayList;

/**
 * 
 * @author Jc
 *
 */
public class Agent 
{
	private String name = "";
	private String ip = "";
	private String ipCamera = "";
	private int nCameras;
	private ArrayList<String> listaPadrao = new ArrayList<String>();
	
	public Agent(String name, String ip)
	{
		this.name = name;
		this.ip = ip;
	}
	
	public Agent(String name, String ip, String ipCamera)
	{
		this.name = name;
		this.ip = ip;
		this.ipCamera = ipCamera;
	}
	
	public String getIpCamera()
	{
		return ipCamera;
	}
	public void setIpCamera(String ipCamera)
	{
		this.ipCamera = ipCamera;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getIP()
	{
		return ip;
	}
	public void setIP(String ip)
	{
		this.ip = ip;
	}

	public int getnCameras() {
		return nCameras;
	}
	public ArrayList getlistaPadrao() {
		return listaPadrao;
	}

	public void setnCameras(int nCameras) {
		this.nCameras = nCameras;
	}
	public void setlistaPadrao(ArrayList listaPadrao) {
		this.listaPadrao = listaPadrao;
	}
}
