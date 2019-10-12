package loja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import loja.dao.ProdutoDao;
import loja.model.Produto;

@RestController
public class ProdutoController {

	
	@Autowired
	private ProdutoDao dao;
	
	 
	@ApiOperation(value = "Lista todos os produtos da base ")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Retorna a lista geral")
			}
			)
	@RequestMapping(
			value = "/produtos",
			method = RequestMethod.GET,
			produces = "application/json"
			)
	public List<Produto> listAll(){
		List<Produto> prods = dao.obterTodos();
		return prods;
	}
	
	
	@RequestMapping( 
				value = "/produtos/{id}",
				method = RequestMethod.GET,
				produces = "application/json"
			)
	public ResponseEntity<Produto> 
			obterPeloId (@PathVariable(value = "id") Integer id){
		Produto produto = dao.obterPeloId(id);
		if(produto == null) {
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Produto>(produto, HttpStatus.OK);
		}
	}
	

	@ApiOperation(value = "Grava um produto na base ")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Retorna o produto gravado"),
					@ApiResponse(code = 403, message = "Voce não tem permissão para gravar")
			}
			)
	
	@RequestMapping( 
			value = "/produtos",
			method = RequestMethod.POST,
			produces = "application/json",
			consumes = "application/json"
			)
	public Produto salvar(@RequestBody Produto produto) {
		return dao.salvar(produto); 
	}
	
	

	@RequestMapping( 
				value = "/produtos/{id}",
				method = RequestMethod.PUT,
				produces = "application/json",
				consumes = "application/json"
				
			)
	public ResponseEntity<Produto> 
			alterar(
					@PathVariable(value = "id") Integer id,
					@RequestBody Produto produto
					){
		
		if(produto == null) {
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		} else {
			Produto produtoEditado = dao.editar(id, produto);
			return new ResponseEntity<Produto>(produtoEditado, HttpStatus.ACCEPTED);
		}
	}
	
	
	
	@RequestMapping( 
			value = "/produtos/{id}",
			method = RequestMethod.DELETE,
			produces  = "application/json"
		)
	public ResponseEntity<Produto> 
		deletar(
				@PathVariable(value = "id") Integer id
				){
		dao.apagar(id);
	
		return new ResponseEntity<Produto>(HttpStatus.NO_CONTENT);
	}


	public ProdutoDao getDao() {
		return dao;
	}


	public void setDao(ProdutoDao dao) {
		this.dao = dao;
	}
	
}
