package com.bignerdranch.android.prak5romanov

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

lateinit var pas:EditText
lateinit var log:EditText
class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        pas=findViewById(R.id.password)
        log=findViewById(R.id.login)
    }

    fun Main(view: View) {
        if(log.text.isEmpty()||pas.text.isEmpty())
        {
           Toast.makeText(this,"Логин и пароль не должны быть пустыми", Toast.LENGTH_LONG).show()
        }else {
            if(log.text.toString()=="ects"&&pas.text.toString()=="ects2024")
            {
            var intent = Intent(this, MainScreen::class.java)
            startActivity(intent)
            }
        }
    }
}