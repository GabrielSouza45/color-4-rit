package br.com.color4rit.controller;

import br.com.color4rit.repository.CrudDao;

import java.util.List;

public class Sessao extends ConectarDao implements CrudDao<Sessao> {


    @Override
    public void criarTabela() {

    }

    @Override
    public List<Sessao> listarTodos() {
        return null;
    }

    @Override
    public void cadastrar(Sessao objeto) {

    }

    @Override
    public void editar(Sessao objeto) {

    }

    @Override
    public void excluir(Sessao objeto) {

    }
}
