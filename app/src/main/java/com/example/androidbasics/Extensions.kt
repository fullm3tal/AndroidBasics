package com.example.androidbasics

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

fun FragmentTransaction.loadFragment(fragment: Fragment) {
    replace(R.id.fragment_container, fragment)
    addToBackStack(null)
    commit()
}