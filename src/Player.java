
public class Player {
	private PlayerState state;
	private Square position;

	public Player() {
		this.state = new PlayerCanPlay(this);
		position = new Square(0,0);
	}

	public void setState(PlayerState state) {
		this.state = state;
	}

	public void move(int point) {
		if(this.state.isTurn() == false) return;
		//เดินทางซ้ายแบบไม่เปลี่ยนแถว
		if(position.getX()+point<10&&position.getY()%2==0) {
			position.setX(position.getX()+point);
		}
		//ซ้ายเปลี่ยนแถว
		else if(position.getX()+point>9&&position.getY()%2==0&&position.getX()+point<20) {
			position.setX(9-(position.getX()+point-10));
			position.setY(position.getY()+1);
		}
		//ขวา
		else if(position.getX()-point>=0&&position.getY()%2==1) {		
			position.setX(position.getX()-point);
		}
		//ขวาเปลี่ยนแถว
		else if(position.getX()-point<0&&position.getY()%2==1&&-(position.getX()-point)-1<10) {
			if(position.getY()==9&&position.getX()-point<=0) {
				System.out.println("end!!");
				position.setX(0);
				position.setY(9);
			}else {
			position.setX(-(position.getX()-point)-1);
			position.setY(position.getY()+1);
			}
		}
		//เดินซ้ายขึ้น2แถว 
		else if(position.getX()+point>19&&position.getY()%2==0) {
			if(position.getY()==8&&(position.getX()>7)&&point>10) {
				System.out.println("end!!");
				position.setX(0);
				position.setY(9);
			}else {
			position.setX(point+position.getX()-20);
			position.setY(position.getY()+2);
			}
		}
		//ขวาขึ้น2แถว 
		else if(-(position.getX()-point)-1>9&&position.getY()%2==1&&position.getX()-point<0) {
			position.setX(8+position.getX());
			position.setY(position.getY()+2);
		}
	}
	
	public void setPosition(Square square) {
		this.position = square;
	}

	public Square getPosition() {
		return position;
	}

}
