package com.microservice.orderservice.service;

import com.microservice.orderservice.common.Payment;
import com.microservice.orderservice.common.TransactionRequest;
import com.microservice.orderservice.common.TransactionResponse;
import com.microservice.orderservice.entity.Order;
import com.microservice.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public TransactionResponse save(TransactionRequest request) {

        String response = "";

        Order order = request.getOrder();  // Obtém o objeto de pedido da solicitação.

        Payment payment = request.getPayment();  // Obtém o objeto de pagamento da solicitação.
        payment.setOrderId(order.getId());  // Define o ID do pedido no objeto de pagamento.
        payment.setAmount(order.getPrice());  // Define o preço do pedido no objeto de pagamento.

        // restTemplate - criando comunicação com o microserviço payment-service
        /* Faz uma solicitação POST para um serviço externo (microserviço de pagamento) com os detalhes do pagamento. Ele espera receber de volta um objeto Payment como resposta. */
        Payment paymentResponse =  restTemplate.postForObject("http://PAYMENT-SERVICE/payment/doPayment", payment, Payment.class);

        /* Verifica se o status do pagamento retornado é "sucesso". Se for, define uma mensagem de sucesso; caso contrário, define uma mensagem de falha. */
        response = paymentResponse.getPaymentStatus().equals("sucess")? "processamento de pagamento bem-sucedido e pedido realizado" : "há uma falha na API de pagamento, pedido adicionado ao carrinho";

        orderRepository.save(order); // Salva o objeto de pedido no repositório de pedidos.

        /* Retorna um objeto TransactionResponse que contém detalhes da transação, como o objeto de pedido, o valor da transação, o ID da transação e a resposta da transação (sucesso ou falha). */
        return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);
    }
}

