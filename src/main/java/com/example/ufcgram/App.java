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
        // Classe para abrir a janela

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/viewLogin.fxml")); // Insere o arquivo FXML na janela ao abrir-la
        Application.setUserAgentStylesheet((new CupertinoDark()).getUserAgentStylesheet()); // Adiciona o estilo do atlantafx
        Scene scene = new Scene((Parent)fxmlLoader.load(), (double)320.0F, (double)240.0F); // Cria a janela
        scene.getStylesheets().add(((URL)Objects.requireNonNull(this.getClass().getResource("/com/example/ufcgram/style/global.css"))).toExternalForm()); // Adicona o global.css no o projeto
        stage.setTitle("UFCGram"); // Define o titulo da janela
        stage.setScene(scene); // Adiciona a janela
        stage.setMaximized(true); // Abrir a janela já maximizada
        stage.show(); // Mostrar a janela
    }
}
