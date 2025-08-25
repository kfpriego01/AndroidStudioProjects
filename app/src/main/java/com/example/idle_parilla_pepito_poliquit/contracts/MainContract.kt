package com.example.idle_parilla_pepito_poliquit.contracts

interface MainContract {

    interface View {
        fun navigateToLogin()
        fun navigateToRegister()
    }

    interface Presenter {
        fun onLoginClicked()
        fun onRegisterClicked()
        fun onDestroy()
    }
}