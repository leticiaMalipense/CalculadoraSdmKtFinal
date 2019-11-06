import android.content.Context
import android.content.SharedPreferences
import br.edu.ifsp.scl.calculadorasdmkt.model.Configuracao
import br.edu.ifsp.scl.calculadorasdmkt.data.ConfiguracaoDao
import br.edu.ifsp.scl.calculadorasdmkt.data.ConfiguracaoBaseDao
import br.edu.ifsp.scl.calculadorasdmkt.model.ConfiguracaoBase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONObject

class ConfiguracaoSharedPreferences(context: Context): ConfiguracaoDao,
    ConfiguracaoBaseDao {

    // Constantes
    companion object{
        val TAG_CONFIGURACAO = "configuracoes"
        val TAG_CONFIGURACAO_BASE = "configuracao_base"

        val NOME_ARQUIVO = "configuracoes"
        val NOME_ARQUIVO_BASE = "configuracao_base"
        val MODO_ARQUIVO = Context.MODE_PRIVATE
    }
    val sharedPreferences: SharedPreferences
    val sharedPreferencesBase: SharedPreferences
    val gson: Gson
    init{
        sharedPreferences = context.getSharedPreferences(NOME_ARQUIVO,
            MODO_ARQUIVO)
        sharedPreferencesBase = context.getSharedPreferences(NOME_ARQUIVO_BASE,
            MODO_ARQUIVO)
        gson = GsonBuilder().create()
    }

    override fun createOrUpdateConfiguracao(configuracao: Configuracao) {
        val configuracaoJson = JSONObject(gson.toJson(configuracao))

        val spEditor: SharedPreferences.Editor = sharedPreferences.edit()

        spEditor.putString(TAG_CONFIGURACAO, configuracaoJson.toString())
        spEditor.commit()

    }

    override fun readConfiguracao(): Configuracao {
        val configuracaoString = sharedPreferences.getString(TAG_CONFIGURACAO, "")
        return if (configuracaoString != "")

            gson.fromJson(configuracaoString, Configuracao::class.java)
        else
            Configuracao()
    }

    override fun createOrUpdateConfiguracaoBase(configuracao: ConfiguracaoBase) {
        val configuracaoJson = JSONObject(gson.toJson(configuracao))

        val spEditor: SharedPreferences.Editor = sharedPreferencesBase.edit()

        spEditor.putString(TAG_CONFIGURACAO_BASE, configuracaoJson.toString())
        spEditor.commit()

    }

    override fun readConfiguracaoBase(): ConfiguracaoBase {
        val configuracaoString = sharedPreferencesBase.getString(TAG_CONFIGURACAO_BASE, "")
        return if (configuracaoString != "")

            gson.fromJson(configuracaoString, ConfiguracaoBase::class.java)
        else
            ConfiguracaoBase()
    }
}