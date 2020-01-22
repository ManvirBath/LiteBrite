import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.ColorPicker;

public class LiteBrite extends Application {
    
	final static ColorPicker colorpicker = new ColorPicker();
	
    @Override
    public void start(final Stage stage) throws Exception {
    
    	
        int rows = 50;
        int columns = 50;

        stage.setTitle("LiteBrite. Enjoy your game.");

        GridPane grid = new GridPane();
        grid.getStyleClass().add("game-grid");
        
        for(int i = 0; i < columns; i++) {
            ColumnConstraints column = new ColumnConstraints(10);
            grid.getColumnConstraints().add(column);
        }

        for(int i = 0; i < rows; i++) {
            RowConstraints row = new RowConstraints(10);
            grid.getRowConstraints().add(row);
        }

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                Pane pane = new Pane();
                pane.setOnMouseReleased(e -> {
                    pane.getChildren().add(Anims.getAtoms(1));
                });
                pane.getStyleClass().add("game-grid-cell");
                if (i == 0) {
                    pane.getStyleClass().add("first-column");
                }
                if (j == 0) {
                    pane.getStyleClass().add("first-row");
                }
                grid.add(pane, i, j);
            }
        }

        GridPane.setConstraints(colorpicker,100, 0);
        grid.getChildren().addAll(colorpicker);


        Scene scene = new Scene(grid, (columns * 12)+20, (rows * 10)+20);
        scene.getStylesheets().add(LiteBrite.class.getResource("resources/game.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static class Anims {

    	public static Node getAtoms(final int number) {
            Rectangle rectangle = new Rectangle(8,8);
            rectangle.setFill(colorpicker.getValue());
            Group group = new Group();
            group.getChildren().add(rectangle);
            return group;
        }
    }

    public static void main(final String[] arguments) {
        Application.launch(arguments);
    }
}