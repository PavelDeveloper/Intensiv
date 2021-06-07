package ru.androidschool.intensiv.data

class Movie(
    var title: String? = "",
    val previewUrl: String? = "",
    var voteAverage: Double = 0.0,
    val description: String? = "",
    val year: String? = "",
    val genre: String? = "",
    val studio: String? = "",
    val actors: List<Actor> = emptyList()
) {

    val rating: Float
        get() = voteAverage.div(2).toFloat()
}
