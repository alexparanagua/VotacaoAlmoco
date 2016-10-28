package br.com.alura.listavip.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.alura.listavip.model.TbUsuario;

public interface ConvidadoRepository extends CrudRepository<TbUsuario, Long>{
    List<TbUsuario> findByLogin(String login);
}
