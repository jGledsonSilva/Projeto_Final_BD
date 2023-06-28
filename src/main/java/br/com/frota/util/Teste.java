package br.com.frota.util;

import br.com.frota.DAO.*;

public class Teste {

    static PessoaDAO pessoa = new PessoaDAO();
    public static void main(String[] args) {
        Integer ab = pessoa.count();
        System.out.println(ab);
    }
}
