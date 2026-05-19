package com.example.costoapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MedicionViewModel(application: Application) : AndroidViewModel(application) {

    private val medicionDao: MedicionDao
    val todasLasMediciones: LiveData<List<Medicion>>

    init {
        val database = MedicionDatabase.obtenerDatabase(application)
        medicionDao = database.medicionDao()
        todasLasMediciones = medicionDao.obtenerTodasLasMediciones().asLiveData()
    }

    fun insertarMedicion(medicion: Medicion) {
        viewModelScope.launch {
            medicionDao.insertarMedicion(medicion)
        }
    }

    fun eliminarMedicion(id: Int) {
        viewModelScope.launch {
            medicionDao.eliminarMedicion(id)
        }
    }
}
