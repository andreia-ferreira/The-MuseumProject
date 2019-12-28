package com.penguin.thebooklore.model.interfacee

interface IMapper<I, O> {
    fun map(input: I): O
}