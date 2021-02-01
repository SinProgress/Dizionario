package com.example.dizionario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_uso_della_parola.*

class ActivityUsoDellaParola : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uso_della_parola)

       // usoParola.text = intent.getStringExtra(MainActivity.KEY) // usa intento con cui è stata chiamata
        val listaDefinizioni= intent.getStringArrayExtra(MainActivity.KEY)

        val adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listaDefinizioni!!) //adapter di default per gli array
        // android.R.. : layout già presente nelle librerie android che adapter prende (contesto,layout,array di roba da metterci)
        listaRisultati.adapter=adapter // collega la listview fatta nell'xml con l'adapter qui creato che collega coi dati

        tornaIndietro.setOnClickListener { finish() }
    }
}