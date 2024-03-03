package ch.hslu.mopro.firstappfinal.lifecyclelog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ch.hslu.mopro.firstappfinal.R

class LifecycleLogFragment : Fragment(R.layout.fragment_lifecycle_logger) {
	private val logTag = "hslu_mobApp_yr"
	companion object {
		fun newInstance(): LifecycleLogFragment {
			return LifecycleLogFragment()
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		Log.i(logTag, "Fragment onCreate() aufgerufen")
	}

	override fun onCreateView(
		//create Layout
		inflater: LayoutInflater,
		//parent container
		container: ViewGroup?,
		//prev. state
		savedInstanceState: Bundle?
	): View? {
		Log.i(logTag, "Fragment onCreateView() aufgerufen")
		return super.onCreateView(inflater, container, savedInstanceState)

	}

	// Done: Add further implementations of onX-methods.

	override fun onStart() {
		super.onStart()
		Log.i(logTag, "Fragment onStart() aufgerufen")
	}

	override fun onResume() {
		super.onResume()
		Log.i(logTag, "Fragment onResume() aufgerufen")
	}

	override fun onPause() {
		super.onPause()
		Log.i(logTag, "Fragment onPause() aufgerufen")
	}


	override fun onStop() {
		super.onStop()
		Log.i(logTag, "Fragment onStop() aufgerufen")
	}

	override fun onDestroy() {
		super.onDestroy()
		Log.i(logTag, "Fragment onViewCreated() aufgerufen")
	}


}
