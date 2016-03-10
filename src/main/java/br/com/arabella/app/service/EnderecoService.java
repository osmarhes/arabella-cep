package br.com.arabella.app.service;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.arabella.app.entidade.Logradouro;
import br.com.arabella.app.repository.LogradouroRepository;
import br.com.arabella.app.viewmodel.Endereco;

@Service
@Repository
public class EnderecoService {

	@Autowired
	private LogradouroRepository logRepo;

	@Transactional
	public Endereco findByCep(String cep, boolean mask) {
		Endereco endereco = new Endereco();
		
		try {	
			Logradouro log = logRepo.findByCep(cep.replaceAll("-", ""));

			endereco.setTipo_logradouro(log.getTipoLogradouro());
			endereco.setLogradouro(log.getLogradouro());
			endereco.setCep(mask ? format("#####-###", log.getCep()) : log.getCep());
			endereco.setBairro(log.getBairro().getBairro());
			endereco.setCidade(log.getLocalidade().getCidade());
			endereco.setUf(log.getLocalidade().getUf());
			endereco.setResultado("1");
			endereco.setResultado_txt("Sucesso");
			
		} catch (NullPointerException e) {
			endereco.setResultado("0");
			endereco.setResultado_txt("CEP - Não encontrado!");
		}

		return endereco;
	}
	
	private static String format(String pattern, Object value) {
        MaskFormatter mask;
        try {
            mask = new MaskFormatter(pattern);
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
