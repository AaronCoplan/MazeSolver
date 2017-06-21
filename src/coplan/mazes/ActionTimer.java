/*
MIT License

Copyright (c) 2017 Aaron Coplan

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

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
	
	public static void sumTimes(String time1, String time2, String time3, String time4, String message){
		double time = Double.parseDouble(time4) + Double.parseDouble(time3) + Double.parseDouble(time2) + Double.parseDouble(time1);
		System.out.println("Total time elapsed " + message + ": " + new DecimalFormat("#.0000").format(time));
	}
}
