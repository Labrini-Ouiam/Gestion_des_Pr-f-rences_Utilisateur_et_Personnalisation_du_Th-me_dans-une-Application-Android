package com.example.ex2preferencespartagees

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import android.content.res.ColorStateList
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var mainLayout: ConstraintLayout
    private lateinit var themeGroup: RadioGroup
    private lateinit var level : RadioGroup
    private lateinit var preferences: SharedPreferences
    private lateinit var son: SeekBar
    private lateinit var luminosite: SeekBar
    private lateinit var saveButton : Button

    companion object {
        const val PREFS_NAME = "AppPrefs"
        const val THEME_KEY = "themeColor"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainLayout = findViewById(R.id.main)
        themeGroup = findViewById(R.id.theme)
        level = findViewById(R.id.level)
        son = findViewById(R.id.son)
        luminosite = findViewById(R.id.luminosite)
        saveButton = findViewById(R.id.saveButton)
        preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        val savedTheme = preferences.getString(THEME_KEY, "Blue") ?: "Blue"
        applyTheme(savedTheme)
    }

    fun onThemeSelected(view: View) {
        if (view is RadioButton) {
            val selectedTheme = view.text.toString()
            saveTheme(selectedTheme)
            applyTheme(selectedTheme)
        }
    }

    private fun saveTheme(theme: String) {
        preferences.edit().putString(THEME_KEY, theme).apply()
    }

    private fun applyTheme(theme: String) {
        val color = when (theme) {
            "Rouge" -> Color.RED
            "Blue" -> Color.BLUE
            "Vert" -> Color.GREEN
            else -> Color.WHITE
        }

        // Appliquer la couleur au fond de la mise en page
            //mainLayout.setBackgroundColor(color)

        // Changer la couleur des boutons radio
        for (i in 0 until themeGroup.childCount) {
            val child = themeGroup.getChildAt(i)
            if (child is RadioButton) {
                child.buttonTintList = ColorStateList.valueOf(color)
            }
        }

        for (i in 0 until level.childCount) {
            val child = level.getChildAt(i)
            if (child is RadioButton) {
                child.buttonTintList = ColorStateList.valueOf(color)
            }
        }

        // Changer la couleur des SeekBars
        son.progressTintList = ColorStateList.valueOf(color)
        luminosite.progressTintList = ColorStateList.valueOf(color)
        // Changer la couleur de fond du bouton
        saveButton.backgroundTintList = ColorStateList.valueOf(color)
    }
}
