package com.example.idle_parilla_pepito_poliquit

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {

    private lateinit var nameInputLayout: TextInputLayout
    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var departmentInputLayout: TextInputLayout
    private lateinit var nameEditText: TextInputEditText
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var departmentSpinner: Spinner
    private lateinit var registerButton: Button
    private lateinit var loginButton: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initViews()
        setupSpinner()
        setupClickListeners()
    }

    private fun initViews() {
        nameInputLayout = findViewById(R.id.nameInputLayout)
        emailInputLayout = findViewById(R.id.emailInputLayout)
        passwordInputLayout = findViewById(R.id.passwordInputLayout)
        departmentInputLayout = findViewById(R.id.departmentInputLayout)
        nameEditText = findViewById(R.id.nameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        departmentSpinner = findViewById(R.id.departmentSpinner)
        registerButton = findViewById(R.id.registerButton)
        loginButton = findViewById(R.id.loginButton)
        progressBar = findViewById(R.id.progressBar)
    }

    private fun setupSpinner() {
        val departments = arrayOf(
            "Select Department",
            "IT",
            "HR",
            "Finance",
            "Marketing",
            "Operations",
            "Sales",
            "Customer Service",
            "Management"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, departments)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        departmentSpinner.adapter = adapter
    }

    private fun setupClickListeners() {
        registerButton.setOnClickListener {
            clearErrors()
            performRegistration()
        }

        loginButton.setOnClickListener {
            navigateToLogin()
        }
    }

    private fun clearErrors() {
        nameInputLayout.error = null
        emailInputLayout.error = null
        passwordInputLayout.error = null
    }

    private fun performRegistration() {
        val name = nameEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()
        val department = if (departmentSpinner.selectedItemPosition == 0) ""
        else departmentSpinner.selectedItem.toString()

        if (validateInput(name, email, password, department)) {
            showProgress()
            simulateRegistration(name, email, password, department)
        }
    }

    private fun validateInput(name: String, email: String, password: String, department: String): Boolean {
        var isValid = true

        // Name validation
        when {
            name.isEmpty() -> {
                nameInputLayout.error = "Full name is required"
                isValid = false
            }
            name.length < 2 -> {
                nameInputLayout.error = "Name must be at least 2 characters"
                isValid = false
            }
            !name.matches(Regex("^[a-zA-Z\\s]+$")) -> {
                nameInputLayout.error = "Name should only contain letters and spaces"
                isValid = false
            }
            else -> {
                nameInputLayout.error = null
            }
        }

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
            !password.matches(Regex(".*[A-Za-z].*")) -> {
                passwordInputLayout.error = "Password must contain at least one letter"
                isValid = false
            }
            !password.matches(Regex(".*[0-9].*")) -> {
                passwordInputLayout.error = "Password must contain at least one number"
                isValid = false
            }
            else -> {
                passwordInputLayout.error = null
            }
        }

        // Department validation
        if (department.isEmpty()) {
            Toast.makeText(this, "Please select a department", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        return isValid
    }

    private fun simulateRegistration(name: String, email: String, password: String, department: String) {
        // Simulate network delay
        android.os.Handler(mainLooper).postDelayed({
            hideProgress()

            // Check if email already exists (simulate)
            if (email == "test@example.com") {
                showRegisterError("This email is already registered. Please use a different email.")
                return@postDelayed
            }

            // Simulate successful registration
            showRegisterSuccess()
            android.os.Handler(mainLooper).postDelayed({
                navigateToLogin()
            }, 1000)
        }, 2000)
    }

    private fun showProgress() {
        progressBar.visibility = View.VISIBLE
        registerButton.isEnabled = false
        loginButton.isEnabled = false
        nameEditText.isEnabled = false
        emailEditText.isEnabled = false
        passwordEditText.isEnabled = false
        departmentSpinner.isEnabled = false
    }

    private fun hideProgress() {
        progressBar.visibility = View.GONE
        registerButton.isEnabled = true
        loginButton.isEnabled = true
        nameEditText.isEnabled = true
        emailEditText.isEnabled = true
        passwordEditText.isEnabled = true
        departmentSpinner.isEnabled = true
    }

    private fun showRegisterError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun showRegisterSuccess() {
        Toast.makeText(this, "Account created successfully! Please login.", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToLogin() {
        finish() // Go back to login activity
    }
}