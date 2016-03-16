package coplan.mazes;

public class Cell {

	private boolean topOpening, rightOpening, bottomOpening, leftOpening;
	private boolean isStart, isEnd, isSolutionCell;
	private int numOpenings;
	
	public Cell(int topOpening, int rightOpening, int bottomOpening, int leftOpening){
		switch(topOpening)
		{
		case 0: this.topOpening = true; break;
		case 1: this.topOpening = false; break;
		}
		
		switch(rightOpening)
		{
		case 0: this.rightOpening = true; break;
		case 1: this.rightOpening = false; break;
		}
		
		switch(bottomOpening)
		{
		case 0: this.bottomOpening = true; break;
		case 1: this.bottomOpening = false; break;
		}
		
		switch(leftOpening)
		{
		case 0: this.leftOpening = true; break;
		case 1: this.leftOpening = false; break;
		}
		
		this.numOpenings = calculateNumOpenings();
	}
	
	public void setAsStart(){
		this.isStart = true;
	}
	
	public void setAsEnd(){
		this.isEnd = true;
	}
	
	public boolean isStart(){
		return this.isStart;
	}
	
	public boolean isEnd(){
		return this.isEnd;
	}
	
	public void updateNumOpenings(){
		this.numOpenings = calculateNumOpenings();
	}
	
	private int calculateNumOpenings(){
		int total = 0;
		
		if(topOpening){total++;}
		if(rightOpening){total++;}
		if(bottomOpening){total++;}
		if(leftOpening){total++;}
		
		return total;
	}
	
	public void changeLeftOpening(boolean state){
		this.leftOpening = state;
		updateNumOpenings();
	}
	
	public void changeRightOpening(boolean state){
		this.rightOpening = state;
		updateNumOpenings();
	}
	
	public void changeTopOpening(boolean state){
		this.topOpening = state;
		updateNumOpenings();
	}
	
	public void changeBottomOpening(boolean state){
		this.bottomOpening = state;
		updateNumOpenings();
	}
	
	public boolean isLeftOpen(){
		return leftOpening;
	}
	
	public boolean isRightOpen(){
		return rightOpening;
	}
	
	public boolean isTopOpen(){
		return topOpening;
	}
	
	public boolean isBottomOpen(){
		return bottomOpening;
	}
	
	public int getNumOpening(){
		return this.numOpenings;
	}
	
	public void setAsSolutionCell(){
		this.isSolutionCell = true;
	}
	
	public boolean isSolutionCell(){
		return this.isSolutionCell;
	}
	
	public boolean isBox(){
		//this is used for solving purposes
		return this.getNumOpening() == 4;
	}
}
