import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.DataItem
import com.example.domain.entity.SurahResponse
import com.example.graduation_project.databinding.ListItemSoraNameBinding

class SurahAdapter(private val itemClickListener: SurahItemClickListener) :
    RecyclerView.Adapter<SurahAdapter.SurahViewHolder>() {

    private val dataList = mutableListOf<DataItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        val binding = ListItemSoraNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SurahViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
        val dataItem = dataList[position]
        holder.bind(dataItem)
    }

    override fun getItemCount() = dataList.size

    fun setData(dataList: List<DataItem>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }
    interface SurahItemClickListener {
        fun onSurahItemClick(dataItem: DataItem)
    }
    inner class SurahViewHolder(private val binding: ListItemSoraNameBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedItem = dataList[position]
                    itemClickListener.onSurahItemClick(clickedItem)
                }
            }
        }
        fun bind(dataItem: DataItem) {
            binding.dataItem = dataItem
            binding.executePendingBindings()
        }
    }
}