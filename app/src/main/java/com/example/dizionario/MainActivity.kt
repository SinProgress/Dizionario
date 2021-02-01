package com.example.dizionario
// stampare la prima dros della drp https://dictionaryapi.com/products/api-learners-dictionary
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
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
                        estraiDefinizioni(risposta)
                    } catch(exception : Exception) {
                        exception.printStackTrace()
                    }


                },
                { error ->
                    risultato.text=error.toString()
                })
            queue.add(richiestaDefinizione)
        }
    }

    private fun estraiDefinizioni(s : String){
        val arrayJson = JSONArray(s)
        val primoElemento = arrayJson.getJSONObject(0)
        val primoLivello = primoElemento.getJSONArray("dros")
      //  val secondoLivello = primoLivello.getJSONObject(0) //recupero il primo oggetto dell'array dros
      //  val definizioneScelta = secondoLivello.getString("drp") //drp=chiave con cui è registrata la proprietà
        var listaDefinizioni: Array<String> = Array(primoLivello.length()){""}
        for(i in 0 until primoLivello.length()) {
            val secondoLivello = primoLivello.getJSONObject(i) //recupero dell'oggetto in pos i dell'array dros
            listaDefinizioni[i]=secondoLivello.getString("drp")// mette la definizione nell'array
        }
      //  val adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listaDefinizioni) //adapter di default per gli array
        // android.R.. : layout già presente nelle librerie android che adapter prende (contesto,layout,array di roba da metterci)
     //   listaRisultati.adapter=adapter // collega la listview fatta nell'xml con l'adapter qui creato che collega coi dati

        val intent = Intent(this, ActivityUsoDellaParola::class.java)
        intent.putExtra(KEY,listaDefinizioni)
        // il putExtra non prende tutto, o primitive o array di primitive e basta.
        startActivity(intent)

    }

    private fun estraiDefinizione(s : String){
        val arrayJson = JSONArray(s)
        val primoElemento = arrayJson.getJSONObject(0)
        val primoLivello = primoElemento.getJSONArray("dros")
        val secondoLivello = primoLivello.getJSONObject(0) //recupero il primo oggetto dell'array dros
        val definizioneScelta = secondoLivello.getString("drp") //drp=chiave con cui è registrata la proprietà

        val intent = Intent(this, ActivityUsoDellaParola::class.java)
        intent.putExtra(KEY,definizioneScelta)
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
