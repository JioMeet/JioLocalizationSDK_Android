package com.jio.jiolocalizationsdkandroid

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jio.jiolocalizationsdkandroid.network.model.Completion
import com.jio.jiolocalizationsdkandroid.network.model.Server
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private lateinit var jioLocalization : JioLocalization
    var isLoading by mutableStateOf(true)
        private set

    fun initJioTranslate(context: Context) {
        val builder = JioLocalization.Builder()
        //enter the api key
        val apiKey = ""

        builder.apply {
            init(context, Server.SIT.baseURL)
            setApiKey(apiKey)
        }

        jioLocalization = builder.build()
        viewModelScope.launch {
            jioLocalization.loadLocalization("en"){ result ->
                isLoading = false
                when (result) {
                    is Completion.Success -> {
                        Log.d("TAG", "Localization successfully loaded: ", )
                    }
                    is Completion.Error -> {
                        Log.e("TAG", "Some error while loading localization: " + result.error)
                    }
                }
            }
        }
    }

    fun getLocalizedString(key:String,value:Map<String,String> = emptyMap(), defaultValue:String = ""):String{
        return jioLocalization.getLocalizedString(key, value, defaultValue = defaultValue)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MainViewModel()
            }
        }
    }
}