package com.example.jsonlocal.utils

import android.content.Context
import android.content.res.AssetManager
import android.net.Uri
import android.util.Log
import androidx.core.content.FileProvider
import com.example.jsonlocal.BuildConfig
import com.example.jsonlocal.JsonFileApp
import com.example.jsonlocal.data.MainWords
import com.google.gson.Gson
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.lang.System.`in`
import java.lang.System.out

const val WORDS_PATH = "words.json"   // const val WORDS_PATH = "words.json" -> это  название файла с asset
object FileUtils {

    private fun loadJsonFromAsset(path: String): String? { // функ считыван файла  asset word.json // т.к в этом класе нету контекста!!
        var json: String? = null

        try {
            val from = JsonFileApp.applicationContext().assets.open(path)  // обращаемс к контексту с класса JsonFileApp
            val buffer = ByteArray(from.available())
            from.read(buffer)
            from.close()
            json = String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

        return json
    }


    fun getWordData(): MainWords {   //json
        val json = loadJsonFromAsset(WORDS_PATH)

        val gson = Gson().fromJson(json, MainWords::class.java)
        return gson


    }

    // для чтения PdfFile
     fun loadPdfFromAsset(): Uri {
        val assetManager: AssetManager = JsonFileApp.applicationContext().assets

        var `in`: InputStream? = null
        var out: OutputStream? = null
        val file: File = File(JsonFileApp.applicationContext().filesDir, "abc.pdf")
        val uri = FileProvider.getUriForFile(
            JsonFileApp.applicationContext(),
            BuildConfig.APPLICATION_ID.toString() + ".provider",
            file
        )
        try {
            `in` = assetManager.open("abc.pdf")
            out = JsonFileApp.applicationContext()
                .openFileOutput(file.name, Context.MODE_WORLD_READABLE)
            copyFile(`in`, out)
            `in`.close()
            `in` = null
            out!!.flush()
            out.close()
            out = null
        } catch (e: Exception) {
            Log.e("tag", e.message!!)
        }
        return uri

        Log.d("sdgsdgs", "gsdgdsgdsgs")
     }

    private fun copyFile(inputStream: InputStream, outputStream: OutputStream) {  // pdf
        val buffer = ByteArray(1024)

        var read = 0
        while ((read) != -1) {
            read = inputStream.read(buffer)
            outputStream.write(buffer, 0, read);
        }
    }
}