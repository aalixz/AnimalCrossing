package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Lane {

	// region Properties
	private int length;
	private int rowIndex;
	private GameObject[] objects;
	// endregion

	public Lane(int length, int rowIndex) {
		this.length = length;
		this.rowIndex = rowIndex;
	}

	public void generateSafeLane(Grid grid) {
		this.objects = new GameObject[0];
		drawBackground(grid, "BackgroundTiles/SafeGrassTile.png");
	}

	public void generateCollidableLane(Grid grid, GameObject.Direction dir, int num, int spacing) {
		this.objects = new GameObject[num];

		int offset = (int)(Math.random() * 3) * Grid.CELL_SIZE + Grid.PADDING;

		spacing = (spacing * Grid.CELL_SIZE);

		int random = (int)(Math.random() * Collidable.CollidableType.values().length);
		Collidable.CollidableType type = Collidable.CollidableType.values()[random];


		switch (type) {
			case FOX:
				drawBackground(grid, "BackgroundTiles/GrassTile.png");
				int posX = offset;
				for (int i = 0; i < num; i++) {
					this.objects[i] = GameObjectFactory.getNewCollidable(posX, grid.rowToY(rowIndex), type, dir);
					posX += spacing;
				}
				break;

			case TRACTOR:
				drawBackground(grid, "BackgroundTiles/CropTile.png");
				posX = offset;
				for (int i = 0; i < num; i++) {
					this.objects[i] = GameObjectFactory.getNewCollidable(posX, grid.rowToY(rowIndex), type, dir);
					posX += spacing;
				}
				break;

			default:
				generateSafeLane(grid);
				break;
		}

	}

	public void generateRideableLane(Grid grid, GameObject.Direction dir, int num, int spacing) {
		this.objects = new GameObject[num];

		int offset = (int)(Math.random() * 3) * Grid.CELL_SIZE + Grid.PADDING;

		spacing = spacing * Grid.CELL_SIZE;

		int random = (int)(Math.random() * Rideable.RideableType.values().length);
		Rideable.RideableType type = Rideable.RideableType.values()[random];


		switch (type) {
			case PIG:
				drawBackground(grid, "BackgroundTiles/MudTile.png");
				int posX = offset;
				for (int i = 0; i < num; i++) {
					this.objects[i] = GameObjectFactory.getNewRideable(posX, grid.rowToY(rowIndex), type, dir);
					posX += spacing;
				}
				break;

			case PLANK:
				drawBackground(grid, "BackgroundTiles/MudTile.png");
				posX = offset;
				for (int i = 0; i < num; i++) {
					this.objects[i] = GameObjectFactory.getNewRideable(posX, grid.rowToY(rowIndex), type, dir);
					posX += spacing;
				}
				break;

			default:
				generateSafeLane(grid);
				break;
		}

	}

	private void drawBackground(Grid grid, String backgroundTile) {
		for (int col = 0; col < grid.getCols(); col++) {
			Picture tile = new Picture(grid.columnToX(col), grid.rowToY(rowIndex),
					backgroundTile);
			tile.draw();
		}
	}

	public void moveAllObjects() {
		for (GameObject o : objects) {
			for (int counter = 0; counter < o.getSpeed(); counter++) {
				if (o.getDir() == GameObject.Direction.RIGHT &&
						o.getSprite().getMaxX() > length - o.getSprite().getWidth()) {
					o.getSprite().translate(-(o.getSprite().getX() - Grid.PADDING), 0);
				} else if (o.getDir() == GameObject.Direction.LEFT &&
						o.getSprite().getX() < Grid.PADDING + o.getSprite().getWidth()) {
					o.getSprite().translate(length - o.getSprite().getMaxX(), 0);
				} else {
					o.move();
				}
			}
		}
	}

	// region Getters
	public int getRowIndex() {
		return rowIndex;
	}

	public GameObject[] getObjects() {
		return objects;
	}
	// endregion

}
