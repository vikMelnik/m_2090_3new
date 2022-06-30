package come.geekbrains.vitekm.m_2090_3.view


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.snackbar.Snackbar
import come.geekbrains.vitekm.m_2090_3.R
import come.geekbrains.vitekm.m_2090_3.databinding.FragmentPictureBinding
import come.geekbrains.vitekm.m_2090_3.viewmodel.AppState
import come.geekbrains.vitekm.m_2090_3.viewmodel.PictureOfTheDayViewModel
import java.util.*


class PictureOfTheDayFragment : Fragment() {

    private val c = Calendar.getInstance()
    private val year = c.get(Calendar.YEAR).toString()

    private val month = (c.get(Calendar.MONTH) + 1).toString()

    private val day = c.get(Calendar.DAY_OF_MONTH).toString()

    private val yesterday = (c.get(Calendar.DAY_OF_MONTH) - 1).toString()
    private val beforeYesterday = (c.get(Calendar.DAY_OF_MONTH) - 2).toString()

    //private val date: String = SimpleDateFormat("yyyy-MM-dd").format(Date())


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
        viewModel.sendRequest("$year-$month-$day")
        Log.d("MyLog", "$year-$month-$day")

        binding.chipDayBefore.setOnClickListener {
            viewModel.sendRequest("$year-$month-$beforeYesterday")
        }

        binding.chipToday.setOnClickListener {
            viewModel.sendRequest("$year-$month-$day")
        }
        //binding.chipYesterday.isEnabled = false
        binding.chipYesterday.setOnClickListener {
            viewModel.sendRequest("$year-$month-$yesterday")
            Log.d("MyLog", "$year-$month-$yesterday")
        }

        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${binding.input.text.toString()}")
            })
        }
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
        fun newInstance() = PictureOfTheDayFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}