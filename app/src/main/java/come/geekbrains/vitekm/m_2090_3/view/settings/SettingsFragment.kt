package come.geekbrains.vitekm.m_2090_3.view.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayout
import come.geekbrains.vitekm.m_2090_3.MainActivity
import come.geekbrains.vitekm.m_2090_3.ThemePurple
import come.geekbrains.vitekm.m_2090_3.ThemeRed
import come.geekbrains.vitekm.m_2090_3.ThemeYellow
import come.geekbrains.vitekm.m_2090_3.databinding.FragmentSettingsBinding
import come.geekbrains.vitekm.m_2090_3.viewmodel.PictureOfTheDayViewModel

class SettingsFragment : Fragment() {

    private lateinit var parentActivity: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentActivity = (context as MainActivity)
        parentActivity =
            requireActivity() as MainActivity
    }

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root

    }

    private val viewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTabLayoutClicks()
    }

    private fun setTabLayoutClicks()  = with(binding) {
        when ((parentActivity.getCurrentTheme())) {
            ThemeRed -> {
                tabsGroup.selectTab(binding.tabsGroup.getTabAt(0))
            }
            ThemeYellow -> {
                tabsGroup.selectTab(binding.tabsGroup.getTabAt(1))
            }
            ThemePurple -> {
                tabsGroup.selectTab(binding.tabsGroup.getTabAt(2))
            }
        }

        tabsGroup.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        parentActivity.setCurrentTheme(ThemeRed)
                        parentActivity.recreate()
                    }
                    1 -> {
                        parentActivity.setCurrentTheme(ThemeYellow)
                        parentActivity.recreate()
                    }
                    2 -> {
                        parentActivity.setCurrentTheme(ThemePurple)
                        parentActivity.recreate()
                    }
                }
                requireActivity().recreate()
            }


            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}