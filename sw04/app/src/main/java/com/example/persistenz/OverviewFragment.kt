package com.example.persistenz

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.persistenz.databinding.FragmentOverviewBinding // Import FragmentOverviewBinding

class OverviewFragment : Fragment() {


    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!
    companion object {
        private const val SHARED_PREFERENCES_AMOUNT = "sharedPreferencesAmount"
        private const val COUNT_KEY = "amountOpened"
        fun newInstance(): OverviewFragment {
            return OverviewFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Initialize _binding
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        // Return the root
        return _binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Set _binding to null when the view is destroyed to avoid memory leaks
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        setOpenedCount(getAmountOpened() + 1)

    }

    private fun getAmountOpened(): Int {
        val preferences: SharedPreferences = requireActivity().getSharedPreferences(
            SHARED_PREFERENCES_AMOUNT, Context.MODE_PRIVATE)
        return preferences.getInt(COUNT_KEY, 0)
    }


    private fun setOpenedCount(count: Int) {
        val preferences: SharedPreferences = requireActivity().getSharedPreferences(
            SHARED_PREFERENCES_AMOUNT, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor?.putInt(COUNT_KEY, count)
        editor.apply()
    }

    private fun updateAmountOpenedText() {
        binding.tvPreferencesReopen.text =
            getString(R.string.application_preferences_reopen, getAmountOpened())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateAmountOpenedText()


    }

}
