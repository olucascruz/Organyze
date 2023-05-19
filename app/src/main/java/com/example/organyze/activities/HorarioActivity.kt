package com.example.organyze.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.organyze.R
import com.example.organyze.classes.HorariosAdapter
import com.example.organyze.classes.MateriasAdapter
import com.example.organyze.database.AppDatabase
import com.example.organyze.database.models.Horario
import com.google.android.material.button.MaterialButtonToggleGroup
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HorarioActivity : AppCompatActivity() {

    private lateinit var horariosAdapter: HorariosAdapter
    private lateinit var horarios: List<Horario>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()


        //Logica dos botoes de mudança de pagina
        val  btMudancaDePagina = findViewById<MaterialButtonToggleGroup>(R.id.paginas);
        btMudancaDePagina.addOnButtonCheckedListener{btMudancaDePagina, checkedId, isCheked ->
            if(isCheked){
                when(checkedId){
                    R.id.btPaginaHorario -> {
                        startActivity(Intent(this, HorarioActivity::class.java))
                        this.finish()
                    }
                    R.id.btPaginaMaterias -> {
                        startActivity(Intent(this, MateriasActivity::class.java))
                        this.finish()
                    }
                    R.id.btPaginaAnalise ->{
                        startActivity(Intent(this, AnaliseActivity::class.java))
                        this.finish()
                    }
                }

            }

        }


        //Logica dos botões de dias da semana
        val toggleButtonGroup = findViewById<MaterialButtonToggleGroup>(R.id.diasDaSemana)
        toggleButtonGroup.addOnButtonCheckedListener{ toggleButtonGroup, checkedId, isChecked ->
            if(isChecked){
                when(checkedId){

                        R.id.btSeg -> GetHorarios("segunda")

                        R.id.btTer -> GetHorarios("terça")

                        R.id.btQua -> GetHorarios("quarta")

                        R.id.btQui -> GetHorarios("quinta")

                        R.id.btSex -> GetHorarios("sexta")

                }
            }
        }
    }

        fun GetHorarios(dia: String){
            GlobalScope.launch {
                val dao = AppDatabase.getInstance(applicationContext).horarioDao()
                this@HorarioActivity.horarios = dao.getHorarioByDay(dia)
                runOnUiThread {
                    horariosAdapter.setDataSet(this@HorarioActivity.horarios)
                    horariosAdapter.notifyDataSetChanged()
                }
            }
        }

        private fun initRecyclerView() {
            this.horariosAdapter = HorariosAdapter()

            val listaMaterias = findViewById<RecyclerView>(R.id.rvHorarios)

            listaMaterias.layoutManager = LinearLayoutManager(this@HorarioActivity)
            listaMaterias.adapter = this.horariosAdapter

    }
}