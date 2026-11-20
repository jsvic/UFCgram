//Import de bibliotecas

package com.example.ufcgram.service;
import com.example.ufcgram.model.Usuario;
import com.example.ufcgram.repository.ContaDAO;
import com.example.ufcgram.security.SecuritySenha;

//Classe responsável pelas ações principais (criar, editar e excluir usuário) e pela comunicação com o banco de dados.
//As validações ficam na classe ContaCadastro.

public class Cadastro {

    //Instanciar para se comunicar com o banco de dados
    private final ContaDAO dao = new ContaDAO();

    //Instanciar para validar os dados antes de enviar ao banco de dados
    private final ContaCadastro contaCadastro = new ContaCadastro();

    //Metodo chamado pelo Model com o objeto Usuario ja preenchido
    public void criarUsuario(Usuario usuario, String senha, String confirmarSenha) {

        //Valida a confirmação da senha.
        contaCadastro.validarSenhaCadastro(senha, confirmarSenha);

        //Gera o hash da senha antes de salvar.
        usuario.setSenha(new SecuritySenha().gerar(senha));

        //Valida os demais dados do usuário.
        contaCadastro.validarDados(usuario);

        try {
            dao.create(usuario);
            System.out.println("Cadastro realizado com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException("Erro no service: erro ao cadastrar conta", e);
        }
    }

    public void editarUsuario(Usuario usuario, String senha, String confirmarSenha) {

        // Verifica se uma nova senha foi informada e se a confirmação está correta.
        boolean alterarSenha = contaCadastro.validarSenhaEdicao(senha, confirmarSenha);

        // Só gera um novo hash se o usuário quiser alterar a senha.
        if (alterarSenha) {
            usuario.setSenha(new SecuritySenha().gerar(senha));
        }

        // Valida os demais dados do usuário.
        contaCadastro.validarDados(usuario);

        // Atualiza os dados no banco.
        dao.update(usuario);
    }

    //Metodo chamado pelo Model com a matricula do usuario
    public void excluirUsuario(int matricula) {

        //passa a matricula para o DAO excluir do banco de dados
        dao.delete(matricula);
    }
}
