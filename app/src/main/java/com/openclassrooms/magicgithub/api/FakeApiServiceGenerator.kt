package com.openclassrooms.magicgithub.api


import com.openclassrooms.magicgithub.model.User
import java.util.*

object FakeApiServiceGenerator {

    fun getAvatarUrl(name: String): String {
        return "https://ui-avatars.com/api/?name=${name.replace(" ", "+")}&size=512"
    }

    @JvmField
    var FAKE_USERS = mutableListOf(
        User("001", "Jake", "https://api.adorable.io/AVATARS/512/1.png", true),
        User("002", "Paul", "https://api.adorable.io/AVATARS/512/2.png", false),
        User("003", "Phil", "https://api.adorable.io/AVATARS/512/3.png", true),
        User("004", "Guillaume", "https://api.adorable.io/AVATARS/512/4.png", false),
        User("005", "Francis", "https://api.adorable.io/AVATARS/512/5.png", true),
        User("006", "George", "https://api.adorable.io/AVATARS/512/6.png", true),
        User("007", "Louis", "https://api.adorable.io/AVATARS/512/7.png", false),
        User("008", "Mateo", "https://api.adorable.io/AVATARS/512/8.png", true),
        User("009", "April", "https://api.adorable.io/AVATARS/512/9.png", false),
        User("010", "Louise", "https://api.adorable.io/AVATARS/512/10.png", true),
        User("011", "Elodie", "https://api.adorable.io/AVATARS/512/11.png", true),
        User("012", "Helene", "https://api.adorable.io/AVATARS/512/12.png", false),
        User("013", "Fanny", "https://api.adorable.io/AVATARS/512/13.png", true),
        User("014", "Laura", "https://api.adorable.io/AVATARS/512/14.png", false),
        User("015", "Gertrude", "https://api.adorable.io/AVATARS/512/15.png", true),
        User("016", "Chloé", "https://api.adorable.io/AVATARS/512/16.png", false),
        User("017", "April", "https://api.adorable.io/AVATARS/512/17.png", true),
        User("018", "Marie", "https://api.adorable.io/AVATARS/512/18.png", false),
        User("019", "Henri", "https://api.adorable.io/AVATARS/512/19.png", true),
        User("020", "Rémi", "https://api.adorable.io/AVATARS/512/20.png", false)
    )

    @JvmField
    var FAKE_USERS_RANDOM = Arrays.asList(
        User("021", "Lea", "https://api.adorable.io/AVATARS/512/21.png", true),
        User("022", "Geoffrey", "https://api.adorable.io/AVATARS/512/22.png", true),
        User("023", "Simon", "https://api.adorable.io/AVATARS/512/23.png", true),
        User("024", "André", "https://api.adorable.io/AVATARS/512/24.png", true),
        User("025", "Leopold", "https://api.adorable.io/AVATARS/512/25.png", true)
    )
}