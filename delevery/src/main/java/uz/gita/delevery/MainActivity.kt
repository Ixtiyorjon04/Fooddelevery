package uz.gita.delevery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import uz.gita.core.OrderRepository
import uz.gita.core.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: RecyclerAdapter
    private lateinit var rv: RecyclerView
    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository = Repository
        rv = findViewById(R.id.rv)
        adapter = RecyclerAdapter()
        repository.orderRepository.getAllOrderTwo().observe(this) {
            adapter.submitList(it.getOrNull()?.toList())
        }
        rv.adapter = adapter

    }
}