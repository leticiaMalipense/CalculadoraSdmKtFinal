package br.edu.ifsp.scl.calculadorasdmkt.data

import br.edu.ifsp.scl.calculadorasdmkt.model.Configuracao

interface ConfiguracaoDao {
    // CRUD, exceto Delete no nosso exemplo
    fun createOrUpdateConfiguracao(configuracao: Configuracao)
    fun readConfiguracao(): Configuracao
}