
public class Player {
	private int collect;
	private Dice dice=new Dice();
	public Player() {
		collect=0;
	}
	public void move(){
		dice.roll();
		int point = dice.getPoint();
		//do st in array
		collect=collect+point;
	}
	public int getCollect() {
		return collect;
	}
}
