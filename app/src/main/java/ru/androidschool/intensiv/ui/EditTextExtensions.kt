package ru.androidschool.intensiv.ui

import android.text.Editable
import android.widget.EditText
import androidx.core.widget.addTextChangedListener

fun EditText.afterTextChanged(action: (s: Editable?) -> Unit) =
    addTextChangedListener(afterTextChanged = action)

fun EditText.onTextChanged(action: (text: CharSequence?, start: Int?, before: Int?, count: Int?) -> Unit) =
    addTextChangedListener(onTextChanged = action)
