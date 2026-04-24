package com.post.console.post.application.prots.input

interface UpdatePostInputPort {
    fun addComment(postId: String, commentId: String)
}

