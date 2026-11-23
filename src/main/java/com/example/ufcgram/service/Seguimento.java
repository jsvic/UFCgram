package com.example.ufcgram.service;

import com.example.ufcgram.model.Usuario;
import com.example.ufcgram.repository.ContaDAO;

import java.util.ArrayList;

//Classe responsável por receber as duas matriculas e passar para o DAO registrar o seguir.

public class Seguimento {

    private final ContaDAO dao = new ContaDAO();

    public void seguirUsuario(int matriculaSeguidor, int matriculaSeguido) {
        dao.seguir(matriculaSeguidor, matriculaSeguido);
    }

    public void deixarSeguirUsuario(int matriculaSeguidor, int matriculaSeguido) {
        dao.deixarSeguir(matriculaSeguidor, matriculaSeguido);
    }

    public ArrayList<Usuario> seguindos(int matricula) { return dao.consultarSeguindo(matricula); }
    public ArrayList<Usuario> seguidores(int matricula) { return dao.consultarSeguidores(matricula); }

}
