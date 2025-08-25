package com.example.idle_parilla_pepito_poliquit.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.idle_parilla_pepito_poliquit.R
import com.example.idle_parilla_pepito_poliquit.adapters.DeskAdapter
import com.example.idle_parilla_pepito_poliquit.adapters.LogsAdapter
import com.example.idle_parilla_pepito_poliquit.contracts.HomeContract
import com.example.idle_parilla_pepito_poliquit.models.DeskInfo
import com.example.idle_parilla_pepito_poliquit.models.DeskOccupancy
import com.example.idle_parilla_pepito_poliquit.models.User
import  com.example.idle_parilla_pepito_poliquit.activities.LoginActivity
import com.example.idle_parilla_pepito_poliquit.presenters.HomePresenter

class HomeActivity : AppCompatActivity(), HomeContract.View {

    private lateinit var presenter: HomePresenter
    private lateinit var welcomeText: TextView
    private lateinit var departmentText: TextView
    private lateinit var deskRecyclerView: RecyclerView
    private lateinit var logsRecyclerView: RecyclerView
    private lateinit var refreshButton: Button
    private lateinit var logoutButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var deskAdapter: DeskAdapter
    private lateinit var logsAdapter: LogsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initViews()
        setupPresenter()
        setupRecyclerViews()
        setupClickListeners()

        presenter.refreshAllData()
    }

    private fun initViews() {
        welcomeText = findViewById(R.id.welcomeText)
        departmentText = findViewById(R.id.departmentText)
        deskRecyclerView = findViewById(R.id.deskRecyclerView)
        logsRecyclerView = findViewById(R.id.logsRecyclerView)
        refreshButton = findViewById(R.id.refreshButton)
        logoutButton = findViewById(R.id.logoutButton)
        progressBar = findViewById(R.id.progressBar)
    }

    private fun setupPresenter() {
        presenter = HomePresenter()
        presenter.attachView(this)
    }

    private fun setupRecyclerViews() {
        deskAdapter = DeskAdapter()
        deskRecyclerView.layoutManager = LinearLayoutManager(this)
        deskRecyclerView.adapter = deskAdapter

        logsAdapter = LogsAdapter()
        logsRecyclerView.layoutManager = LinearLayoutManager(this)
        logsRecyclerView.adapter = logsAdapter
    }

    private fun setupClickListeners() {
        refreshButton.setOnClickListener {
            presenter.refreshAllData()
        }

        logoutButton.setOnClickListener {
            presenter.logout()
        }
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun displayUserInfo(user: User) {
        // User model commented out - mock implementation
        // welcomeText.text = "Welcome, ${user.name}!"
        // departmentText.text = "Department: ${user.department}"
        welcomeText.text = "Welcome, Test User!"
        departmentText.text = "Department: IT"
    }

    override fun displayDeskList(desks: List<DeskInfo>) {
        deskAdapter.updateDesks(desks)
    }

    override fun displayOccupancyLogs(logs: List<DeskOccupancy>) {
        logsAdapter.updateLogs(logs)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun refreshData() {
        presenter.refreshAllData()
    }

    override fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}