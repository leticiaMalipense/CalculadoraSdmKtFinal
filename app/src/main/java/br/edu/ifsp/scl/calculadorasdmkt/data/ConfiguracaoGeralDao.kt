package br.edu.ifsp.scl.calculadorasdmkt.data

import br.edu.ifsp.scl.calculadorasdmkt.model.ConfiguracaoGeral

interface ConfiguracaoGeralDao {
    // CRUD, exceto Delete no nosso exemplo
    fun createOrUpdateConfiguracaoGeral(configuracaoGeral: ConfiguracaoGeral)
    fun readConfiguracaoGeral(): ConfiguracaoGeral
}