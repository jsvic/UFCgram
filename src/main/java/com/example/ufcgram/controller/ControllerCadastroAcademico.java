package com.example.ufcgram.controller;

import com.example.ufcgram.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerCadastroAcademico {

    private Usuario usuario;

    public void setConta(Usuario usuario){
        this.usuario = usuario;
    }

    @FXML private Spinner<Integer> inMatr;  // Atributo que referencia o campo Matricul
    @FXML private ComboBox<String> inCurso; // Atributo que referencia o campo Curso
    @FXML private ComboBox<String> inSemestre; // Atributo que referencia o campo Semestre
    @FXML private ComboBox<String> inCampus;  // Atributo que referencia o campo Campus

    @FXML private ControllerMensagem mensagemController; // Cria um objeto para o Controller do Message

    @FXML
    public void initialize() {
        // Metodo chamado automaticamente ao abrir o FXML

        inMatr.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                        100000, // Mínimo
                        999999, // Máximo
                        100000 // Valor padrão

                )
        );

        inCurso.getItems().addAll(  // Configura o valor no campo Curso
                "Administração (Diurno)",
                "Administração (Noturno)",
                "Administração Pública",
                "Agronomia",
                "Análise e Desenvolvimento de Sistemas",
                "Arquitetura e Urbanismo",
                "Biblioteconomia",
                "Biotecnologia",
                "Ciência da Computação",
                "Ciência de Dados",
                "Ciências Ambientais",
                "Ciências Atuariais",
                "Ciências Biológicas (Bacharelado)",
                "Ciências Biológicas (Licenciatura)",
                "Ciências Contábeis (Diurno)",
                "Ciências Contábeis (Noturno)",
                "Ciências Econômicas (Diurno)",
                "Ciências Econômicas (Noturno)",
                "Ciências Sociais (Bacharelado)",
                "Ciências Sociais (Licenciatura)",
                "Cinema e Audiovisual",
                "Comunicação Social - Publicidade e Propaganda",
                "Dança (Bacharelado)",
                "Dança (Licenciatura)",
                "Design",
                "Design - Moda",
                "Design Digital",
                "Direito (Diurno)",
                "Direito (Noturno)",
                "Economia Doméstica",
                "Economia Ecológica",
                "Educação Física (Diurno)",
                "Educação Física (Noturno)",
                "Enfermagem",
                "Engenharia Ambiental e Sanitária",
                "Engenharia Civil",
                "Engenharia da Computação",
                "Engenharia de Minas",
                "Engenharia de Produção",
                "Engenharia de Software",
                "Engenharia Elétrica",
                "Engenharia Mecânica",
                "Farmácia",
                "Filosofia",
                "Finanças",
                "Física",
                "Fisioterapia",
                "Gastronomia",
                "Geografia",
                "Geologia",
                "Gestão de Políticas Públicas",
                "História",
                "Jornalismo",
                "Letras - Espanhol",
                "Letras - Inglês",
                "Letras - Libras",
                "Letras - Língua Portuguesa e Língua Alemã e Respectivas Literaturas",
                "Letras - Língua Portuguesa e Língua Espanhola e Respectivas Literaturas",
                "Letras - Língua Portuguesa e Língua Francesa e Respectivas Literaturas",
                "Letras - Língua Portuguesa e Língua Inglesa e Respectivas Literaturas",
                "Letras - Língua Portuguesa e Língua Italiana e Respectivas Literaturas",
                "Letras - Língua Portuguesa e Respectivas Literaturas",
                "Licenciatura Intercultural Indígena (LII PITAKAJÁ)",
                "Licenciatura Intercultural Indígena Kuaba",
                "Matemática",
                "Matemática Industrial",
                "Medicina",
                "Música",
                "Oceanografia",
                "Odontologia",
                "Pedagogia (Diurno)",
                "Pedagogia (Noturno)",
                "Psicologia",
                "Química",
                "Redes de Computadores",
                "Secretariado Executivo",
                "Segurança da Informação",
                "Sistemas e Mídias Digitais (Diurno)",
                "Sistemas e Mídias Digitais (Noturno)",
                "Sistemas de Informação",
                "Teatro",
                "Tecnologia em Gestão de Qualidade",
                "Zootecnia"
        );

        inSemestre.getItems().addAll (  // Configura o valor do campo de Semestre
                "2026.2",
                "2026.1",
                "2025.2",
                "2025.1",
                "2024.2",
                "2024.1",
                "2023.2",
                "2023.1",
                "2022.2",
                "2022.1",
                "2021.2",
                "2021.1",
                "2020.2",
                "2020.1",
                "2019.2",
                "2019.1",
                "2018.2",
                "2018.1",
                "2017.2",
                "2017.1",
                "2016.2",
                "2016.1",
                "2015.2",
                "2015.1",
                "2014.2",
                "2014.1",
                "2013.2",
                "2013.1",
                "2012.2",
                "2012.1",
                "2011.2",
                "2011.1",
                "2010.2",
                "2010.1"
        );

        inCampus.getItems().addAll( // Configura o valor do campo de Campus
                "Campus do Pici",
                "Campus do Benfica",
                "Campus do Porangabussu",
                "Campus de Crateús",
                "Campus de Itapajé",
                "Campus de Quixadá",
                "Campus de Russas",
                "Campus de Sobral"
        );
    }

    @FXML
    public void abrirCadastroFinal(ActionEvent event) throws IOException {
        // Metodo que redireciona para a pagina de dados finais do cadastro

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/ufcgram/view/viewCadastroFinais.fxml"
        )
        ); // Abre o arquivo FXML

        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        ControllerCadastroFinais controller = loader.getController(); // Armazena o controller do FXMl
        controller.setConta(usuario); // Passa o objeto Conta para o Controller

        stage.getScene().setRoot(root); // Adiona o arquino na janela
    }

    @FXML
    public void abrirCadastroPessoal(ActionEvent event) throws IOException {
        // Meotodo chamado no botao de voltar, esse metodo redireciona para a tela de cadastro de dados pessoais

        Parent root = FXMLLoader.load(
                getClass().getResource(
                        "/com/example/ufcgram/view/viewCadastroPessoais.fxml"
                )
        );

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.getScene().setRoot(root);
    }

    @FXML
    public void limpar() {
        // Metodo chamada ao clicar no botao "limpar"

        inMatr.getValueFactory().setValue(100000); // Retoma o valor do campo matricula ao valor inicial
        inCurso.setValue(""); // Retoma o valor do campo curso ao valor inicial
        inCampus.setValue(""); // Retoma o valor do campo campus ao valor inicial
        inSemestre.setValue(""); // Retoma o valor do campo semestre ao valor inicial
    }

    @FXML
    public void continuarCadastro(ActionEvent event) throws IOException {
        if (usuario == null) {
            throw new IllegalStateException("Conta não foi repassada para ControllerCadastroAcademico");
        }

        String curso, semestre, campus;
        int matr;

        matr = inMatr.getValue(); //  Pega o valor do campo Matricula
        curso = inCurso.getValue(); // Pega o valor  do campo Curso
        semestre = inSemestre.getValue(); // Pega o valor do campo Semestre
        campus = inCampus.getValue(); // Pega o valor do Campus

        if (usuario != null){
            usuario.setMatricula(matr);
            usuario.setCurso(curso);
            usuario.setSemestreInicial(semestre);
            usuario.setCampus(campus);
        }

        try {
            System.out.printf("\nInformações:\nMatr: %d\ncurso: %s\nsemestre: %s\ncampus: %s\n", matr, curso, semestre, campus);
            abrirCadastroFinal(event);
        } catch (Exception e) {
            String[] msgError= e.getMessage().split(": "); // Seleciona apenas a mensagem necessária para o usuario
            mensagemController.exibir("Erro no Login", msgError[1], "danger"); // Exibe o componente de Mensage com o texto do erro
        }

    }
}
