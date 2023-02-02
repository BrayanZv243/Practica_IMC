package com.example.asignacion04_calculadoraimc_zavalabrayan

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val kilosText: TextView = findViewById(R.id.weight)
        val estaturaText: TextView = findViewById(R.id.height)
        val imc: TextView = findViewById(R.id.imc)
        val rangoSalud: TextView = findViewById(R.id.range)
        val calcular: Button = findViewById(R.id.calcular)


        calcular.setOnClickListener {
            // Convertimos los kilos y estatura a double.
            var peso: Double = 0.0
            var estatura: Double = 0.0
            var resultado:Double = 0.0
            // Cerramos el teclado
            val view = this.currentFocus
            if (view != null) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
            try{
                peso = kilosText.text.toString().toDouble()
                estatura = estaturaText.text.toString().toDouble()
                resultado = calcularIMC(estatura,peso)

            }catch (e: java.lang.Exception){
                imc.setText("Ingrese válores válidos")
                rangoSalud.setText("")
                println(e)
                return@setOnClickListener
            }

            val formattedNumber = "%.2f".format(resultado)
            imc.setText(formattedNumber)

            var salud: String
            var color: Int

            when {
                resultado < 18.5 -> {
                    salud = "Bajo Peso"
                    color = R.color.colorRed
                }
                resultado >= 18.5 && resultado <= 24.9 -> {
                    salud = "Saludable"
                    color = R.color.colorGreenish
                }
                resultado >= 25 && resultado <= 29.9 -> {
                    salud = "Sobrepeso"
                    color = R.color.colorYellow
                }
                resultado >= 30 && resultado <= 34.9 -> {
                    salud = "Obesidad Grado 1"
                    color = R.color.colorOrange
                }
                resultado >= 35 && resultado <= 39.9 -> {
                    salud = "Obesidad Grado 2"
                    color = R.color.colorBrown
                }
                resultado >= 40 -> {
                    salud = "Obesidad Grado 3"
                    color = R.color.colorRed
                }
                else -> {
                    salud = "Ingrese valores válidos"
                    color = 0
                }
            }

            rangoSalud.setBackgroundResource(color)
            rangoSalud.setText(salud)

        }
    }

    fun calcularIMC(heigth:Double, weigth: Double): Double{
        return weigth / (heigth * heigth)
    }
}