package berthier.antoine.birudroid.model

import org.junit.Assert
import org.junit.Test
import java.text.SimpleDateFormat

class BreweryTest {

    @Test
    fun creation_brewery(){

        var ft =   SimpleDateFormat ("dd/MM/yyyy 'at' hh:mm:ss");
        var date = ft.parse("11/08/2020 at 11:30:55")
        var bre = Brewery("idBrewed","7 rue des grimettes 92190",date , "description test")

        Assert.assertEquals("idBrewed",bre.id)
        Assert.assertEquals("7 rue des grimettes 92190", bre.adress)
        Assert.assertEquals("11/08/2020 at 11:30:55",ft.format(bre.creation))
        Assert.assertEquals("description test",bre.description)

    }

}