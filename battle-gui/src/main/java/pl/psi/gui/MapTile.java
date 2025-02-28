package pl.psi.gui;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class MapTile extends StackPane
{

    private final Rectangle rect;
    private final Label label;
    private final ImageView iconView;
    private final Label countLabel;

    MapTile( final String aName )
    {
        rect = new Rectangle( 50, 50 );
        rect.setFill( Color.WHITE );
        rect.setStroke( Color.RED );
        getChildren().add( rect );
        label = new Label( aName );
        getChildren().add( label );

        // Add ImageView to display the icon
        iconView = new ImageView();


        // Position the icon
        iconView.setTranslateX(0);
        iconView.setTranslateY(0);
        getChildren().add(iconView);

        // Add countLabel and position it in the lower right corner
        countLabel = new Label();

        StackPane.setAlignment(countLabel, Pos.BOTTOM_RIGHT);
        getChildren().add(countLabel);
    }

    void setName( final String aName )
    {
        label.setText( aName );
    }

    void setBackground( final Color aColor )
    {
        rect.setFill( aColor );
    }

    void setBackgroundImage(final ImagePattern image) {
        rect.setFill( image );
    }

    public void setIcon(final Image icon) {
        iconView.setFitWidth(30);
        iconView.setFitHeight(30);
        iconView.setImage(icon);
    }

    public void setUnitSprite(Image tilemap, Rectangle2D viewport) {
        iconView.setImage(tilemap);
        iconView.setViewport(viewport);
        iconView.setFitWidth(50);
        iconView.setFitHeight(50);
        iconView.setPreserveRatio(true);
//        label.setVisible(false);
    }

    public void setUnitCount(int count) {
        countLabel.setStyle("-fx-background-color: rgba(0,0,0,0.5); -fx-text-fill: white; -fx-padding: 2; -fx-font-size: 10;");
        countLabel.setText(String.valueOf(count));
    }
}
