package com.example.organyze.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.organyze.R
import com.google.android.material.button.MaterialButtonToggleGroup

class AnaliseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analise)


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
    }
}