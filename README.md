# PicPay Simplificado

O PicPay Simplificado é uma plataforma de pagamentos que permite a realização de transferências de dinheiro entre usuários. O sistema suporta dois tipos de usuários:

- **Usuários Comuns:** Podem enviar e receber dinheiro.

- **Lojistas:** Apenas recebem dinheiro, não podendo realizar transferências.
---

## Funcionalidades

Cadastro de usuários com Nome Completo, CPF/CNPJ, e-mail e senha.

Validação de unicidade de CPF/CNPJ e e-mail.

Transferência de dinheiro entre usuários e lojistas, respeitando regras de negócio.

Consulta a um serviço autorizador externo antes da transferência.

Transações seguras e reversíveis em caso de erro.

Notificação ao destinatário após uma transferência bem-sucedida.

API RESTFul para integração com outras aplicações.

---

## API RESTFul

A API expõe um endpoint para realizar cadastros e transferências:

**Endpoint de Cadastro**
![OrderAPI](assets/img.png)

**Endpoint de Transferência**
![OrderAPI](assets/img_1.png)

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem de Programação**: Java 21  
- **Banco de Dados**: MySQL  
- **Contêineres**: Docker  

---
Desenvolvido por [Victor Ribeiro](https://github.com/victorribeirog).
