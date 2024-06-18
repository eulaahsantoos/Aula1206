package com.example.demo.controller;

import com.example.demo.model.Jogo;
import com.example.demo.service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    @Autowired
    private JogoService jogoService;

    @GetMapping
    public List<Jogo> listarJogos() {
        return jogoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogo> buscarJogoPorId(@PathVariable Long id) {
        Optional<Jogo> jogo = jogoService.buscarPorId(id);
        if (jogo.isPresent()) {
            return ResponseEntity.ok(jogo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Jogo> criarJogo(@RequestBody Jogo jogo) {
        Jogo novoJogo = jogoService.salvar(jogo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoJogo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogo> atualizarJogo(@PathVariable Long id, @RequestBody Jogo jogoAtualizado) {
        Optional<Jogo> jogo = jogoService.buscarPorId(id);
        if (jogo.isPresent()) {
            jogoAtualizado.setId(id); // Garantir que o ID seja definido corretamente
            Jogo jogoAtualizadoSalvo = jogoService.salvar(jogoAtualizado);
            return ResponseEntity.ok(jogoAtualizadoSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarJogo(@PathVariable Long id) {
        jogoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // Implemente outros métodos conforme necessário
}
