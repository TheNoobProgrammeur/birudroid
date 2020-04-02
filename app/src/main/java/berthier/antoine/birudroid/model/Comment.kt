package berthier.antoine.birudroid.model

import java.util.*

data class Comment(val id:String, val idUser:String, val idBeer:String, val date:Date, val message:String) {
}