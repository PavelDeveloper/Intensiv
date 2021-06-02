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
}
