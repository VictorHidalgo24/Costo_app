package com.example.costoapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mediciones")
data class Medicion(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val tipoMedidor: String,   // "AGUA", "LUZ" o "GAS"
    val valorMedidor: Double,
    val fecha: String          // formato yyyy-MM-dd
)
