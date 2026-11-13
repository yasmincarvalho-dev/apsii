# Sistema PedidoFacil

Projeto desenvolvido para a disciplina de Programação Orientada a Objetos pelos alunos Yasmin, Felipe Gabriel e Vitória. Trata-se de um sistema de console em Java para gerenciamento de pedidos e controle de caixa de uma lanchonete.

## Tecnologias e Conceitos Aplicados
* Java (Console/Terminal)
* Programação Orientada a Objetos (POO): Encapsulamento, Herança e Polimorfismo.
* Padrão de Projeto: Singleton.
* Princípios SOLID: Aplicados na arquitetura e na divisão de responsabilidades das classes.

## Justificativa do Padrão Singleton
O padrão Singleton foi escolhido para a classe `CaixaCentral` porque, em um estabelecimento comercial real, o caixa físico e o registro de faturamento devem ser centralizados em uma única fonte de dados. Se o sistema permitisse criar vários objetos de caixa independentes (`new CaixaCentral()`), haveria perda de registros e inconsistência financeira. O Singleton garante que exista apenas uma única instância global acessível de forma controlada em toda a aplicação.

### Outros Padrões que Poderiam Ser Utilizados
1. **Strategy:** Poderia ser utilizado para encapsular os diferentes algoritmos de pagamento (Pix, Cartão, Dinheiro) e regras de desconto, permitindo alternar a forma de cobrança dinamicamente.
2. **Factory Method:** Poderia ser utilizado para centralizar e automatizar a criação dos objetos de pagamento ou itens do cardápio com base na escolha digitada pelo usuário no menu.

## Divisão da Equipe e Arquitetura
* **Yasmin (pacote `modelo`):** Estrutura base do projeto, implementação do padrão Singleton (`CaixaCentral`) e classe de modelo (`Pedido`).
* **Felipe Gabriel (pacote `pagamento`):** Implementação de polimorfismo para as formas de pagamento (`Pagamento`, `PagamentoPix`, `PagamentoCartao`, `PagamentoDinheiro`).
* **Vitória (pacote `aplicacao`):** Menu interativo no console (`Principal`), integração entre pedidos, pagamentos e o caixa central.