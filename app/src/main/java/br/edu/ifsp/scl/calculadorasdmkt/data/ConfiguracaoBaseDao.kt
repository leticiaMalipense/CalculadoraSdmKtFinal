package br.edu.ifsp.scl.calculadorasdmkt.data

import br.edu.ifsp.scl.calculadorasdmkt.model.ConfiguracaoBase

interface ConfiguracaoBaseDao {
    // CRUD, exceto Delete no nosso exemplo
    fun createOrUpdateConfiguracaoBase(configuracaoBase: ConfiguracaoBase)
    fun readConfiguracaoBase(): ConfiguracaoBase
}