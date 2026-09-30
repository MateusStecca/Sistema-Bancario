package com.mateus.banco.controller;

import com.mateus.banco.dto.ValorDTO;
import com.mateus.banco.model.Conta;
import com.mateus.banco.service.ContaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
@CrossOrigin("*")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    public ResponseEntity<Conta> criarConta(@RequestBody Conta conta) {
        Conta novaConta = contaService.criarConta(conta.getTitular(), conta.getSaldo());
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConta);
    }

    @GetMapping
    public ResponseEntity<List<Conta>> listarContas() {
        return ResponseEntity.ok(contaService.listarContas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> buscarConta(@PathVariable Long id) {
        return ResponseEntity.ok(contaService.buscarPorId(id));
    }

    @PostMapping("/{id}/depositar")
    public ResponseEntity<Conta> depositar(@PathVariable Long id, @RequestBody ValorDTO dto) {
        return ResponseEntity.ok(contaService.depositar(id, dto.getValor()));
    }

    @PostMapping("/{id}/sacar")
    public ResponseEntity<Conta> sacar(@PathVariable Long id, @RequestBody ValorDTO dto) {
        return ResponseEntity.ok(contaService.sacar(id, dto.getValor()));
    }
}