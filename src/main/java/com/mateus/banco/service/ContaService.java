package com.mateus.banco.service;

import com.mateus.banco.model.Conta;
import com.mateus.banco.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Conta criarConta(String titular, double saldoInicial) {
        Conta conta = new Conta(titular, saldoInicial);
        return contaRepository.save(conta);
    }

    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

    public Conta buscarPorId(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada com o ID: " + id));
    }

    public Conta depositar(Long id, double valor) {
        Conta conta = buscarPorId(id);
        conta.depositar(valor);
        return contaRepository.save(conta);
    }

    public Conta sacar(Long id, double valor) {
        Conta conta = buscarPorId(id);
        conta.sacar(valor);
        return contaRepository.save(conta);
    }
}