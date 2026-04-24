package com.post.console.post.application

import com.post.console.post.application.dto.CreatePostRequest
import com.post.console.post.application.ports.input.CreatePostInputPort
import com.post.console.post.domain.model.Post
import com.post.console.post.domain.PostRepository
import com.post.console.post.application.mapper.toDomain

class CreatePostUseCases(
    private val postRepository: PostRepository
) : CreatePostInputPort {

    override fun create(request: CreatePostRequest): String {
        require(request.id.isNotBlank()) { "ID cannot be blank" }
        require(request.title.isNotBlank()) { "Title cannot be blank" }
        require(request.content.isNotBlank()) { "Content cannot be blank" }
        require(request.authorId.isNotBlank()) { "Author ID cannot be blank" }

        val post = request.toDomain()
        postRepository.create(post)

        return post.id
    }

}