package com.meuteste.cadastrocliente;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.util.ArrayList;

public class Controlador {
    @FXML
    private TextField inNome;
    @FXML
    private TextField inTelefone;
    @FXML
    private TextField inCEP;
    @FXML
    private TextField inRua;
    @FXML
    private TextField inNumero;
    @FXML
    private TextField inCidade;
    @FXML
    private TextField inEstado;

    @FXML
    private Button botaoBuscar;
    @FXML
    private Button botaoCadastrar;
    @FXML
    private Button botaoLimpar;
    @FXML
    private Button botaoListarClientes; 

    private ArrayList<Cliente> clientes = new ArrayList<>();

    /**
     *
     */
    @FXML
    public void initialize() {
        botaoBuscar.setOnAction(e -> buscarEndereco());
        botaoCadastrar.setOnAction(e -> cadastrarCliente());
        botaoLimpar.setOnAction(e -> limparCampos());
        botaoListarClientes.setOnAction(e -> listarClientes());

    }
    
    @FXML
    private void buscarEndereco() {
        String cep = inCEP.getText();
        try {
            Buscador buscador = new Buscador();
            Endereco endereco = buscador.buscar(cep);
            // Preenchendo os campos de endereço
            inRua.setText(endereco.getRua());
            inCidade.setText(endereco.getCidade());
            inEstado.setText(endereco.getEstado());
            inNumero.setText(""); 
        } catch (IOException e) {
            // Tratamento específico para IOException
            mostrarMensagemErro("Erro: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            // Tratamento específico para IllegalArgumentException
            mostrarMensagemErro("Argumento inválido: " + e.getMessage());
        } catch (Exception e) {
            // Tratamento genérico para outras exceções
            mostrarMensagemErro("Erro inesperado: " + e.getMessage());
        }
    }
    
    @FXML
    private void cadastrarCliente() {
        String nome = inNome.getText();
        String telefone = inTelefone.getText();
        String cep = inCEP.getText();
        String rua = inRua.getText();
        String numero = inNumero.getText();
        String cidade = inCidade.getText();
        String estado = inEstado.getText();

        Endereco endereco = new Endereco(cep, rua, numero, cidade, estado);
        Cliente cliente = new Cliente(nome, endereco, telefone);
        clientes.add(cliente);
        
        mostrarMensagemSucesso("Cliente cadastrado com sucesso! Código: " + cliente.getCodigo());
        limparCampos();
        System.out.println(" ");
        System.out.println("Cliente cadastrado:");
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Telefone: " + cliente.getTelefone());
        System.out.println("Endereco:");
        System.out.println("CEP: " + endereco.getCep());
        System.out.println("Rua: " + endereco.getRua());
        System.out.println("Numero: " + endereco.getNumero());
        System.out.println("Cidade: " + endereco.getCidade());
        System.out.println("Estado: " + endereco.getEstado());
        System.out.println(" ");
    }
    
    @FXML
    private void limparCampos() {
        inNome.clear();
        inTelefone.clear();
        inCEP.clear();
        inRua.clear();
        inNumero.clear();
        inCidade.clear();
        inEstado.clear();
    }

    @FXML
    private void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            System.out.println(" ");
            System.out.println("Lista de clientes cadastrados:");
            for (Cliente cliente : clientes) {
                System.out.println("Codigo: " + cliente.getCodigo());
                System.out.println("Nome: " + cliente.getNome());
                System.out.println("Telefone: " + cliente.getTelefone());
                System.out.println("Endereco:");
                System.out.println("CEP: " + cliente.getEndereco().getCep());
                System.out.println("Rua: " + cliente.getEndereco().getRua());
                System.out.println("Numero: " + cliente.getEndereco().getNumero());
                System.out.println("Cidade: " + cliente.getEndereco().getCidade());
                System.out.println("Estado: " + cliente.getEndereco().getEstado());
                System.out.println("-----");
            }
        }
    }

    private void mostrarMensagemErro(String mensagem) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarMensagemSucesso(String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
