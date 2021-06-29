package ru.androidschool.intensiv.domain

interface DataEntity<out T> {
    fun toDomain(): T
}
