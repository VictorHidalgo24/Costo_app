package com.example.costoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.costoapp.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private lateinit var medicionViewModel: MedicionViewModel
    private lateinit var medicionAdapter: MedicionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        medicionAdapter = MedicionAdapter()
        binding.recyclerMediciones.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerMediciones.adapter = medicionAdapter
        binding.recyclerMediciones.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )

        // Usamos requireActivity() para compartir el mismo ViewModel con SecondFragment
        medicionViewModel = ViewModelProvider(requireActivity()).get(MedicionViewModel::class.java)

        medicionViewModel.todasLasMediciones.observe(viewLifecycleOwner) { listaMediciones ->
            medicionAdapter.actualizarLista(listaMediciones)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
