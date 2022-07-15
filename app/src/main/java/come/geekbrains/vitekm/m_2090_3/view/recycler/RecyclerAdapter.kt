package come.geekbrains.vitekm.m_2090_3.view.recycler


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import come.geekbrains.vitekm.m_2090_3.databinding.ActivityMainRecyclerItemEarthBinding
import come.geekbrains.vitekm.m_2090_3.databinding.ActivityMainRecyclerItemHeaderBinding
import come.geekbrains.vitekm.m_2090_3.databinding.ActivityMainRecyclerItemMarsBinding
import come.geekbrains.vitekm.m_2090_3.view.recycler.model.Data
import come.geekbrains.vitekm.m_2090_3.view.recycler.model.TYPE_EARTH
import come.geekbrains.vitekm.m_2090_3.view.recycler.model.TYPE_HEADER
import come.geekbrains.vitekm.m_2090_3.view.recycler.model.TYPE_MARS

class RecyclerAdapter(private val listData: List<Data>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return listData[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            TYPE_EARTH -> {
                val binding =
                    ActivityMainRecyclerItemEarthBinding.inflate(LayoutInflater.from(parent.context))
                EarthViewHolder(binding)
            }
            TYPE_MARS -> {
                val binding =
                    ActivityMainRecyclerItemMarsBinding.inflate(LayoutInflater.from(parent.context))
                MarsViewHolder(binding)

            }
            else -> {
                val binding =
                    ActivityMainRecyclerItemHeaderBinding.inflate(LayoutInflater.from(parent.context))
                HeaderViewHolder(binding)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class EarthViewHolder(val binding: ActivityMainRecyclerItemEarthBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    class MarsViewHolder(val binding: ActivityMainRecyclerItemMarsBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    class HeaderViewHolder(val binding: ActivityMainRecyclerItemHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

}