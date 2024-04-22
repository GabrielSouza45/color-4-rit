package br.com.color4rit.controller;

import br.com.color4rit.repository.CrudDao;

import java.sql.Blob;
import java.util.List;

public class Musica extends ConectarDao implements CrudDao<Musica> {


    @Override
    public void criarTabela() {

    }

    @Override
    public List<Musica> listarTodos() {
        return null;
    }

    @Override
    public void cadastrar(Musica objeto) {

    }

    @Override
    public void editar(Musica objeto) {

    }

    @Override
    public void excluir(Musica objeto) {

    }
}
