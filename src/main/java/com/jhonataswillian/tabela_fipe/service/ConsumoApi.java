package com.jhonataswillian.tabela_fipe.service;

import org.springframework.web.client.RestClient;

public class ConsumoApi {
    public String obterDados(String endereco) {
        RestClient client = RestClient.create();
        return client
                .get()
                .uri(endereco)
                .retrieve()
                .body(String.class);
    }
}
