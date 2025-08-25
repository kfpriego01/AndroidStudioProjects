package com.example.idle_parilla_pepito_poliquit.presenters

import com.example.idle_parilla_pepito_poliquit.contracts.HomeContract
import com.example.idle_parilla_pepito_poliquit.models.DeskInfo
import com.example.idle_parilla_pepito_poliquit.models.DeskOccupancy
// import com.google.firebase.auth.FirebaseAuth
// import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class HomePresenter : HomeContract.Presenter {
    private var view: HomeContract.View? = null
    // private val firestore = FirebaseFirestore.getInstance()
    // private val auth = FirebaseAuth.getInstance()

    override fun attachView(view: HomeContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun loadUserData() {
        // Firebase implementation commented out
        // val userId = auth.currentUser?.uid ?: return
        //
        // firestore.collection("users").document(userId)
        //     .get()
        //     .addOnSuccessListener { document ->
        //         if (document.exists()) {
        //             val user = document.toObject(User::class.java)
        //             user?.let { view?.displayUserInfo(it) }
        //         }
        //     }
        //     .addOnFailureListener { exception ->
        //         view?.showError("Failed to load user data: ${exception.message}")
        //     }
    }

    override fun loadDeskData() {
        view?.showProgress()

        // Firebase implementation commented out
        // firestore.collection("desk_info")
        //     .get()
        //     .addOnSuccessListener { documents ->
        //         val desks = documents.mapNotNull { doc ->
        //             doc.toObject(DeskInfo::class.java)
        //         }
        //         view?.displayDeskList(desks)
        //         view?.hideProgress()
        //     }
        //     .addOnFailureListener { exception ->
        //         view?.hideProgress()
        //         view?.showError("Failed to load desk data: ${exception.message}")
        //     }

        // Mock data for testing
        android.os.Handler().postDelayed({
            val mockDesks = listOf(
                DeskInfo("1", "Desk 01", "Floor 1, Section A", "Available", "10:30 AM", 120),
                DeskInfo("2", "Desk 02", "Floor 1, Section B", "Occupied", "11:15 AM", 90),
                DeskInfo("3", "Desk 03", "Floor 2, Section A", "Available", "09:45 AM", 180)
            )
            view?.displayDeskList(mockDesks)
            view?.hideProgress()
        }, 1000)
    }

    override fun loadOccupancyLogs() {
        // Firebase implementation commented out
        // val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        //
        // firestore.collection("desk_occupancy")
        //     .whereGreaterThanOrEqualTo("timestamp", "${today}T00:00:00")
        //     .orderBy("timestamp", com.google.firebase.firestore.Query.Direction.DESCENDING)
        //     .limit(50)
        //     .get()
        //     .addOnSuccessListener { documents ->
        //         val logs = documents.mapNotNull { doc ->
        //             doc.toObject(DeskOccupancy::class.java)
        //         }
        //         view?.displayOccupancyLogs(logs)
        //     }
        //     .addOnFailureListener { exception ->
        //         view?.showError("Failed to load occupancy logs: ${exception.message}")
        //     }

        // Mock data for testing
        val mockLogs = listOf(
            DeskOccupancy("1", "Desk 01", "Occupied", "10:30 AM", 45, "user1"),
            DeskOccupancy("2", "Desk 02", "Available", "09:45 AM", 0, ""),
            DeskOccupancy("3", "Desk 03", "Occupied", "11:15 AM", 30, "user2")
        )
        view?.displayOccupancyLogs(mockLogs)
    }

    override fun refreshAllData() {
        loadUserData()
        loadDeskData()
        loadOccupancyLogs()
    }

    override fun logout() {
        // auth.signOut()
        view?.navigateToLogin()
    }
}