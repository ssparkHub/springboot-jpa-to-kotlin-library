package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookServiceTest @Autowired constructor(
    private val bookService: BookService,
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
) {

    @AfterEach
    fun clean() {
        bookRepository.deleteAll()
        userRepository.deleteAll()
    }

    @Test
    @DisplayName("책 등록 테스트")
    fun saveBookTest() {
        // given
        val request = BookRequest("파수꾼의 숲")
        // when
        bookService.saveBook(request)
        // then
        val books = bookRepository.findAll()
        assertThat(books).hasSize(1)
        assertThat(books[0].name).isEqualTo("파수꾼의 숲")
    }

    @Test
    @DisplayName("책 대출 동작 테스트")
    fun loanBookTest() {
        // given
        val loanedBook = bookRepository.save(Book("워터 댄서"))
        val savedUser = userRepository.save(User("박성술", 31))
        val request = BookLoanRequest(savedUser.name, loanedBook.name,)
        // when
        bookService.loanBook(request)
        // then
        val results = userLoanHistoryRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].bookName).isEqualTo(loanedBook.name)
        assertThat(results[0].user.id).isEqualTo(savedUser.id)
        assertThat(results[0].isReturn).isFalse()

    }

    @Test
    @DisplayName("대출된 책, 재 대출 실패 테스트")
    fun loanBookFailTest() {
        // given
        val loanedBook = bookRepository.save(Book("워터 댄서"))
        val savedUser = userRepository.save(User("박성술", 31))
        userLoanHistoryRepository.save(UserLoanHistory(savedUser, loanedBook.name, false))
        val request = BookLoanRequest(savedUser.name, loanedBook.name,)

        // when & then
        assertThrows<IllegalArgumentException> {
            bookService.loanBook(request)
        }.apply {
            assertThat(this.message).isEqualTo("진작 대출되어 있는 책입니다")
        }
    }

    @Test
    @DisplayName("반납 정상 동작 테스트")
    fun returnBookTest() {

        // given
        val loanedBook = bookRepository.save(Book("워터 댄서"))
        val savedUser = userRepository.save(User("박성술", 31))
        userLoanHistoryRepository.save(UserLoanHistory(savedUser, loanedBook.name, false))
        val request = BookReturnRequest(savedUser.name, loanedBook.name)

        // when
        bookService.returnBook(request)

        // then
        val result = userLoanHistoryRepository.findAll()
        assertThat(result).hasSize(1)
        assertThat(result[0].isReturn).isTrue()

    }
}