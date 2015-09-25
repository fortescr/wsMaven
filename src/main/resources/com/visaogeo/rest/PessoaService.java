package com.visaogeo.rest;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.visaogeo.dao.GenericDAO;
import com.visaogeo.model.Pessoa;

@Path("/pessoas")
public class PessoaService {

	@GET
	@Produces("application/json")
	public List<Pessoa> getPessoas() {
		GenericDAO<Pessoa> dao = new GenericDAO<Pessoa>();
		return dao.buscarTodos("Pessoa");
	}

	@POST
	@Produces("application/json")
	public String salvar(@PathParam("nome") String nome,
			@PathParam("formacao") String formacao,
			@PathParam("habilidade") String habilidade,
			@PathParam("arquivo") String arquivo) {

		Pessoa pessoa = new Pessoa();
		try {  
			byte[] bytes = arquivo.getBytes();    
			File file = new File("anexo.pdf");   
			BufferedOutputStream bos = null;  
			bos = new BufferedOutputStream(new FileOutputStream(file));   
			bos.write(bytes);   
			bos.close();   
			pessoa.setArquivo(file);

		} catch (Exception ex) {  
			return "Arquivo inválido";  
		}
		GenericDAO<Pessoa> dao = new GenericDAO<Pessoa>();

		try{
			pessoa.setFormacao(formacao);
			pessoa.setNome(nome);

			dao.salvar(pessoa);
			return "Sucesso na inclusão das informações";
		}catch(Exception e){
			return "Erro ao salvar";
		}
	}
}
