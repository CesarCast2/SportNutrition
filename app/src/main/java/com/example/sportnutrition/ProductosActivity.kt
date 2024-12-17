package com.example.sportnutrition

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProductosActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var textView: TextView
    private lateinit var  btnBuy: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_productos)
        databaseHelper = DatabaseHelper(this)
        textView = findViewById(R.id.textViewProductos)
        btnBuy = findViewById(R.id.btnBuy)

        val productos = databaseHelper.showProducts()
        textView.text = productos

        // Configurar el botón Buy
        btnBuy.setOnClickListener {
            // URL de la página de pago
            val paymentUrl = "https://www.mercadopago.com.mx/" // Reemplaza con tu enlace real de pago
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(paymentUrl)
            }
            startActivity(intent)
        }
    }
}