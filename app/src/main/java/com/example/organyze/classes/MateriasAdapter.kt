package com.example.organyze.classes

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.organyze.R
import com.example.organyze.activities.MateriasActivity
import com.example.organyze.database.AppDatabase
import com.example.organyze.database.models.Materia


class MateriasAdapter(
    private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Materia> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MateriasViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_materia, parent, false
            ),
            context
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MateriasViewHolder ->{
                holder.bind(items[position], position, this)

            }
        }

    }

    override fun getItemCount(): Int {

        return items.size
    }

    fun setDataSet(materias: List<Materia>){
        this.items = materias
    }

    class MateriasViewHolder constructor(itemView: View, context: Context): RecyclerView.ViewHolder(itemView) {

        private val nameMateria = itemView.findViewById<TextView>(R.id.tvNomeMateria)
        val bd = AppDatabase.getInstance(context)
        val materiaDao = bd.materiaDao()
        val messageDelete = Toast.makeText(context, "Item Deletado", Toast.LENGTH_SHORT)

        fun bind(materia: Materia, position: Int, adapter: MateriasAdapter){
            nameMateria.text = materia.nome

            nameMateria.setOnLongClickListener {
                Log.d("MinhaTag", "Esta é uma mensagem de depuração")
                (itemView.context as MateriasActivity).runOnUiThread {
                    adapter.items = adapter.items.toMutableList().apply {
                        removeAt(position)
                    }
                    adapter.notifyItemRemoved(position) // Remove o item da lista e atualiza o RecyclerView
                    messageDelete.show()
                }
                Thread{
                    try{
                        materiaDao.delete(materia)
                    } catch (e: Exception) {
                        Log.e("MinhaTag", "Erro ao deletar", e)
                    }
                }.start()
                true // Indica que o evento foi consumido
            }
        }
    }

}