package com.example.taskn7

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatEditText
import com.example.taskn7.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var addButton: AppCompatButton
    private lateinit var enterFieldEditText: AppCompatEditText
    private lateinit var checkIfNumeric: AppCompatCheckBox
    private val editTexts = mutableListOf<String>()
    private lateinit var editTextContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding){
            editTextContainer = etContainer
            enterFieldEditText = fieldNameEt
            checkIfNumeric = checkNumeric
            addButton = addFieldBtn
        }
        addButtonClick()
    }

    private fun addButtonClick() {
        addButton.setOnClickListener {
            val enteredText: String = enterFieldEditText.text.toString().trim()
            if (enteredText.isNotEmpty() && !editTexts.contains(enteredText)) {
                createEditText(enteredText)
            } else {
                if (enteredText.isEmpty()) {
                    showToast(this, "Enter the field")
                } else {
                    showToast(this, "such field already exist")
                }
            }
        }
    }

    private fun showToast(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
    private fun createEditText(text: String){
        val newEditText = AppCompatEditText(this)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.topMargin =
            resources.getDimensionPixelSize(R.dimen.edit_text_margin_top)
        with(newEditText) {
            inputType = if (checkIfNumeric.isChecked) {
                InputType.TYPE_CLASS_NUMBER
            } else {
                InputType.TYPE_CLASS_TEXT
            }
            id = View.generateViewId()
            setLines(1)
            this.layoutParams = layoutParams
            hint = "Enter $text"
        }
        editTextContainer.addView(newEditText)
        editTexts.add(enterFieldEditText.text.toString())
        enterFieldEditText.setText("")
    }
}