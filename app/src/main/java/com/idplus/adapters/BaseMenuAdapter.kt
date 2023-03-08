package com.idplus.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.idplus.fresqueclimat.databinding.MenuListItemBinding


class BaseMenuAdapter(
    var context: Context,
    var menuItemsList: List<String> = emptyList()
)
    : BaseAdapter() {

    override fun getCount(): Int = menuItemsList.size

    override fun getItem(p0: Int): Any = menuItemsList[p0]

    override fun getItemId(p0: Int): Long = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: MenuListItemBinding
        if(convertView == null) {
            binding = MenuListItemBinding.inflate(LayoutInflater.from(context), parent, false)
            binding.root.tag = binding
        }
        else {
            binding = convertView.tag as MenuListItemBinding
        }
        binding.tvMenuItemName.text = getItem(position) as String

        return binding.root
    }

}