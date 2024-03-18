package com.example.persistenz

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Telephony
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.example.persistenz.databinding.FragmentOverviewBinding
import android.Manifest

class OverviewFragment : Fragment() {


    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!
    private val sharedPreferencesViewModel: SharedPreferencesViewModel by activityViewModels()
    private val requestPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission(),
            object: ActivityResultCallback<Boolean> {
                override fun onActivityResult(result: Boolean) {
                    if(!result) {
                        Toast.makeText(context, "Permission denied!", Toast.LENGTH_LONG).show()
                        return
                    } else {
                        showSmsDialog()
                    }
                }
            })
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
        updateTeaPreferenceText()
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

        // setup buttons
        binding.btnPreferencesEdit.setOnClickListener {
            showTeaPreferences() // show tea settings screen
        }

        binding.btnPreferencesSet.setOnClickListener {
            setTeaPreferences() // set default tea preferences
        }

        binding.btnProviderShow.setOnClickListener {
            showSmsDialog() // list sent sms
        }

    }

    private fun showTeaPreferences() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_host, TeaPreferenceFragment.newInstance())
            .addToBackStack("preferences")
            .commit()
    }

    private fun setTeaPreferences() {
        sharedPreferencesViewModel.setDefaultTeaPreferences()
        updateTeaPreferenceText()
    }

    private fun updateTeaPreferenceText() {
        if(sharedPreferencesViewModel.isTeaWithSugar) {
            binding.tvTeePreference.text = getString(R.string.tee_preferences_text_sweet, sharedPreferencesViewModel.teaPreference, sharedPreferencesViewModel.teaSweetenerDisplayValues)
        } else {
            binding.tvTeePreference.text =
                getString(R.string.tee_preference_text, sharedPreferencesViewModel.teaPreference)
        }
    }



    private fun showSmsDialog() {
        val permissionSms = Manifest.permission.READ_SMS
        val grantedReadSms = ContextCompat.checkSelfPermission(requireContext(), permissionSms) == PackageManager.PERMISSION_GRANTED

        if(grantedReadSms) {
            accessSmsContent()
        } else {
            requestPermissionLauncher.launch(permissionSms)
        }
    }

    private fun accessSmsContent() {
        val cursor = requireActivity().contentResolver.query(
            Telephony.Sms.Inbox.CONTENT_URI, arrayOf(Telephony.Sms.Inbox._ID, Telephony.Sms.Inbox.BODY),
            null,
            null,
            null
        )
        context?.let {
            AlertDialog.Builder(it)
                .setTitle("SMS in Inbox")
                .setCursor(cursor, null, Telephony.TextBasedSmsColumns.BODY)
                .setNeutralButton("Ok", null)
                .create()
                .show()
        }
    }

}
