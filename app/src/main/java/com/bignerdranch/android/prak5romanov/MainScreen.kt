package com.bignerdranch.android.prak5romanov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.PI

class MainScreen : AppCompatActivity() {
    var numberA: EditText? = null
    var numberB: EditText? = null
    var prop: Int = 0
    var sqorcr: Int = 0
    var res: Double? = 0.0

    val shapes = arrayOf("Треугольник", "Окружность")
    val images = arrayOf(R.drawable.vibor2, R.drawable.vibor1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        numberA = findViewById<EditText>(R.id.number)
        numberB = findViewById<EditText>(R.id.numbertr)
        val editText: EditText = findViewById(R.id.numbertr)
        editText.visibility = View.GONE

        val shapeSpinner = findViewById<Spinner>(R.id.spfigure)
        val imageView = findViewById<ImageView>(R.id.imaget)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, shapes)
        shapeSpinner.adapter = adapter

        shapeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val edt1 = findViewById<EditText>(R.id.number)
                val edt2 = findViewById<EditText>(R.id.numbertr)
                when (position) {
                    0 -> {
                        edt1.hint = "a"
                        edt2.visibility = View.VISIBLE
                        edt2.hint = "b"
                        prop = 0
                    }

                    1 -> {
                        edt1.hint = "Введите данные для расчёта"
                        edt2.visibility = View.GONE
                        prop = 1
                        sqorcr = 1
                    }

                    2 -> {
                        edt1.hint = "Введите данные для расчёта"
                        edt2.visibility = View.GONE
                        prop = 1
                        sqorcr = 2
                    }
                }
                imageView.setImageResource(images[position])
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }
    fun onResultScreen(view: View) {
        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        if (prop == 1) {
            if (!numberA?.text.isNullOrEmpty()) {
                if (numberA?.text.toString().toInt() != 0) {
                    val a = numberA?.text.toString().toDouble()
                    if (sqorcr == 1) {
                        res = (a / (2 * PI))
                        val roundRes =
                            BigDecimal(res!!).setScale(2, RoundingMode.HALF_UP).toDouble()
                        editor.putString("figure", "Окружность")
                        editor.putString("result", "$roundRes")
                    }

                    editor.apply()
                    startActivity(Intent(this, ResultScreen::class.java))

                }
                else { Snackbar.make(view,"Ошибка: значение '0' недопустимо. Пожалуйста, введите другое число.", Snackbar.LENGTH_LONG).show()}
            } else {
                showError("Вы не ввели данные для расчёта")
            }
        } else {
            if (!numberA?.text.toString().isNullOrEmpty() && !numberB?.text.toString().isNullOrEmpty()) {
                if (numberA?.text.toString().toInt() != 0 && numberB?.text.toString().toInt() != 0) {
                    if (2*numberA?.text.toString().toDouble() > numberB?.text.toString().toDouble()) {
                        val a = numberA?.text.toString().toDouble()
                        val b = numberB?.text.toString().toDouble()
                        res = 2 * a + b
                        editor.putString("figure", "Треугольник")
                        editor.putString("result", "$res")
                        editor.apply()
                        startActivity(Intent(this, ResultScreen::class.java))

                    }
                    else { Snackbar.make(view,"Ошибка: треугольника с такими сторонами не сущестует.", Snackbar.LENGTH_LONG).show()}
                }
                else { Snackbar.make(view,"Ошибка: значение '0' недопустимо. Пожалуйста, введите другое число.", Snackbar.LENGTH_LONG).show()}
            } else {
                showError("Вы не ввели данные для расчёта")
            }
        }
    }

    private fun showError(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Ошибка")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .create()
            .show()
    }
}