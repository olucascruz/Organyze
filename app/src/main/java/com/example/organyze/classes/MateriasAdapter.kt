package com.example.organyze.classes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.organyze.R
import com.example.organyze.database.models.Materia


class MateriasAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Materia> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MateriasViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_materia, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){

            is MateriasViewHolder ->{
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {

        return items.size
    }

    fun setDataSet(materias: List<Materia>){
        this.items = materias
    }

    class MateriasViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val nameMateria = itemView.findViewById<TextView>(R.id.tvNomeMateria)

        fun bind(materia: Materia){
            nameMateria.text = materia.nome
        }
    }

}