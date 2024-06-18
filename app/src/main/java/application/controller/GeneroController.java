package com.example.demo.controller;

import com.example.demo.model.Genero;
import com.example.demo.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping
    public List<Genero> listarGeneros() {
        return generoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genero> buscarGeneroPorId(@PathVariable Long id) {
        Optional<Genero> genero = generoService.buscarPorId(id);
        if (genero.isPresent()) {
            return ResponseEntity.ok(genero.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Genero> criarGenero(@RequestBody Genero genero) {
        Genero novoGenero = generoService.salvar(genero);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoGenero);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genero> atualizarGenero(@PathVariable Long id, @RequestBody Genero generoAtualizado) {
        Optional<Genero> genero = generoService.buscarPorId(id);
        if (genero.isPresent()) {
            generoAtualizado.setId(id); // Garantir que o ID seja definido corretamente
            Genero generoAtualizadoSalvo = generoService.salvar(generoAtualizado);
            return ResponseEntity.ok(generoAtualizadoSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGenero(@PathVariable Long id) {
        generoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // Implemente outros métodos conforme necessário
}
