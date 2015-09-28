/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carbingo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CarBingo extends Application {

//    public static boolean gameOver = false;
    private static final int SIZE = 5;
    boolean[][] check = new boolean[SIZE][SIZE];

    DropShadow shadow = new DropShadow();

    @Override
    public void start(Stage primaryStage) {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(8);

        HBox hBox = new HBox();
        hBox.setSpacing(40);
        hBox.setAlignment(Pos.BOTTOM_CENTER);

        HBox hBox2 = new HBox();
        hBox2.setSpacing(40);
        hBox2.setAlignment(Pos.BOTTOM_CENTER);

        //NewGame Btn appears once the game has ended
        Button btnNew = new Button();
        btnNew.setText("New Game");
        btnNew.setOnAction(e -> {
            newGame(grid, hBox2);
            btnNew.setVisible(false);
        });

        //Adding the shadow when the mouse cursor is on
        btnNew.setOnMouseEntered(e -> {
            btnNew.setEffect(shadow);
        });
        //Removing the shadow when the mouse cursor is off
        btnNew.setOnMouseExited(e -> {
            btnNew.setEffect(null);
        });
        btnNew.setVisible(false);

        hBox.getChildren().add(btnNew);
        
        //create a title screen
        titlePage(grid, hBox2);

        BorderPane borderPane = new BorderPane();
        borderPane.setBottom(hBox);
        borderPane.setBottom(hBox2);
        borderPane.setCenter(grid);

        Scene scene = new Scene(borderPane, 500, 500);

        primaryStage.setTitle("BINGO");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void newGame(GridPane grid, HBox hBox2) {

//        gameOver = false;

        grid.getChildren().clear();
        buildGame(grid, hBox2);
    }

    public void endGame(GridPane grid, HBox hBox2) {

        //if the game is won
        for (int j = 0; j < SIZE; j++) {
            final int jj = j;

            for (int i = 0; i < SIZE; i++) {
                final int ii = i;
                if ((check[0][0] == true)&&(check[0][4] == true)&&(check[4][0] == true)&&(check[4][4] == true)) {
                    endPage(grid, hBox2);
                }

            }
        }

    }

    public void buildGame(GridPane grid, HBox hBox2) {
        for (int j = 0; j < SIZE; j++) {
            final int jj = j;

            States names = new States();
            ToggleButton[] row = new ToggleButton[SIZE];

            for (int i = 0; i < SIZE; i++) {
                final int ii = i;

                //Initialize the buttons and set them to a random state 
                row[i] = new ToggleButton();
                row[i].setText(names.getRandomWord());
                check[jj][ii] = false;

                //Add the shadow when clicked
                if (row[ii].isSelected() == false) {
                    row[i].setOnMouseClicked(e -> {
                        check[jj][ii] = true;
                        //Check for Game Over
                        endGame(grid, hBox2);

                    });

                } else {

                    row[ii].setOnMouseClicked(e -> {
                        check[jj][ii] = false;

                    });
                }

                //Add the free space
                if ((j == 2) && (i == 2)) {
                    row[i].setText("Free");
                    check[jj][ii] = true;
                    row[ii].setSelected(true);
                }

            }

            //Add the buttons to the grid
            for (int i = 0; i < row.length; i++) {
                GridPane.setConstraints(row[i], i, j);
                grid.getChildren().addAll(row[i]);
            }

        }
        
        
        
        
        //Testing EndPage
        Button btnTest = new Button();
        btnTest.setText("test");
        GridPane.setConstraints(btnTest, 6, 4);
        btnTest.setOnMouseClicked(e -> {
            endPage(grid, hBox2);

        });
        grid.getChildren().addAll(btnTest);
    }

    public void titlePage(GridPane grid, HBox hBox2) {
        Button start = new Button();
        Label welcome = new Label();
        welcome.setText("Welcome to CarBingo!");

        start.setText("Start Game");
        start.setOnAction(e -> {
            newGame(grid, hBox2);
            start.setVisible(false);
            hBox2.getChildren().clear();
        });
        grid.getChildren().addAll(welcome);
        GridPane.setConstraints(welcome, 2, 2);
        hBox2.getChildren().add(start);

    }

    public void endPage(GridPane grid, HBox hBox2) {
        Button startNew = new Button();
        Button quit = new Button();
        Label end = new Label();
        end.setText("You WIN!");

        grid.getChildren().clear();

        startNew.setText("Start New Game");
        startNew.setOnAction(e -> {
            newGame(grid, hBox2);
            startNew.setVisible(false);
            hBox2.getChildren().clear();
        });

        quit.setText("Quit Game");
        quit.setOnAction(e -> {
            System.exit(1);
        });

        grid.getChildren().addAll(end);
        GridPane.setConstraints(end, 2, 2);
        hBox2.getChildren().add(startNew);
        hBox2.getChildren().add(quit);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
