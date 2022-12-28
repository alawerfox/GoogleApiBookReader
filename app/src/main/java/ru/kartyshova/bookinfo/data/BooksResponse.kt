package ru.kartyshova.bookinfo.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BooksResponse(
    val items: List<Book>
): Parcelable

@Parcelize
data class Book (
    val volumeInfo: VolumeInfo
): Parcelable

@Parcelize
data class VolumeInfo(
    val title: String?,
    val subtitle: String?,
    val authors: List<String>?,
    var description: String? = null,
    var imageLinks: ImageLinks?,
    var publishedDate: String? = null,
    var previewLink: String? = null
): Parcelable

@Parcelize
data class ImageLinks (
    val smallThumbnail: String? = null
): Parcelable