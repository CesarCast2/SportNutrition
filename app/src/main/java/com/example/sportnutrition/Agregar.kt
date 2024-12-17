package com.example.sportnutrition

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Agregar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Referencias a los elementos de la interfaz
        val edNombreProducto = findViewById<EditText>(R.id.edNombreProducto)
        val edPrecio = findViewById<EditText>(R.id.edPrecio)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        //Instanciar el database helper
        val dbHelper = DatabaseHelper(this)

        //Configuracion del evento click del boton de guardar
        btnGuardar.setOnClickListener{
            val nombreProducto = edNombreProducto.text.toString().trim()
            val precioProducto = edPrecio.text.toString().trim()

            //Validar que los campos no esten vacios
            if (nombreProducto.isEmpty() || precioProducto.isEmpty()){
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            }else{
                //Convertir el precio a tipo double
                val precio = precioProducto.toDoubleOrNull()
                if (precio != null){
                    //Guardar el producto en la base de datos
                    dbHelper.addProduct(nombreProducto, precio)
                    Toast.makeText(this, "Datos guardados exitosamente", Toast.LENGTH_SHORT).show()

                //Limpiar los campos
                    edNombreProducto.text.clear()
                    edPrecio.text.clear()
                }else{
                    Toast.makeText(this, "El precio debe ser un número válido", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}