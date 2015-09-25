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
import com.visaogeo.model.Habilidade;
import com.visaogeo.model.Pessoa;

@Path("/habilidades")
public class HabilidadeService {
	
	@GET
	@Produces("application/json")
	public List<Habilidade> getPessoas() {
		GenericDAO<Habilidade> dao = new GenericDAO<Habilidade>(); 
		return dao.buscarTodos("Habilidade");
	}
	
	@POST
	@Produces("application/json")
	public String salvar(@PathParam("descricao") String descricao) {

		Habilidade hab = new Habilidade();

		GenericDAO<Habilidade> dao = new GenericDAO<Habilidade>();

		try{
			hab.setDescricao(descricao);;

			dao.salvar(hab);
			return "Sucesso na inclusão das informações";
		}catch(Exception e){
			return "Erro ao salvar";
		}
	}
}
