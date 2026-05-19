package com.example.costoapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.costoapp.databinding.FragmentSecondBinding
import java.util.Calendar

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private lateinit var medicionViewModel: MedicionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        medicionViewModel = ViewModelProvider(requireActivity()).get(MedicionViewModel::class.java)

        binding.editTextFecha.setOnClickListener { mostrarDatePicker() }
        binding.botonRegistrar.setOnClickListener { guardarMedicion() }
    }

    private fun mostrarDatePicker() {
        val calendario = Calendar.getInstance()

        DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                // month es 0-indexed en Calendar, por eso se suma 1
                val fechaFormateada = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
                binding.editTextFecha.setText(fechaFormateada)
            },
            calendario.get(Calendar.YEAR),
            calendario.get(Calendar.MONTH),
            calendario.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun guardarMedicion() {
        val valorTexto = binding.editTextMedidor.text.toString()
        val fecha = binding.editTextFecha.text.toString()

        if (valorTexto.isEmpty() || fecha.isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.campo_vacio), Toast.LENGTH_SHORT).show()
            return
        }

        val tipoMedidor = when {
            binding.radioLuz.isChecked -> "LUZ"
            binding.radioGas.isChecked -> "GAS"
            else -> "AGUA"
        }

        val nuevaMedicion = Medicion(
            tipoMedidor = tipoMedidor,
            valorMedidor = valorTexto.toDouble(),
            fecha = fecha
        )

        medicionViewModel.insertarMedicion(nuevaMedicion)
        Toast.makeText(requireContext(), getString(R.string.registro_guardado), Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
