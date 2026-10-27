package br.ufrgs.inf.lab.nivel2;

import br.ufrgs.inf.lab.nivel1.Conta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ServicoTransferenciaTest {

    private ServicoTransferencia servico;
    private Conta origem;
    private Conta destino;
    private static final double DELTA = 0.001;

    @BeforeEach
    void setUp() {
        servico = new ServicoTransferencia();
        origem = new Conta("Ana", 2000.0);
        destino = new Conta("Bruno", 500.0);
    }

    @Test
    void transferenciaValidaDeveAtualizarSaldos() {
        StatusTransferencia st = servico.transferir(origem, destino, 300.0);
        assertEquals(StatusTransferencia.OK, st);
        assertEquals(1700.0, origem.getSaldo(), DELTA);
        assertEquals(800.0, destino.getSaldo(), DELTA);
    }

    @Test
    void transferenciaMesmaContaDeveFalhar() {
        StatusTransferencia st = servico.transferir(origem, origem, 100.0);
        assertEquals(StatusTransferencia.MESMA_CONTA, st);
    }

    @Test
    void transferenciaValorZeroOuNegativoDeveFalhar() {
        StatusTransferencia stZero = servico.transferir(origem, destino, 0.0);
        assertEquals(StatusTransferencia.VALOR_INVALIDO, stZero);

        StatusTransferencia stNegativo = servico.transferir(origem, destino, -50.0);
        assertEquals(StatusTransferencia.VALOR_INVALIDO, stNegativo);
    }

    @Test
    void transferenciaAcimaDoLimiteDeveFalhar() {
        StatusTransferencia st = servico.transferir(origem, destino, 5000.01);
        assertEquals(StatusTransferencia.ACIMA_DO_LIMITE, st);
    }

    @Test
    void transferenciaSaldoInsuficienteDeveFalhar() {
        StatusTransferencia st = servico.transferir(origem, destino, 2500.0);
        assertEquals(StatusTransferencia.SALDO_INSUFICIENTE, st);
    }
}