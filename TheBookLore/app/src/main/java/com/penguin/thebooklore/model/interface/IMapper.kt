package com.penguin.thebooklore.model.`interface`

interface IMapper<I, O> {
    fun map(input: I): O
}