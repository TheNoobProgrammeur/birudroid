package berthier.antoine.birudroid.model

import org.junit.Assert
import org.junit.Test

class BrandTest {

    @Test
    fun creation_brand(){

        var brand = Brand("idTes","testName","TesDescription")
        Assert.assertEquals("idTes", brand.id)
        Assert.assertEquals("testName",brand.name)
        Assert.assertEquals("TesDescription",brand.description)

    }
}