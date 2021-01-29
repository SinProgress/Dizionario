package com.example.dizionario
// stampare la prima dros della drp https://dictionaryapi.com/products/api-learners-dictionary
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.* //passo 3 abbreviazioni
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    companion object Data {
         val KEY = "WORD DESCRIPTION"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val queue = Volley.newRequestQueue(this)

        searchButton.setOnClickListener {
            val url = getUrl()
            val richiestaDefinizione = StringRequest(Request.Method.GET,url,
                { risposta ->
                    try {
                        estraiDefinizione(risposta)
                    } catch(exception : Exception) {
                        exception.printStackTrace()
                    }


                },
                { error ->
                    risultato.text=error.toString()
                })
        }
    }

    private fun estraiDefinizione(s : String){
        val arrayJson = JSONArray(s)
        val primoElemento = arrayJson.getJSONObject(0)
        val primoLivello = primoElemento.getJSONArray("dros")
        val secondoLivello = primoLivello.getJSONObject(0) //recupero il primo oggetto dell'array dros
        val definizioneScelta = secondoLivello.getString("drp") //drp=chiave con cui è registrata la proprietà

        val intent = Intent(this, ActivityUsoDellaParola::class.java)
        intent.putExtra(KEY,definizioneScelta.toString())
        startActivity(intent)

    }

    private fun getUrl() : String{
        val word = campoRicerca.text
        val apiKey = "de0ddda2-c2f1-4c70-8e61-1054ac1d30ea"
        val url =
            "https://www.dictionaryapi.com/api/v3/references/learners/json/$word?key=$apiKey"

        return url
    }
}
