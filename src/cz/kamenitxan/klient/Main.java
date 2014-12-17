package cz.kamenitxan.klient;

import com.aquafx_project.AquaFx;
import cz.kamenitxan.sceneswitcher.SceneSwitcher;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private final SceneSwitcher sceneSwitcher = SceneSwitcher.getInstance();

    @Override
    public void start(Stage primaryStage) throws Exception{

        sceneSwitcher.addScene("a", "main.fxml");

        primaryStage.setTitle("IT podpora");
        primaryStage.setScene(sceneSwitcher.createMainScene(this.getClass()));
        
        if(System.getProperty("os.name").equals("Mac OS X")){
            AquaFx.style();
        }
        sceneSwitcher.loadScene("a");
        primaryStage.show();

        XmlCreator.startCreator();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
