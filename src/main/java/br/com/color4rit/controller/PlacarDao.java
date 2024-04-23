package br.com.color4rit.controller;

import br.com.color4rit.repository.CrudDao;

import java.util.List;

public class PlacarDao extends ConectarDao implements CrudDao<PlacarDao> {


    @Override
    public void criarTabela() {

    }

    @Override
    public List<PlacarDao> listarTodos() {
        return null;
    }

    @Override
    public PlacarDao listarPorId(long id) {
        return null;
    }

    @Override
    public void cadastrar(PlacarDao objeto) {

    }

    @Override
    public void editar(PlacarDao objeto) {

    }

    @Override
    public void excluir(PlacarDao objeto) {

    }
}
