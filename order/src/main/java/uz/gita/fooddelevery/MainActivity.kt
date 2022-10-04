package uz.gita.fooddelevery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import uz.gita.core.OrderData
import uz.gita.core.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var rv: RecyclerView
    private lateinit var adapter: RecyclerAdapter
    private lateinit var list: ArrayList<OrderData>
    private lateinit var repository: Repository
    private lateinit var send: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        send = findViewById(R.id.send)

        repository = Repository
        list = ArrayList()

        adapter = RecyclerAdapter()
        adapter.submitList(initList())
        adapter.submitListener { item, check ->
            check.isChecked = !check.isChecked

            if (check.isChecked) {
                list.add(item)
            } else {
                list.remove(item)
            }
        }
        rv = findViewById(R.id.rv)

        send.setOnClickListener {
            Toast.makeText(this, list.size.toString(), Toast.LENGTH_SHORT).show()
            for (i in 0 until list.size) {
                repository.orderRepository.placingOrder(list[i])
            }

        }
        rv.adapter = adapter
    }

    fun initList(): ArrayList<OrderData> {
        return arrayListOf(
            OrderData("0", "Huggy", "", "26 000 so'm", "1", 0.toString()),
            OrderData("1", "Hamburger", "", "26 000 so'm", "1", 0.toString()),
            OrderData("2", "Lavash", "", "26 000 so'm", "1", 0.toString()),
            OrderData("3", "Sandvich", "", "26 000 so'm", "1", 0.toString()),
            OrderData("4", "Hotdog", "", "26 000 so'm", "1", 0.toString()),
            OrderData("5", "Clubs", "", "26 000 so'm", "1", 0.toString()),
            OrderData("6", "Free", "", "26 000 so'm", "1", 0.toString()),
            OrderData("7", "Xonim", "", "26 000 so'm", "1", 0.toString()),
        )
    }
}