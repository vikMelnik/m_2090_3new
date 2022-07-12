package come.geekbrains.vitekm.m_2090_3.view.animation

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Explode
import androidx.transition.TransitionManager
import come.geekbrains.vitekm.m_2090_3.R
import come.geekbrains.vitekm.m_2090_3.databinding.ActivityAnimationBinding


class AnimationActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAnimationBinding
    var isFlag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        binding.recyclerView.adapter = Adapter()

    }
    inner class Adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return MyViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.activity_animation_explode_recycle_view_item,
                    parent,
                    false
                ) as View
            )
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            holder.itemView.setOnClickListener {

//                val rect = Rect(it.x.toInt(), it.y.toInt(),
//                    it.x.toInt() + it.width,
//                    it.x.toInt() + it.height)
                val rect = Rect()
                it.getGlobalVisibleRect(rect)

                val explode = Explode()
                explode.duration = 1000L
                explode.epicenterCallback = object : androidx.transition.Transition.EpicenterCallback(){
                    override fun onGetEpicenter(transition: androidx.transition.Transition): Rect {
                       return rect
                    }

                }

                TransitionManager.beginDelayedTransition(binding.recyclerView,
                    explode)
                binding.recyclerView.adapter = null
            }
        }

            override fun getItemCount(): Int {
                return 32
            }

            inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

        }
    }


