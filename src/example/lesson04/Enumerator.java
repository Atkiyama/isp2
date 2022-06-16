package example.lesson04;

import java.util.ArrayList;
import java.util.List;

public class Enumerator {

	private int lowerEndpoint;
	
	private int upperEndpoint;
	
	private List<Integer> numberList;
	
	public void setLowerEndpoint(int lowerEndpoint) {
		this.lowerEndpoint = lowerEndpoint;
	}

	public void setUpperEndpoint(int upperEndpoint) {
		this.upperEndpoint = upperEndpoint;
	}
	
	public void execute() {
		this.numberList = new ArrayList<Integer>();
		for(int i = this.lowerEndpoint; i <= this.upperEndpoint; i++) {
			this.numberList.add(i);
		}
	}
	
	public List<Integer> getNumberList() {
		return this.numberList;
	}
	
}
