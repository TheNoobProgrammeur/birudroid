package berthier.antoine.birudroid.model

class User(
    var name: String,
    var mail: String,
    var id: String,
    var beerFavorite: ArrayList<Beer> = ArrayList<Beer>(),
    var beerNote: ArrayList<Note> = ArrayList<Note>()
) {

}