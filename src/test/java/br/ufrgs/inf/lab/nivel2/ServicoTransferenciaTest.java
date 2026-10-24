package br.ufrgs.inf.lab.nivel2;

import static org.junit.jupiter.api.Assertions.*;

import br.ufrgs.inf.lab.nivel1.Conta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * NÍVEL 2 (semi-guiado): ServicoTransferencia.
 *
 * Aqui NÃO há os asserts: cada teste abaixo diz o CENÁRIO e o que deve
 * ser verificado. Você decide como escrever (assertEquals, assertTrue...).
 * Leia as regras R1..R5 em ServicoTransferencia.java.
 *
 * Remova o @Disabled e o fail(...) de cada teste à medida que o implementar.
 * Sinta-se livre para ACRESCENTAR testes (e usar @ParameterizedTest).
 */
class ServicoTransferenciaTest {

    private static final double DELTA = 0.001;

    private ServicoTransferencia servico;
    private Conta origem;
    private Conta destino;

    // Roda ANTES de cada teste: garante contas "limpas" (testes independentes)
    @BeforeEach
    void preparar() {
        servico = new ServicoTransferencia();
        origem = new Conta("Origem", 10_000.0);
        destino = new Conta("Destino", 0.0);
    }

    // ---------------------------------------------------------------
    // PRONTO (exemplo)
    // ---------------------------------------------------------------

    @Test
    void transferenciaPequenaDeveSerAceitaSemTarifa() {
        StatusTransferencia status = servico.transferir(origem, destino, 200.0);

        assertEquals(StatusTransferencia.OK, status);
        assertEquals(9_800.0, origem.getSaldo(), DELTA);
        assertEquals(200.0, destino.getSaldo(), DELTA);
    }

    // ---------------------------------------------------------------
    // PARA VOCÊ ESCREVER
    // ---------------------------------------------------------------

    @Test
    @Disabled("N2-A")
    void transferenciaAcimaDe1000PagaTarifaDe1Porcento() {
        // DADO   origem com 10.000 e destino com 0
        // QUANDO transfere 2.000
        // ENTÃO  status = ?  | calcularTarifa(2000) = ?  | saldo da origem = ?  | saldo do destino = ?
        //        (calcule os valores esperados na mão ANTES de rodar!)
        fail("Escreva o teste N2-A");
    }

    @Test
    @Disabled("N2-B")
    void transferenciaDeExatamente1000NaoPagaTarifa() {
        // Valor-limite da regra R4: "ACIMA de 1.000" ... e 1.000 exatos?
        // Verifique o status, a tarifa e os saldos.
        fail("Escreva o teste N2-B");
    }

    @Test
    @Disabled("N2-C")
    void transferenciaAcimaDoLimiteDeveSerRecusada() {
        // Qual status? Qual o valor mínimo que ultrapassa o limite (R3)?
        // E um teste com EXATAMENTE o limite: o que deveria acontecer?
        // Verifique também que nenhuma conta foi alterada.
        fail("Escreva o teste N2-C");
    }

    @Test
    @Disabled("N2-D")
    void saldoInsuficienteParaValorMaisTarifaNaoAlteraNenhumaConta() {
        // DICA: crie uma conta de origem com saldo suficiente para o VALOR,
        //       mas NÃO suficiente para VALOR + TARIFA (ex.: saldo 2.000, valor 2.000).
        // Verifique o status e que origem E destino mantêm os saldos originais.
        fail("Escreva o teste N2-D");
    }

    @Test
    @Disabled("N2-E")
    void mesmaContaEValorInvalidoDevemSerRecusados() {
        // Cubra R1 (mesma conta) e R2 (valor zero e valor negativo).
        // Quantos testes/asserts você precisa para exercitar os DOIS lados de cada "if"?
        // Em todos os casos, confira o status E que os saldos não mudaram.
        fail("Escreva o teste N2-E");
    }
}
