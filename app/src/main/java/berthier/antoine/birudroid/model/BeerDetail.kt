package berthier.antoine.birudroid.model

class BeerDetail : Beer {
     var description : String
     var brewery : Brewery
     var listeCommentaire : ArrayList<Comment>

    constructor(beer: Beer, description : String, brewery: Brewery, listeCommentaire:ArrayList<Comment> = ArrayList<Comment>() ):super(beer){
        this.description = description
        this.brewery = brewery
        this.listeCommentaire = listeCommentaire
    }

    constructor(id:String, name:String, degree:Double, brand: Brand, typeBeer: TypeBeer, description : String, brewery: Brewery, nbVotent: Int = 0, averger: Double =0.00, listeCommentaire:ArrayList<Comment> = ArrayList<Comment>())
            : super(id, name, degree, brand, typeBeer,nbVotent,averger ) {
        this.description = description
        this.brewery = brewery
        this.listeCommentaire = listeCommentaire
    }
}
