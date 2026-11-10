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
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/viewLogin.fxml")); // Adiciona a tela de login como tela padrao
        Application.setUserAgentStylesheet((new CupertinoDark()).getUserAgentStylesheet()); // Adiciona o tema do AtlantaFX
        Scene scene = new Scene((Parent)fxmlLoader.load(), (double)320.0F, (double)240.0F); // Abre o FXML e define o tamalho da tela
        scene.getStylesheets()
                .add(((URL)Objects
                        .requireNonNull(this.getClass()
                                .getResource("/com/example/ufcgram/style/global.css")))
                        .toExternalForm()); // Adiciona o arquivo global.css para estilizar toda a aplicacao

        stage.setTitle("UFCGram"); // Adiciona o titulo da janela
        stage.setScene(scene); // Adiciona o FXML
        stage.setMaximized(true); // Configura para abrir
        stage.show(); // Abre a tela
    }
}
