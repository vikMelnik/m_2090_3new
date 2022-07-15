package come.geekbrains.vitekm.m_2090_3.view.recycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import come.geekbrains.vitekm.m_2090_3.databinding.ActivityMainRecyclerBinding
import come.geekbrains.vitekm.m_2090_3.view.recycler.model.Data
import come.geekbrains.vitekm.m_2090_3.view.recycler.model.TYPE_EARTH
import come.geekbrains.vitekm.m_2090_3.view.recycler.model.TYPE_HEADER
import come.geekbrains.vitekm.m_2090_3.view.recycler.model.TYPE_MARS

class RecyclerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainRecyclerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = arrayListOf(
            Data( "Заголовок", type = TYPE_HEADER),
            Data( "Earth", type = TYPE_EARTH),
            Data( "Earth", type = TYPE_EARTH),
            Data("Earth", type = TYPE_EARTH),
            Data( "Earth", type = TYPE_EARTH),
            Data("Earth", type = TYPE_EARTH),
            Data( "Earth", type = TYPE_EARTH),
            Data("Mars", "", type = TYPE_MARS),
            Data( "Earth", type = TYPE_EARTH),
            Data("Earth", type = TYPE_EARTH),
            Data("Earth", type = TYPE_EARTH),
            Data("Mars", null, type = TYPE_MARS)
        )

        binding.recyclerView.adapter = RecyclerAdapter(data)
    }
}