package berthier.antoine.birudroid.model

import org.junit.Assert
import org.junit.Test

class UserTest {

    @Test
    fun creation_constructeur_not_beerList(){
        val user = User("Antoine","aberthier@gmail.com","1XD09NDJ76")
        Assert.assertEquals("Antoine",user.name)
        Assert.assertEquals("aberthier@gmail.com",user.mail)
        Assert.assertEquals("1XD09NDJ76",user.id)
        Assert.assertEquals(0,user.beerFavorite.size)
        Assert.assertEquals(0,user.beerNote.size)
    }

    @Test
    fun creation_constructeur_beerList(){
        val beerList = ArrayList<Beer>()
        beerList.add(Beer())
        val user = User("Antoine","aberthier@gmail.com","1XD09NDJ76",beerList)
        Assert.assertEquals("Antoine",user.name)
        Assert.assertEquals("aberthier@gmail.com",user.mail)
        Assert.assertEquals("1XD09NDJ76",user.id)
        Assert.assertEquals(1,user.beerFavorite.size)
        Assert.assertEquals(0,user.beerNote.size)
    }

    @Test
        fun creation_constructeur_BeerNoteList(){
            val beerNoteList = ArrayList<Note>()
            beerNoteList.add(Note("IDBEER",4))
            val user = User("Antoine","aberthier@gmail.com","1XD09NDJ76",beerNote=beerNoteList)
            Assert.assertEquals("Antoine",user.name)
            Assert.assertEquals("aberthier@gmail.com",user.mail)
            Assert.assertEquals("1XD09NDJ76",user.id)
            Assert.assertEquals(0,user.beerFavorite.size)
            Assert.assertEquals(1,user.beerNote.size)
        }

}