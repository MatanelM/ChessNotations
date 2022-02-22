package application;

import application.MVC.Controller;
import application.MVC.Model;
import application.MVC.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) {
		View view = new View(stage);
		Model model = new Model();
		Controller controller = new Controller(view, model);
		model.loadData();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
