package Day5;

public class OptContainer {

	static final int POSITION_MODE = 0;
	static final int IMMEDIATE_MODE = 1;
	
	static final int PARAM_SIZE = 3;
	
	public int optCode;
	public int[] params = new int[PARAM_SIZE];

	public OptContainer(int code) {
		optCode = code % 100;
		params[0] = (code / 100) % 10;
		params[1] = code / 1000;
		params[2] = POSITION_MODE;
	}
	
	public int get(Integer[] optcodes, int value, int index) {
		switch(params[index - 1]) {
		case POSITION_MODE:
			return optcodes[value];
		case IMMEDIATE_MODE:
			return value;
		}
		
		return 0;
	}
}
