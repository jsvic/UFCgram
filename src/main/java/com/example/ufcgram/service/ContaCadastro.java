//Import das bibliotecas

package com.example.ufcgram.service;
import com.example.ufcgram.model.Usuario;

import java.util.regex.Pattern;

//Classe responsável por validar os dados do usuário antes de enviar ao banco de dados

public class ContaCadastro {

    private static final Pattern EMAIL = Pattern.compile("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$");

    /*Expressão para validar o formato do e-mail.
     * Pattern.compile() compila uma expressão regular para que ela possa
     * ser utilizada posteriormente na verificação de um texto.
     * Significado das expressões:
     * ^          -> Indica o início do texto.
     * [\\w.+-]+  -> Permite letras, números, _, pontos, + e - antes do @.
     * @          -> Exige a presença do símbolo arroba.
     * [\\w-]+    -> Permite letras, números, _ e -.
     * (\\.[\\w-]+)+ -> Exige pelo menos um ponto seguido de caracteres válidos.
     * $          -> Indica o final do texto.
     * Exemplo válido: pessoa@gmail.com
     */
    private static final Pattern SEMESTRE = Pattern.compile("^\\d{4}\\.[12]$");

    /*Explicação da expressão:
     * ^       -> Indica o início do texto.
     * \\d     -> Representa um algarismo de 0 a 9.
     * {4}     -> Exige exatamente quatro algarismos.
     * \\.     -> Exige um ponto literal após o ano.
     * [12]    -> Permite somente os números 1 ou 2.
     * $       -> Indica o final do texto.
     *Exemplo válido: 2026.1
     */

    // Valida os dados de um usuário, no cadastro e na edição
    public void validarDados(Usuario usuario) {

        if (usuario == null) {
            throw new RuntimeException("Erro de validação: Usuário inválido");
        }

        if (usuario.getMatricula() < 100000 || usuario.getMatricula() > 999999) {
            throw new RuntimeException("Erro de validação: A matrícula deve ter 6 dígitos");
        }

        if (vazio(usuario.getNome()) || usuario.getNome().length() > 80) {
            throw new RuntimeException("Erro de validação: Informe um nome válido (máximo de 80 caracteres)");
        }

        if (vazio(usuario.getEmail())
                || usuario.getEmail().length() > 150
                || !EMAIL.matcher(usuario.getEmail()).matches()) {
            throw new RuntimeException("Erro de validação: Informe um e-mail válido (máximo de 150 caracteres)");
        }

        if (usuario.getIdade() < 10 || usuario.getIdade() > 100) {
            throw new RuntimeException("Erro de validação: Idade inválida");
        }

        if (vazio(usuario.getGenero()) || usuario.getGenero().length() > 50) {
            throw new RuntimeException("Erro de validação: Selecione um gênero válido (máximo de 50 caracteres)");
        }

        if (vazio(usuario.getCurso()) || usuario.getCurso().length() > 150) {
            throw new RuntimeException("Erro de validação: Selecione um curso válido (máximo de 150 caracteres)");
        }

        if (vazio(usuario.getCampus()) || usuario.getCampus().length() > 100) {
            throw new RuntimeException("Erro de validação: Selecione um campus válido (máximo de 100 caracteres)");
        }

        if (vazio(usuario.getSemestreInicial())
                || usuario.getSemestreInicial().length() > 6
                || !SEMESTRE.matcher(usuario.getSemestreInicial()).matches()) {
            throw new RuntimeException("Erro de validação: Selecione um semestre inicial válido");
        }

        if (vazio(usuario.getSenha()) || usuario.getSenha().length() > 255) {
            throw new RuntimeException("Erro de validação: Senha inválida");
        }
    }

    //Metodo auxiliar: retorna true se o texto for nulo ou estiver em branco
    private boolean vazio(String texto) {
        return texto == null || texto.isBlank();
    }

    public void validarSenhaCadastro(String senha, String confirmarSenha) {
        if (vazio(senha)) {
            throw new RuntimeException("Erro de validação: A senha é obrigatória");
        }
        if (!senha.equals(confirmarSenha)) {
            throw new RuntimeException("Erro de validação: As senhas não coincidem");
        }
    }
    public boolean validarSenhaEdicao(String senha, String confirmarSenha) {
        // Se os dois campos estiverem vazios, mantém a senha atual.
        if (vazio(senha) && vazio(confirmarSenha)) {
            return false;
        }
        // Se apenas um campo estiver preenchido, exige os dois.
        if (vazio(senha) || vazio(confirmarSenha)) {
            throw new RuntimeException("Erro de validação: Preencha os dois campos de senha");
        }
        if (!senha.equals(confirmarSenha)) {
            throw new RuntimeException("Erro de validação: As senhas não coincidem");
        }
        return true;
    }
}