package com.example.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.authentication.databinding.ActivityMainBinding
import com.example.authentication.viewmodels.AuthViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.registerBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()
            val confirmPass = binding.conpassEt.text.toString()

            if (email.isEmpty() || password.isEmpty() || confirmPass.isEmpty()) {
                Toast.makeText(this, "Please fill up all the required fields", Toast.LENGTH_SHORT)
                    .show()
            } else if (!password.equals(confirmPass)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else{
                viewModel.signup(email, confirmPass).observe(this, {result->
                    Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
                    if(it.equals("Sign in successful")){
                        startActivity(Intent(this, HomeActivity::class.java))
                    }
                })

            }
        }
        binding.logintxt.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }



    }
}