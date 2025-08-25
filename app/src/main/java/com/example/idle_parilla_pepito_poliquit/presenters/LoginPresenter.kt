package com.example.idle_parilla_pepito_poliquit.presenters

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Patterns
import java.text.SimpleDateFormat
import java.util.*
import com.example.idle_parilla_pepito_poliquit.contracts.LoginContract

class LoginPresenter : LoginContract.Presenter {
    private var view: LoginContract.View? = null
    private val auth = FirebaseAuth.getInstance()

    override fun attachView(view: LoginContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun login(email: String, password: String) {
        if (!validateInputs(email, password)) return

        view?.showProgress()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                view?.hideProgress()
                if (task.isSuccessful) {
                    view?.showLoginSuccess()
                    view?.navigateToHome()
                } else {
                    val error = task.exception?.message ?: "Login failed"
                    view?.showLoginError(error)
                }
            }
    }

    private fun validateInputs(email: String, password: String): Boolean {
        var isValid = true

        if (!validateEmail(email)) {
            view?.showEmailError("Please enter a valid email address")
            isValid = false
        }

        if (!validatePassword(password)) {
            view?.showPasswordError("Password must be at least 6 characters")
            isValid = false
        }

        return isValid
    }

    override fun validateEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun validatePassword(password: String): Boolean {
        return password.length >= 6
    }

    override fun navigateToRegister() {
        view?.navigateToRegister()
    }
}
