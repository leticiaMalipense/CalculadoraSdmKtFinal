package br.edu.ifsp.scl.calculadorasdmkt.controller

import ConfiguracaoService
import br.edu.ifsp.scl.calculadorasdmkt.model.Configuracao
import br.edu.ifsp.scl.calculadorasdmkt.model.ConfiguracaoBase
import br.edu.ifsp.scl.calculadorasdmkt.view.ConfiguracaoActivity

class ConfiguracaoController(val view: ConfiguracaoActivity) {
    // Model
    val model: ConfiguracaoService
    init{
        model = ConfiguracaoService(view.applicationContext)
    }

    fun salvaConfiguracao(configuracao: Configuracao) {
        model.setConfiguracao(configuracao)
        view.atualizaViewConfiguracao(configuracao)
    }

    fun salvaConfiguracaoBase(configuracaoBase: ConfiguracaoBase) {
        model.setConfiguracaoBase(configuracaoBase)
        view.atualizaViewConfiguracaoBase(configuracaoBase)
    }

    fun buscaConfiguracao() {
        val configuracao = model.getConfiguracao()
        val configuracaoBase = model.getConfiguracaoBase()
        view.atualizaViewConfiguracao(configuracao)
        view.atualizaViewConfiguracaoBase(configuracaoBase)
    }
}