package com.example.jsonlocal.ui

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.example.jsonlocal.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.*


class MainActivity : AppCompatActivity() {

    private val vm by viewModel<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm.loadData()
        setupViewModel()
        setupListeners()

    }



    private fun setupListeners() {
        btnstart.setOnClickListener {
            //tvtitle.text = "123456"
            chekPermisson()
        }
    }

    private fun setupViewModel() {
        vm.pdf.observe(this, Observer {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(it, "application/pdf")
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

            startActivity(intent)
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == EXT_STORAGE_PERMISSION_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "succes", Toast.LENGTH_LONG).show()
            openScreen()
        }
    }

    fun chekPermisson(){  /// для считыван PDF
        if (ContextCompat.checkSelfPermission(
                        this,
                        WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(WRITE_EXTERNAL_STORAGE),
                    EXT_STORAGE_PERMISSION_CODE);
            Log.d(TAG, "After getting permission: " + WRITE_EXTERNAL_STORAGE + " " + ContextCompat.checkSelfPermission(
                    this,
                    WRITE_EXTERNAL_STORAGE));

        } else {
            // We were granted permission already before
            Toast.makeText(this, "succes", Toast.LENGTH_LONG).show()
            openScreen()
        }
    }

    private fun openScreen(){
       val fileName = vm.loadPDF()
        copyFileFromAssets(fileName)
        var uri: Uri? = null
        val file = File(tmpFolder + "/"
                + fileName)

        uri = FileProvider.getUriForFile(this@MainActivity,
                getString(R.string.file_provider_authority),
                file)
        Log.i(TAG, "Launching viewer " + fileName.toString() + " " + file.absolutePath)

        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(uri, "application/pdf")
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        try {
            Log.i(TAG, "Staring pdf viewer activity")
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Log.e(TAG, e.message!!)
        }
    }

    private fun copyFileFromAssets(fileName: String) {  //сохраняет файл внутри локального хранилища
        Log.i(TAG, "Copy file from asset:$fileName")
        val assetManager = this.assets

        // file to copy to from assets
        val cacheFile = File("$tmpFolder/$fileName")
        var `in`: InputStream? = null
        var out: OutputStream? = null
        try {
            Log.d(TAG, "Copying from assets folder to cache folder")
            if (cacheFile.exists()) {
                // already there. Do not copy
                Log.d(TAG, "Cache file exists at:" + cacheFile.getAbsolutePath())
                return
            } else {
                Log.d(TAG, "Cache file does NOT exist at:" + cacheFile.getAbsolutePath())
                // TODO: There should be some error catching/validation etc before proceeding
                `in` = assetManager.open(fileName)
                out = FileOutputStream(cacheFile)
                copyFile(`in`, out)
                `in`.close()
                `in` = null
                out.flush()
                out.close()
                out = null
            }
        } catch (ioe: IOException) {
            Log.e(TAG, "Error in copying file from assets $fileName")
            ioe.printStackTrace()
        }
    }

    @Throws(IOException::class)
    private fun copyFile(`in`: InputStream, out: OutputStream) {
        val buffer = ByteArray(1024)
        var read: Int
        while (`in`.read(buffer).also { read = it } != -1) {
            out.write(buffer, 0, read)
        }
    }


    companion object{
        private const val TAG = "MAIN_ACTIVITY"
        private const val EXT_STORAGE_PERMISSION_CODE = 101
        private val tmpFolder = Environment.getExternalStorageDirectory().path + "/pdfFiles"
    }
}