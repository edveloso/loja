package loja.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import loja.dao.ProdutoDao;
import loja.model.Produto;

@RestController
public class ProdutoController {

	
	@Autowired
	private ProdutoDao dao;
	
	
	@RequestMapping(
			value = "/produtos",
			method = RequestMethod.GET,
			produces = "application/json"
			)
	public List<Produto> listAll(){
	
		List<Produto> prods = dao.obterTodos();
		
		return prods;
		
	}


	public ProdutoDao getDao() {
		return dao;
	}


	public void setDao(ProdutoDao dao) {
		this.dao = dao;
	}
	
}
