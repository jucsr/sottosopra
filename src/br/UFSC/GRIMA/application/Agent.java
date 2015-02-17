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
	
	public Agent(String name, String ip)
	{
		this.name = name;
		this.ip = ip;
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
