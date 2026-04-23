package com.post.console.post.application

import com.post.console.post.domain.PostRepository
import com.post.console.post.application.dto.PostResponse
import com.post.console.post.application.ports.input.GetPostInputPort
import com.post.console.post.application.mapper.toResponse

class GetPostUseCases(
    private val postRepository: PostRepository
) : GetPostInputPort {

    override fun get(): List<PostResponse> { 
        val posts = postRepository.get()
        return posts.map { it.toResponse() }
    }

    override fun get(id: String): PostResponse? {
        val post = postRepository.get(id)
        return post?.toResponse()
     }
}