package com.jhonataswillian.tabela_fipe.principal;

import com.jhonataswillian.tabela_fipe.model.Dados;
import com.jhonataswillian.tabela_fipe.model.Modelos;
import com.jhonataswillian.tabela_fipe.model.Veiculo;
import com.jhonataswillian.tabela_fipe.service.ConsumoApi;
import com.jhonataswillian.tabela_fipe.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner scanner;
    private ConsumoApi consumoApi;
    private ConverteDados converteDados;

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public Principal() {
        this.scanner = new Scanner(System.in);
        this.consumoApi = new ConsumoApi();
        this.converteDados = new ConverteDados();
    }

    public void exibeMenu() {

        System.out.println("OPÇÕES: Carro, Moto, Caminhão");
        System.out.println("Digite sua opção: ");
        var opcao = scanner.nextLine();

        String endereco;

        if (opcao.toLowerCase().contains("carr")) {
            endereco = URL_BASE + "carros/marcas";
        } else if (opcao.toLowerCase().contains("mot")) {
            endereco = URL_BASE + "motos/marcas";
        } else if (opcao.toLowerCase().contains("caminh")) {
            endereco = URL_BASE + "caminhoes/marcas";
        } else {
            System.out.printf("Inválido, \"%s\" não existe!", opcao);
            return;
        }

        var json = consumoApi.obterDados(endereco);
        System.out.println(json);

        var marcas = converteDados.obterDados(json, Dados[].class);

        for (Dados marca : marcas) {
            System.out.println(marca);
        }

        System.out.println("\nInforme o código da marca para consulta: ");
        var codigoMarca = scanner.nextLine();

        endereco = endereco + "/" + codigoMarca + "/modelos";
        json = consumoApi.obterDados(endereco);

        var modeloLista = converteDados.obterDados(json, Modelos.class);

        System.out.println("\nModelos dessa marca: ");
        modeloLista.modelos().forEach(System.out::println);

        System.out.println("\nDigite um trecho do nome do carro a ser buscado: ");
        var nomeVeiculo = scanner.nextLine();

        List<Dados> modelosFiltrados = modeloLista.modelos().stream()
                .filter(modelo -> modelo.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos Filtrados: ");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("\nDigite o código do modelo para buscar os valores de avaliação: ");
        var codigoModelo = scanner.nextLine();

        endereco = endereco + "/" + codigoModelo + "/anos";
        json = consumoApi.obterDados(endereco);

        var anos = converteDados.obterDados(json, Dados[].class);

        List<Veiculo> veiculos = new ArrayList<>();

        for (Dados ano : anos) {
            var enderecoAno = endereco + "/" + ano.codigo();
            json = consumoApi.obterDados(enderecoAno);

            Veiculo veiculo = converteDados.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }

        System.out.println("\nTodos os veículos filtrados com avaliações por ano: ");
        veiculos.forEach(System.out::println);
    }
}
