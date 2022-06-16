package exercise.lesson04;

public class Adder {
	private int augend;
	private int addend;
	private int sum;
	public void setAugend(int augend) {
		this.augend = augend;
	}
	public void setAddend(int addend) {
		this.addend = addend;
	}
	public void execute() {
		sum=augend+addend;
	}
	public int getSum() {
		return sum;
	}





}
