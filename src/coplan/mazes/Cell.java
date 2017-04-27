package coplan.mazes;

public class Cell {

	private boolean topOpening, rightOpening, bottomOpening, leftOpening;
	private boolean isStart, isEnd, isSolutionCell, isUserBlockCell;
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
	
	public Cell(boolean topOpening, boolean rightOpening, boolean bottomOpening, boolean leftOpening){
		this.topOpening = topOpening;
		this.rightOpening = rightOpening;
		this.bottomOpening = bottomOpening;
		this.leftOpening = leftOpening;
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
	
	public void setLeftOpening(boolean state){
		this.leftOpening = state;
	}
	
	public void setRightOpening(boolean state){
		this.rightOpening = state;
	}
	
	public void setTopOpening(boolean state){
		this.topOpening = state;
	}
	
	public void setBottomOpening(boolean state){
		this.bottomOpening = state;
	}

	public void setAsUserBlockCell(boolean state){ this.isUserBlockCell = state; }
	
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

	public boolean isUserBlockCell(){ return isUserBlockCell; }
	
	public int getNumOpening(){
		updateNumOpenings();
		return this.numOpenings;
	}
	
	public void setAsSolutionCell(){
		this.isSolutionCell = true;
	}
	
	public boolean isSolutionCell(){
		return this.isSolutionCell;
	}
	
	public boolean isBox(){
		return this.getNumOpening() == 0;
	}
}
