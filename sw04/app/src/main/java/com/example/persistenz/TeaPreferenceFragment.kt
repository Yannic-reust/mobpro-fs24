package com.example.persistenz

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class TeaPreferenceFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    companion object {
        fun newInstance(): TeaPreferenceFragment {
            return TeaPreferenceFragment()
        }
    }
}