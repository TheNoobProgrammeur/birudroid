package berthier.antoine.birudroid.model

import android.graphics.Bitmap
import java.io.Serializable
import java.lang.reflect.Type

open class Beer(
        var id: String = "ID_NOT_DEF",
        var name: String = "NAME_NOT_DEF",
        var degree:Double = 0.00,
        var brand: Brand = Brand("ID_NOT_DEF","NAME_NOT_DEF","DESCRIPTION_NOT_DEF"),
        var typeBeer: TypeBeer=TypeBeer.NOT_DEF,
        var description: String = "DESCRIPTION_NOT_DEF",
        var nbVotent: Int = 0,
        var averger: Double = 0.00,
        var idImageBeer: String =  "null",
        var listeUserVoted: ArrayList<String> = ArrayList<String>()
) : Serializable {
    constructor(beer: Beer) : this(beer.id, beer.name, beer.degree, beer.brand, beer.typeBeer, beer.description, beer.nbVotent, beer.averger,beer.idImageBeer,beer.listeUserVoted)
}
