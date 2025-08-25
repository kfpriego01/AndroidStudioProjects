package com.example.idle_parilla_pepito_poliquit.contracts

interface LoginContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun showEmailError(message: String)
        fun showPasswordError(message: String)
        fun navigateToHome()
        fun navigateToRegister()
        fun showLoginError(message: String)
        fun showLoginSuccess()
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun login(email: String, password: String)
        fun navigateToRegister()
        fun validateEmail(email: String): Boolean
        fun validatePassword(password: String): Boolean
    }
}
