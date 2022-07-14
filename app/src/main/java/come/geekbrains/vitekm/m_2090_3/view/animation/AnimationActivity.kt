package come.geekbrains.vitekm.m_2090_3.view.animation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.Rect
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*
import come.geekbrains.vitekm.m_2090_3.R
import come.geekbrains.vitekm.m_2090_3.databinding.*


class AnimationActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAnimationsFabScrolBinding
    var isFlag = false
    private val duration = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationsFabScrolBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.scrollView.setOnScrollChangeListener { _, _, _, _, _ ->
            binding.toolbar.isSelected = binding.scrollView.canScrollVertically(-1)
        }

        // 4) Mix
        val titles: MutableList<String> = ArrayList()
        for (i in 0..4) {
            titles.add(String.format("Item %d", i + 1))
        }

        binding.fab.setOnClickListener {
            isFlag = !isFlag

            if(isFlag){
                ObjectAnimator.ofFloat(binding.plusImageview, View.ROTATION, 0f, 675f).setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.optionOneContainer, View.TRANSLATION_Y, -130f).setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.optionTwoContainer, View.TRANSLATION_Y, -230f).setDuration(duration).start()

                ObjectAnimator.ofFloat(binding.transparentBackground, View.ALPHA, 0f).setDuration(duration).start()

                binding.optionOneContainer.animate().alpha(1f).setDuration(duration).setListener(
                    object : AnimatorListenerAdapter(){
                        override fun onAnimationEnd(animation: Animator?) {
                            binding.optionOneContainer.isClickable = true
                        }
                    }
                )
                binding.optionTwoContainer.animate().alpha(1f).setDuration(duration).setListener(
                    object : AnimatorListenerAdapter(){
                        override fun onAnimationEnd(animation: Animator?) {
                            binding.optionTwoContainer.isClickable = true
                        }
                    }
                )

            }else{
                ObjectAnimator.ofFloat(binding.plusImageview, View.ROTATION, 675f, 0f).setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.optionOneContainer, View.TRANSLATION_Y, 0f).setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.optionTwoContainer, View.TRANSLATION_Y, 0f).setDuration(duration).start()

                ObjectAnimator.ofFloat(binding.transparentBackground, View.ALPHA, 0.8f).setDuration(duration).start()

                binding.optionOneContainer.animate().alpha(0f).setDuration(duration).setListener(
                    object : AnimatorListenerAdapter(){
                        override fun onAnimationEnd(animation: Animator?) {
                            binding.optionOneContainer.isClickable = false
                        }
                    }
                )
                binding.optionTwoContainer.animate().alpha(0f).setDuration(duration).setListener(
                    object : AnimatorListenerAdapter(){
                        override fun onAnimationEnd(animation: Animator?) {
                            binding.optionTwoContainer.isClickable = false
                        }
                    }
                )
            }

        }
    }
}


