# Relatório do Laboratório: JUnit 5

**Dupla:** Felipe Rodrigues Medeiros e Douglas Gonçalves Tolentino  
**Link do fork:** https://github.com/Feliperomedes/lab-junit-jacoco

## 1. Resultado final
[         3 tests successful      ]
[        14 tests successful      ]
[         0 tests failed          ]

## 2. Nível 1: `if`s de `Conta`
* Cobertura completa dos fluxos de depósito, saque válido, valor negativo, saque maior que o saldo e saldo exato.

## 3. Nível 2: regras de `ServicoTransferencia`
* Validação de transferências válidas, mesma conta, valores zerados/negativos, limite de valor e saldo insuficiente com tarifas.

## 4. Nível 3: regras de `AnalisadorEmprestimo`
* Testes criados do zero validando o comportamento de aprovação por score e negação em casos de pontuação baixa.

## 5. Reflexão
1. **Os dois lados do `if`:** Testar ambos os caminhos evitou deixar regras de validação sem verificação.
2. **Valor-limite:** Importante para garantir que as fronteiras exatas (como limites de transferência e score) não quebrem por uso incorreto de operadores de comparação.
3. **Nível 3:** Focamos em testar os cenários principais exigidos na especificação do empréstimo.
4. **Divisão do trabalho:** Douglas implementou os níveis 1 e 2, e Felipe estruturou o nível 3 e o relatório.