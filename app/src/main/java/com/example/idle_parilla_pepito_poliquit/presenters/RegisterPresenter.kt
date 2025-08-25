package com.example.idle_parilla_pepito_poliquit.presenters

import android.util.Patterns
import com.example.idle_parilla_pepito_poliquit.contracts.RegisterContract
// import com.example.idle_parilla_pepito_poliquit.models.User
// import com.google.firebase.auth.FirebaseAuth
// import com.google.firebase.firestore.FirebaseFirestore

class RegisterPresenter : RegisterContract.Presenter {
    private var view: RegisterContract.View? = null
    // private val auth = FirebaseAuth.getInstance()
    // private val firestore = FirebaseFirestore.getInstance()

    override fun attachView(view: RegisterContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun register(name: String, email: String, password: String, department: String) {
        if (!validateInputs(name, email, password, department)) return

        view?.showProgress()

        // Firebase authentication commented out
        // auth.createUserWithEmailAndPassword(email, password)
        //     .addOnCompleteListener { task ->
        //         if (task.isSuccessful) {
        //             val userId = auth.currentUser?.uid ?: return@addOnCompleteListener
        //             saveUserData(userId, name, email, department)
        //         } else {
        //             view?.hideProgress()
        //             val error = task.exception?.message ?: "Registration failed"
        //             view?.showRegisterError(error)
        //         }
        //     }

        // Mock registration success for testing
        android.os.Handler().postDelayed({
            view?.hideProgress()
            view?.showRegisterSuccess()
            view?.navigateToLogin()
        }, 2000) // Simulate 2 second delay
    }

    private fun saveUserData(userId: String, name: String, email: String, department: String) {
        // Firebase Firestore commented out
        // val user = User(userId, email, name, department)
        //
        // firestore.collection("users").document(userId)
        //     .set(user)
        //     .addOnCompleteListener { task ->
        //         view?.hideProgress()
        //         if (task.isSuccessful) {
        //             view?.showRegisterSuccess()
        //             view?.navigateToLogin()
        //         } else {
        //             val error = task.exception?.message ?: "Failed to save user data"
        //             view?.showRegisterError(error)
        //         }
        //     }
    }

    private fun validateInputs(name: String, email: String, password: String, department: String): Boolean {
        var isValid = true

        if (!validateName(name)) {
            view?.showNameError("Name must be at least 2 characters")
            isValid = false
        }

        if (!validateEmail(email)) {
            view?.showEmailError("Please enter a valid email address")
            isValid = false
        }

        if (!validatePassword(password)) {
            view?.showPasswordError("Password must be at least 6 characters")
            isValid = false
        }

        if (!validateDepartment(department)) {
            view?.showDepartmentError("Please select a department")
            isValid = false
        }

        return isValid
    }

    override fun validateName(name: String): Boolean {
        return name.trim().length >= 2
    }

    override fun validateEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun validatePassword(password: String): Boolean {
        return password.length >= 6
    }

    override fun validateDepartment(department: String): Boolean {
        return department.isNotEmpty()
    }

    override fun navigateToLogin() {
        view?.navigateToLogin()
    }
}