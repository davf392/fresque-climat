package com.davidfz.fresqueclimat.adapters

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import com.davidfz.fresqueclimat.utils.Language

class LanguageAdapter (
    context: Context,
    resource: Int,
    var availableLanguages: List<String>,
    var selectedLanguages: MutableList<String>
) :
    ArrayAdapter<String>(context, resource, availableLanguages) {

    private val filter: Filter = LanguageFilter()

    override fun getFilter(): Filter {
        return filter
    }

    fun updateData(availableLanguages: List<String>, selectedLanguages: MutableList<String>) {
        this.availableLanguages = availableLanguages
        this.selectedLanguages = selectedLanguages
    }

    private inner class LanguageFilter : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = FilterResults()

            if (constraint != null) {
                val filteredLanguages = availableLanguages
                    .filter { it.lowercase().contains(constraint.toString().lowercase()) }
                    .filterNot { selectedLanguages.contains(it) }

                results.values = filteredLanguages
                results.count = filteredLanguages.size
            }
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results != null && results.count > 0) {
                @Suppress("UNCHECKED_CAST")
                val filteredLanguages = results.values as List<String>
                clear()
                addAll(filteredLanguages)
                notifyDataSetChanged()
            } else {
                notifyDataSetInvalidated()
            }
        }
    }
}