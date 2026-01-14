package com.jhonataswillian.tabela_fipe.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
