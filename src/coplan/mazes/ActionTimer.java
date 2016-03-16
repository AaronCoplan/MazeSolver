package coplan.mazes;

import java.text.DecimalFormat;

public class ActionTimer {

	private long startTime, endTime, elapsedTime;
	private DecimalFormat df;
	
	public ActionTimer(){
		this.df = new DecimalFormat("#.0000");
	}
	
	public void start(){
		this.startTime = System.nanoTime();
	}
	
	public void stop(){
		this.endTime = System.nanoTime();
		elapsedTime = endTime - startTime;
	}
	
	public String getTimeElapsed(){
		double secondsElapsed = ((double)elapsedTime) / 1000000000.0;
		return df.format(secondsElapsed);
	}
	
	public static void sumTimes(String time1, String time2, String time3){
		double time = Double.parseDouble(time3) + Double.parseDouble(time2) + Double.parseDouble(time1);
		System.out.println("Total time elapsed: " + new DecimalFormat("#.0000").format(time));
	}
}
