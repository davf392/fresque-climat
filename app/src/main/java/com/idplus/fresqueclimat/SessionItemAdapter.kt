package com.idplus.fresqueclimat

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.idplus.fresqueclimat.data.Session


class SessionItemAdapter : RecyclerView.Adapter<SessionItemAdapter.SessionItemViewHolder>() {

    var data = listOf<Session>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionItemViewHolder {
        return SessionItemViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: SessionItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    class SessionItemViewHolder(val cardView: CardView): RecyclerView.ViewHolder(cardView) {

        val sessionDate = cardView.findViewById<TextView>(R.id.date_time)
        val sessionLocation = cardView.findViewById<TextView>(R.id.location)
        val availableParticipants = cardView.findViewById<TextView>(R.id.participants)
        val availableFacilitators = cardView.findViewById<TextView>(R.id.facilitators)

        companion object {
            fun inflateFrom(parent: ViewGroup): SessionItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.session_item, parent, false) as CardView
                return SessionItemViewHolder(view)
            }
        }

        fun bind(item: Session) {
            sessionDate.text = item.date.toString()
            sessionLocation.text = if(item.format.contentEquals("online")) "En ligne" else "${item.city}, ${item.country}"
            availableParticipants.text = "${item.capacityParticipants} places·s restante·s"
            availableFacilitators.text = "${item.capacityFacilitators} anima·teur·trice·s restant·e·s"
        }
    }
}