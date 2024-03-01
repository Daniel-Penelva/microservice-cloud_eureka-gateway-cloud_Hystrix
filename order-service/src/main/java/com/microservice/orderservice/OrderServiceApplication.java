package com.microservice.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

/* OBS. @LoadBalanced
* A anotação @LoadBalanced habilita o balanceamento de carga entre múltiplas instâncias de um serviço.
*
* Quando anota um bean do RestTemplate com @LoadBalanced, o Spring Cloud injetará um RestTemplate especial que tem a capacidade de resolver
* automaticamente o nome lógico do serviço (registrado no Eureka ou em outro serviço de descoberta) para as instâncias físicas do serviço. Isso
* permite que faça chamadas de forma transparente a diferentes instâncias de um serviço sem ter que lidar explicitamente com a lógica de balanceamento
* de carga.
*
* Com essa configuração, sempre que injetar um bean do tipo RestTemplate em outras classes do aplicativo, o RestTemplate injetado será um RestTemplate
* com capacidade de balanceamento de carga. Então, quando fizer chamadas a serviços usando esse RestTemplate, o Spring Cloud resolverá automaticamente
* o nome lógico do serviço e fará a chamada para uma das instâncias disponíveis, distribuindo assim a carga entre elas.
*
* Essa funcionalidade é especialmente útil em ambientes de microsserviços, nos quais várias instâncias de um serviço podem estar em execução e o
* balanceamento de carga é necessário para distribuir o tráfego de forma eficiente e evitar sobrecarga em uma única instância.
* */