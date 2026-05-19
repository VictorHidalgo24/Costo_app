package com.example.costoapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Medicion::class], version = 1)
abstract class MedicionDatabase : RoomDatabase() {

    abstract fun medicionDao(): MedicionDao

    companion object {
        // @Volatile garantiza que INSTANCIA sea visible entre hilos inmediatamente
        @Volatile
        private var INSTANCIA: MedicionDatabase? = null

        fun obtenerDatabase(context: Context): MedicionDatabase {
            return INSTANCIA ?: synchronized(this) {
                val instancia = Room.databaseBuilder(
                    context.applicationContext,
                    MedicionDatabase::class.java,
                    "mediciones_database"
                ).build()
                INSTANCIA = instancia
                instancia
            }
        }
    }
}
