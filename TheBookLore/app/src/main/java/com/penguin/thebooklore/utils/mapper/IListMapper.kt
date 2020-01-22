package com.penguin.thebooklore.utils.mapper

// not null to not null
interface IListMapper<I, O>: IMapper<List<I>, List<O>>

class IListMapperImpl<I, O>(
        private val mapper: IMapper<I, O>
) : IListMapper<I, O> {
    override fun map(input: List<I>): List<O> {
        return input.map { mapper.map(it) }
    }
}