package berthier.antoine.birudroid.model

import org.junit.Test
import java.util.*

class CommentTest {

    @Test
    fun test_creation(){
        var comment = Comment("idComment","idUser","idBeer", Date(),"TODO")
    }

}