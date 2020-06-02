package berthier.antoine.birudroid.model

import java.io.Serializable

enum class TypeBeer(s: String) : Serializable  {
    BELGES("Belges"),
    BELGES_FORT("Belges Fort"),
    ACIDULEES("Acidulees"),
    BOCK("Bock"),
    HYBRIDES_AMBRES("Hybrides Ambres"),
    HYBRIDES_BLONDE("Hybrides Blonde"),
    IPA("IPA"),
    AMBREE("Ambree"),
    BLONDE("Blonde"),
    FONCEE("Foncee"),
    NOT_DEF("TYPE_BEER_NOT_DEF")
}