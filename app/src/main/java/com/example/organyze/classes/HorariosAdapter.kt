package com.example.organyze.classes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.organyze.R
import com.example.organyze.database.AppDatabase
import com.example.organyze.database.models.Horario
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HorariosAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var items: List<Horario> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HorariosAdapter.HorariosViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_horario, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){

            is HorariosAdapter.HorariosViewHolder ->{
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setDataSet(horariosMaterias: List<Horario>){
        this.items = horariosMaterias

    }

    class HorariosViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val conteudoHorario = itemView.findViewById<TextView>(R.id.tvConteudoHorario)

        fun bind(horario: Horario){

            GlobalScope.launch {
                val dao = AppDatabase.getInstance(itemView.context.applicationContext).materiaDao()
                val siglaMateria = dao.getAbreviacaoById(horario.materiaId);

                val sigla = siglaMateria
                val horario_inicio = horario.horaInicio
                val horario_fim = horario.horaFim
                val conteudo = "$sigla $horario_inicio - $horario_fim"

                conteudoHorario.text = conteudo
            }
        }
    }

}