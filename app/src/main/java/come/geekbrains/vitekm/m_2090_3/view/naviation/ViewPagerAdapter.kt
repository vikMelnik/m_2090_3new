package come.geekbrains.vitekm.m_2090_3.view.naviation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragments = arrayOf(EarthFragment(), MarsFragment(), SystemFragment())

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}

    //    override fun createFragment(position: Int): Fragment {
//        return when (position) {
//            0 -> fragments[EARTH_FRAGMENT]
//            1 -> fragments[MARS_FRAGMENT]
//            2 -> fragments[SYSTEM_FRAGMENT]
//            else -> fragments[EARTH_FRAGMENT]
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return fragments.size
//    }
//
//    companion object {
//    private const val EARTH_FRAGMENT = 0
//    private const val MARS_FRAGMENT = 1
//    private const val SYSTEM_FRAGMENT = 2
//}


