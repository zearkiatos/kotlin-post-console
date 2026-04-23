package com.post.console.post.application.ports.input

import com.post.console.post.application.dto.PostWithCommentsResponse

interface GetPostWithCommentsInputPort {
    fun getPostWithComments(postId: String): PostWithCommentsResponse?
}