package com.peak.GameTest;

public class DebugTools
{
	
	private boolean debug;
	private int waitTime;
	
	public DebugTools()
	{
		debug = false;
		waitTime = 1000;
	}
	
	public DebugTools(boolean enabled)
	{
		this();
		debug = enabled;
	}
	
	public DebugTools(int wait)
	{
		this();
		this.waitTime = wait;
	}
	
	public DebugTools(boolean enabled, int wait)
	{
		this();
		this.debug = enabled;
		this.waitTime = wait;
	}
	
	public int getWaitTime()
	{
		return waitTime;
	}
	
	public void setWaitTime(int input)
	{
		this.waitTime = input;
	}
	
	public void switchDebugState()
	{
		debug = !debug;
	}
	
	public void debugPrint(String str)
	{
		System.out.print(str);
	}
	
	public void debugPrintln(String str)
	{
		debugPrint(str + "\n");
	}
}