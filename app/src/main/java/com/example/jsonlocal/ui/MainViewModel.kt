package com.example.jsonlocal.ui

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jsonlocal.utils.FileUtils

class MainViewModel : ViewModel() {

    val pdf = MutableLiveData<Uri>()

    fun loadData() {
        val data = FileUtils.getWordData()  // для json
        Log.d("sgdsg", "gsdggdsg")

        val data1 = FileUtils.loadPdfFromAsset()  //для pdf
        Log.d("sgdsg", "gsdggdsg")
        pdf.postValue(data1)



    }
}