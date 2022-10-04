package uz.gita.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class OrderRepositoryImpl : OrderRepository {

    private val db = Firebase.firestore
    val massageLiveData = MutableLiveData<Result<Unit>>()

    override fun placingOrder(orderData: OrderData): LiveData<Result<Unit>> {

        db.collection("orders").document(orderData.id).set(orderData)
            .addOnSuccessListener { massageLiveData.value = Result.success(Unit) }
            .addOnFailureListener { massageLiveData.value = Result.failure(it) }

        return massageLiveData
    }

    override fun getAllOrder(): LiveData<Result<List<OrderData>>> {

        val list = MutableLiveData<Result<List<OrderData>>>()

        db.collection("orders").get().addOnSuccessListener {

            val ls = it.documents.map { item -> Mapper.run { item.toOrder() } }
            list.value = Result.success(ls)
        }
            .addOnFailureListener {
                list.value = Result.failure(it)
            }
        return list
    }

    override fun getAllOrderTwo(): LiveData<Result<List<OrderData>>> {

        val liveData = MediatorLiveData<Result<List<OrderData>>>()
        liveData.addDisposable(getAllOrder()) {
            liveData.value = it
        }
        db.collection("orders").addSnapshotListener { value, error ->
            liveData.addDisposable(getAllOrder()) {
                liveData.value = it
            }
        }
        return liveData
    }

    private fun <T, K> MediatorLiveData<T>.addDisposable(source: LiveData<K>, block: Observer<K>) {
        addSource(source) {
            block.onChanged(it)
            removeSource(source)
        }
    }
}