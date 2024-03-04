package ch.hslu.mopro.firstappfinal

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import ch.hslu.mopro.firstappfinal.lifecyclelog.LifecycleLogActivity
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    companion object {
        val QUESTION = "question"
        val ANSWER = "answer"
    }

    private val openQuestionActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            //Done check if the result is ok and set the content to the textview
            if (result.resultCode == Activity.RESULT_OK) {
                val textView = findViewById<TextView>(R.id.main_textView_result)

                var answer = resources.getString(R.string.main_text_gotAnswer)
                result.data?.extras?.let { extra: Bundle ->
                    answer = answer.plus(extra.getString(ANSWER) ?: "")
                }
                textView.text = answer
            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<Button>(R.id.main_button_logActivity).setOnClickListener { startLogActivity() }
        findViewById<Button>(R.id.main_button_startBrowser).setOnClickListener { startBrowser() }
        findViewById<Button>(R.id.main_button_questionActivity).setOnClickListener { startQuestionActivity() }

    }


    private fun startLogActivity() {
        // Done: start LifecylceLogActivity mit LifecycleLogFragment
        val changePage = Intent(this, LifecycleLogActivity::class.java)
        startActivity(changePage)

       


    }

    private fun startBrowser() {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse("https://www.hslu.ch/de-ch/")
        startActivity(openURL)

    }

    private fun startQuestionActivity() {
        // Done: launch QuestionActivity with Intent

        val changePage = Intent(this, QuestionActivity::class.java).apply {

                putExtra(QUESTION, "und, wie läuft's so mit der Androidprogrammierung bis jetzt?")

        }
        openQuestionActivity.launch(changePage)

    }
}