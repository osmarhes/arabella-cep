package br.com.arabella.app.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name="log_bairro")
public class Bairro {
	@Id
	@Column(name="bai_nu_sequencial")
	private Integer id;
	
	@Column(name="bai_no")
	private String bairro;
	
}
