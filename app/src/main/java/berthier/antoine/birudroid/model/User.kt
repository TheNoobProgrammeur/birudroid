package berthier.antoine.birudroid.model

class User(
    var name: String,
    var mail: String,
    var id: String,
    var beerFavorite: ArrayList<Beer> = ArrayList<Beer>(),
    var beerNote: ArrayList<Note> = ArrayList<Note>()
) {
    companion object Instance{
        lateinit var mName: String
        lateinit var mMail: String
        lateinit var mId: String
        var mBeerFavorite: ArrayList<Beer> = ArrayList<Beer>()
        var mBeerNote: ArrayList<Note> = ArrayList<Note>()

        fun createUser(
            name: String,
            mail: String,
            id: String
        ){
            mName = name
            mMail = mail
            mId = id

        }
    }
}