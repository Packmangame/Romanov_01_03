package com.bignerdranch.android.prak5romanov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class FirstScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)
    }

    fun Register(view: View) {
        var intent= Intent(this, Register::class.java)
        startActivity(intent)
    }
}