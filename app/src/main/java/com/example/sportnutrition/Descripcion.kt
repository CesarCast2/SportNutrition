package com.example.sportnutrition

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Descripcion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_descripcion)

        //Configuracion de insetns para el diseño
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //obtener extras enviados desde el intent
        val descripcion = intent.getStringExtra("descripcion")
        val imageResId = intent.getIntExtra("imagesResId", 0)

        // Referencias a los elementos de la interfaz
        val productImage = findViewById<ImageView>(R.id.productImage)
        val productDescription = findViewById<TextView>(R.id.productDescription)

        //Establece la imagen y dexcripcion de la interfaz
        if(imageResId !=0){ //Asegurar que hay una imagen valida
            productImage.setImageResource(imageResId)
        }
        productDescription.text = descripcion
    }
}