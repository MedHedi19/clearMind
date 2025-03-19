package esprit.javafxesprit.games;

import esprit.javafxesprit.games.snake.snake;
import esprit.javafxesprit.games.tetris.Tetris;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;

public class GamesController {

    @FXML
    private Button snake_btn;

    @FXML
    private Button tetris_btn;

    @FXML
    void handleSnake(ActionEvent event) {
        try {
            snake.resetGame();
            snake game = new snake();
            game.start(new javafx.stage.Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    void handleTetris() {
        try {
            Tetris.resetGame();
            Tetris game = new Tetris();
            game.start(new javafx.stage.Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
