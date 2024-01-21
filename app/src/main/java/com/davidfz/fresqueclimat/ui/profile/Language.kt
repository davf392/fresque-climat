package com.davidfz.fresqueclimat.ui.profile

data class Language(val id: Int, val name: String) {
    override fun toString(): String {
        return name
    }
}