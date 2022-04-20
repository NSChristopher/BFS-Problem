import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.Clip;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

public class Main extends Application{

    ArrayList<Circle> cirArray = new ArrayList<>();
    ArrayList<Integer> userArray = new ArrayList<>();
    ArrayList<Integer> bftPath = new ArrayList<>();

    //animations
    private Timeline timeline = new Timeline(1500);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Depth First Traversal");
        ArrayList<GridPane> arrPanes = createPanes();
        
        // Add UI controls
        addNodes(arrPanes);


        // Create a scene
        Scene scene = new Scene(arrPanes.get(0), 1200, 600);
        
        // Set the scene in primary stage	
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ArrayList<GridPane> createPanes() {
        ArrayList<GridPane> arrPanes = new ArrayList<GridPane>();

        // Instantiate a new Grid Pane
        GridPane rootPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        rootPane.setAlignment(Pos.CENTER);

        // Set a padding on each side
        rootPane.setPadding(new Insets(10, 10, 10, 10));
        
        // Set the horizontal gap between columns
        rootPane.setHgap(60);

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints rootcolumnConstraints = new ColumnConstraints(0, 0, Double.MAX_VALUE);
        rootcolumnConstraints.setHalignment(HPos.RIGHT);

        rootPane.getColumnConstraints().addAll(rootcolumnConstraints, rootcolumnConstraints, rootcolumnConstraints);
        
        // Create Sub Pane1 pane2 and pane3
        GridPane pane1 = new GridPane();
        GridPane pane2 = new GridPane();

        // Set a padding of 20px on each side
        pane1.setPadding(new Insets(40, 40, 40, 40));
        pane2.setPadding(new Insets(40, 40, 40, 40));



        // Set the horizontal gap between columns
        pane1.setHgap(10);
        pane2.setHgap(10);

        // Set the vertical gap between rows
        pane1.setVgap(10);
        pane2.setVgap(10);

        pane2.setBackground(new Background(new BackgroundFill(Paint.valueOf("white"), CornerRadii.EMPTY, Insets.EMPTY)));
        
        rootPane.add(pane1, 0, 0, 2, 1);
        rootPane.add(pane2, 0, 1, 2, 1);
        
        // Add Panes to ArrayList

        arrPanes.add(rootPane);
        arrPanes.add(pane1);
        arrPanes.add(pane2);

        return arrPanes;

    }

    private void addShapes(GridPane pane, ArrayList<Integer> userArray) {
        pane.getChildren().clear();
        for (int i = 0; i < userArray.size(); i++) {
            StackPane shapeStack = new StackPane();
            Rectangle rect = new Rectangle(70, 70);
            rect.setFill(Paint.valueOf("transparent"));
            rect.setStroke(Paint.valueOf("Black"));
            rect.setStrokeWidth(3);

            Circle cir = new Circle(35);
            cir.setFill(Paint.valueOf("transparent"));
            cir.setStroke(Paint.valueOf("Black"));
            cir.setOpacity(0);
            cir.setStrokeWidth(3);

            GridPane.setColumnIndex(cir, 0);
            GridPane.setRowIndex(cir, 0);



            Text text = new Text(userArray.get(i).toString());
            text.setFont(new Font(32));


            GridPane.setHalignment(shapeStack, HPos.CENTER);
            GridPane.setColumnIndex(shapeStack, i);
            GridPane.setRowIndex(shapeStack, 0);
            shapeStack.getChildren().addAll(cir, rect, text);
            pane.getChildren().add(shapeStack);

            cirArray.add(cir);
        }
    }

    private void addNodes(ArrayList<GridPane> arrPanes) {
        GridPane rootPane = arrPanes.get(0);
        GridPane pane1 = arrPanes.get(1);
        GridPane pane2 = arrPanes.get(2);

        Label headerLabel = new Label("Enter Data");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        pane1.add(headerLabel, 0,0,2,2);
        GridPane.setValignment(headerLabel, VPos.TOP);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        TextField dataEntryField = new TextField();
        pane1.add(dataEntryField, 0, 2, 2, 2);

        Label dataEntryLabel = new Label("Enter up to 10 numbers as a comma \",\" separated list (Ex: 2, 3, 5, 4, 8).");
        dataEntryLabel.setWrapText(true);
        pane1.add(dataEntryLabel, 0, 4,2,2);

        Button runBtn = new Button("Run");
        runBtn.setPrefHeight(40);
        runBtn.setDefaultButton(true);
        runBtn.setPrefWidth(100);
        pane1.add(runBtn, 0, 6, 2, 2);
        pane1.setHalignment(runBtn, HPos.CENTER);
        pane1.setMargin(runBtn, new Insets(20, 0,20,0));

        runBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                dataEntryField.setDisable(true);
                dataEntryLabel.setDisable(true);
                runBtn.setDisable(true);

                ArrayList<Integer> userArray = new ArrayList<>();
                ArrayList<Integer> traversalPath = new ArrayList<>();

                // get user data

                String commaSepList = dataEntryField.getText();

                String[] splitList = commaSepList.split(",", -1);
                try {
                    for (int i = 0; i < splitList.length && i < 10; i++) {
                        userArray.add(Integer.parseInt(splitList[i].trim()));
                    }
                }
                catch (Exception e){
                    showAlert(Alert.AlertType.ERROR,  rootPane.getScene().getWindow(), "Error!", "Follow direction on inputting data! \n " + e.getMessage());
                }

                // add shapes
                addShapes(arrPanes.get(2), userArray);

                // Graph navigation and Depth First Traversal
                GraphNavigator gn = new GraphNavigator(userArray);

                gn.DFS(0);
                traversalPath = gn.getTraversalPath();

                if(gn.isSolved()){
                    headerLabel.setText("Solved!");
                }
                else {
                    headerLabel.setText("Not solvable!");
                }
                

                // Animation

                ArrayList<KeyFrame> keyFrameArray = new ArrayList<>();

                for (int i = 0; i < traversalPath.size(); i++) {
                    int curr = traversalPath.get(i);
                    if(i > 0) {
                        int prev = traversalPath.get(i - 1);
                        keyFrameArray.add(new KeyFrame(Duration.millis(1000 * i), new KeyValue(cirArray.get(prev).opacityProperty(), 1)));
                        keyFrameArray.add(new KeyFrame(Duration.millis(1000 * (i + 1)), new KeyValue(cirArray.get(prev).opacityProperty(), 0)));    
                    }
                    keyFrameArray.add(new KeyFrame(Duration.millis(1000 * i), new KeyValue(cirArray.get(curr).opacityProperty(), 0)));
                    keyFrameArray.add(new KeyFrame(Duration.millis(1000 * (i + 1)), new KeyValue(cirArray.get(curr).opacityProperty(), 1)));

                }
                timeline.getKeyFrames().addAll(keyFrameArray);
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();

            }
            
        });


    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    
}
