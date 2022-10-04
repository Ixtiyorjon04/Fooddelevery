package uz.gita.fooddelevery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.core.OrderData

class RecyclerAdapter : ListAdapter<OrderData, RecyclerAdapter.ViewHolder>(
    RecyclerDiffUtilCallback
) {

    private var listener: ((OrderData, CheckBox) -> Unit)? = null

    fun submitListener(block: (OrderData, CheckBox) -> Unit) {
        listener = block
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val name = itemView.findViewById<TextView>(R.id.name)
        val price = itemView.findViewById<TextView>(R.id.price)
        var checkBox = itemView.findViewById<CheckBox>(R.id.checkbox)

        init {
            checkBox.setOnClickListener {
                checkBox.isChecked = !checkBox.isChecked
                listener?.invoke(getItem(adapterPosition), checkBox)
            }
        }

        fun onBind() {
            name.text = getItem(adapterPosition).name
            price.text = getItem(adapterPosition).cost

            checkBox.isChecked = getItem(adapterPosition).status == "1"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_example, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()
}

private val RecyclerDiffUtilCallback = object : DiffUtil.ItemCallback<OrderData>() {
    override fun areItemsTheSame(oldItem: OrderData, newItem: OrderData): Boolean {
        return oldItem.cost == newItem.cost
    }

    override fun areContentsTheSame(oldItem: OrderData, newItem: OrderData): Boolean {
        return oldItem.cost == newItem.cost
                && oldItem.id == newItem.id
                && oldItem.image == newItem.image
                && oldItem.status == newItem.status
                && oldItem.count == newItem.count
    }

}