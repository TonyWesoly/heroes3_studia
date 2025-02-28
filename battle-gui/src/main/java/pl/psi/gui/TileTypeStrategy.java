package pl.psi.gui;

import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pl.psi.GameEngine;
import pl.psi.Point;
import pl.psi.Tile;

public class TileTypeStrategy implements TileStrategy {
    private GameEngine gameEngine;
    private static final Image CROSS_ICON = new Image(Start.class.getClassLoader()
            .getResource("icons/cross.png").toString());
    private static final Image PLUS_ICON = new Image(Start.class.getClassLoader()
            .getResource("icons/plus.png").toString());
    private static final Image SKULL_ICON = new Image(Start.class.getClassLoader()
            .getResource("icons/skull.png").toString());

    public TileTypeStrategy(GameEngine aGameEngine) {
        this.gameEngine = aGameEngine;
    }

    @Override
    public void apply(MapTile aMapTile, Point aPoint) {
        Tile tile = gameEngine.getTile(aPoint);
        boolean canMoveOnTile = gameEngine.canMove(aPoint);



        if (tile != null) {
            switch (tile.getType()) {
                case OBSTACLE -> {
                    aMapTile.setBackground(Color.BLACK);
                    aMapTile.setIcon(CROSS_ICON);
                }
                case DAMAGE -> {
                    aMapTile.setBackground(canMoveOnTile ? Color.DARKORANGE : Color.ORANGE);
                    aMapTile.setIcon(SKULL_ICON);
                }
                case BUFF -> {
                    aMapTile.setBackground(canMoveOnTile ? Color.DARKBLUE : Color.BLUE);
                    aMapTile.setIcon(PLUS_ICON);
                }
            }
        }
    }

}
