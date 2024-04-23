package br.com.color4rit.controller;

import br.com.color4rit.repository.CrudDao;

import java.util.List;

public class MusicaDao extends ConectarDao implements CrudDao<MusicaDao> {


    @Override
    public void criarTabela() {

    }

    @Override
    public List<MusicaDao> listarTodos() {
        return null;
    }

    @Override
    public MusicaDao listarPorId(long id) {
        return null;
    }

    @Override
    public void cadastrar(MusicaDao objeto) {

    }

    @Override
    public void editar(MusicaDao objeto) {

    }

    @Override
    public void excluir(MusicaDao objeto) {

    }
}
