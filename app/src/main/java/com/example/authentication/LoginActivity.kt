package com.example.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.authentication.databinding.ActivityLoginBinding
import com.example.authentication.databinding.ActivityMainBinding
import com.example.authentication.viewmodels.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: AuthViewModel
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        binding.loginBtm.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill up all the required fields", Toast.LENGTH_SHORT)
                    .show()
            } else {
                viewModel.signin(email, password).observe(this, {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                    if (it.equals("sing in your account")) {
                        startActivity(Intent(this,HomeActivity::class.java))
                    }
                })
            }
        }
        binding.registerTxt.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        if(FirebaseAuth.getInstance().currentUser?.uid!=null){
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}