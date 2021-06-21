package ru.androidschool.intensiv.data.mock

data class MovieMock(
    var title: String? = "",
    val previewUrl: String? = "",
    var voteAverage: Double = 0.0,
    val description: String? = "",
    val year: String? = "",
    val genre: String? = "",
    val studio: String? = ""
) {

    val rating: Float
        get() = voteAverage.div(2).toFloat()
}
