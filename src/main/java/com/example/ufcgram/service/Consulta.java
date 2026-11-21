package com.example.ufcgram.service;
import com.example.ufcgram.model.Usuario;
import com.example.ufcgram.repository.ContaDAO;
import java.util.ArrayList;

//Classe responsável pelas consultas de usuários no feed

public class Consulta {
    private ContaDAO dao = new ContaDAO();
    private ContaCadastro contaCadastro = new ContaCadastro();

    public ArrayList<Usuario> recomendarContas(Usuario usuario) {
        int atributoMinimo = 1;
        int limiteContas = 10;

        if (usuario == null) {
            throw new RuntimeException("Erro de validação: Usuário inválido.");
        }

    contaCadastro.validarMatricula(usuario.getMatricula());
        return dao.recomendar(usuario.getMatricula(),
                usuario.getCurso(),
                usuario.getCampus(),
                usuario.getSemestreInicial(),atributoMinimo,limiteContas
                );
    }
}

