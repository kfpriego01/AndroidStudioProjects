package com.example.idle_parilla_pepito_poliquit.activities

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.idle_parilla_pepito_poliquit.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {

    private lateinit var firstNameLayout: TextInputLayout
    private lateinit var middleNameLayout: TextInputLayout
    private lateinit var lastNameLayout: TextInputLayout
    private lateinit var companyLayout: TextInputLayout
    private lateinit var emailLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var confirmPasswordLayout: TextInputLayout

    private lateinit var firstNameEditText: TextInputEditText
    private lateinit var middleNameEditText: TextInputEditText
    private lateinit var lastNameEditText: TextInputEditText
    private lateinit var companyEditText: TextInputEditText
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var confirmPasswordEditText: TextInputEditText

    private lateinit var termsCheckBox: CheckBox
    private lateinit var registerButton: Button
    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initViews()
        setupClickListeners()
    }

    private fun initViews() {
        firstNameLayout = findViewById(R.id.firstNameLayout)
        middleNameLayout = findViewById(R.id.middleNameLayout)
        lastNameLayout = findViewById(R.id.lastNameLayout)
        companyLayout = findViewById(R.id.companyLayout)
        emailLayout = findViewById(R.id.emailLayout)
        passwordLayout = findViewById(R.id.passwordLayout)
        confirmPasswordLayout = findViewById(R.id.confirmPasswordLayout)

        firstNameEditText = findViewById(R.id.firstNameEditText)
        middleNameEditText = findViewById(R.id.middleNameEditText)
        lastNameEditText = findViewById(R.id.lastNameEditText)
        companyEditText = findViewById(R.id.companyEditText)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText)

        termsCheckBox = findViewById(R.id.termsCheckBox)
        registerButton = findViewById(R.id.registerButton)
        btnBack = findViewById(R.id.btnBack)
    }

    private fun setupClickListeners() {
        btnBack.setOnClickListener {
            finish() // go back
        }

        registerButton.setOnClickListener {
            clearErrors()
            performRegistration()
        }
    }

    private fun clearErrors() {
        firstNameLayout.error = null
        middleNameLayout.error = null
        lastNameLayout.error = null
        companyLayout.error = null
        emailLayout.error = null
        passwordLayout.error = null
        confirmPasswordLayout.error = null
    }

    private fun performRegistration() {
        val firstName = firstNameEditText.text.toString().trim()
        val middleName = middleNameEditText.text.toString().trim()
        val lastName = lastNameEditText.text.toString().trim()
        val company = companyEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()
        val confirmPassword = confirmPasswordEditText.text.toString().trim()

        if (validateInput(firstName, lastName, email, password, confirmPassword, company)) {
            if (!termsCheckBox.isChecked) {
                Toast.makeText(this, "You must agree to the Terms and Conditions", Toast.LENGTH_SHORT).show()
                return
            }

            // ✅ Simulate successful registration
            Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()
            finish() // go back to login screen
        }
    }

    private fun validateInput(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String,
        company: String
    ): Boolean {
        var isValid = true

        if (firstName.isEmpty()) {
            firstNameLayout.error = "First name is required"
            isValid = false
        }

        if (lastName.isEmpty()) {
            lastNameLayout.error = "Last name is required"
            isValid = false
        }

        if (company.isEmpty()) {
            companyLayout.error = "Company is required"
            isValid = false
        }

        if (email.isEmpty()) {
            emailLayout.error = "Email is required"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.error = "Invalid email format"
            isValid = false
        }

        if (password.isEmpty()) {
            passwordLayout.error = "Password is required"
            isValid = false
        } else if (password.length < 6) {
            passwordLayout.error = "Password must be at least 6 characters"
            isValid = false
        }

        if (confirmPassword.isEmpty()) {
            confirmPasswordLayout.error = "Please confirm password"
            isValid = false
        } else if (password != confirmPassword) {
            confirmPasswordLayout.error = "Passwords do not match"
            isValid = false
        }

        return isValid
    }
}
