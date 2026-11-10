package com.example.ufcgram.controller;

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

    @FXML private Spinner<Integer> inMatr; // Atributo que referencia o campo Matricula
    @FXML private ComboBox<String> inCurso; // Atributo que referencia o campo Curso
    @FXML private ComboBox<String> inSemestre; // Atributo que referencia o campo Semestre
    @FXML private ComboBox<String> inCampus; // Atributo que referencia o campo Campus

    @FXML
    public void initialize() {
        // Metodo chamado automaticamente ao abrir o FXML

        inMatr.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                        100000,       // Mínimo
                        999999, // Máximo
                        100000 // Valor padrão

                )
        ); // Configura o campo Matricula

        inCurso.getItems().addAll(
                "Administração – Diurno – Fortaleza",
                "Administração – Noturno – Fortaleza",
                "Administração Pública – Modalidade a Distância",
                "Agronomia – Fortaleza",
                "Análise e Desenvolvimento de Sistemas – Itapajé",
                "Arquitetura e Urbanismo – Fortaleza",
                "Biblioteconomia – Fortaleza",
                "Biotecnologia – Fortaleza",
                "Ciência da Computação – Crateús",
                "Ciência da Computação – Fortaleza",
                "Ciência da Computação – Quixadá",
                "Ciência de Dados – Itapajé",
                "Ciências Atuariais – Fortaleza",
                "Ciências Biológicas (Diurno) – Fortaleza",
                "Ciências Biológicas (Noturno) – Fortaleza",
                "Ciências Contábeis – Diurno – Fortaleza",
                "Ciências Contábeis – Noturno – Fortaleza",
                "Ciências Econômicas – Diurno – Fortaleza",
                "Ciências Econômicas – Noturno – Fortaleza",
                "Ciências Sociais – Diurno – Fortaleza",
                "Ciências Sociais – Noturno – Fortaleza",
                "Cinema e Audiovisual – Fortaleza",
                "Design – Fortaleza",
                "Design Digital – Quixadá",
                "Direito – Diurno – Fortaleza",
                "Direito – Noturno – Fortaleza",
                "Educação Física – Fortaleza",
                "Enfermagem – Fortaleza",
                "Engenharia Ambiental e Sanitária – Fortaleza",
                "Engenharia Civil – Crateús",
                "Engenharia Civil – Fortaleza",
                "Engenharia Civil – Russas",
                "Engenharia de Alimentos – Fortaleza",
                "Engenharia de Computação – Fortaleza",
                "Engenharia de Computação – Quixadá",
                "Engenharia de Energias e Meio Ambiente – Fortaleza",
                "Engenharia de Pesca – Fortaleza",
                "Engenharia de Produção – Fortaleza",
                "Engenharia de Produção – Russas",
                "Engenharia de Software – Quixadá",
                "Engenharia de Telecomunicações – Fortaleza",
                "Engenharia Elétrica – Fortaleza",
                "Engenharia Elétrica – Sobral",
                "Engenharia Mecânica – Fortaleza",
                "Engenharia Mecânica – Russas",
                "Engenharia Metalúrgica – Fortaleza",
                "Engenharia Química – Fortaleza",
                "Estatística – Fortaleza",
                "Farmácia – Fortaleza",
                "Filosofia – Diurno – Fortaleza",
                "Filosofia – Noturno – Fortaleza",
                "Física – Fortaleza",
                "Fisioterapia – Fortaleza",
                "Fonoaudiologia – Fortaleza",
                "Geografia – Diurno – Fortaleza",
                "Geografia – Noturno – Fortaleza",
                "Geologia – Fortaleza",
                "História – Diurno – Fortaleza",
                "História – Noturno – Fortaleza",
                "Inteligência Artificial – Quixadá",
                "Jornalismo – Fortaleza",
                "Letras – Espanhol – Fortaleza",
                "Letras – Francês – Fortaleza",
                "Letras – Inglês – Fortaleza",
                "Letras – Licenciatura ou Bacharelado – Modalidade a Distância",
                "Letras – Português – Fortaleza",
                "Letras – Português e Literaturas – Fortaleza",
                "Matemática – Diurno – Fortaleza",
                "Matemática – Noturno – Fortaleza",
                "Matemática – Modalidade a Distância",
                "Medicina – Fortaleza",
                "Medicina – Sobral",
                "Medicina Veterinária – Fortaleza",
                "Música – Fortaleza",
                "Nutrição – Fortaleza",
                "Odontologia – Fortaleza",
                "Pedagogia – Diurno – Fortaleza",
                "Pedagogia – Noturno – Fortaleza",
                "Psicologia – Fortaleza",
                "Publicidade e Propaganda – Fortaleza",
                "Química – Bacharelado – Fortaleza",
                "Química – Licenciatura – Fortaleza",
                "Química – Modalidade a Distância",
                "Redes de Computadores – Quixadá",
                "Secretariado Executivo – Fortaleza",
                "Serviço Social – Fortaleza",
                "Sistemas de Informação – Quixadá",
                "Sistemas e Mídias Digitais – Fortaleza",
                "Teatrologia / Artes Cênicas – Fortaleza",
                "Zootecnia – Fortaleza"
        ); // Configura o valor no campo Curso

        inSemestre.getItems().addAll (
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
        ); // Configura o valor do campo de Semestre

        inCampus.getItems().addAll(
                "Campus do Pici",
                "Campus do Benfica",
                "Campus do Porangabussu",
                "Campus de Crateús",
                "Campus de Itapajé",
                "Campus de Quixadá",
                "Campus de Russas",
                "Campus de Sobral"
        ); // Configura o valor do campo de Campus
    }

    @FXML
    public void abrirCadastroFinal(ActionEvent event) throws IOException {
        // Redireciona para a tela de cadastro dos dados finais

        Parent root = FXMLLoader.load(
                getClass().getResource(
                        "/com/example/ufcgram/view/viewCadastroFinais.fxml"
                )
        ); // Carrega o arquivo FXML

        Stage stage = (Stage) ((Node) event.getSource()) // Carrega a janela
                .getScene()
                .getWindow();

        stage.getScene().setRoot(root);  // Insere a pagina na janela
    }

    @FXML
    public void continuarCadastro(ActionEvent event) throws IOException {
        // Metodo chamado ao clicar no botao "Avançar"

        String curso, semestre, campus;
        int matr;

        matr = inMatr.getValue(); // Pega o valor do campo Matricula
        curso = inCurso.getValue(); // Pega o valor  do campo Curso
        semestre = inSemestre.getValue(); // Pega o valor do campo Semestre
        campus = inCampus.getValue(); // Pega o valor do Campus

        System.out.printf("\nInformações:\nMatr: %d\ncurso: %s\nsemestre: %s\ncampus: %s\n", matr, curso, semestre, campus);
        abrirCadastroFinal(event); // Chama o metodo para redirecionar a tela


    }
}
