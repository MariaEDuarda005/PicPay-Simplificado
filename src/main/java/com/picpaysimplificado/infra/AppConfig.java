package com.picpaysimplificado.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration // essa vai ser a classe de configuração
public class AppConfig {
    @Bean // serve para exportar uma classe para o Spring, para que ele consiga carregar essa classe e fazer injeção de dependência dela em outra classes.
    // indicando apenas qual é a instancia da classe que ele precisa retornar
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
