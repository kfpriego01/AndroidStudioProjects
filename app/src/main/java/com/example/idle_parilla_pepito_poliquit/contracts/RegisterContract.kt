package com.example.idle_parilla_pepito_poliquit.contracts

interface RegisterContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun showNameError(message: String)
        fun showEmailError(message: String)
        fun showPasswordError(message: String)
        fun showDepartmentError(message: String)
        fun navigateToLogin()
        fun showRegisterError(message: String)
        fun showRegisterSuccess()
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun register(name: String, email: String, password: String, department: String)
        fun navigateToLogin()
        fun validateName(name: String): Boolean
        fun validateEmail(email: String): Boolean
        fun validatePassword(password: String): Boolean
        fun validateDepartment(department: String): Boolean
    }
}