package com.example.idle_parilla_pepito_poliquit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initializeViews()
        setupClickListeners()
    }

    private fun initializeViews() {
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        emailInputLayout = findViewById(R.id.emailInputLayout)
        passwordInputLayout = findViewById(R.id.passwordInputLayout)
        loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.registerButton)
        progressBar = findViewById(R.id.progressBar)
    }

    private fun setupClickListeners() {
        loginButton.setOnClickListener {
            clearErrors()
            performLogin()
        }

        registerButton.setOnClickListener {
            navigateToRegister()
        }
    }

    private fun clearErrors() {
        emailInputLayout.error = null
        passwordInputLayout.error = null
    }

    private fun performLogin() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (validateInput(email, password)) {
            showLoading(true)
            // Simulate login without Firebase
            simulateLogin(email, password)
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        var isValid = true

        // Email validation
        when {
            email.isEmpty() -> {
                emailInputLayout.error = "Email is required"
                isValid = false
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                emailInputLayout.error = "Please enter a valid email address"
                isValid = false
            }
            else -> {
                emailInputLayout.error = null
            }
        }

        // Password validation
        when {
            password.isEmpty() -> {
                passwordInputLayout.error = "Password is required"
                isValid = false
            }
            password.length < 6 -> {
                passwordInputLayout.error = "Password must be at least 6 characters"
                isValid = false
            }
            else -> {
                passwordInputLayout.error = null
            }
        }

        return isValid
    }

    private fun simulateLogin(email: String, password: String) {
        // Simulate network delay
        android.os.Handler(mainLooper).postDelayed({
            showLoading(false)

            // For demo purposes - check for demo credentials
            if (email == "admin@example.com" && password == "123456") {
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                navigateToMainApp()
            } else {
                // Accept any valid email/password for demo
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                navigateToMainApp()
            }
        }, 1500)
    }

    private fun showLoading(isLoading: Boolean) {
        progressBar.isVisible = isLoading
        loginButton.isEnabled = !isLoading
        registerButton.isEnabled = !isLoading
        emailEditText.isEnabled = !isLoading
        passwordEditText.isEnabled = !isLoading
    }

    private fun navigateToMainApp() {
        try {
            val intent = Intent(this, DeskTrackerApplication::class.java)
            startActivity(intent)
            finish() // Prevent going back to login
        } catch (e: Exception) {
            Toast.makeText(this, "Main app not found. Creating placeholder...", Toast.LENGTH_LONG).show()
            // For now, just show success and stay on login
        }
    }

    private fun navigateToRegister() {
        try {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Register activity not found", Toast.LENGTH_SHORT).show()
        }
    }
}