package com.example.sportnutrition

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class Inicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inicio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }
        //Configurar los image view con los ids especificos
        val imgCbum = findViewById<ImageView>(R.id.imgCbum)
        val imgPsicotic = findViewById<ImageView>(R.id.imgPsicotic)
        val imgRyse = findViewById<ImageView>(R.id.imgRyse)

        //Listener para el producto Cbum
        imgCbum.setOnClickListener{
            val intent = Intent(this, Descripcion::class.java)
            intent.putExtra("descripcion", "Este pre entreno tiene 300 mg de cafeina, 5500 mg de cintrulina, 6000 mg de betalanina, su precio es de $800")
            intent.putExtra("imagesResId", R.drawable.cbum)
            startActivity(intent)
        }
        //Listener para el producto Psicotic
        imgPsicotic.setOnClickListener{
            val intent = Intent(this, Descripcion::class.java)
            intent.putExtra("descripcion", "420 mg de cafeina, 4000 mg de cintrulina, 5000 mg de betalanina, 5 mg alpha yoimbina, Su precio es de $450")
            intent.putExtra("imagesResId", R.drawable.sicotic)
            startActivity(intent)
        }
        //Listener para el producto Ryse
        imgRyse.setOnClickListener{
            val intent = Intent(this, Descripcion::class.java)
            intent.putExtra("descripcion", "400 mg de cafeina, 6000 mg de cintrulina, 4000 mg de betalanina, Su precio es de $750")
            intent.putExtra("imagesResId", R.drawable.ryse)
            startActivity(intent)
        }
        //Configurar el boton de cerrar sesion
        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)
        btnCerrarSesion.setOnClickListener{
            //intent para regresar a la pantalla de inicio
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish() //Cierra el activity actual
        }
        //Configurar el boton de ver productos
        val btnVerProductos = findViewById<Button>(R.id.btnVerProductos)
        btnVerProductos.setOnClickListener {
            val intent = Intent(this, ProductosActivity::class.java)
            startActivity(intent)
        }
        //Configurar botones de agregar, modificar y eliminar
        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        val btnModificar = findViewById<Button>(R.id.btnModificar)
        val btnEliminar = findViewById<Button>(R.id.btnEliminar)

        btnAgregar.setOnClickListener{
            val intent = Intent(this, Agregar::class.java)
            startActivity(intent)
        }
        btnModificar.setOnClickListener{
            val intent = Intent(this, Modificar::class.java)
            startActivity(intent)
        }
        btnEliminar.setOnClickListener{
            val intent = Intent(this, Eliminar::class.java)
            startActivity(intent)
        }
    }
}

