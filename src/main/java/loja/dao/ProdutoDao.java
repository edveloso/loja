package loja.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Repository;

import loja.model.Produto;

@Repository
public class ProdutoDao {

	private Map<Integer, Produto> produtos = 
			new TreeMap<Integer, Produto>();
	
	private int count = 1;
	
	
	public Produto salvar(Produto produto) {
		produto.setId(count);
		produtos.put(count++, produto);
		return produto;
	}
	
	public Produto editar(Integer id, Produto produto) {
		produtos.put(id, produto);
		return produto;
	}
	
	public void apagar(Integer id) {
		produtos.remove(id);
	}
	
	public Produto obterPeloId(Integer id) {
		Produto produto = produtos.get(id);
		return produto;
	}
	
	public List<Produto> obterTodos(){
		return new ArrayList<Produto>(produtos.values());
	}
	
	
	
	
	
}
