package com.example.ufcgram.controller;

import com.example.ufcgram.model.Usuario;
import com.example.ufcgram.security.SecuritySenha;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerConfiguracao {
    // Controller para a pagina da Configuração

    private Usuario usuarioSessao; // Conta que esta em longada

    @FXML private TextField inNome; // Referencia o campo Nome
    @FXML private TextField inEmail; // Referencia o campo Email
    @FXML private Spinner<Integer> inIdade; // Referencia o campo Idade
    @FXML private ComboBox<String> inGenero; // Referencia o campo Genero
    @FXML private Spinner<Integer> inMatr; // Referencia o campo Matricula
    @FXML private ComboBox<String> inCampus; // Referencia o campo Campus
    @FXML private ComboBox<String> inSemestre; // Referencia o campo Semestre
    @FXML private ComboBox<String> inCurso; // Referencia o campo Curso
    @FXML private PasswordField inSenha; // Referencia o campo Senha
    @FXML private PasswordField inSenhaConfirma; // Referencia o campo Confirmar Senha
    @FXML private TextArea inDescricao; // Referencia o campo Descricao

    @FXML private HBox btnGroup; // // Referencia o HBox de id btnGroup

    @FXML private ControllerHeaderFeed headerFeedController; // Referencia o Controller do Header
    @FXML private ControllerMensagem mensagemController; // Referencia o Controller do Mesagem
    @FXML private ControllerModalDeletarConta modalController;

    @FXML private Button btnEdicao; // Referencia o botao de edicao

    private boolean disableInput = true; // Atributo para a habilitacao da modificacao dos inputs

    @FXML
    public void initialize() {
        // Metodo que executa assim que o FXMl é iniciado

        inIdade.setValueFactory( // Adiciona o valor do campo Idade
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                        10,
                        100

                )
        );

        inGenero.getItems().addAll( // Adiciona o valor do campo Genero
                "Masculino",
                "Feminino",
                "Não Binário"
        );


        inMatr.setValueFactory( // Adiciona o valor do campo Matricula
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

        inMatr.setDisable(true); // A matricula identifica a conta e nao pode ser editada
    }

    @FXML public void abrirModal() {
        modalController.setContaSessao(usuarioSessao);
        modalController.abrirModal();
    }

    @FXML public void setContaSessao(Usuario usuarioSessao) {
        // Define qual conta esta sessao

        this.usuarioSessao = usuarioSessao;
        headerFeedController.setContaSessao(usuarioSessao); // Adiciona a conta em sessao no componente do Header
        System.out.printf("\nConta Sessao (Configuracao): %d", usuarioSessao.getMatricula());
        valoresPadrao();
    }

    @FXML
    public void abrirFeed(ActionEvent event) throws IOException {
        // Redereciona para o Feed

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/ufcgram/view/viewFeed.fxml"
        )
        ); // Abre o arquivo FXMl

        Parent root = loader.load();

        System.out.printf("\nConta Sessao (Configuracao): %d", usuarioSessao.getMatricula());
        ControllerFeed controller = loader.getController(); // Pega o Controller do FXML
        controller.setContaSessao(usuarioSessao); // Passa o objeto Conta para o Controller

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.getScene().setRoot(root); // Adicionar o FXMl à janela
    }

    @FXML
    public void abrirLogin(ActionEvent event) throws IOException {
        // Redereciona para o Login

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/ufcgram/view/viewLogin.fxml"
        )
        ); // Abre o arquivo FXMl

        Parent root = loader.load();

        System.out.printf("\nConta Sessao (Configuracao): %d", usuarioSessao.getMatricula());
        ControllerLogin controller = loader.getController(); // Pega o Controller do FXML
        controller.abrirMensagemSair(); // Passa o objeto Conta para o Controller

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.getScene().setRoot(root); // Adicionar o FXMl à janela
    }

    @FXML public void camposEdicao() {
        // Habilita ou desabilita os campos de edicao

        if(!disableInput) {
            // Caso os campos estejam habilitados, desabilita os campos e altera o texto do botao
            disableInput = true;
            btnEdicao.setText("Habilitar Edição");
            valoresPadrao();
            System.out.printf("\nNome: %s\n", usuarioSessao.getNome());
        } else {
            // Caso os campos estejam desabilitados, habilita os campos e altera o texto do botao
            disableInput = false;
            btnEdicao.setText("Desabilitar Edição");
        }

        btnGroup.setManaged(!disableInput);
        btnGroup.setVisible(!disableInput);

        inNome.setDisable(disableInput);
        inEmail.setDisable(disableInput);
        inIdade.setDisable(disableInput);
        inGenero.setDisable(disableInput);
        //inMatr nao é habilitado: a matricula nao pode ser editada
        inCampus.setDisable(disableInput);
        inSemestre.setDisable(disableInput);
        inCurso.setDisable(disableInput);
        inSenha.setDisable(disableInput);
        inSenhaConfirma.setDisable(disableInput);
        inDescricao.setDisable(disableInput);
    }

    @FXML public void valoresPadrao() {
        //Adiciona os valores da conta em sessao nos campos

        inNome.setText(usuarioSessao.getNome());
        inEmail.setText(usuarioSessao.getEmail());
        inIdade.getValueFactory().setValue(usuarioSessao.getIdade());
        inGenero.setValue(usuarioSessao.getGenero());
        inMatr.getValueFactory().setValue(usuarioSessao.getMatricula());
        inCampus.setValue(usuarioSessao.getCampus());
        inSemestre.setValue(usuarioSessao.getSemestreInicial());
        inCurso.setValue(usuarioSessao.getCurso());
        inDescricao.setText(usuarioSessao.getBio());
    }

    @FXML
    public void editar() {

        String senha = inSenha.getText();
        String senhaConfirma = inSenhaConfirma.getText();

        //Monta o objeto Usuario com os dados dos campos.
        //A senha atual é mantida inicialmente.
        Usuario usuarioEditado = new Usuario(
                usuarioSessao.getMatricula(),
                inNome.getText(),
                usuarioSessao.getSenha(),
                inEmail.getText(),
                inIdade.getValue(),
                inCurso.getValue(),
                inCampus.getValue(),
                inDescricao.getText(),
                inSemestre.getValue(),
                inGenero.getValue()
        );
        try {
            //Encaminha os dados e as senhas para o Model e o Service.
            usuarioSessao.editarUsuario(usuarioEditado, senha, senhaConfirma);
            mensagemController.exibir(
                    "Edição da Conta",
                    "Sua conta foi editada com sucesso.",
                    "accent"
            );
            inSenha.clear();
            inSenhaConfirma.clear();
        } catch (Exception e) {
            mensagemController.exibir(
                    "Erro na Edição",
                    extrairMensagem(e),
                    "danger"
            );
        }
    }
    //Metodo de extração de mensagem (obs.: eu copiei e colei em CadastroFinais)
    private String extrairMensagem(Exception e) {

        String textoCompleto = e.getMessage();
        if (textoCompleto == null) {
            return "Erro desconhecido";
        }
        String[] partes = textoCompleto.split(": ");
        if (partes.length < 2) {
            return textoCompleto;
        }
        return partes[1];
    }
}