package com.example.idle_parilla_pepito_poliquit.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.idle_parilla_pepito_poliquit.R
import com.example.idle_parilla_pepito_poliquit.RegisterActivity
import com.example.idle_parilla_pepito_poliquit.contracts.MainContract
import com.example.idle_parilla_pepito_poliquit.presenters.MainPresenter

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize presenter
        presenter = MainPresenter(this)

        // Initialize views
        initViews()
        setupClickListeners()
    }

    private fun initViews() {
        loginButton = findViewById(R.id.btn_login)
        registerButton = findViewById(R.id.btn_register)
    }

    private fun setupClickListeners() {
        loginButton.setOnClickListener {
            presenter.onLoginClicked()
        }

        registerButton.setOnClickListener {
            presenter.onRegisterClicked()
        }
    }

    override fun navigateToLogin() {
        // Navigate to login activity
        val intent = Intent(this,   LoginActivity::class.java)
        startActivity(intent)
    }

    override fun navigateToRegister() {
        // Navigate to register activity
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}