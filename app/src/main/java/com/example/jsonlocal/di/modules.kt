package com.example.jsonlocal.di

import com.example.jsonlocal.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



val viewModelModule = module {
    viewModel { MainViewModel() }
}

val appModules = listOf(viewModelModule)