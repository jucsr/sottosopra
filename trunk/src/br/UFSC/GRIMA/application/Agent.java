package br.UFSC.GRIMA.application;
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
}
