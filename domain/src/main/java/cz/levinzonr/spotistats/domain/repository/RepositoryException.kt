package cz.levinzonr.spotistats.domain.repository

data class RepositoryException(
    val code: Int,
    val errorBody: String?,
    val msg: String
) : RuntimeException(msg)