package com.example.climby.ui.publish

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.climby.R
import com.example.climby.data.model.school.SchoolModel
import com.example.climby.databinding.ActivityWhatPlaceBinding
import com.example.climby.ui.publish.viewmodel.WhatPlaceViewModel
import com.example.climby.view.activity.MainActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WhatPlaceActivity : AppCompatActivity() {

    private lateinit var whatPlaceViewModel: WhatPlaceViewModel
    private lateinit var binding: ActivityWhatPlaceBinding

    private lateinit var school: String
    private lateinit var date: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        whatPlaceViewModel = ViewModelProvider(this).get(WhatPlaceViewModel::class.java)
        binding = ActivityWhatPlaceBinding.inflate(layoutInflater)

        setContentView(binding.root)

        showKeyboard()
        getData()

        binding.IVBack.setOnClickListener {
            onBackPressed()
            closeKeyboard()
        }

        binding.ACSchool.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                checkControls()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                checkControls()
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                checkControls()
            }
        })

        whatPlaceViewModel.schoolsModel.observe(this, {
            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, it)
            binding.ACSchool.setAdapter(adapter)
        })

        binding.ACSchool.setOnItemClickListener { parent, _, position, _ ->
            school = parent.getItemAtPosition(position) as String
            closeKeyboard()
        }

        binding.BTSave.setOnClickListener {
            /*onBackPressed()*/
            /*replaceFragment()*/
            replaceFragment()
            closeKeyboard()
        }

        whatPlaceViewModel.getAllSchools()
    }

    private fun replaceFragment() {
        val intent = Intent(applicationContext.applicationContext, MainActivity::class.java).apply {

            putExtra("schoolPublish", binding.ACSchool.text.toString())
            putExtra("provincePublish", intent.extras?.getInt("provincePublish", 0))
            putExtra("typePublish", intent.extras?.getInt("typePublish", 0))
            putExtra("datePublish", intent.extras?.getString("datePublish", ""))
            putExtra("datePublishWithOutFormat", intent.extras?.getString("datePublishWithOutFormat", ""))
            putExtra("placePublish", intent.extras?.getInt("placePublish", 0))
        }
        startActivity(intent)
        finish()
        overridePendingTransition(0, R.anim.slide_in_down)

    }

    private fun showKeyboard() {
        binding.ACSchool.requestFocus()
        val inputMethodManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    private fun closeKeyboard() {
        val inputMethodManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }

    fun checkControls() {
        if (binding.ACSchool.text.toString().isNotEmpty()) {
            binding.BTSave.isEnabled = true
            binding.BTSave.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.primary))
        } else {
            binding.BTSave.isEnabled = false
            binding.BTSave.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.grey))
        }
    }

    private fun getData() {
        val bundle = intent.extras
        if (bundle != null) {
            school = bundle.getString("schoolPublish", "")

        }
    }
}