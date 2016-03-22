package coplan.mazes;

public class Driver
{
	public static void main(String[] args)
	{	
		//all events are initialized & carried out in the GUI	
		//from here, everything happens on the swing thread or the action threads
		new Display().setUp();	
	}
}
