package come.geekbrains.vitekm.m_2090_3.view.animation

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
import come.geekbrains.vitekm.m_2090_3.databinding.ActivityAnimationBinding
import come.geekbrains.vitekm.m_2090_3.databinding.ActivityAnimationMixBinding
import come.geekbrains.vitekm.m_2090_3.databinding.ActivityAnimationTreckBinding


class AnimationActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAnimationMixBinding
    var isFlag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationMixBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val titles: MutableList<String> = ArrayList()
        for (i in 0..4) {
            titles.add(String.format("Item %d", i + 1))
        }

        binding.button.setOnClickListener{
            isFlag = !isFlag
            TransitionManager.beginDelayedTransition(binding.root)
            binding.transitionsContainer.removeAllViews()

            titles.shuffle()
            titles.forEach {binding.transitionsContainer.addView(
                    TextView(this).apply {
                        text = it
                        ViewCompat.setTransitionName(this, it) // Задали псевдоним
                    }
                )
            }

        }

        // 3) Tracking
//        binding.button.setOnClickListener{
//            isFlag = !isFlag
//
//            val params = it.layoutParams as FrameLayout.LayoutParams
//
//            val changeBounds = ChangeBounds()
//            changeBounds.duration = 2000L
//            changeBounds.setPathMotion(ArcMotion())
//
//            TransitionManager.beginDelayedTransition(binding.root, changeBounds)
//
//            if (isFlag){
//                params.gravity = Gravity.TOP or Gravity.START
//
//            }else{
//                params.gravity = Gravity.BOTTOM or Gravity.END
//
//            }
//
//            it.layoutParams = params    //Иногда работает без нее
//        }


        // 2) Increase objects
//        binding.imageView.setOnClickListener{
//            isFlag = !isFlag
//
//            val params = it.layoutParams as ConstraintLayout.LayoutParams
//
//            val transitionSet = TransitionSet()
//            val changeImageTransform = ChangeImageTransform()
//            val changeBounds = ChangeBounds()
//            changeImageTransform.duration = 2000L
//            changeBounds.duration = 2000L
//
//            transitionSet.addTransition(changeBounds)           // Важен порядок, changeImageTransform после changeBounds
//            transitionSet.addTransition(changeImageTransform)
//
//
//            TransitionManager.beginDelayedTransition(binding.root, transitionSet)
//
//            if (isFlag){
//                params.height = ConstraintLayout.LayoutParams.MATCH_PARENT
//                binding.imageView.scaleType = ImageView.ScaleType.CENTER_CROP
//            }else{
//                params.height = ConstraintLayout.LayoutParams.WRAP_CONTENT
//                binding.imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
//            }
//
//            it.layoutParams = params    //Иногда работает без нее
//        }

        // 1) Transition
//        binding.button.setOnClickListener{
//            isFlag = !isFlag
//
//            val myAutoTransition = TransitionSet()
//            myAutoTransition.ordering = TransitionSet.ORDERING_TOGETHER
//
//            //val fade = Fade()
//            val fade = Hold()
//            //val fade = Slide(Gravity.END)
//            fade.duration = 1000L
//            val changeBounds = ChangeBounds()
//            changeBounds.duration = 2000L
//
//            myAutoTransition.addTransition(fade)
//            myAutoTransition.addTransition(changeBounds)
//
//            TransitionManager.beginDelayedTransition(binding.transitionContainer, myAutoTransition)
//            binding.text.visibility = if (isFlag) View.VISIBLE else {
//                View.GONE
//            }
//        }
//        binding.recyclerView.adapter = Adapter()

    }
//    inner class Adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//            return MyViewHolder(
//                LayoutInflater.from(parent.context).inflate(
//                    R.layout.activity_animation_explode_recycle_view_item,
//                    parent,
//                    false
//                ) as View
//            )
//        }
//
//        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//            holder.itemView.setOnClickListener {
//
//        //                val rect = Rect(it.x.toInt(), it.y.toInt(),
//        //                    it.x.toInt() + it.width,
//        //                    it.x.toInt() + it.height)
//                val rect = Rect()
//                it.getGlobalVisibleRect(rect)
//
//                val explode = Explode()
//                explode.duration = 1000L
//                explode.epicenterCallback = object : androidx.transition.Transition.EpicenterCallback(){
//                    override fun onGetEpicenter(transition: androidx.transition.Transition): Rect {
//                       return rect
//                    }
//
//                }
//
//                TransitionManager.beginDelayedTransition(binding.recyclerView,
//                    explode)
//                binding.recyclerView.adapter = null
//            }
//        }
//
//            override fun getItemCount(): Int {
//                return 32
//            }
//
//            inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
//
//        }
    }


