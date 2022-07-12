package come.geekbrains.vitekm.m_2090_3.view.animation

import android.os.Bundle
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.TransitionManager

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.TransitionSet
import come.geekbrains.vitekm.m_2090_3.databinding.ActivityAnimationBinding


class AnimationActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAnimationBinding
    var isFlag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener{
            isFlag = !isFlag

            val myAutoTransition = TransitionSet()
            myAutoTransition.ordering = TransitionSet.ORDERING_TOGETHER

            val fade = Fade()
            val changeBounds = ChangeBounds()

            myAutoTransition.addTransition(fade)
            myAutoTransition.addTransition(changeBounds)

            TransitionManager.beginDelayedTransition(binding.transitionContainer, myAutoTransition)
            binding.text.visibility = if (isFlag) View.VISIBLE else {
                View.GONE
            }
        }
    }


}