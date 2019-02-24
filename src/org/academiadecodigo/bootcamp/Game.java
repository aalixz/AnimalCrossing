package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game implements KeyboardHandler {

	private Grid grid;
	private Lane[] lanes;
	private Player bunny;
	private Keyboard kb;
	private boolean gameOver;
	private int playerStartX;
	private int playerStartY;
	private final Picture MOVE_TEXT;
	private final Picture QUIT_TEXT;

	public Game() {
		this.grid = new Grid();
		this.playerStartX = grid.columnToX(grid.getCols() / 2);
		this.playerStartY = grid.rowToY(grid.getRows() - 1);
		this.bunny = new Player(playerStartX, playerStartY);
		this.kb = new Keyboard(this);
		this.gameOver = false;
		this.MOVE_TEXT = new Picture(Grid.PADDING, Grid.PADDING, "Move.png");
		this.QUIT_TEXT = new Picture(grid.getWidth() - 70, Grid.PADDING, "Quit.png");
	}

	public void start() throws Exception {

		// region Key Events
		addKeyEvent(KeyboardEvent.KEY_UP, KeyboardEventType.KEY_PRESSED);
		addKeyEvent(KeyboardEvent.KEY_DOWN, KeyboardEventType.KEY_PRESSED);
		addKeyEvent(KeyboardEvent.KEY_LEFT, KeyboardEventType.KEY_PRESSED);
		addKeyEvent(KeyboardEvent.KEY_RIGHT, KeyboardEventType.KEY_PRESSED);
		addKeyEvent(KeyboardEvent.KEY_Q, KeyboardEventType.KEY_PRESSED);
		addKeyEvent(KeyboardEvent.KEY_R, KeyboardEventType.KEY_PRESSED);
		// endregion

		lanes = new Lane[grid.getRows()];

		generateLanes();

		bunny.show();

		MOVE_TEXT.draw();
		QUIT_TEXT.draw();

		Lane bunnyLane;
		boolean touchingObject;

		while (true) {
			moveAllLanes();

			bunnyLane = lanes[grid.yToRow(bunny.getPlayerSprite().getY())];
			touchingObject = CollisionDetector.collided(bunny, bunnyLane);

			if (bunnyLane.getType() == Lane.LaneType.COLLIDABLE && touchingObject ||
			bunnyLane.getType() == Lane.LaneType.RIDEABLE && !touchingObject) {
				playerReset();
			}

			if (bunny.getPlayerSprite().getY() == grid.rowToY(0) && !gameOver) {
				win();
			}

			Thread.sleep(1000);
		}

	}

	public void moveAllLanes() {
		for (Lane lane : lanes) {
			lane.moveAllObjects();
		}
	}

	private void addKeyEvent(int key, KeyboardEventType type) {
		KeyboardEvent keyEvent = new KeyboardEvent();
		keyEvent.setKey(key);
		keyEvent.setKeyboardEventType(type);
		kb.addEventListener(keyEvent);
	}

	@Override
	public void keyPressed(KeyboardEvent event) {
		switch (event.getKey()) {

			case KeyboardEvent.KEY_UP:
				if (bunny.getPlayerSprite().getY() > Grid.PADDING) {
					bunny.moveUp();
				}
				break;

			case KeyboardEvent.KEY_DOWN:
				if (bunny.getPlayerSprite().getMaxY() < grid.getHeight()) {
					bunny.moveDown();
				}
				break;

			case KeyboardEvent.KEY_LEFT:
				if (bunny.getPlayerSprite().getX() > Grid.PADDING) {
					bunny.moveLeft();
				}
				break;

			case KeyboardEvent.KEY_RIGHT:
				if (bunny.getPlayerSprite().getMaxX() < grid.getWidth()) {
					bunny.moveRight();
				}
				break;

			case KeyboardEvent.KEY_Q:
				System.exit(0);
				break;

/*			case KeyboardEvent.KEY_R:
				reset();
				break;*/

			default:
				break;

		}
	}

	@Override
	public void keyReleased(KeyboardEvent keyboardEvent) {

	}

	private void generateLanes() {
		lanes[0] = new Lane(grid.getWidth(), 0);
		lanes[0].generateSafeLane(grid);

		lanes[1] = new Lane(grid.getWidth(), 1);
		lanes[1].generateRideableLane(grid, GameObject.Direction.LEFT, 3, 2);

		lanes[2] = new Lane(grid.getWidth(), 2);
		lanes[2].generateRideableLane(grid, GameObject.Direction.RIGHT, 2, 2);

		lanes[3] = new Lane(grid.getWidth(), 3);
		lanes[3].generateRideableLane(grid, GameObject.Direction.RIGHT, 3, 2);

		lanes[4] = new Lane(grid.getWidth(), 4);
		lanes[4].generateRideableLane(grid, GameObject.Direction.RIGHT, 4, 2);

		lanes[5] = new Lane(grid.getWidth(), 5);
		lanes[5].generateSafeLane(grid);

		lanes[6] = new Lane(grid.getWidth(), 6);
		lanes[6].generateCollidableLane(grid, GameObject.Direction.RIGHT, 2, 2);

		lanes[7] = new Lane(grid.getWidth(), 7);
		lanes[7].generateCollidableLane(grid, GameObject.Direction.RIGHT, 2, 2);

		lanes[8] = new Lane(grid.getWidth(), 8);
		lanes[8].generateCollidableLane(grid, GameObject.Direction.RIGHT, 2, 2);

		lanes[9] = new Lane(grid.getWidth(), 9);
		lanes[9].generateCollidableLane(grid, GameObject.Direction.LEFT, 2, 2);

		lanes[10] = new Lane(grid.getWidth(), 10);
		lanes[10].generateSafeLane(grid);
	}

	private void playerReset() {
		bunny.getPlayerSprite().translate(playerStartX - bunny.getPlayerSprite().getX(),
				playerStartY - bunny.getPlayerSprite().getY());
	}

	private void win() {
		this.gameOver = true;
		// TODO: mostra win screen
	}

	// TODO: implementar reset. Não funciona porque tenho de mandar parar os objetos antes
/*	public void reset() {
		generateLanes();
		bunny.hide();
		bunny.show();
		MOVE_TEXT.delete();
		QUIT_TEXT.delete();
		MOVE_TEXT.draw();
		QUIT_TEXT.draw();
	}*/

}
