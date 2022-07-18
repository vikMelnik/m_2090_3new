package come.geekbrains.vitekm.m_2090_3.view.naviation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import come.geekbrains.vitekm.m_2090_3.R
import come.geekbrains.vitekm.m_2090_3.databinding.FragmentEarthBinding

class EarthFragment : Fragment() {

    private var _binding: FragmentEarthBinding? = null
    private val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEarthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = ViewPager2AdapterForEarthFragment(this)
        setTabs()
    }

    private fun setTabs() {
        TabLayoutMediator(binding.tabLayout,
            binding.viewPager
        ) { tab, position ->
            tab.text = when (position) {
                0 -> {
                    resources.getString(R.string.today)
                }
                1 -> {
                    resources.getString(R.string.yesterday)
                }
                2 -> {
                    resources.getString(R.string.day_before_yesterday)
                }
                else -> "Земля"
            }
        }.attach()
    }
}
