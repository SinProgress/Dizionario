package com.example.dizionario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_uso_della_parola.*

class ActivityUsoDellaParola : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uso_della_parola)

        usoParola.text = intent.getStringExtra(MainActivity.KEY) // usa intento con cui è stata chiamata
        tornaIndietro.setOnClickListener { finish() }
    }
}