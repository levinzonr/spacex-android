package cz.levinzonr.spotistats.domain.interactors

sealed class InteractorResult<out T> {

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Fail -> "Fail[throwable=$throwable]"
            is Loading -> "Loading"
            Uninitialized -> "Uninitialized"
        }
    }
}

sealed class IncompleteResult<out T> : InteractorResult<T>()
sealed class CompleteResult<out T> : InteractorResult<T>()

data class Success<out T>(val data: T) : CompleteResult<T>()
data class Fail(val throwable: Throwable) : CompleteResult<Nothing>()
object Uninitialized : IncompleteResult<Nothing>()
class Loading<out T> : IncompleteResult<T>()




fun<I, O> Interactor<I, O>.asResult() : Interactor<I, CompleteResult<O>> {
    return object : Interactor<I, CompleteResult<O>> {
        override suspend fun invoke(input: I): CompleteResult<O> {
            return try {
                Success(this@asResult.invoke(input))
            } catch (e: Throwable) {
                Fail(e)
            }
        }
    }
}

