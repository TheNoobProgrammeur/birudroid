package berthier.antoine.birudroid.model

class BeerDetail : Beer {
     var brewery : Brewery
     var listeCommentaire : ArrayList<Comment>

    constructor(beer: Beer, brewery: Brewery, listeCommentaire:ArrayList<Comment> = ArrayList<Comment>() ):super(beer){
        this.brewery = brewery
        this.listeCommentaire = listeCommentaire
    }

    constructor(id:String, name:String, degree:Double, brand: Brand, typeBeer: TypeBeer, description : String, brewery: Brewery, idImageBeer: String = "Null", listeUserVoted : ArrayList<User> = ArrayList<User>(), nbVotent: Int = 0, averger: Double =0.00, listeCommentaire:ArrayList<Comment> = ArrayList<Comment>())
            : super(id, name, degree, brand, typeBeer, description, nbVotent,averger,idImageBeer ) {
        this.brewery = brewery
        this.listeCommentaire = listeCommentaire
    }
}
