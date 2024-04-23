package br.com.color4rit.controller;

import br.com.color4rit.repository.CrudDao;

import java.util.List;

public class SessaoDao extends ConectarDao implements CrudDao<SessaoDao> {


    @Override
    public void criarTabela() {

    }

    @Override
    public List<SessaoDao> listarTodos() {
        return null;
    }

    @Override
    public SessaoDao listarPorId(long id) {
        return null;
    }

    @Override
    public void cadastrar(SessaoDao objeto) {

    }

    @Override
    public void editar(SessaoDao objeto) {

    }

    @Override
    public void excluir(SessaoDao objeto) {

    }
}
