package com.example.jsonlocal.utils

import com.example.jsonlocal.JsonFileApp
import com.example.jsonlocal.data.MainWords
import com.google.gson.Gson
import java.io.IOException

const val WORDS_PATH = "words.json"   // const val WORDS_PATH = "words.json" -> это  название файла с asset
object FileUtils {

    private fun  loadJsonFromAsset(path : String) : String?{  // функ считыван файла  asset word.json

        // т.к в этом класе нету контекста!!
        var json : String? = null

        try {
            val from = JsonFileApp.applicationContext().assets.open(path)  // обращаемс к контексту с класса JsonFileApp
            val buffer = ByteArray(from.available())
            from.read(buffer)
            from.close()
            json = String(buffer)

        } catch (e : IOException){
            e.printStackTrace()
            return null
        }
        return json
    }

    fun getWordData(): MainWords{
        val json = loadJsonFromAsset(WORDS_PATH)
        val gson = Gson().fromJson(json, MainWords::class.java)
        return gson

    }
}