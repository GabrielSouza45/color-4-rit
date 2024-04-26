package br.com.color4rit.repository;

import br.com.color4rit.model.Mapa;
import br.com.color4rit.model.Musica;

import java.util.List;

public interface CrudDao<T> {

    public void criarTabela();
    public List<T> listarTodos();
    public void cadastrar(T objeto);
    public void editar(T objeto);
    public void excluir(T objeto);

}
