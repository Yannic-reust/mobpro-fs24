package ch.hslu.mopro.firstappfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    companion object {
        val QUESTION = "question"
        val ANSWER = "answer"
    }

    private val openQuestionActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            //TODO check if the result is ok and set the content to the textview
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<Button>(R.id.main_button_logActivity).setOnClickListener { startLogActivity() }
        findViewById<Button>(R.id.main_button_startBrowser).setOnClickListener { startBrowser() }
        findViewById<Button>(R.id.main_button_questionActivity).setOnClickListener { startQuestionActivity() }

    }


    private fun startLogActivity() {
        // TODO: start LifecylceLogActivity mit LifecycleLogFragment
    }

    private fun startBrowser() {
        // TODO: start Browser with http://www.hslu.ch
    }

    private fun startQuestionActivity() {
        // TODO: launch QuestionActivity with Intent
    }
}