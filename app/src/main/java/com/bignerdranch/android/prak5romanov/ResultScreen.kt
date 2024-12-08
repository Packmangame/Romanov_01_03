package com.bignerdranch.android.prak5romanov

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class ResultScreen : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var namefigure: String
    private lateinit var resulta: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_screen)
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        namefigure = sharedPreferences.getString("figure", "") ?: ""
        resulta = sharedPreferences.getString("result", "") ?: ""

        val figureTextView: TextView = findViewById(R.id.namefig)
        figureTextView.text = namefigure
        val resultTextView: TextView = findViewById(R.id.result)
        resultTextView.text = resulta
    }

    fun onRegistrationScreen(view: View) {
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
    }

}