package com.darwinlab.musiccats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            //Используем Scope уровня приложения т.к. Activity = APP в данном случае
            //Запускаем в основном потоке ибо работаем с View
            GlobalScope.launch(Dispatchers.Main) {
                val result = getPlayList()
                textView.text = result;
            }
        }
    }

}