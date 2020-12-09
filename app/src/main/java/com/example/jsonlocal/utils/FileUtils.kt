package com.example.jsonlocal.utils

import android.content.res.AssetManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.example.jsonlocal.JsonFileApp
import com.example.jsonlocal.data.MainWords
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream


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
     fun loadPdfFromAsset(): MutableList<String> {  // метод считывает пдф файлы
        val context = JsonFileApp.applicationContext()
        val pdfFiles: MutableList<String> = ArrayList()
        val assetManager: AssetManager = context.assets

        try {
            for (name in assetManager.list("")!!) {
                // include files which end with pdf only
                if (name.toLowerCase().endsWith("pdf")) {
                    pdfFiles.add(name)
                }
            }
        } catch (ioe: IOException) {
            val mesg = "Could not read files from assets folder"
            Log.e("TAG", mesg)
            Toast.makeText(context, mesg, Toast.LENGTH_LONG).show()
        }
        return pdfFiles
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