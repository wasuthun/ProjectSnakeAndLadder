
public class Ladder {
	private Square top;
	private Square bottom;
	
	public Ladder(Square top , Square bottom) {
		this.top = top;
		this.bottom = bottom;
	}
	
	public Square getTop() {
		return top;
	}
	
	public Square getBottom() {
		return bottom;
	}
}
