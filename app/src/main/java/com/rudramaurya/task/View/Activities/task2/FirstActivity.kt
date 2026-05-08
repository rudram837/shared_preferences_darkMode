package com.rudramaurya.task.View.Activities.task2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.rudramaurya.task.R
import com.rudramaurya.task.databinding.ActivityFirstBinding
import com.rudramaurya.task.utils.PrefManager

class FirstActivity : AppCompatActivity() {
    private lateinit var binding:
            ActivityFirstBinding
    private var isDarkTheme = false
    private lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {

        prefManager = PrefManager(this)

        if (prefManager.isDarkMode()) {
            AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES
            )
        } else {
            AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO
            )
        }

        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefManager = PrefManager(this)

        binding.submitFirst.setOnClickListener {

            val name = binding.name.text.toString().trim()
            val text1 = binding.text1.text.toString().trim()
            val text2 = binding.text2.text.toString().trim()

            if (name.isEmpty() && text1.isEmpty() && text2.isEmpty()) {
                Toast.makeText(this, "Please enter atleast 1 fields", Toast.LENGTH_SHORT).show()
            } else {
                if (name.isNotEmpty()) {
                    prefManager.saveList("field1", name)
                }
                if (text1.isNotEmpty()) {
                    prefManager.saveList("field2", text1)
                }
                if (text2.isNotEmpty()) {
                    prefManager.saveList("field3", text2)
                }
                startActivity(Intent(this, SecondActivity::class.java))
                clear()
            }
        }

        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
//            isDarkTheme = isChecked
//            toggleTheme()
            prefManager.saveTheme(isChecked)

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
        binding.switchTheme.isChecked =
            prefManager.isDarkMode()
    }

    private fun clear() {
        binding.name.text!!.clear()
        binding.text1.text?.clear()
        binding.text2.text?.clear()
    }

    private fun toggleTheme() {
        if (isDarkTheme) {
            setTheme(R.style.AppTheme_Light)

        } else {
            setTheme(R.style.AppTheme_Dark)
        }
        recreate() // Recreate the activity to apply the new theme
        isDarkTheme = !isDarkTheme // Toggle the theme flag
    }
}