package cz.levinzonr.spotistats.presentation.extensions

import cz.levinzonr.spotistats.domain.interactors.Fail
import cz.levinzonr.spotistats.domain.interactors.InteractorResult
import cz.levinzonr.spotistats.domain.interactors.Success



suspend fun <T, R> InteractorResult<T>.isSuccess(block: suspend (T) -> R): InteractorResult<T> {
    if (this is Success) {
        block(this.data)
    }
    return this
}

suspend fun <T, R> InteractorResult<T>.isError( block: suspend (throwable: Throwable) -> R): InteractorResult<T> {
    if (this is Fail) {
        block(this.throwable)
    }
    return this
}