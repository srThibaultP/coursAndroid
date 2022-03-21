package moe.caiomi.tp1formation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import moe.caiomi.tp1formation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val email by lazy { findViewById<EditText>(R.id.username)}
        val password by lazy { findViewById<EditText>(R.id.password)}
        val login by lazy { findViewById<Button>(R.id.login)}

        lateinit var binding: ActivityMainBinding


        /*login.setOnClickListener(){
            Toast.makeText(this,"Connexion réussite", Toast.LENGTH_SHORT).show()
            Log.i("DEV", "oui oui")
            email = findViewById(R.id.login)
            Log.i("DEV",email.toString())
        }*/
        fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
            super.onCreate(savedInstanceState, persistentState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
        }
        login.setOnClickListener(){
            //Toast.makeText(this,"Connexion réussite", Toast.LENGTH_SHORT).show()
            //Log.i("DEV",email.text.toString())
            if(email.text.toString() == "admin" && password.text.toString() == "password") {
                Log.i("LOGIN", "tu est admin")
                Toast.makeText(this,"Connexion réussite", Toast.LENGTH_SHORT).show()
            } else {
                Log.i("LOGIN", "tu n'est pas admin")
            }
        }
    }
}

