package com.example.sportnutrition

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Modificar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_modificar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Referencias a los elementos de la interfaz
        val edModId = findViewById<EditText>(R.id.edModId)
        val edModNombre = findViewById<EditText>(R.id.edModNombre)
        val edModPrecio = findViewById<EditText>(R.id.edModPrecio)
        val btnModifica = findViewById<Button>(R.id.btnModifica)

        //Instancia database helper
        val dbHelper = DatabaseHelper(this)

        //Configurar el evento onclick del boton de modificar
        btnModifica.setOnClickListener{
            val idProducto = edModId.text.toString().trim()
            val nombreProducto = edModNombre.text.toString().trim()
            val precioProducto = edModPrecio.text.toString().trim()

            //Validar que los campos no esten vacios
            if (idProducto.isEmpty() || nombreProducto.isEmpty() || precioProducto.isEmpty()){
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }else{
                //convertir el Id y el precio a sus respectivos tipos
                val id = idProducto.toIntOrNull()
                val precio = precioProducto.toDoubleOrNull()
                if (id !=null && precio !=null){
                    //Modificar el producto en la base de datos
                    val rowsUpdated = dbHelper.updateProduct(id, nombreProducto, precio)
                    if (rowsUpdated > 0){
                        Toast.makeText(this, "Producto modificado exitosamente", Toast.LENGTH_SHORT).show()
                        //Limpiar campos
                        edModId.text.clear()
                        edModNombre.text.clear()
                        edModPrecio.text.clear()
                    }else{
                        Toast.makeText(this, "No se encontró un producto con ese ID", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, "El ID debe ser un número entero y el precio un número válido", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}