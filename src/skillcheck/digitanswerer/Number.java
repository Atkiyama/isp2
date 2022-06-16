package skillcheck.digitanswerer;

public class Number {
	private int value;

	public void setValue(int value) {
		this.value = value;
	}
	public int getDigit() {
		int digit=0;
		while(value!=0) {
            value /= 10;
            digit++;
        }
		return digit;

	}


}
