package com.example.ufcgram.security;

import com.password4j.Argon2Function;
import com.password4j.Password;
import com.password4j.types.Argon2;

public class SecuritySenha {
    // Classe para adicionar o Hash em informacoes sensiveis
    private Argon2Function ARGON2 =
            Argon2Function.getInstance(
                    19456, // Memória em KiB
                    2,     // Iterações
                    1,     // Paralelismo
                    32,    // Tamanho do hash
                    Argon2.ID
            );

    public String gerar(String senha) {
        // Metodo para criar o hash
        return Password.hash(senha).with(ARGON2).getResult();
    }

    public boolean checar(String senha, String hashSenha) {
        // Metodo para checar se um valor é o mesmo que o valor original do hash

        if(senha == null || hashSenha == null || hashSenha.isBlank() || senha.isBlank()) {return false;}
        else {
            return Password.check(senha, hashSenha).with(ARGON2);
        }
    }
}
