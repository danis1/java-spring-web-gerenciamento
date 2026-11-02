package br.edu.fatecpg.apibiblioteca.repository;

import br.edu.fatecpg.apibiblioteca.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}