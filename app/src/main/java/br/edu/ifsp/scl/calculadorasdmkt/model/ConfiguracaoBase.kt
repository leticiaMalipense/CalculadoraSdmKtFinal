package br.edu.ifsp.scl.calculadorasdmkt.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


enum class BaseDeConfiguracao { SHARED_PEFERENCES, BANCO }

@Parcelize
data class ConfiguracaoBase(var baseDeConfiguracao: BaseDeConfiguracao = BaseDeConfiguracao.SHARED_PEFERENCES): Parcelable