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

			checkCollisions(lanes[grid.yToRow(bunny.getPlayerSprite().getY())]);

			if (bunny.getPlayerSprite().getY() == grid.rowToY(0) && !gameOver) {
				win();
			}

			Thread.sleep(1000);
		}

	}

	private void checkCollisions(Lane bunnyLane) {
		boolean touchingObject = CollisionDetector.collided(bunny, bunnyLane);

		if (bunnyLane.getType() == Lane.LaneType.COLLIDABLE && touchingObject ||
		bunnyLane.getType() == Lane.LaneType.RIDEABLE && !touchingObject) {
			playerReset();
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
					checkCollisions(lanes[grid.yToRow(bunny.getPlayerSprite().getY())]);
				}
				break;

			case KeyboardEvent.KEY_DOWN:
				if (bunny.getPlayerSprite().getMaxY() < grid.getHeight()) {
					bunny.moveDown();
					checkCollisions(lanes[grid.yToRow(bunny.getPlayerSprite().getY())]);
				}
				break;

			case KeyboardEvent.KEY_LEFT:
				if (bunny.getPlayerSprite().getX() > Grid.PADDING) {
					bunny.moveLeft();
					checkCollisions(lanes[grid.yToRow(bunny.getPlayerSprite().getY())]);
				}
				break;

			case KeyboardEvent.KEY_RIGHT:
				if (bunny.getPlayerSprite().getMaxX() < grid.getWidth()) {
					bunny.moveRight();
					checkCollisions(lanes[grid.yToRow(bunny.getPlayerSprite().getY())]);
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
		drawWinScreen();
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

	private void drawWinScreen() {
		Picture[][] screen = new Picture[grid.getRows()][grid.getCols()];

		screen[0] = drawGreenLine(0);
		screen[1] = drawGreenLine(1);
		screen[2] = drawGreenLine(2);

		screen[3][0] = new Picture(grid.columnToX(0), grid.rowToY(3), "BackgroundTiles/GrassTile.png");
		screen[3][1] = new Picture(grid.columnToX(1), grid.rowToY(3), "BackgroundTiles/GrassTile.png");
		screen[3][2] = new Picture(grid.columnToX(2), grid.rowToY(3), "BackgroundTiles/MudTile.png");
		screen[3][3] = new Picture(grid.columnToX(3), grid.rowToY(3), "BackgroundTiles/GrassTile.png");
		screen[3][4] = new Picture(grid.columnToX(4), grid.rowToY(3), "BackgroundTiles/GrassTile.png");
		screen[3][5] = new Picture(grid.columnToX(5), grid.rowToY(3), "BackgroundTiles/GrassTile.png");
		screen[3][6] = new Picture(grid.columnToX(6), grid.rowToY(3), "BackgroundTiles/GrassTile.png");
		screen[3][7] = new Picture(grid.columnToX(7), grid.rowToY(3), "BackgroundTiles/GrassTile.png");
		screen[3][8] = new Picture(grid.columnToX(8), grid.rowToY(3), "BackgroundTiles/GrassTile.png");
		screen[3][9] = new Picture(grid.columnToX(9), grid.rowToY(3), "BackgroundTiles/GrassTile.png");
		screen[3][10] = new Picture(grid.columnToX(10), grid.rowToY(3), "BackgroundTiles/MudTile.png");
		screen[3][11] = new Picture(grid.columnToX(11), grid.rowToY(3), "BackgroundTiles/GrassTile.png");
		screen[3][12] = new Picture(grid.columnToX(12), grid.rowToY(3), "BackgroundTiles/GrassTile.png");

		screen[4][0] = new Picture(grid.columnToX(0), grid.rowToY(4), "BackgroundTiles/GrassTile.png");
		screen[4][1] = new Picture(grid.columnToX(1), grid.rowToY(4), "BackgroundTiles/MudTile.png");
		screen[4][2] = new Picture(grid.columnToX(2), grid.rowToY(4), "BackgroundTiles/GrassTile.png");
		screen[4][3] = new Picture(grid.columnToX(3), grid.rowToY(4), "BackgroundTiles/MudTile.png");
		screen[4][4] = new Picture(grid.columnToX(4), grid.rowToY(4), "BackgroundTiles/GrassTile.png");
		screen[4][5] = new Picture(grid.columnToX(5), grid.rowToY(4), "BackgroundTiles/GrassTile.png");
		screen[4][6] = new Picture(grid.columnToX(6), grid.rowToY(4), "BackgroundTiles/GrassTile.png");
		screen[4][7] = new Picture(grid.columnToX(7), grid.rowToY(4), "BackgroundTiles/GrassTile.png");
		screen[4][8] = new Picture(grid.columnToX(8), grid.rowToY(4), "BackgroundTiles/GrassTile.png");
		screen[4][9] = new Picture(grid.columnToX(9), grid.rowToY(4), "BackgroundTiles/MudTile.png");
		screen[4][10] = new Picture(grid.columnToX(10), grid.rowToY(4), "BackgroundTiles/GrassTile.png");
		screen[4][11] = new Picture(grid.columnToX(11), grid.rowToY(4), "BackgroundTiles/MudTile.png");
		screen[4][12] = new Picture(grid.columnToX(12), grid.rowToY(4), "BackgroundTiles/GrassTile.png");

		screen[5] = drawGreenLine(5);

		screen[6][0] = new Picture(grid.columnToX(0), grid.rowToY(6), "BackgroundTiles/GrassTile.png");
		screen[6][1] = new Picture(grid.columnToX(1), grid.rowToY(6), "BackgroundTiles/GrassTile.png");
		screen[6][2] = new Picture(grid.columnToX(2), grid.rowToY(6), "BackgroundTiles/GrassTile.png");
		screen[6][3] = new Picture(grid.columnToX(3), grid.rowToY(6), "BackgroundTiles/GrassTile.png");
		screen[6][4] = new Picture(grid.columnToX(4), grid.rowToY(6), "BackgroundTiles/GrassTile.png");
		screen[6][5] = new Picture(grid.columnToX(5), grid.rowToY(6), "BackgroundTiles/MudTile.png");
		screen[6][6] = new Picture(grid.columnToX(6), grid.rowToY(6), "BackgroundTiles/MudTile.png");
		screen[6][7] = new Picture(grid.columnToX(7), grid.rowToY(6), "BackgroundTiles/MudTile.png");
		screen[6][8] = new Picture(grid.columnToX(8), grid.rowToY(6), "BackgroundTiles/GrassTile.png");
		screen[6][9] = new Picture(grid.columnToX(9), grid.rowToY(6), "BackgroundTiles/GrassTile.png");
		screen[6][10] = new Picture(grid.columnToX(10), grid.rowToY(6), "BackgroundTiles/GrassTile.png");
		screen[6][11] = new Picture(grid.columnToX(11), grid.rowToY(6), "BackgroundTiles/GrassTile.png");
		screen[6][12] = new Picture(grid.columnToX(12), grid.rowToY(6), "BackgroundTiles/GrassTile.png");

		screen[7][0] = new Picture(grid.columnToX(0), grid.rowToY(7), "BackgroundTiles/GrassTile.png");
		screen[7][1] = new Picture(grid.columnToX(1), grid.rowToY(7), "BackgroundTiles/GrassTile.png");
		screen[7][2] = new Picture(grid.columnToX(2), grid.rowToY(7), "BackgroundTiles/GrassTile.png");
		screen[7][3] = new Picture(grid.columnToX(3), grid.rowToY(7), "BackgroundTiles/GrassTile.png");
		screen[7][4] = new Picture(grid.columnToX(4), grid.rowToY(7), "BackgroundTiles/GrassTile.png");
		screen[7][5] = new Picture(grid.columnToX(5), grid.rowToY(7), "BackgroundTiles/GrassTile.png");
		screen[7][6] = new Picture(grid.columnToX(6), grid.rowToY(7), "BackgroundTiles/MudTile.png");
		screen[7][7] = new Picture(grid.columnToX(7), grid.rowToY(7), "BackgroundTiles/GrassTile.png");
		screen[7][8] = new Picture(grid.columnToX(8), grid.rowToY(7), "BackgroundTiles/GrassTile.png");
		screen[7][9] = new Picture(grid.columnToX(9), grid.rowToY(7), "BackgroundTiles/GrassTile.png");
		screen[7][10] = new Picture(grid.columnToX(10), grid.rowToY(7), "BackgroundTiles/GrassTile.png");
		screen[7][11] = new Picture(grid.columnToX(11), grid.rowToY(7), "BackgroundTiles/GrassTile.png");
		screen[7][12] = new Picture(grid.columnToX(12), grid.rowToY(7), "BackgroundTiles/GrassTile.png");

		screen[8][0] = new Picture(grid.columnToX(0), grid.rowToY(8), "BackgroundTiles/GrassTile.png");
		screen[8][1] = new Picture(grid.columnToX(1), grid.rowToY(8), "BackgroundTiles/GrassTile.png");
		screen[8][2] = new Picture(grid.columnToX(2), grid.rowToY(8), "BackgroundTiles/GrassTile.png");
		screen[8][3] = new Picture(grid.columnToX(3), grid.rowToY(8), "BackgroundTiles/MudTile.png");
		screen[8][4] = new Picture(grid.columnToX(4), grid.rowToY(8), "BackgroundTiles/GrassTile.png");
		screen[8][5] = new Picture(grid.columnToX(5), grid.rowToY(8), "BackgroundTiles/GrassTile.png");
		screen[8][6] = new Picture(grid.columnToX(6), grid.rowToY(8), "BackgroundTiles/MudTile.png");
		screen[8][7] = new Picture(grid.columnToX(7), grid.rowToY(8), "BackgroundTiles/GrassTile.png");
		screen[8][8] = new Picture(grid.columnToX(8), grid.rowToY(8), "BackgroundTiles/GrassTile.png");
		screen[8][9] = new Picture(grid.columnToX(9), grid.rowToY(8), "BackgroundTiles/MudTile.png");
		screen[8][10] = new Picture(grid.columnToX(10), grid.rowToY(8), "BackgroundTiles/GrassTile.png");
		screen[8][11] = new Picture(grid.columnToX(11), grid.rowToY(8), "BackgroundTiles/GrassTile.png");
		screen[8][12] = new Picture(grid.columnToX(12), grid.rowToY(8), "BackgroundTiles/GrassTile.png");

		screen[9][0] = new Picture(grid.columnToX(0), grid.rowToY(9), "BackgroundTiles/GrassTile.png");
		screen[9][1] = new Picture(grid.columnToX(1), grid.rowToY(9), "BackgroundTiles/GrassTile.png");
		screen[9][2] = new Picture(grid.columnToX(2), grid.rowToY(9), "BackgroundTiles/GrassTile.png");
		screen[9][3] = new Picture(grid.columnToX(3), grid.rowToY(9), "BackgroundTiles/GrassTile.png");
		screen[9][4] = new Picture(grid.columnToX(4), grid.rowToY(9), "BackgroundTiles/MudTile.png");
		screen[9][5] = new Picture(grid.columnToX(5), grid.rowToY(9), "BackgroundTiles/MudTile.png");
		screen[9][6] = new Picture(grid.columnToX(6), grid.rowToY(9), "BackgroundTiles/GrassTile.png");
		screen[9][7] = new Picture(grid.columnToX(7), grid.rowToY(9), "BackgroundTiles/MudTile.png");
		screen[9][8] = new Picture(grid.columnToX(8), grid.rowToY(9), "BackgroundTiles/MudTile.png");
		screen[9][9] = new Picture(grid.columnToX(9), grid.rowToY(9), "BackgroundTiles/GrassTile.png");
		screen[9][10] = new Picture(grid.columnToX(10), grid.rowToY(9), "BackgroundTiles/GrassTile.png");
		screen[9][11] = new Picture(grid.columnToX(11), grid.rowToY(9), "BackgroundTiles/GrassTile.png");
		screen[9][12] = new Picture(grid.columnToX(12), grid.rowToY(9), "BackgroundTiles/GrassTile.png");

		screen[10] = drawGreenLine(10);

		for(int row = 0; row < screen.length; row++) {
			for (int col = 0; col < screen[row].length; col++) {
				screen[row][col].draw();
			}
		}
	}

	private Picture[] drawGreenLine(int lineIndex) {
		Picture[] line = new Picture[grid.getCols()];
		for (int col = 0; col < line.length; col++) {
			line[col] = new Picture(grid.columnToX(col), grid.rowToY(lineIndex), "BackgroundTiles/GrassTile.png");
		}
		return line;
	}

}
