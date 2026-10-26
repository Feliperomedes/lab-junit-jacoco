package br.ufrgs.inf.lab.nivel2;

import static org.junit.jupiter.api.Assertions.*;

import br.ufrgs.inf.lab.nivel1.Conta;
import org.junit.jupiter.api.BeforeEach;
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
    void transferenciaAcimaDe1000PagaTarifaDe1Porcento() {
        // DADO   origem com 10.000 e destino com 0
        // QUANDO transfere 2.000
        // ENTÃO   status = OK | saldo da origem = 10000 - 2000 - (2000 * 0.01) = 7980.0 | saldo do destino = 2000.0
        StatusTransferencia status = servico.transferir(origem, destino, 2_000.0);

        assertEquals(StatusTransferencia.OK, status);
        assertEquals(7_980.0, origem.getSaldo(), DELTA);
        assertEquals(2_000.0, destino.getSaldo(), DELTA);
    }

    @Test
    void transferenciaDeExatamente1000NaoPagaTarifa() {
        // Valor-limite da regra R4: "ACIMA de 1.000" ... e 1.000 exatos?
        // Verifique o status, a tarifa e os saldos (sem tarifa aplicada).
        StatusTransferencia status = servico.transferir(origem, destino, 1_000.0);

        assertEquals(StatusTransferencia.OK, status);
        assertEquals(9_000.0, origem.getSaldo(), DELTA);
        assertEquals(1_000.0, destino.getSaldo(), DELTA);
    }

    @Test
    void transferenciaAcimaDoLimiteDeveSerRecusada() {
        // Qual status? (Excedeu o limite máximo por transferência).
        // Testando com o limite excedido (ex: 5000.01 se o limite for 5000) e com exatamente o limite.
        StatusTransferencia statusInvalido = servico.transferir(origem, destino, 5_000.01);
        assertEquals(StatusTransferencia.VALOR_INVALIDO, statusInvalido);
        assertEquals(10_000.0, origem.getSaldo(), DELTA);
        assertEquals(0.0, destino.getSaldo(), DELTA);

        // Testando exatamente no limite (ex: 5000.0 sendo aceito)
        StatusTransferencia statusValido = servico.transferir(origem, destino, 5_000.0);
        assertEquals(StatusTransferencia.OK, statusValido);
    }

    @Test
    void saldoInsuficienteParaValorMaisTarifaNaoAlteraNenhumaConta() {
        // DICA: crie uma conta de origem com saldo suficiente para o VALOR,
        //       mas NÃO suficiente para VALOR + TARIFA (ex.: saldo 2.000, valor 2.000).
        Conta origemLimitada = new Conta("OrigemLimitada", 2_000.0);
        StatusTransferencia status = servico.transferir(origemLimitada, destino, 2_000.0);

        assertEquals(StatusTransferencia.SALDO_INSUFICIENTE, status);
        assertEquals(2_000.0, origemLimitada.getSaldo(), DELTA);
        assertEquals(0.0, destino.getSaldo(), DELTA);
    }

    @Test
    void mesmaContaEValorInvalidoDevemSerRecusados() {
        // Cubra R1 (mesma conta) e R2 (valor zero e valor negativo).
        // 1. Mesma conta
        StatusTransferencia statusMesmaConta = servico.transferir(origem, origem, 100.0);
        assertEquals(StatusTransferencia.CONTA_ORIGEM_DESTINO_IGUAL, statusMesmaConta);

        // 2. Valor zero
        StatusTransferencia statusZero = servico.transferir(origem, destino, 0.0);
        assertEquals(StatusTransferencia.VALOR_INVALIDO, statusZero);

        // 3. Valor negativo
        StatusTransferencia statusNegativo = servico.transferir(origem, destino, -50.0);
        assertEquals(StatusTransferencia.VALOR_INVALIDO, statusNegativo);

        // Confira que os saldos não mudaram em nenhuma das tentativas inválidas
        assertEquals(10_000.0, origem.getSaldo(), DELTA);
        assertEquals(0.0, destino.getSaldo(), DELTA);
    }
}