package com.br.Zlash.service;

import com.br.Zlash.models.Saldo;
import com.br.Zlash.repositories.SaldoRepository;
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
public class SaldoServiceTest {

    @Autowired
    private SaldoService saldoService;

    @MockBean
    private SaldoRepository saldoRepository;

    private Saldo saldoTeste;

    @BeforeEach
    public void setUp(){
        this.saldoTeste = new Saldo();
        this.saldoTeste.setCpf("108.162.870-74");
        this.saldoTeste.setValor(1000.00);
    }

    @Test
    public void testarBuscaDeSaldoPeloCPFCaminhaBom(){
        Optional<Saldo> optionalSaldo = Optional.of(this.saldoTeste);
        Mockito.when(saldoRepository.findById(Mockito.anyString())).thenReturn(optionalSaldo);

        Saldo saldo = saldoService.buscarPorCPF("xablau");

        Assertions.assertSame(this.saldoTeste, saldo);

        Assertions.assertEquals(saldo.getCpf(),"108.162.870-74" );
    }
}
