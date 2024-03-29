package come.geekbrains.vitekm.m_2090_3.view.naviation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.badge.BadgeDrawable
import come.geekbrains.vitekm.m_2090_3.R
import come.geekbrains.vitekm.m_2090_3.databinding.ActivityBottomNavigationViewBinding

class BottomBarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationViewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_view_earth -> {
                    navigateTo(EarthFragment()); true
                }
                R.id.action_view_mars -> {
                    navigateTo(MarsFragment());true
                }
                R.id.action_view_system -> {
                    navigateTo(SystemFragment()); true
                }
                else -> true
            }

        }
        binding.bottomNavigationView.selectedItemId = R.id.action_view_earth

        val badgeS = binding.bottomNavigationView.getOrCreateBadge(R.id.action_view_system)
        val badgeM = binding.bottomNavigationView.getOrCreateBadge(R.id.action_view_mars)
         //badge
        badgeS.number = 99
        badgeS.maxCharacterCount = 3
        badgeS.badgeGravity = BadgeDrawable.TOP_START

        badgeM.number = 10000
        badgeM.maxCharacterCount = 5
        badgeM.badgeGravity = BadgeDrawable.BOTTOM_END

        //Очистка бейджа
        //binding.bottomNavigationView.removeBadge(R.id.action_view_system)
    }

    private fun navigateTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }

}