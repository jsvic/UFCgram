package com.example.ufcgram.controller;

import atlantafx.base.controls.Message;
import atlantafx.base.util.Animations;
import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.util.Duration;

public class ControllerMensagem {
    // Classe para o componente parcial Message

    @FXML private Message mensagem; // Referencia o componente Message
    private Timeline transicao; // Objeto para a transicao do JavaFX

    public void exibir(String titulo, String descricao, String classe) {
        // Metodo usado para exibir o componente Message

        mensagem.getStyleClass().removeAll( // Remove os atributos que o componente tem
                "accent",
                "success",
                "warning",
                "danger"
        );
        mensagem.setTitle(titulo); // Adiciona o titulo da mensagem
        mensagem.setDescription(descricao); // Adiciona o conteudo da mensagem
        mensagem.getStyleClass().add(classe); // Adicina a classe da mensagem
        mensagem.setManaged(true); // Os contaniers começam a considerar o componentes
        mensagem.setVisible(true); // Deixa o componente visiel

        // Adiciona uma animacao e uma transicao na entrada do componente
        transicao = Animations.fadeInUp (
                mensagem,
                Duration.seconds(.85)
        );

        transicao.playFromStart();

        PauseTransition duracao = new PauseTransition(Duration.seconds(4));
        duracao.setOnFinished(e -> ocultar());
        duracao.playFromStart();
    }

    public void ocultar() {
        // Metodo usado para ocultar o componente Message

        // Adiciona a transicao de saida

        if(transicao != null) { transicao.stop(); }

        transicao = Animations.fadeOutDown (
                mensagem,
                Duration.seconds(.85)
        );

        transicao.setOnFinished(event -> {

            mensagem.setVisible(false); // O Message fica invisivel
            mensagem.setManaged(false); // Os containers vai ignorar o componente Message

            Animations.reset(mensagem);

            transicao = null;
        });

        transicao.playFromStart();
    }
}
