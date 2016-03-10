package br.com.arabella.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.arabella.app.entidade.Logradouro;

public interface LogradouroRepository extends JpaRepository<Logradouro, Integer>{
	@Query("select l from log_logradouro l where l.cep = :cep")
	Logradouro findByCep(@Param("cep") String cep);
}
