package come.geekbrains.vitekm.m_2090_3.view.animation

import android.os.Bundle
import android.view.animation.AnticipateOvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import come.geekbrains.vitekm.m_2090_3.R
import come.geekbrains.vitekm.m_2090_3.databinding.*


class AnimationActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAnimationsBonusStartBinding
    var isFlag = false
    private val duration = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationsBonusStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val constraintSet = ConstraintSet()
        //constraintSetStart.clone(binding.constraintContainer)
        constraintSet.clone(this, R.layout.activity_animations_bonus_start)


        binding.backgroundImage.setOnClickListener{
            isFlag = !isFlag

            val transition =  ChangeBounds()
            transition.interpolator = AnticipateOvershootInterpolator(5.0f)
            transition.duration = 1200
            TransitionManager.beginDelayedTransition(binding.constraintContainer, transition)

            if (isFlag){
                constraintSet.connect(R.id.title, ConstraintSet.RIGHT, R.id.backgroundImage, ConstraintSet.RIGHT)

            }else{
                constraintSet.connect(R.id.title, ConstraintSet.RIGHT, R.id.backgroundImage, ConstraintSet.LEFT)

            }
            constraintSet.applyTo(binding.constraintContainer)
        }
    }
}




