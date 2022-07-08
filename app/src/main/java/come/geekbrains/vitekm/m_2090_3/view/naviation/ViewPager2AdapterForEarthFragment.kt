package come.geekbrains.vitekm.m_2090_3.view.naviation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import come.geekbrains.vitekm.m_2090_3.utils.day
import come.geekbrains.vitekm.m_2090_3.utils.month
import come.geekbrains.vitekm.m_2090_3.utils.year
import come.geekbrains.vitekm.m_2090_3.view.picture.PictureOfTheDayFragment

class ViewPager2AdapterForEarthFragment(fr: Fragment) : FragmentStateAdapter(fr) {

    private val bundleToday = Bundle().apply { putInt(PictureOfTheDayFragment.DAY_BUNDLE_EXTRA, 0) }
    private val bundleYesterday = Bundle().apply { putInt(PictureOfTheDayFragment.DAY_BUNDLE_EXTRA, 1) }
    private val bundleDayBeforeYesterday = Bundle().apply { putInt(PictureOfTheDayFragment.DAY_BUNDLE_EXTRA, 2) }

    private val fragments = arrayOf(
        PictureOfTheDayFragment.newInstance(bundleToday),
        PictureOfTheDayFragment.newInstance(bundleYesterday),
        PictureOfTheDayFragment.newInstance(bundleDayBeforeYesterday))

   // private val fragments = arrayOf(PictureOfTheDayFragment.newInstance(), PictureOfTheDayFragment.newInstance(), PictureOfTheDayFragment.newInstance())
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}
