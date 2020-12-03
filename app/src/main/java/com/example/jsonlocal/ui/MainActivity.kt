package com.example.jsonlocal.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jsonlocal.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val vm by viewModel<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm.loadData()
    }
}