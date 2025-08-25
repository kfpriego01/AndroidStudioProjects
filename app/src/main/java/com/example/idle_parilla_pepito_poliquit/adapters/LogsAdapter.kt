package com.example.idle_parilla_pepito_poliquit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.idle_parilla_pepito_poliquit.R
import com.example.idle_parilla_pepito_poliquit.models.DeskOccupancy

class LogsAdapter : RecyclerView.Adapter<LogsAdapter.LogViewHolder>() {

    private var logs = listOf<DeskOccupancy>()

    fun updateLogs(newLogs: List<DeskOccupancy>) {
        logs = newLogs
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_log, parent, false)
        return LogViewHolder(view)
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        holder.bind(logs[position])
    }

    override fun getItemCount() = logs.size

    class LogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val deskId: TextView = itemView.findViewById(R.id.logDeskId)
        private val status: TextView = itemView.findViewById(R.id.logStatus)
        private val timestamp: TextView = itemView.findViewById(R.id.logTimestamp)
        private val duration: TextView = itemView.findViewById(R.id.logDuration)

        fun bind(log: DeskOccupancy) {
            deskId.text = log.deskId
            status.text = log.status
            timestamp.text = log.timestamp
            duration.text = if (log.duration > 0) "${log.duration} min" else ""
        }
    }
}