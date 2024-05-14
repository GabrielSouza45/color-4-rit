// Este Arquivo serve para criar uma instancia global do usuario logado
const user = {
    id: null,
    login: null,
    nome: null
}

function userLogado(id, login, nome) {
    user.id = id,
    user.login = login,
    user.nome = nome
}

export {user, userLogado}; 

