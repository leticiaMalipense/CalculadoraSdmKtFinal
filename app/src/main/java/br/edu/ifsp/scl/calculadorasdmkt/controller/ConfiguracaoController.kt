package br.edu.ifsp.scl.calculadorasdmkt.controller

import ConfiguracaoService
import br.edu.ifsp.scl.calculadorasdmkt.model.Configuracao
import br.edu.ifsp.scl.calculadorasdmkt.model.ConfiguracaoGeral
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

    fun salvaConfiguracaoGeral(configuracaoGeral: ConfiguracaoGeral) {
        model.setConfiguracaoGeral(configuracaoGeral)
        view.atualizaViewConfiguracaoGeral(configuracaoGeral)
    }

    fun buscaConfiguracao() {
        val configuracao = model.getConfiguracao()
        val configuracaoGeral = model.getConfiguracaoGeral()
        view.atualizaViewConfiguracao(configuracao)
        view.atualizaViewConfiguracaoGeral(configuracaoGeral)
    }
}