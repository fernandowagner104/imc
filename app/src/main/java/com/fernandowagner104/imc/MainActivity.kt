package com.fernandowagner104.imc

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }


    private fun setListeners() {
        var weightEdt = findViewById<EditText>(R.id.weightEdt)
        var heightEdt = findViewById<EditText>(R.id.heightEdt)
        val calculateBtn = findViewById<Button>(R.id.calculateBtn)

        heightEdt?.doAfterTextChanged { text ->
            //Toast.makeText(this,text.toString(), Toast.LENGTH_SHORT).show()
        }

        weightEdt?.doOnTextChanged { text, _, _, count ->
            //titleTxt.text = text
        }

        calculateBtn.setOnClickListener {

            calculateImc(weightEdt.text.toString(), heightEdt.text.toString())
        }
    }

    private fun calculateImc(weight: String, height: String) {
        var titleTxt = findViewById<TextView>(R.id.titleTxt)
        val weight = weight.toFloatOrNull()
        val height = height.toFloatOrNull()
        val classificationText = findViewById<TextView>(R.id.ClassificationTxt)

        if (weight != null && height != null) {
            val imc = weight.div(height * height)

            when (imc) {
                in 17.0..18.4 ->  { classificationText.text = "Sua classificação: MAGREZA I "
                                    classificationText.setBackgroundColor(ContextCompat.getColor(baseContext,R.color.thinness))}

                in 18.5..24.9 -> {  classificationText.text = "Sua classificação: SAUDÁVEL "
                                    classificationText.setBackgroundColor(ContextCompat.getColor(baseContext,R.color.healthness))}

                in 25.0..29.9 -> {  classificationText.text = "Sua classificação: SOBREPESO "
                                    classificationText.setBackgroundColor(ContextCompat.getColor(baseContext,R.color.overweight))}

                in 30.0..34.9 -> {  classificationText.text = "Sua classificação: OBESIDADE I "
                                    classificationText.setBackgroundColor(ContextCompat.getColor(baseContext,R.color.obesity1))}

                in 35.0..39.9 -> {  classificationText.text = "Sua classificação: OBESIDADE II "
                                    classificationText.setBackgroundColor(ContextCompat.getColor(baseContext,R.color.obesity1))}

                in 40.0..500.0 -> {  classificationText.text = "Sua classificação: OBESIDADE III "
                                    classificationText.setBackgroundColor(ContextCompat.getColor(baseContext,R.color.obesity1))}

                else -> classificationText.text = "Classificação não encontrada "

            }

            titleTxt.text = "Seu IMC é: %.2f".format(imc)
        }
    }
}

//  classificationText.setBackgroundColor(Color.RED)
//classificationText.setTextAppearance(title_purple)
//classificationText.setBackgroundColor(ContextCompat.getColor(baseContext,R.color.red_happy))