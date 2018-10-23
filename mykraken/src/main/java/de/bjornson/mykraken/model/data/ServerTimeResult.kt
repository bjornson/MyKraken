package de.bjornson.mykraken.model.data

data class ServerTimeResult(
        val error: List<Any>,
        val result: ServerTimeEntry
)