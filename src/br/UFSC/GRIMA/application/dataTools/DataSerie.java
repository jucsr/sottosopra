package br.UFSC.GRIMA.application.dataTools;

public class DataSerie 
{
	private String name = "";
	private boolean chartType;
	private SerieIdentifier serieIdentifier;
	
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}
	public SerieIdentifier getSerieIdentifier() 
	{
		return this.serieIdentifier;
	}
	public void setSerieIdentifier(SerieIdentifier serieIdentifier) 
	{
		this.serieIdentifier = serieIdentifier;
	}
	
}
