package ru.borisov.approvedelete

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var inputEditText: EditText
    lateinit var outputTextView: TextView
    lateinit var saveButton: Button
    lateinit var deleteButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputEditText = findViewById(R.id.inputTextED)
        outputTextView = findViewById(R.id.outputTextTV)
        saveButton = findViewById(R.id.saveBTN)
        deleteButton = findViewById(R.id.deleteBTN)
        saveButton.setOnClickListener(::onSaveClick)
        deleteButton.setOnClickListener(::onDeleteClick)
    }

    private fun onSaveClick(view: View) {
        if (inputEditText.text.isEmpty()) {
            Snackbar.make(view, getString(R.string.text_nothing_input), Snackbar.LENGTH_SHORT).show()
        } else {
            outputTextView.text = inputEditText.text.toString()
        }
    }

    private fun onDeleteClick(view: View) {
        hideKeyboard()
        if (inputEditText.text.isEmpty()) {
            Snackbar.make(view, getString(R.string.text_not_saved), Snackbar.LENGTH_SHORT).show()
        } else {
            Snackbar.make(view, getString(R.string.text_approve_delete), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.text_delete)) {
                    outputTextView.text = resources.getString(R.string.text_output)
                    inputEditText.setText("")
                    Snackbar.make(it, getString(R.string.text_data_delete), Snackbar.LENGTH_LONG).show()
                }
                .show()
        }
    }

    private fun hideKeyboard() {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(inputEditText.windowToken, 0)
    }
}

