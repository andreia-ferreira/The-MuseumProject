package com.penguin.thebooklore.utils.mapper

interface IMapper<I, O> {
    fun map(input: I): O
}