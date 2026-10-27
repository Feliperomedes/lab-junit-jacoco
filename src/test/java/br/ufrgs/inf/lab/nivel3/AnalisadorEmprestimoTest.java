package br.ufrgs.inf.lab.nivel3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnalisadorEmprestimoTest {

    @Test
    void emprestimoValidoDeveSerAprovado() {
        AnalisadorEmprestimo analisador = new AnalisadorEmprestimo();
        DecisaoEmprestimo decisao = analisador.analisar(700, 5000.0, 1000.0, 12);
        assertEquals(DecisaoEmprestimo.APROVADO, decisao);
    }

    @Test
    void scoreBaixoDeveNegarEmprestimo() {
        AnalisadorEmprestimo analisador = new AnalisadorEmprestimo();
        DecisaoEmprestimo decisao = analisador.analisar(250, 5000.0, 1000.0, 12);
        assertEquals(DecisaoEmprestimo.NEGADO, decisao);
    }

    @Test
    void dadosInvalidosDevemSerRecusados() {
        AnalisadorEmprestimo analisador = new AnalisadorEmprestimo();
        DecisaoEmprestimo decisao = analisador.analisar(700, 0.0, 1000.0, 12);
        assertEquals(DecisaoEmprestimo.DADOS_INVALIDOS, decisao);
    }
}