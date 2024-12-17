package com.example.sportnutrition

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Inicializar el helper de base de datos
        databaseHelper = DatabaseHelper(this)
        // Obtener referencias a los campos de entrada
        val edCorreo = findViewById<EditText>(R.id.edCorreo) // Campo para el correo
        val edContraseña = findViewById<EditText>(R.id.edContraseña) // Campo para la contraseña
        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion) // Botón para iniciar sesión
        // Configurar el listener del botón de inicio de sesión
        btnIniciarSesion.setOnClickListener {
            val email = edCorreo.text.toString()
            val password = edContraseña.text.toString()

            // Validar que los campos no estén vacíos
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Verificar las credenciales en la base de datos
                if (databaseHelper.checkUser(email, password)) {
                    // Si las credenciales son correctas, mostrar un mensaje y redirigir
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    // Aquí puedes iniciar la siguiente actividad que desees
                    val intent = Intent(this, Inicio::class.java) // Cambia AnotherActivity por tu actividad de destino
                    startActivity(intent)
                    finish() // Finalizar la actividad actual si no es necesaria
                } else {
                    Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }


        // Obtener el botón y configurar el listener
        val btnRegistro = findViewById<Button>(R.id.btnRegistro)
        btnRegistro.setOnClickListener{
            // Mostrar el mensaje Toast
            Toast.makeText(this, "Botón de registro presionado", Toast.LENGTH_SHORT).show()
            // Crear un Intent para abrir RegistroActivity
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }
    }
}