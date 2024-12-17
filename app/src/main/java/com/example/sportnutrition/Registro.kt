package com.example.sportnutrition

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Registro : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)

        // Inicializar el helper de base de datos
        databaseHelper = DatabaseHelper(this)

        // Configurar los insets para los márgenes de la ventana
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }

        // Obtener referencias a los campos de entrada
        val edUsuario = findViewById<EditText>(R.id.edUsuario) // Nombre de usuario
        val edPassword = findViewById<EditText>(R.id.edPassword) // Contraseña
        val edEmail = findViewById<EditText>(R.id.edEmail) // Campo para el correo
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)

        // Configurar el listener del botón de registro
        btnRegistrar.setOnClickListener {
            val username = edUsuario.text.toString()
            val password = edPassword.text.toString()
            val email = edEmail.text.toString() // Obtener el correo

            // Validar que los campos no estén vacíos
            if (username.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty()) {
                // Registrar el usuario en la base de datos
                databaseHelper.addUser(username, password, email) // Pasar el correo
                Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()

                // Limpiar los campos
                edUsuario.text.clear()
                edPassword.text.clear()
                edEmail.text.clear() // Limpiar el campo de correo
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}