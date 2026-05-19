package com.example.costoapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicionDao {

    @Insert
    suspend fun insertarMedicion(medicion: Medicion)

    // Ordenamos DESC para mostrar la lectura mas reciente primero
    @Query("SELECT * FROM mediciones ORDER BY fecha DESC")
    fun obtenerTodasLasMediciones(): Flow<List<Medicion>>

    @Query("DELETE FROM mediciones WHERE id = :id")
    suspend fun eliminarMedicion(id: Int)
}
