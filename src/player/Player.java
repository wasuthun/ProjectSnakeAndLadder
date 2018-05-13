package player;

import java.util.ArrayList;
import java.util.List;

import game.BackwardSquare;
import game.FreezeSquare;
import game.Game;
import game.Square;

/**
 * Player class which have state and position of player.
 * @author Wasuthan, Patcharapol
 *
 */
public class Player {
	private PlayerState state;
	private Square position;
	private boolean freezeBool = true;
	private Game game = Game.getInstance();
	private List<Square> replay = new ArrayList<Square>();

	public Player() {
		this.state = new PlayerCanPlay(this);
		position = new Square(0, 9);
	}

	/**
	 * Set player state.
	 * @param state is state that you want to set.
	 */
	public void setState(PlayerState state) {
		this.state = state;
	}

	/**
	 * Move player backward by points.
	 * @param point is point of dices.
	 */
	public void moveBackWord(int point) {
		if (this.state.isTurn() == false)
			return;
		// เดินทางขวาแบบไม่เปลี่ยนแถว
		if (position.getX() + point < 10 && position.getY() % 2 == 0) {
			position = new Square(position.getX() + point, 0);
		}
		// ขวาเปลี่ยนแถว
		else if (position.getX() + point > 9 && position.getY() % 2 == 0 && position.getX() + point < 20) {
			position = new Square(9 - (position.getX() + point - 10), position.getY() + 1);
		}
		// ซ้าย
		else if (position.getX() - point >= 0 && position.getY() % 2 == 1) {
			position = new Square(position.getX() - point, position.getY());
		}
		// ซ้ายเปลี่ยนแถว
		else if (position.getX() - point < 0 && position.getY() % 2 == 1 && -(position.getX() - point) - 1 < 10) {
			if (position.getY() == 9 && position.getX() - point <= 0) {
				position = new Square(0, 9);
			} else {
				position = new Square(-(position.getX() - point) - 1, position.getY() + 1);
			}
		}
		// เดินขวาขึ้น2แถว
		else if (position.getX() + point > 19 && position.getY() % 2 == 0) {
			if (position.getY() == 8 && (position.getX() > 7) && point > 10) {
				position = new Square(0, 9);
			} else {
				position = new Square(point + position.getX() - 20, position.getY() + 2);
			}
		}
		// ซ้ายขึ้น2แถว
		else if (-(position.getX() - point) - 1 > 9 && position.getY() % 2 == 1 && position.getX() - point < 0) {
			position = new Square(8 + position.getX(), position.getY() + 2);
		}
		replay.add(position);
	}

	/**
	 * Move player forward.
	 * @param point is points of dices.
	 */
	public void move(int point) {
		if (this.state.isTurn() == false)
			return;
		for (FreezeSquare freeze : game.getFreezelist()) {
			if (freeze.getX() == position.getX() && freeze.getY() == position.getY()) {
				if (freezeBool) {
					freezeBool = false;
					return;
				}
				freezeBool = true;
			}
		}
		for (BackwardSquare backward : game.getBacklist()) {
			if (backward.getX() == position.getX() && backward.getY() == position.getY()) {
				moveBackWord(point);
				return;
			}
		}
		// ขวา
		if (position.getX() + point < 10 && position.getY() % 2 == 1) {
			position = new Square(position.getX() + point, position.getY());
		}
		// ขวาขึ้น1แถว
		else if (position.getX() + point > 9 && position.getY() % 2 == 1 && position.getX() + point < 20) {
			position = new Square(9 - (position.getX() + point - 10), position.getY() - 1);
		}
		// ซ้าย
		else if (position.getX() - point >= 0 && position.getY() % 2 == 0) {
			position = new Square(position.getX() - point, position.getY());
		}
		// ซ้าย1แถว
		else if (position.getX() - point < 0 && position.getY() % 2 == 0 && -(position.getX() - point) - 1 < 10) {
			if (position.getY() == 0 && position.getX() - point <= 0) {
				if (position.getX() - point == 0) {
					System.out.println("end!!");
					position = new Square(0, 0);
				} else {
					int x = point - position.getX();
					position.setX(1);
					moveBackWord(x - 1);
				}
			} else {
				position = new Square(-(position.getX() - point) - 1, position.getY() - 1);
			}
		}
		// ขวาขึ้น2แถว
		else if (position.getX() + point > 19 && position.getY() % 2 == 1) {
			if (position.getY() == 1 && (position.getX() > 7) && point > 10) {
				if (point + position.getX() == 19) {
					System.out.println("end!!");
					position = new Square(0, 0);
				} else {
					int x = point + position.getX() - 19;
					position.setX(1);
					position.setY(0);
					moveBackWord(x - 1);
				}
			} else {
				position = new Square(point + position.getX() - 20, position.getY() - 2);
			}
		}
		// ซ้าย2แถว
		else if (-(position.getX() - point) - 1 > 9 && position.getY() % 2 == 0 && position.getX() - point < 0) {
			position = new Square(8 + position.getX(), position.getY() - 2);
		}
		replay.add(position);
	}

	/**
	 * Set player position.
	 * @param square is player new position.
	 */
	public void setPosition(Square square) {
		this.position = square;
	}

	/**
	 * @return Player current position.
	 */
	public Square getPosition() {
		return position;
	}

	/**
	 * @return position that player has moved p
	 */
	public List<Square> getReplay() {
		return replay;
	}

	/**
	 * Set Player is freeze or not.
	 * @param freezeBool is status that player is freezing or not.
	 */
	public void setFreezeBool(boolean freezeBool) {
		this.freezeBool = freezeBool;
	}

	/**
	 * @return Status that player is freezing or not.
	 */
	public boolean getFreezeBool() {
		return freezeBool;
	}
}
