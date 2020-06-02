package berthier.antoine.birudroid.model

import org.junit.Assert
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList

class Beer_Detail_Test{

    @Test
    fun creation_by_beer(){
        var brand = Brand("idTes","testName","TesDescription")
        var beer = Beer("test","test",1.89, brand,TypeBeer.BELGES)
        var brewery = Brewery("breweryIdTes","breewerAdressTes", Date(),"description")
        var beerDetail = BeerDetail(beer,brewery)
        beerDetail.description = "description"
        Assert.assertEquals("test",beerDetail.id)
        Assert.assertEquals("test",beerDetail.name)
        Assert.assertEquals(1.89,beerDetail.degree, 0.00)
        Assert.assertEquals(brand,beerDetail.brand)
        Assert.assertEquals(TypeBeer.BELGES,beerDetail.typeBeer)
        Assert.assertEquals(0,beerDetail.nbVotent)
        Assert.assertEquals(0.00,beerDetail.averger,0.00)
        Assert.assertEquals(brewery,beerDetail.brewery)
        Assert.assertEquals("description",beerDetail.description)
        Assert.assertEquals(0,beerDetail.listeCommentaire.size)
    }

    @Test
    fun creation_by_beer_and_comments(){
        var brand = Brand("idTes","testName","TesDescription")
        var beer = Beer("test","test",1.89, brand,TypeBeer.BELGES)
        var brewery = Brewery("breweryIdTes","breewerAdressTes", Date(),"description")
        var comments = ArrayList<Comment>()
        comments.add(Comment("1","userID",beer.id, Date(), "TODO"))
        var beerDetail = BeerDetail(beer,brewery,listeCommentaire=comments)
        beerDetail.description = "description"
        Assert.assertEquals("test",beerDetail.id)
        Assert.assertEquals("test",beerDetail.name)
        Assert.assertEquals(1.89,beerDetail.degree, 0.00)
        Assert.assertEquals(brand,beerDetail.brand)
        Assert.assertEquals(TypeBeer.BELGES,beerDetail.typeBeer)
        Assert.assertEquals(0,beerDetail.nbVotent)
        Assert.assertEquals(0.00,beerDetail.averger,0.00)
        Assert.assertEquals(brewery,beerDetail.brewery)
        Assert.assertEquals("description",beerDetail.description)
        Assert.assertEquals(1,beerDetail.listeCommentaire.size)
    }

    @Test
    fun creation(){
        var brand = Brand("idTes","testName","TesDescription")
        var beer = Beer("test","test",1.89, brand,TypeBeer.BELGES)
        var brewery = Brewery("breweryIdTes","breewerAdressTes", Date(),"description")
        var comments = ArrayList<Comment>()
        comments.add(Comment("1","userID",beer.id, Date(), "TODO"))
        var beerDetail = BeerDetail("test","test",1.89, brand,TypeBeer.BELGES,"description",brewery, listeCommentaire=comments)
        Assert.assertEquals("test",beerDetail.id)
        Assert.assertEquals("test",beerDetail.name)
        Assert.assertEquals(1.89,beerDetail.degree, 0.00)
        Assert.assertEquals(brand,beerDetail.brand)
        Assert.assertEquals(TypeBeer.BELGES,beerDetail.typeBeer)
        Assert.assertEquals(0,beerDetail.nbVotent)
        Assert.assertEquals(0.00,beerDetail.averger,0.00)
        Assert.assertEquals(brewery,beerDetail.brewery)
        Assert.assertEquals("description",beerDetail.description)
        Assert.assertEquals(1,beerDetail.listeCommentaire.size)
    }
}