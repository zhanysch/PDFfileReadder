package com.example.jsonlocal.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.jsonlocal.utils.FileUtils

class MainViewModel : ViewModel() {

      fun loadData() {
        val data = FileUtils.getWordData()
        Log.d("sgdsg", "gsdggdsg")
    }
}