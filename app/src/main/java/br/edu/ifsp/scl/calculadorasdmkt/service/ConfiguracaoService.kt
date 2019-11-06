import android.content.Context
import br.edu.ifsp.scl.calculadorasdmkt.data.ConfiguracaoDao
import br.edu.ifsp.scl.calculadorasdmkt.data.ConfiguracaoGeralDao
import br.edu.ifsp.scl.calculadorasdmkt.data.ConfiguracaoSqlite
import br.edu.ifsp.scl.calculadorasdmkt.model.*

class ConfiguracaoService(context: Context) {
    var context: Context
    var configuracaoDao: ConfiguracaoDao
    var configuracaoGeralDao: ConfiguracaoGeralDao

    init{
        this.context = context
        this.configuracaoGeralDao = ConfiguracaoSharedPreferences(context)

        // Inicializando conforme a fonte de dados utilizada
        this.configuracaoDao = getInstanceConfiguracao(context)
    }
    fun setConfiguracao(configuracao: Configuracao) {
        /* Qualquer tratamento necessário aos dados antes de salvá-los
        na fonte de dados escolhida deve ser feita no Service.
        As classes que implementam o DAO devem esconder as peculiaridades
        para acesso a cada fonte de dados diferente e executar apenas as funções
        de CRUD.*/
        // Tratamento de dados aqui!
        // Delegando ao modelo
        configuracaoDao = getInstanceConfiguracao(context)
        configuracaoDao.createOrUpdateConfiguracao(configuracao)
    }

    fun setConfiguracaoGeral(configuracaoGeral: ConfiguracaoGeral) {
        /* Qualquer tratamento necessário aos dados antes de salvá-los
        na fonte de dados escolhida deve ser feita no Service.
        As classes que implementam o DAO devem esconder as peculiaridades
        para acesso a cada fonte de dados diferente e executar apenas as funções
        de CRUD.*/
        // Tratamento de dados aqui!
        // Delegando ao modelo
        configuracaoGeralDao.createOrUpdateConfiguracaoGeral(configuracaoGeral)
    }

    fun getConfiguracao(): Configuracao {
        // Tratamento de dados aqui!
        // Delegando ao modelo
        configuracaoDao = getInstanceConfiguracao(context)
        return configuracaoDao.readConfiguracao()
    }

    fun getConfiguracaoGeral(): ConfiguracaoGeral {
        // Tratamento de dados aqui!
        // Delegando ao modelo
        return configuracaoGeralDao.readConfiguracaoGeral()
    }

    fun getInstanceConfiguracao(context: Context): ConfiguracaoDao {
        if(isConfiguracaoNoBanco()){
            return ConfiguracaoSqlite(context)
        }else{
            return ConfiguracaoSharedPreferences(context)
        }
    }

    fun isConfiguracaoNoBanco(): Boolean {
      val configuracaoGeral = configuracaoGeralDao.readConfiguracaoGeral()
        if(configuracaoGeral.baseDeConfiguracao.equals(BaseDeConfiguracao.BANCO)){
            return true
        }
        return false
    }
}