package com.example.proyectospeaker

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener  {
    var speaker: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Guardando el texto que ingresa en el input

        // aplicando el evento click al botón
        findViewById<Button>(R.id.sendButton).setOnClickListener {
            var text = findViewById<EditText>(R.id.examenInput).text.toString()
            // imprimiendo en consola logcat
            if(text.isNotEmpty()) {
                //Log.i("evento click", "$text")
                Log.i("evento","$text")
            }

            speaker?.speak(text, TextToSpeech.QUEUE_FLUSH,null)
        }

        speaker = TextToSpeech(this, this)

    }

    override fun onInit(status: Int) {
    if(status == TextToSpeech.SUCCESS){
        speaker?.setLanguage(Locale("es","ES"))
        //speaker?.setLanguage(Locale("it","IT"))
        //speaker?.setLanguage(Locale("fr","FR"))
        //speaker?.setLanguage(Locale("en","US"))
findViewById<TextView>(R.id.texttospeech).text = "Servicio disponible"
    }else{
        findViewById<TextView>(R.id.texttospeech).text = "Servicio no disponible"
    }
       findViewById<ProgressBar>(R.id.progressbar).visibility = View.GONE
    }


}





