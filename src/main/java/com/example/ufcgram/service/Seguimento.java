package com.example.ufcgram.service;

import com.example.ufcgram.repository.ContaDAO;

//Classe responsável por receber as duas matriculas e passar para o DAO registrar o seguir.

public class Seguimento {

    private final ContaDAO dao = new ContaDAO();

    public void seguirUsuario(int matriculaSeguidor, int matriculaSeguido) {
        dao.seguir(matriculaSeguidor, matriculaSeguido);
    }
}
