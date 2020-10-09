package cz.levinzonr.spotistats.domain.datasource

interface LocalDataSource<T> {
    fun getAll(): List<T>
    fun getById(id: String): T?
    fun put(item: T)
    fun putAll(items: List<T>)
}