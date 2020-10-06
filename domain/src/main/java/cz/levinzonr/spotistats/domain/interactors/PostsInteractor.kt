package cz.levinzonr.spotistats.domain.interactors

import cz.levinzonr.spotistats.domain.models.Post
import cz.levinzonr.spotistats.domain.repository.PostRepository

class PostsInteractor(
    private val postRepository: PostRepository
) : NoInputInteractor<List<Post>> {

    override suspend fun invoke(input: Unit): List<Post> {
        return listOf()
    }
}