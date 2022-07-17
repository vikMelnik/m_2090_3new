package come.geekbrains.vitekm.m_2090_3.view.picture


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.snackbar.Snackbar
import come.geekbrains.vitekm.m_2090_3.R
import come.geekbrains.vitekm.m_2090_3.databinding.FragmentPictureBinding
import come.geekbrains.vitekm.m_2090_3.utils.*
import come.geekbrains.vitekm.m_2090_3.view.drawer.BottomNavigationDrawerFragment
import come.geekbrains.vitekm.m_2090_3.view.settings.SettingsFragment
import come.geekbrains.vitekm.m_2090_3.viewmodel.AppState
import come.geekbrains.vitekm.m_2090_3.viewmodel.PictureOfTheDayViewModel



class PictureOfTheDayFragment : Fragment() {

    private var _binding: FragmentPictureBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureBinding.inflate(inflater, container, false)
        return binding.root

    }

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(viewLifecycleOwner) { appState ->
            renderData(appState)
        }

        val dateOfTheDay = when(arguments?.getInt(DAY_BUNDLE_EXTRA)){
            0 -> "$year-$month-$day"
            1 -> "$year1-$month1-$yesterday"
            2 -> "$year2-$month2-$beforeYesterday"
            else -> "$year-$month-$day"
        }

        viewModel.sendRequest(dateOfTheDay)
        Log.d("MyLog", "$year-$month-$day")

        binding.chipDayBefore.setOnClickListener {
            viewModel.sendRequest("$year2-$month2-$beforeYesterday")
        }

        binding.chipToday.setOnClickListener {
            viewModel.sendRequest("$year-$month-$day")
        }

        binding.chipYesterday.setOnClickListener {
            viewModel.sendRequest("$year1-$month1-$yesterday")

        }

        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${binding.input.text.toString()}")
            })
        }
            }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> {}
            R.id.action_settings -> {
                requireActivity().supportFragmentManager.beginTransaction().hide(this)
                    .add(R.id.container, SettingsFragment.newInstance()).addToBackStack("").commit()
            }
            android.R.id.home -> {
                activity?.let {
                    BottomNavigationDrawerFragment().show(it.supportFragmentManager, "tag")
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun renderData(appState: AppState) {

        when (appState) {
            is AppState.Error -> {
                Snackbar.make(
                    binding.pictureFragment,
                    getString(R.string.error), Snackbar.LENGTH_LONG
                )
                    .setAction(getString(R.string.reload))
                   { viewModel.sendRequest("$year-$month-$day") }.show()
            }
            AppState.Loading -> {
                Snackbar.make(
                    binding.pictureFragment,
                    getString(R.string.reload), Snackbar.LENGTH_LONG
                ).show()

            }
            is AppState.Success -> {
                binding.imageView.load(appState.pictureOfTheDayResponseData.url) {
                    placeholder(R.drawable.loading)
                    error(R.drawable.ic_baseline_error_24)
                }
            }
        }

    }
    companion object {
        fun newInstance(bundle: Bundle) : PictureOfTheDayFragment {
            val fragment = PictureOfTheDayFragment()
            fragment.arguments = bundle
            return fragment
        }

        fun newInstance(): PictureOfTheDayFragment {
            return PictureOfTheDayFragment()
        }

        const val DAY_BUNDLE_EXTRA = "DAY_BUNDLE_EXTRA"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

