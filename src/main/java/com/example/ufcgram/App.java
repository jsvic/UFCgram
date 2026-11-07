package com.example.ufcgram;

import atlantafx.base.theme.CupertinoDark;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public App() {
    }

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/viewLogin.fxml"));
        Application.setUserAgentStylesheet((new CupertinoDark()).getUserAgentStylesheet());
        Scene scene = new Scene((Parent)fxmlLoader.load(), (double)320.0F, (double)240.0F);
        scene.getStylesheets().add(((URL)Objects.requireNonNull(this.getClass().getResource("/com/example/ufcgram/style/global.css"))).toExternalForm());
        stage.setTitle("UFCgram");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
