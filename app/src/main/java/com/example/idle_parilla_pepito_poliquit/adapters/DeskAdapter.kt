package com.example.idle_parilla_pepito_poliquit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.idle_parilla_pepito_poliquit.R
import com.example.idle_parilla_pepito_poliquit.models.DeskInfo

class DeskAdapter : RecyclerView.Adapter<DeskAdapter.DeskViewHolder>() {

    private var desks = listOf<DeskInfo>()

    fun updateDesks(newDesks: List<DeskInfo>) {
        desks = newDesks
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_desk, parent, false)
        return DeskViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeskViewHolder, position: Int) {
        holder.bind(desks[position])
    }

    override fun getItemCount() = desks.size

    class DeskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val deskName: TextView = itemView.findViewById(R.id.deskName)
        private val deskLocation: TextView = itemView.findViewById(R.id.deskLocation)
        private val deskStatus: TextView = itemView.findViewById(R.id.deskStatus)
        private val todayUsage: TextView = itemView.findViewById(R.id.todayUsage)
        private val lastUpdated: TextView = itemView.findViewById(R.id.lastUpdated)

        fun bind(desk: DeskInfo) {
            deskName.text = desk.name
            deskLocation.text = desk.location
            deskStatus.text = desk.currentStatus
            todayUsage.text = "Today: ${desk.todayUsage} min"
            lastUpdated.text = "Updated: ${desk.lastUpdated}"

            // Set status color
            val statusColor = if (desk.currentStatus == "Occupied") {
                ContextCompat.getColor(itemView.context, android.R.color.holo_red_light)
            } else {
                ContextCompat.getColor(itemView.context, android.R.color.holo_green_light)
            }
            deskStatus.setTextColor(statusColor)
        }
    }
}