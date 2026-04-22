package com.post.console.post.application.mapper

import com.post.console.post.domain.model.Post
import com.post.console.post.application.dto.CreatePostRequest

fun CreatePostRequest.toDomain(): Post {
    return Post(
        id = id.trim(),
        title = title.trim(),
        content = content.trim(),
        authorId = authorId.trim(),
        comments = comments.toMutableList()
    )
}