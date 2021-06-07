package ru.androidschool.intensiv.data

object MockRepository {

    fun getMovies(): List<Movie> {

        val moviesList = mutableListOf<Movie>()
        for (x in 0..10) {
            val movie = Movie(
                title = "Spider-Man $x",
                voteAverage = 10.0 - x
            )
            moviesList.add(movie)
        }

        return moviesList
    }

    fun getTvShows(): List<Show> {
        val showsList = mutableListOf<Show>()
        showsList.add(
            Show(
                title = "Люцифер",
                voteAverage = 4.5f,
                posterUrl = "https://www.themoviedb.org/t/p/w440_and_h660_face/z24OWggvuqkmB0xmtgChXbl9THw.jpg"
            )
        )

        showsList.add(
            Show(
                title = "Флэш",
                voteAverage = 4.0f,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/nnY2KwN3XR8D2f4p7w05W7CoCv.jpg"
            )
        )

        showsList.add(
            Show(
                title = "Игра престолов",
                voteAverage = 3.5f,
                posterUrl = "https://www.themoviedb.org/t/p/w1280/bHzz0i6Ue7IixhSjFlGs0slzL2m.jpg"
            )
        )

        showsList.add(
            Show(
                title = "Ходячие мертвецы",
                voteAverage = 5.0f,
                posterUrl = "https://www.themoviedb.org/t/p/w1280/nRqzUMMBqw0sa5l8msinhDVP1BI.jpg"
            )
        )

        showsList.add(
            Show(
                title = "Острые козырьки",
                voteAverage = 5.0f,
                posterUrl = "https://www.themoviedb.org/t/p/w1280/jrOHaO3KhboWdsMW3CUFDcj6Mgs.jpg"
            )
        )

        return showsList
    }

    fun getMovieDetail(): Movie = Movie(
        title = "Острые козырьки",
        previewUrl = "https://www.themoviedb.org/t/p/w1280/jrOHaO3KhboWdsMW3CUFDcj6Mgs.jpg",
        actors = getActors(),
        description = "Британский сериал о криминальном мире Бирмингема 20-х годов прошлого века, в котором многолюдная семья Шелби стала одной из самых жестоких и влиятельных гангстерских банд послевоенного времени. Фирменным знаком группировки, промышлявшей грабежами и азартными играми, стали зашитые в козырьки лезвия.",
        voteAverage = 4.7,
        year = "2020",
        genre = "Криминал, драма",
        studio = "BBC One"
    )

    private fun getActors(): List<Actor> {
        val actorsList = mutableListOf<Actor>()
        actorsList.add(
            Actor(
                name = "Cillian Murphy",
                avatarUrl = "https://www.themoviedb.org/t/p/w276_and_h350_face/i8dOTC0w6V274ev5iAAvo4Ahhpr.jpg"
            )
        )
        actorsList.add(
            Actor(
                name = "Paul Anderson",
                avatarUrl = "https://www.themoviedb.org/t/p/w276_and_h350_face/nds5rTBZvJ4rEsP4N6OaoEgQDkW.jpg"
            )
        )
        actorsList.add(
            Actor(
                name = "Helen McCrory",
                avatarUrl = "https://www.themoviedb.org/t/p/w276_and_h350_face/dVtwKuGce3BhUcqfdpxFvpCT8YT.jpg"
            )
        )
        actorsList.add(
            Actor(
                name = "Sophie Rundle",
                avatarUrl = "https://www.themoviedb.org/t/p/w276_and_h350_face/9HxJ6pG1Q0BBbIV1UXk5iU9zDM9.jpg"
            )
        )
        actorsList.add(
            Actor(
                name = "Tom Hardy",
                avatarUrl = "https://www.themoviedb.org/t/p/w276_and_h350_face/yVGF9FvDxTDPhGimTbZNfghpllA.jpg"
            )
        )
        actorsList.add(
            Actor(
                name = "Joe Cole",
                avatarUrl = "https://www.themoviedb.org/t/p/w276_and_h350_face/33oo1eKMjLtknBWJBqtWvJQ8Xgv.jpg"
            )
        )
        return actorsList
    }
}
