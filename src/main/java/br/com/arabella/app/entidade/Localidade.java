package br.com.arabella.app.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name="log_localidade")
public class Localidade {
	@Id
	@Column(name="loc_nu_sequencial")
	private Integer id;
	
	@Column(name="loc_no")
	private String cidade;
	
	@Column(name="ufe_sg")
	private String uf;
	

}
