package com.group.libraryapp.domain.book

import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long>  {
    fun findByName(bookName: String): Book?

//    @Query("select new com.group.libraryapp.dto.book.reponse.BookStatResponse(b.type, COUNT(b.id))" +
//            " from Book b GROUP BY b.type")
//    fun getStats(): List<BookStatResponse>
}