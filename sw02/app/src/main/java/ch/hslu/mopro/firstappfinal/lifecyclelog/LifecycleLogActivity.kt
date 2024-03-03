package ch.hslu.mopro.firstappfinal.lifecyclelog

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import ch.hslu.mopro.firstappfinal.R


class LifecycleLogActivity : AppCompatActivity() {
	private val logTag = "hslu_mobApp_yr"

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_lifecycle_logger)
		Log.i(logTag, "Activity onCreate() aufgerufen")
		if (savedInstanceState == null) {
			//Done: show LifecycleLogFragment
			if(findViewById<FrameLayout>(R.id.fragment_host) != null) {
				supportFragmentManager.beginTransaction().add(R.id.fragment_host, LifecycleLogFragment()).commit()
			}
		}
	}
	// Done: Add further implementions of onX-methods.

	override fun onStart() {
		super.onStart()
		Log.i(logTag, "Activity onStart() aufgerufen")

	}
	override fun onRestart() {
		super.onRestart()
		Log.i(logTag, "Activity onRestart() aufgerufen")

	}
	override fun onResume() {
		super.onResume()
		Log.i(logTag, "Activity onResume() aufgerufen")

	}
	override fun onPause() {
		super.onPause()
		Log.i(logTag, "Activity onPause() aufgerufen")

	}
	override fun onStop() {
		super.onStop()
		Log.i(logTag,"Activity onStop() aufgerufen")

	}
	override fun onDestroy() {
		super.onDestroy()
		Log.i(logTag,"Activity onStop() aufgerufen")

	}

}