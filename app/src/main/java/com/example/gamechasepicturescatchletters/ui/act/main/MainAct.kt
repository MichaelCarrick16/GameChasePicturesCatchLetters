package com.example.gamechasepicturescatchletters.ui.act.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.gamechasepicturescatchletters.R
import com.example.gamechasepicturescatchletters.ui.fragment.menu.MenuAndDetailViewModel

class MainAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var menuAndDetailViewModel : MenuAndDetailViewModel = ViewModelProvider(this).get(MenuAndDetailViewModel::class.java)
        menuAndDetailViewModel.getListText(this)

    }
}