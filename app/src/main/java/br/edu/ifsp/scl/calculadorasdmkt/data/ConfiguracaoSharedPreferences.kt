import android.content.Context
import android.content.SharedPreferences
import br.edu.ifsp.scl.calculadorasdmkt.model.Configuracao
import br.edu.ifsp.scl.calculadorasdmkt.data.ConfiguracaoDao
import br.edu.ifsp.scl.calculadorasdmkt.model.ConfiguracaoGeral
import br.edu.ifsp.scl.calculadorasdmkt.data.ConfiguracaoGeralDao
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONObject

class ConfiguracaoSharedPreferences(context: Context): ConfiguracaoDao,
    ConfiguracaoGeralDao {

    // Constantes
    companion object{
        val TAG_CONFIGURACAO = "configuracoes"
        val TAG_CONFIGURACAO_GERAL = "configuracoes_gerais"

        val NOME_ARQUIVO = "configuracoes"
        val NOME_ARQUIVO_GERAL = "configuracoes_gerais"
        val MODO_ARQUIVO = Context.MODE_PRIVATE
    }
    val sharedPreferences: SharedPreferences
    val sharedPreferencesGeral: SharedPreferences
    val gson: Gson
    init{
        sharedPreferences = context.getSharedPreferences(NOME_ARQUIVO,
            MODO_ARQUIVO)
        sharedPreferencesGeral = context.getSharedPreferences(NOME_ARQUIVO_GERAL,
            MODO_ARQUIVO)
        gson = GsonBuilder().create()
    }

    override fun createOrUpdateConfiguracao(configuracao: Configuracao) {
        val configuracaoJson = JSONObject(gson.toJson(configuracao))

        val spEditor: SharedPreferences.Editor = sharedPreferences.edit()

        spEditor.putString(TAG_CONFIGURACAO, configuracaoJson.toString())
        spEditor.commit()

    }

    override fun createOrUpdateConfiguracaoGeral(configuracao: ConfiguracaoGeral) {
        val configuracaoJson = JSONObject(gson.toJson(configuracao))

        val spEditor: SharedPreferences.Editor = sharedPreferencesGeral.edit()

        spEditor.putString(TAG_CONFIGURACAO_GERAL, configuracaoJson.toString())
        spEditor.commit()

    }

    override fun readConfiguracao(): Configuracao {
        val configuracaoString = sharedPreferences.getString(TAG_CONFIGURACAO, "")
        return if (configuracaoString != "")

            gson.fromJson(configuracaoString, Configuracao::class.java)
        else
            Configuracao()
    }

    override fun readConfiguracaoGeral(): ConfiguracaoGeral {
        val configuracaoString = sharedPreferencesGeral.getString(TAG_CONFIGURACAO_GERAL, "")
        return if (configuracaoString != "")

            gson.fromJson(configuracaoString, ConfiguracaoGeral::class.java)
        else
            ConfiguracaoGeral()
    }
}