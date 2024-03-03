package com.mjollror.noteapp.featureNote.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}