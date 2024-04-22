package br.com.color4rit.controller;

import br.com.color4rit.repository.CrudDao;

import java.util.List;

public class Mapa extends ConectarDao implements CrudDao<Mapa> {


    @Override
    public void criarTabela() {

    }

    @Override
    public List<Mapa> listarTodos() {
        return null;
    }

    @Override
    public void cadastrar(Mapa objeto) {

    }

    @Override
    public void editar(Mapa objeto) {

    }

    @Override
    public void excluir(Mapa objeto) {

    }
}
