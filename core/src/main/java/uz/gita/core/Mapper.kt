package uz.gita.core

import com.google.firebase.firestore.DocumentSnapshot

object Mapper {

    fun DocumentSnapshot.toOrder() = OrderData(
        id = this["id"].toString(),
        name = this["name"].toString(),
        cost = this["cost"].toString(),
        image = this["image"].toString(),
        count = this["count"].toString(),
        status = this["status"].toString()
    )
}