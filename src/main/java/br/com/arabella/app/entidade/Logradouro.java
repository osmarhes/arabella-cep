package br.com.arabella.app.entidade;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity(name="log_logradouro")
public class Logradouro {
	@Id
	@Column(name="log_nu_sequencial")
	private Integer id;
	
	@Column(name="log_tipo_logradouro")
	private String tipoLogradouro;
	
	@Column(name="log_no")
	private String logradouro;
	
	@Column(name="cep")
	private String cep;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "bai_nu_sequencial_ini")
	private Bairro bairro;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "loc_nu_sequencial")
	private Localidade localidade;
}
