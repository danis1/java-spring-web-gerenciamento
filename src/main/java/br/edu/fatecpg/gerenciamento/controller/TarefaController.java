package br.edu.fatecpg.gerenciamento.controller;

import br.edu.fatecpg.gerenciamento.model.Tarefa;
import br.edu.fatecpg.gerenciamento.repository.TarefaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaRepository repository;

    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@Valid @RequestBody Tarefa novaTarefa) {
        Tarefa tarefaSalva = repository.save(novaTarefa);
        return new ResponseEntity<>(tarefaSalva, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Tarefa> listarTodasAsTarefas() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarTarefaPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @Valid @RequestBody Tarefa tarefaAtualizada) {
        return repository.findById(id)
                .map(tarefaExistente -> {
                    tarefaExistente.setDescricao(tarefaAtualizada.getDescricao());
                    tarefaExistente.setConcluida(tarefaAtualizada.getConcluida());
                    tarefaExistente.setPrioridade(tarefaAtualizada.getPrioridade());
                    Tarefa tarefaSalva = repository.save(tarefaExistente);
                    return ResponseEntity.ok(tarefaSalva);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}