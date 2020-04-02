package berthier.antoine.birudroid.model

import org.junit.Assert
import org.junit.Test

class BeerTest {

    @Test
    fun creation_beer(){
        var brand = Brand("idTes","testName","TesDescription")
        var beer = Beer("test","test",1.89, brand,TypeBeer.BELGES,1,1.00)
        Assert.assertEquals("test",beer.id)
        Assert.assertEquals("test",beer.name)
        Assert.assertEquals(1.89,beer.degree, 0.00)
        Assert.assertEquals(brand,beer.brand)
        Assert.assertEquals(TypeBeer.BELGES,beer.typeBeer)
        Assert.assertEquals(1,beer.nbVotent)
        Assert.assertEquals(1.00,beer.averger,0.00)
    }

    @Test
    fun creation_beer_not_nbVotent_and_averge(){
        var brand = Brand("idTes","testName","TesDescription")
        var beer = Beer("test","test",1.89, brand,TypeBeer.BELGES)
        Assert.assertEquals("test",beer.id)
        Assert.assertEquals("test",beer.name)
        Assert.assertEquals(1.89,beer.degree, 0.00)
        Assert.assertEquals(brand,beer.brand)
        Assert.assertEquals(TypeBeer.BELGES,beer.typeBeer)
        Assert.assertEquals(0,beer.nbVotent)
        Assert.assertEquals(0.00,beer.averger,0.00)
    }

    @Test
    fun creation_beer_by_beer(){
        var brand = Brand("idTes","testName","TesDescription")
        var beer = Beer("test","test",1.89, brand,TypeBeer.BELGES,0,0.00)
        var newBeer = Beer(beer)
        Assert.assertEquals("test",newBeer.id)
        Assert.assertEquals("test",newBeer.name)
        Assert.assertEquals(1.89,newBeer.degree, 0.00)
        Assert.assertEquals(brand,newBeer.brand)
        Assert.assertEquals(TypeBeer.BELGES,newBeer.typeBeer)
        Assert.assertEquals(0,newBeer.nbVotent)
        Assert.assertEquals(0.00,newBeer.averger,0.00)
    }

}