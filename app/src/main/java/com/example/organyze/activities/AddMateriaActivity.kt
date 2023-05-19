package com.example.organyze.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.organyze.R
import com.example.organyze.database.AppDatabase
import com.example.organyze.database.models.Horario
import com.example.organyze.database.models.Materia
import com.example.organyze.databinding.ActivityAddMateriaBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddMateriaActivity : AppCompatActivity() {

    private lateinit var biding : ActivityAddMateriaBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.biding = ActivityAddMateriaBinding.inflate(layoutInflater)
        setContentView(biding.root)

    }

    override fun onStart() {
        super.onStart()

        this.biding.btSubmitMateria.setOnClickListener {
            val nomeMateria = this.biding.etMateria.text.toString()
            val abreviacao = this.biding.etAbreviacao.text.toString()

            val bd = AppDatabase.getInstance(this)
            val materiaDao = bd.materiaDao()
            val horarioDao = bd.horarioDao()
            val materia =  Materia(nomeMateria, abreviacao)

            GlobalScope.launch {
                val materiaId = materiaDao.insert(materia)



                if (this@AddMateriaActivity.biding.rbSeg.isChecked) {
                    val inicioSegunda = this@AddMateriaActivity.biding.etHorarioInicioSegunda.text.toString()
                    val fimSegunda = this@AddMateriaActivity.biding.etHorarioFimSegunda.text.toString()
                    val horario = Horario("segunda", inicioSegunda, fimSegunda, materiaId)
                    horarioDao.insert(horario)
                }
                if (this@AddMateriaActivity.biding.rbTer.isChecked) {
                    val inicioTerca = this@AddMateriaActivity.biding.etHorarioInicioTerca.text.toString()
                    val fimTerca = this@AddMateriaActivity.biding.etHorarioFimTerca.text.toString()
                    val horario = Horario("terça", inicioTerca, fimTerca, materiaId)
                    horarioDao.insert(horario)
                }
                if (this@AddMateriaActivity.biding.rbQua.isChecked) {
                    val inicioQuarta = this@AddMateriaActivity.biding.etHorarioInicioQuarta.text.toString()
                    val fimQuarta = this@AddMateriaActivity.biding.etHorarioFimQuarta.text.toString()
                    val horario = Horario("quarta", inicioQuarta, fimQuarta, materiaId)
                    horarioDao.insert(horario)
                }
                if (this@AddMateriaActivity.biding.rbQui.isChecked) {
                    val inicioQuinta = this@AddMateriaActivity.biding.etHorarioInicioQuinta.text.toString()
                    val fimQuinta = this@AddMateriaActivity.biding.etHorarioFimQuinta.text.toString()
                    val horario = Horario("quinta", inicioQuinta, fimQuinta, materiaId)
                    horarioDao.insert(horario)
                }
                if (this@AddMateriaActivity.biding.rbSex.isChecked) {
                    val inicioSexta = this@AddMateriaActivity.biding.etHorarioInicioSexta.text.toString()
                    val fimSexta = this@AddMateriaActivity.biding.etHorarioFimSexta.text.toString()
                    val horario = Horario("sexta", inicioSexta, fimSexta, materiaId)
                    horarioDao.insert(horario)
                }
            }

            val context = applicationContext // ou outra instância de Context
            val message = "Materia adicionada!"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(context, message, duration)
            toast.show()
        }
    }

    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.rbSeg -> {
                    if (checked) {
                        this.biding.etHorarioInicioSegunda.isEnabled = true
                        this.biding.etHorarioFimSegunda.isEnabled = true

                    } else {
                        this.biding.etHorarioInicioSegunda.isEnabled = true
                        this.biding.etHorarioFimSegunda.isEnabled = true

                        this.biding.etHorarioInicioSegunda.setText("")
                        this.biding.etHorarioFimSegunda.setText("")
                    }
                }
                R.id.rbTer -> {
                    if (checked) {
                        this.biding.etHorarioInicioTerca.isEnabled = true
                        this.biding.etHorarioFimTerca.isEnabled = true

                    } else {
                        this.biding.etHorarioInicioTerca.isEnabled = false
                        this.biding.etHorarioFimTerca.isEnabled = false


                        this.biding.etHorarioInicioTerca.setText("")
                        this.biding.etHorarioFimTerca.setText("")
                    }
                }
                R.id.rbQua -> {
                    if (checked) {
                        this.biding.etHorarioInicioQuarta.isEnabled = true
                        this.biding.etHorarioFimQuarta.isEnabled = true

                    } else {
                        this.biding.etHorarioInicioQuarta.isEnabled = false
                        this.biding.etHorarioFimQuarta.isEnabled = false

                        this.biding.etHorarioInicioQuarta.setText("")
                        this.biding.etHorarioFimQuarta.setText("")
                    }
                }
                R.id.rbQui -> {
                    if (checked) {
                        this.biding.etHorarioInicioQuinta.isEnabled = true
                        this.biding.etHorarioFimQuinta.isEnabled = true

                    } else {
                        this.biding.etHorarioInicioQuinta.isEnabled = false
                        this.biding.etHorarioFimQuinta.isEnabled = false

                        this.biding.etHorarioInicioQuinta.setText("")
                        this.biding.etHorarioFimQuinta.setText("")
                    }
                }
                R.id.rbSex -> {
                    if (checked) {
                        this.biding.etHorarioInicioSexta.isEnabled = true
                        this.biding.etHorarioFimSexta.isEnabled = true

                    } else {
                        this.biding.etHorarioInicioSexta.isEnabled = false
                        this.biding.etHorarioFimSexta.isEnabled = false

                        this.biding.etHorarioInicioSexta.setText("")
                        this.biding.etHorarioFimSexta.setText("")
                    }
                }


            }
        }
    }


}

