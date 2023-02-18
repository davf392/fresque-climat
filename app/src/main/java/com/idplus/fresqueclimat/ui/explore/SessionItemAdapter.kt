package com.idplus.fresqueclimat.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.idplus.fresqueclimat.databinding.SessionItemBinding


class SessionItemAdapter(
    private val clickListener: SessionListener
)
    : ListAdapter<SessionItem, SessionItemAdapter.ViewHolder>(SessionDiffCallback()) {

    class ViewHolder(val binding: SessionItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SessionItem, clickListener: SessionListener) {
            binding.session = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    class SessionDiffCallback : DiffUtil.ItemCallback<SessionItem>() {
        override fun areItemsTheSame(oldItem: SessionItem, newItem: SessionItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SessionItem, newItem: SessionItem): Boolean {
            return oldItem == newItem
        }
    }

    class SessionListener(val clickListener: (sessionItem: SessionItem) -> Unit) {
        fun onClick(session: SessionItem) = clickListener(session)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SessionItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}