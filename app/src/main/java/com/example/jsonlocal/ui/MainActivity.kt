package com.example.jsonlocal.ui

import android.R.attr.path
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.jsonlocal.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


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
            tvtitle.text = "123456"
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
}