package com.davidfz.fresqueclimat.ui.resources

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.davidfz.fresqueclimat.R
import com.davidfz.fresqueclimat.data.local.entities.Resource



class ResourceItemAdapter : RecyclerView.Adapter<ResourceItemAdapter.ResourceItemViewHolder>() {

    var data = listOf<Resource>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceItemViewHolder {
        return ResourceItemViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: ResourceItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class ResourceItemViewHolder(private val cardView: CardView): RecyclerView.ViewHolder(cardView) {

        var resourceTitle: TextView = cardView.findViewById(R.id.resource_title)
        var resourceDescription: TextView = cardView.findViewById(R.id.resource_description)

        companion object {
            fun inflateFrom(parent: ViewGroup): ResourceItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.resource_item, parent, false) as CardView
                return ResourceItemViewHolder(view)
            }
        }

        fun bind(item: Resource) {
            resourceTitle.text = item.title
            resourceDescription.text = item.description

            // start resource URL in new activity in a browser
            cardView.setOnClickListener {
                val link = item.externalLink
                if (link != null && link.isNotBlank()) {
                    val openURL = Intent(Intent.ACTION_VIEW)
                    openURL.data = Uri.parse(link)
                    cardView.context.startActivity(openURL)
                }
            }
        }
    }
}