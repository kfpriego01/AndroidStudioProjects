package com.example.idle_parilla_pepito_poliquit.presenters

import com.example.idle_parilla_pepito_poliquit.contracts.MainContract

class MainPresenter(private var view: MainContract.View?) : MainContract.Presenter {

    override fun onLoginClicked() {
        view?.navigateToLogin()
    }

    override fun onRegisterClicked() {
        view?.navigateToRegister()
    }

    override fun onDestroy() {
        view = null
    }
}