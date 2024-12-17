package com.example.sportnutrition

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "sportnutrition.db" // Nombre de la base de datos
        private const val DATABASE_VERSION = 3 // Versión de la base de datos

        // Tablas
        private const val TABLE_USERS = "users"
        private const val TABLE_PRODUCTS = "products"

        // Columnas de la tabla usuarios
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "usuario"
        private const val COLUMN_CORREO = "Correo"
        private const val COLUMN_PASSWORD = "contraseña"

        // Columnas de la tabla products
        private const val COLUMN_PRODUCT_ID = "Id"
        private const val COLUMN_PRODUCT_NAME = "NombreProducto"
        private const val COLUMN_PRODUCT_PRICE = "Precio"


    }

    override fun onCreate(db: SQLiteDatabase) {
        // Crear tabla de usuarios
        val createUsersTable = ("CREATE TABLE $TABLE_USERS ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_USERNAME TEXT NOT NULL, "
                + "$COLUMN_PASSWORD TEXT NOT NULL, "
                + "$COLUMN_CORREO TEXT NOT NULL);") // Incluir la nueva columna
        db.execSQL(createUsersTable)

        // Crear tabla de productos
        val createProductsTable = ("CREATE TABLE $TABLE_PRODUCTS ("
                + "$COLUMN_PRODUCT_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_PRODUCT_NAME TEXT NOT NULL, "
                + "$COLUMN_PRODUCT_PRICE REAL NOT NULL);")
        db.execSQL(createProductsTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Eliminar tablas si ya existen
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCTS")
        onCreate(db) // Crear de nuevo las tablas
    }

    // Método para agregar un usuario
    fun addUser(username: String, password: String, email: String) { // Aceptar correo electrónico
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_USERNAME, username)
        values.put(COLUMN_PASSWORD, password)
        values.put(COLUMN_CORREO, email) // Agregar correo electrónico
        db.insert(TABLE_USERS, null, values)
        db.close()
    }

    fun checkUser(email: String, password: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM users WHERE Correo = ? AND contraseña = ?",
            arrayOf(email, password)
        )
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists // Devuelve true si las credenciales son correctas
    }

    fun addProduct(nombre: String, precio: Double) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("NombreProducto", nombre)
        values.put("Precio", precio)
        db.insert("products", null, values)
        db.close()
    }

    fun updateProduct(id: Int, nombre: String, precio: Double): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("NombreProducto", nombre)
        values.put("Precio", precio)

        // Actualizar la fila donde el ID coincide
        val rowsUpdated = db.update("products", values, "Id = ?", arrayOf(id.toString()))
        db.close()
        return rowsUpdated
    }

    fun deleteProduct(id: Int): Int {
        val db = this.writableDatabase
        // Eliminar la fila donde el ID coincide
        val rowsDeleted = db.delete("products", "Id = ?", arrayOf(id.toString()))
        db.close()
        return rowsDeleted
    }
    // Método para mostrar los productos
    fun showProducts(): String {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_PRODUCTS", null)
        var texto = ""

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_ID))
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_NAME))
                val precio = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_PRICE))

                texto += "ID: $id, Nombre: $nombre, Precio: $precio\n"
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return texto
    }

}
