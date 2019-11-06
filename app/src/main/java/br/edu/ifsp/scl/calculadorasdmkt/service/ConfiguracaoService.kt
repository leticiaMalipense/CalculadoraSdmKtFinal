import android.content.Context
import br.edu.ifsp.scl.calculadorasdmkt.data.ConfiguracaoDao
import br.edu.ifsp.scl.calculadorasdmkt.data.ConfiguracaoBaseDao
import br.edu.ifsp.scl.calculadorasdmkt.data.ConfiguracaoSqlite
import br.edu.ifsp.scl.calculadorasdmkt.model.*

class ConfiguracaoService(context: Context) {
    var context: Context
    var configuracaoDao: ConfiguracaoDao
    var configuracaoBaseDao: ConfiguracaoBaseDao

    init{
        this.context = context
        this.configuracaoBaseDao = ConfiguracaoSharedPreferences(context)

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

    fun getConfiguracao(): Configuracao {
        // Tratamento de dados aqui!
        // Delegando ao modelo
        configuracaoDao = getInstanceConfiguracao(context)
        return configuracaoDao.readConfiguracao()
    }

    fun setConfiguracaoBase(configuracaoBase: ConfiguracaoBase) {
        /* Qualquer tratamento necessário aos dados antes de salvá-los
        na fonte de dados escolhida deve ser feita no Service.
        As classes que implementam o DAO devem esconder as peculiaridades
        para acesso a cada fonte de dados diferente e executar apenas as funções
        de CRUD.*/
        // Tratamento de dados aqui!
        // Delegando ao modelo
        configuracaoBaseDao.createOrUpdateConfiguracaoBase(configuracaoBase)
    }


    fun getConfiguracaoBase(): ConfiguracaoBase {
        // Tratamento de dados aqui!
        // Delegando ao modelo
        return configuracaoBaseDao.readConfiguracaoBase()
    }

    fun getInstanceConfiguracao(context: Context): ConfiguracaoDao {
        if(isConfiguracaoNoBanco()){
            return ConfiguracaoSqlite(context)
        }else{
            return ConfiguracaoSharedPreferences(context)
        }
    }

    fun isConfiguracaoNoBanco(): Boolean {
      val configuracaoBase = configuracaoBaseDao.readConfiguracaoBase()
        if(configuracaoBase.baseDeConfiguracao.equals(BaseDeConfiguracao.BANCO)){
            return true
        }
        return false
    }
}