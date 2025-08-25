package com.example.idle_parilla_pepito_poliquit.contracts
import com.example.idle_parilla_pepito_poliquit.models.User
import com.example.idle_parilla_pepito_poliquit.models.DeskInfo
import com.example.idle_parilla_pepito_poliquit.models.DeskOccupancy
interface HomeContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun displayUserInfo(user: User)
        fun displayDeskList(desks: List<DeskInfo>)
        fun displayOccupancyLogs(logs: List<DeskOccupancy>)
        fun showError(message: String)
        fun refreshData()
        fun navigateToLogin()
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun loadUserData()
        fun loadDeskData()
        fun loadOccupancyLogs()
        fun refreshAllData()
        fun logout()
    }
}