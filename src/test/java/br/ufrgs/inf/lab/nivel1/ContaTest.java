package br.ufrgs.inf.lab.nivel1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ContaTest {

    private static final double DELTA = 0.001;

    @Test
    void contaNovaDeveGuardarTitularESaldoInicial() {
        Conta conta = new Conta("Ana", 100.0);
        assertEquals("Ana", conta.getTitular());
        assertEquals(100.0, conta.getSaldo(), DELTA);
    }

    @Test
    void depositoValidoDeveAumentarOSaldo() {
        Conta conta = new Conta("Ana", 100.0);
        boolean depositou = conta.depositar(50.0);
        assertTrue(depositou);
        assertEquals(150.0, conta.getSaldo(), DELTA);
    }

    @Test
    void depositoComValorNegativoDeveSerRecusado() {
        Conta conta = new Conta("Ana", 100.0);
        boolean depositou = conta.depositar(-10.0);
        assertFalse(depositou);
        assertEquals(100.0, conta.getSaldo(), DELTA);
    }

    @Test
    void saqueValidoDeveDiminuirOSaldo() {
        Conta conta = new Conta("Ana", 100.0);
        boolean sacou = conta.sacar(30.0);
        assertTrue(sacou);
        assertEquals(70.0, conta.getSaldo(), DELTA);
    }

    @Test
    void saqueMaiorQueOSaldoDeveSerRecusado() {
        Conta conta = new Conta("Ana", 100.0);
        boolean sacou = conta.sacar(100.01);
        assertFalse(sacou);
        assertEquals(100.0, conta.getSaldo(), DELTA);
    }

    @Test
    void saqueDoSaldoExatoDeveSerPermitidoEZerarAConta() {
        Conta conta = new Conta("Ana", 100.0);
        boolean sacou = conta.sacar(100.0);
        assertTrue(sacou);
        assertEquals(0.0, conta.getSaldo(), DELTA);
    }
}