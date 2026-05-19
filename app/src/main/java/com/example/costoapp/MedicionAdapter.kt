package com.example.costoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MedicionAdapter : RecyclerView.Adapter<MedicionAdapter.MedicionViewHolder>() {

    private var listaMediciones: List<Medicion> = emptyList()

    class MedicionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconoMedidor: ImageView = itemView.findViewById(R.id.iconoMedidor)
        val textoTipo: TextView = itemView.findViewById(R.id.textoTipoMedidor)
        val textoValor: TextView = itemView.findViewById(R.id.textoValorMedidor)
        val textoFecha: TextView = itemView.findViewById(R.id.textoFecha)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicionViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_medicion, parent, false)
        return MedicionViewHolder(vista)
    }

    override fun onBindViewHolder(holder: MedicionViewHolder, position: Int) {
        val medicion = listaMediciones[position]

        holder.textoTipo.text = medicion.tipoMedidor
        holder.textoValor.text = String.format("%.0f", medicion.valorMedidor)
        holder.textoFecha.text = medicion.fecha

        val iconoRes = when (medicion.tipoMedidor) {
            "LUZ" -> R.drawable.ic_luz
            "GAS" -> R.drawable.ic_gas
            else  -> R.drawable.ic_agua
        }
        holder.iconoMedidor.setImageResource(iconoRes)
    }

    override fun getItemCount(): Int = listaMediciones.size

    fun actualizarLista(nuevaLista: List<Medicion>) {
        listaMediciones = nuevaLista
        notifyDataSetChanged()
    }
}
