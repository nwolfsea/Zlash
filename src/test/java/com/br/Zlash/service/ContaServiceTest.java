package com.br.Zlash.service;

import com.br.Zlash.enuns.StatusConta;
import com.br.Zlash.exceptions.SaldoInsuficienteException;
import com.br.Zlash.models.Conta;
import com.br.Zlash.models.Saldo;
import com.br.Zlash.repositories.ContaRepository;
import com.br.Zlash.services.ContaService;
import com.br.Zlash.services.SaldoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class ContaServiceTest {

    @Autowired
    private ContaService contaService;

    @MockBean
    private ContaRepository contaRepository;

    @MockBean
    private SaldoService saldoService;

    private Conta contaTeste;

    @BeforeEach
    public void setUp() {
        Saldo saldo = new Saldo();
        saldo.setValor(1000.00);
        saldo.setCpf("108.162.870-74");

        this.contaTeste = new Conta();
        this.contaTeste.setId(1);
        this.contaTeste.setValor(1000);
        this.contaTeste.setDescricao("uashuashaus");
        this.contaTeste.setStatus(StatusConta.PAGO);
        this.contaTeste.setSaldo(saldo);
    }

    @Test
    public void testarValidarSaldo() {
        this.contaTeste.setValor(10000);

        Assertions.assertThrows(SaldoInsuficienteException.class, () -> {
            contaService.verificarSaldo(this.contaTeste);
        });
    }

    @Test
    public void testarCalcularSaldo() {
        contaService.calcularSaldo(this.contaTeste);

        Mockito.verify(saldoService, Mockito.times(1)).
                debitarSaldo(Mockito.any(Saldo.class), Mockito.anyDouble());

    }
}