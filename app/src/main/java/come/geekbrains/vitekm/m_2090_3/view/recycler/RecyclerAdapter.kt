package come.geekbrains.vitekm.m_2090_3.view.recycler


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import come.geekbrains.vitekm.m_2090_3.databinding.ActivityMainRecyclerItemEarthBinding
import come.geekbrains.vitekm.m_2090_3.databinding.ActivityMainRecyclerItemHeaderBinding
import come.geekbrains.vitekm.m_2090_3.databinding.ActivityMainRecyclerItemMarsBinding
import come.geekbrains.vitekm.m_2090_3.view.recycler.model.Data
import come.geekbrains.vitekm.m_2090_3.view.recycler.model.TYPE_EARTH
import come.geekbrains.vitekm.m_2090_3.view.recycler.model.TYPE_MARS

class RecyclerAdapter(private val listData: List<Data>) :
    RecyclerView.Adapter<BaseViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return listData[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

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

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
       holder.bind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

}

class EarthViewHolder(val binding: ActivityMainRecyclerItemEarthBinding) :
    BaseViewHolder(binding.root) {
    override fun bind(data: Data) {
        binding.name.text = data.name
    }

}

class MarsViewHolder(val binding: ActivityMainRecyclerItemMarsBinding) :
    BaseViewHolder(binding.root) {
    override fun bind(data: Data) {
        binding.name.text = data.name
    }

}

class HeaderViewHolder(val binding: ActivityMainRecyclerItemHeaderBinding) :
    BaseViewHolder(binding.root) {
    override fun bind(data: Data) {
        binding.name.text = data.name
    }

}

abstract class BaseViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {
    abstract fun bind(data: Data)
}
