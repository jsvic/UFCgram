package com.example.ufcgram.model;
import com.example.ufcgram.service.Consulta;
import com.example.ufcgram.service.Login;
import com.example.ufcgram.service.Seguimento;

import java.util.ArrayList;

//Classe que herda dados pessoais de Aluno e acrescenta dados acadêmicos e senhas.

public class Usuario extends Aluno {
    private String senha;
    private String curso;
    private String campus;
    private String bio;
    private String semestreInicial;
    private ArrayList<Publicacao> postsCriados = new ArrayList<>();
    private ArrayList<Usuario> seguindos = new ArrayList<>();
    private ArrayList<Usuario> seguidores = new ArrayList<>();
    private static int qtdUsuarios;

    //Construtor sem parâmetro:

    public Usuario() {
        super();
        qtdUsuarios++;
    }

    //Construtor com parâmetro:
    public Usuario(int matr, String nome, String senha, String email,
                   int idade, String curso, String campus, String bio,
                   String semestreInicial, String genero) {

        super(matr, nome, email, idade, genero);
        qtdUsuarios++;
        this.senha = senha;
        this.curso = curso;
        this.campus = campus;
        this.bio = bio;
        this.semestreInicial = semestreInicial;
    }

    public Usuario fazerLogin() {
        Login contaSessao = new Login();
        return contaSessao.login(getMatricula(), senha);
    }

    public void seguirUsuario(int matriculaSeguidor, int matriculaSeguido) {
        //Recebe a conta que vai seguir e a conta a ser seguida e passa para o Service
        Seguimento service = new Seguimento();
        service.seguirUsuario(matriculaSeguidor, matriculaSeguido);
    }

    public ArrayList<Usuario> recomendarContas(){
        Consulta service = new Consulta();

        return service.recomendarContas(this);
    }


    @Override
    protected void copiarDados(Usuario outro) {
        //Copia os dados pessoais (Aluno) e depois os dados academicos e a senha
        super.copiarDados(outro);
        this.senha = outro.getSenha();
        this.curso = outro.getCurso();
        this.campus = outro.getCampus();
        this.bio = outro.getBio();
        this.semestreInicial = outro.getSemestreInicial();
    }

    //Getters

    public String getSenha() {
        return senha;
    }
    public String getCurso() {
        return curso;
    }
    public String getCampus() {
        return campus;
    }
    public String getBio() {
        return bio;
    }
    public String getSemestreInicial() {
        return semestreInicial;
    }
    public ArrayList<Publicacao> getPostsCriados() {
        return postsCriados;
    }
    public ArrayList<Usuario> getSeguindos() {
        return seguindos;
    }
    public ArrayList<Usuario> getSeguidores() {
        return seguidores;
    }

    //Setters

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
    public void setCampus(String campus) {
        this.campus = campus;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
    public void setSemestreInicial(String semestreInicial) {
        this.semestreInicial = semestreInicial;
    }
}
