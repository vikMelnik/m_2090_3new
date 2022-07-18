package come.geekbrains.vitekm.m_2090_3.view.naviation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import coil.load
import come.geekbrains.vitekm.m_2090_3.R
import come.geekbrains.vitekm.m_2090_3.databinding.FragmentEarthBinding
import come.geekbrains.vitekm.m_2090_3.databinding.FragmentMarsBinding

class MarsFragment : Fragment() {

    private var _binding: FragmentMarsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMarsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mars.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.mars1))
        }
}
