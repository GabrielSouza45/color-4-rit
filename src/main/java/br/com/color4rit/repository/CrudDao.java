package br.com.color4rit.repository;

import java.util.List;

public interface CrudDao<T> {

    public void criarTabela();
    public List<T> listarTodos();
    public T listarPorId(long id);
    public void cadastrar(T objeto);
    public void editar(T objeto);
    public void excluir(T objeto);

}
