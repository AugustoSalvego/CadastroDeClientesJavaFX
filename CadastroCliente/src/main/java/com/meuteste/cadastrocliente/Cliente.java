package com.meuteste.cadastrocliente;

public class Cliente {
    private static int codigoContador = 1; // Contador para gerar códigos de clientes
    private int codigo;
    private String nome;
    private Endereco endereco;
    private String telefone;

    public Cliente(String nome, Endereco endereco, String telefone) {
        this.codigo = codigoContador++;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }
}
