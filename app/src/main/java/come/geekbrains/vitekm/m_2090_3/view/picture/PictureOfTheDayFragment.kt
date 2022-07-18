package come.geekbrains.vitekm.m_2090_3.view.picture


import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BulletSpan
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.text.style.TypefaceSpan
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.provider.FontRequest
import androidx.core.provider.FontsContractCompat
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
                binding.textView.text = appState.pictureOfTheDayResponseData.explanation
                binding.textView.typeface = Typeface.createFromAsset(requireActivity().assets, "folder1/folder2/Aloevera.ttf")

                val spanned: Spanned
                val spannableString: SpannableString
                var spannableStringBuilder: SpannableStringBuilder

                val text = "My text \nbullet one \nbulleterter two\nbullet wetwwefrtweteone \nbullet wetwettwo\nbullet wetwetwone \nbullet two"
                spannableStringBuilder = SpannableStringBuilder(text)

                binding.textView.setText(spannableStringBuilder, TextView.BufferType.EDITABLE)
                spannableStringBuilder = binding.textView.text as SpannableStringBuilder

                val result = text.indexesOf("\n")
                var current = result.first()

                result.forEach {
                    if(current!=it){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                            spannableStringBuilder.setSpan(BulletSpan(20,ContextCompat.getColor(requireContext(),R.color.my_color),20),
                                current+1,it,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                        }
                    }
                    current = it
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    spannableStringBuilder.setSpan(BulletSpan(20,ContextCompat.getColor(requireContext(),R.color.my_color),20),
                        current+1,text.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }

                Log.d("@@@",result.toString())


                for (i in text.indices){
                    if(text[i]=='t'){
                        spannableStringBuilder.setSpan(
                            ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.my_color)),
                            i,i+1,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    }
                }

                val bitmap = ContextCompat.getDrawable(requireContext(), R.drawable.ic_earth)!!.toBitmap()
                for (i in text.indices){
                    if(text[i]=='o'){
                        spannableStringBuilder.setSpan(
                            ImageSpan(requireContext(), bitmap),
                            i,i+1,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    }
                }

                spannableStringBuilder.insert(3, "word ")
//                binding.textView.text = spannableStringBuilder

                val request = FontRequest("com.google.android.gms.fonts","com.google.android.gms","Aladin",
                    R.array.com_google_android_gms_fonts_certs)

                val callback = object : FontsContractCompat.FontRequestCallback(){
                    override fun onTypefaceRetrieved(typeface: Typeface?) {
                        typeface?.let{
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                                spannableStringBuilder.setSpan(
                                    TypefaceSpan(it),
                                    0,spannableStringBuilder.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                            }
                        }

                        super.onTypefaceRetrieved(typeface)
                    }
                }

                FontsContractCompat.requestFont(requireContext(),request,callback, Handler(Looper.getMainLooper()))
            }
        }

    }
    private fun String.indexesOf(substr: String, ignoreCase: Boolean = true): List<Int> =
        (if (ignoreCase) Regex(substr, RegexOption.IGNORE_CASE) else Regex(substr))
            .findAll(this).map { it.range.first }.toList()

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

