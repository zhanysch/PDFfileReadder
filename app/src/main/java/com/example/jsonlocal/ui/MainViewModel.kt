package com.example.jsonlocal.ui

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jsonlocal.R
import com.example.jsonlocal.utils.FileUtils
import java.lang.StringBuilder

class MainViewModel(private val context: Context) : ViewModel() {

    val pdf = MutableLiveData<Uri>()

    fun loadData() {
        val data = FileUtils.getWordData()  // для json
        Log.d("sgdsg", "gsdggdsg")
    }

    fun loadPDF(): String {
        return FileUtils.loadPdfFromAsset()[0]   // вызов функции из classa FileUtils и метода
    }

    fun replaceWords(message : String): String{

        val result = message.split(" ")    //разделяет слова и делает список по пробелу
        if (result.size == 1){
            return  message
        }
        val builder = StringBuilder()

        for (i in result.size -1 downTo 0 step 1){
            builder.append(result[i])
            if (i !=0) builder.append(" ")
        }

        return builder.toString()
    }

    fun loadWord(): String {  // для мокито
          return context.getString(R.string.app_name)
    }
}