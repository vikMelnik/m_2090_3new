package come.geekbrains.vitekm.m_2090_3.view.naviation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.google.android.material.tabs.TabLayoutMediator
import come.geekbrains.vitekm.m_2090_3.databinding.ActivityViewPagerBinding

class ViewPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager2.adapter = ViewPagerAdapter(this)

        setTabs()
    }

    private fun setTabs() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager2
        ) { tab, position ->
            tab.text = when (position) {
                0 -> {
                    "Earth"
                }
                1 -> {
                    "Mars"
                }
                2 -> {
                    "Sun System"
                }
                else -> "Earth"
            }
        }.attach()
    }
}