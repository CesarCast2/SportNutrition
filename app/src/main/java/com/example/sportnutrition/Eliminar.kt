package com.example.sportnutrition

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Eliminar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_eliminar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Referencia a los elementos de la interfaz
        val edEliminarId = findViewById<EditText>(R.id.edEliminarId)
        val btnQuitar = findViewById<Button>(R.id.btnQuitar)

        // Instancia de DatabaseHelper
        val dbHelper = DatabaseHelper(this)

        //Evento onclick del boton de quitar o eliminar
        btnQuitar.setOnClickListener{
            val idProducto = edEliminarId.text.toString().trim()
             //Validar que el campo id no este vacio
            if (idProducto.isEmpty()){
                Toast.makeText(this, "Favor de ingresar el id del producto a eliminar", Toast.LENGTH_SHORT).show()
            }else{
                //Convertir el id a numero entero
                val id = idProducto.toIntOrNull()
                if (id != null){
                    //Eliminar el producto de la base de datos
                    val rowsDeleted = dbHelper.deleteProduct(id)
                    if (rowsDeleted > 0){
                        Toast.makeText(this, "Producto eliminado exitosamente", Toast.LENGTH_SHORT).show()

                        // Limpiar el campo del id
                        edEliminarId.text.clear()
                    }else{
                        Toast.makeText(this, "No se encontró un producto con ese ID", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, "El ID debe ser un número entero válido", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}