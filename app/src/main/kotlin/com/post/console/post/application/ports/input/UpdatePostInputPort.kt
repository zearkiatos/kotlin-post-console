package com.post.console.post.application.prots.input

import com.post.console.post.application.ports.input.UpdateInputPort

interface UpdatePostInputPort {
    fun addComment(commentId: String)
}

