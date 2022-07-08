package come.geekbrains.vitekm.m_2090_3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import come.geekbrains.vitekm.m_2090_3.databinding.ActivityMainBinding
import come.geekbrains.vitekm.m_2090_3.view.picture.PictureOfTheDayFragment

const val ThemeRed = 1
const val ThemeYellow = 2
const val ThemePurple = 3


class MainActivity : AppCompatActivity() {

    private val KEY_SP = "sp"
    private val KEY_CURRENT_THEME = "current_theme"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        setTheme(getRealStyle(getCurrentTheme()))

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        if(savedInstanceState==null){
//            supportFragmentManager.beginTransaction().replace(R.id.container,
//                PictureOfTheDayFragment.newInstance()).commit()
//        }

    }

    fun setCurrentTheme(currentTheme: Int) {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_CURRENT_THEME, currentTheme)
        editor.apply()
    }


    fun getCurrentTheme(): Int {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        return sharedPreferences.getInt(KEY_CURRENT_THEME, -1)
    }

    private fun getRealStyle(currentTheme: Int): Int {
        return when (currentTheme) {
            ThemeRed -> R.style.MyRedTheme
            ThemeYellow -> R.style.MyYellowTheme
            ThemePurple -> R.style.MyPurpleTheme
            else -> 0
        }
    }
}