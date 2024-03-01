#  Rest Template: 

## APIs Microservices order-service e payment-service

### Endpoint:

```txt
URL : http://localhost:9192/order/bookOrder
HTTP Method : POST
```

### Json Request:

```json
{
  "order":{
    "name":"IPhone",
    "qty":1,
    "price":8000

  },
  "payment":{}
}
```

### Json Response:

```json
{
  "order": {
    "id": 1,
    "name": "IPhone",
    "qty": 1,
    "price": 8000.0
  },
  "amount": 8000.0,
  "transactionId": "97e05e3a-891d-4d34-b041-f99f6a8fef9c",
  "message": "processamento de pagamento bem-sucedido e pedido realizado"
}
```
--- 

# Autor
## Feito por `Daniel Penelva de Andrade`
