package com.example.organyze.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.organyze.R
import com.example.organyze.classes.MateriasAdapter
import com.example.organyze.database.AppDatabase
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MateriasActivity : AppCompatActivity() {

    private lateinit var materiaAdapter: MateriasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materias)

        val btAdicionarMateria = findViewById<FloatingActionButton>(R.id.btSubmitMateria)

        btAdicionarMateria.setOnClickListener {
            startActivity(Intent(this, AddMateriaActivity::class.java))


        }
        val  btMudancaDePagina = findViewById<MaterialButtonToggleGroup>(R.id.paginas);
        btMudancaDePagina.addOnButtonCheckedListener{_, checkedId, isCheked ->
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

        initRecyclerView()
        addDataSource()
    }

    private fun addDataSource(){
        val materiaDao = AppDatabase.getInstance(this).materiaDao()
        Thread {
            val materias = materiaDao.getAllMateria()
            this@MateriasActivity.materiaAdapter.setDataSet(materias)
        }.start()

    }

    private fun initRecyclerView() {
        this.materiaAdapter = MateriasAdapter()

        val listaMaterias = findViewById<RecyclerView>(R.id.rvListaMaterias)

        listaMaterias.layoutManager = LinearLayoutManager(this@MateriasActivity)
        listaMaterias.adapter = this.materiaAdapter

    }


}