package org.academiadecodigo.bootcamp;

public class CollisionDetector {

	public static boolean collided(Player player, Lane currLane) {
		for (GameObject o : currLane.getObjects())
			if (hitObject(player, o)) {
				return true;
			}

		return false;
	}

	private static boolean hitObject(Player player, GameObject object) {
		return (player.getPlayerSprite().getX() >= object.getSprite().getX() &&
				player.getPlayerSprite().getX() < object.getSprite().getMaxX()) ||
				(player.getPlayerSprite().getMaxX() > object.getSprite().getX() &&
						player.getPlayerSprite().getMaxX() <= object.getSprite().getMaxX());
	}

}
