package com.michaldrabik.data_remote.trakt.model

data class ExternalRatings(
  val tmdb: TmdbRatings? = null,
  val imdb: ImdbRatings? = null,
  val metascore: MetascoreRatings? = null,
  val rotten_tomatoes: RottenRatings? = null,
) {

  data class TmdbRatings(
    val rating: Double? = null,
    val votes: Int? = null,
    val link: String? = null,
  )

  data class ImdbRatings(
    val rating: Double? = null,
    val votes: Int? = null,
    val link: String? = null,
  )

  data class MetascoreRatings(
    val rating: Int? = null,
    val link: String? = null,
  )

  data class RottenRatings(
    val rating: Int? = null,
    val state: String? = null,
    val user_rating: Int? = null,
    val user_state: String? = null,
    val link: String? = null,
  )
}
