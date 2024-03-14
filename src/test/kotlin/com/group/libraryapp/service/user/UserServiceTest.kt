package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
internal class UserServiceTest @Autowired constructor(

    private val userRepository: UserRepository,
    private val userService: UserService,

) {
    @AfterEach
    fun clean() {
        userRepository.deleteAll()
    }

    @Test
    @DisplayName("유저 저장 테스트")
    fun saveUser() {
        // given
//        val resquest = UserCreateRequest("박성술", null)
        val request = UserCreateRequest("박성술")

        // when
        userService.saveUser(request)

        // then
        val findAll = userRepository.findAll()
        assertThat(findAll).hasSize(1)
        assertThat(findAll[0].name).isEqualTo("박성술")
        assertThat(findAll[0].age).isNull() //-> error cuz : age가 Integer로 되어있기 때문에 kotlin은 null이 허용 가능한지 알수없다.
        //==> @Nullable 어노테이션 처리
    }

    @Test
    @DisplayName("유저 조회")
    fun getUserTest() {
        // given
        userRepository.saveAll(listOf(
            User("A", 20),
            User("B", null),
        ))

        // when
        val results = userService.getUsers()

        // then
        assertThat(results).hasSize(2)
        assertThat(results).extracting("name").containsExactlyInAnyOrder("A","B")
        assertThat(results).extracting("age").containsExactlyInAnyOrder(20,null)

    }

    @Test
    @DisplayName("유저 이름 수정 테스트")
    fun updateUserNameTest() {
        // given
        val savedUser = userRepository.save(User("AA", null, Collections.emptyList(),null))
        val request = UserUpdateRequest(savedUser.id!!, "B")

        // when
        userService.updateUserName(request)
        // then
        val result = userRepository.findAll()[0]
        assertThat(result.name).isEqualTo("B")
    }

    @Test
    @DisplayName("유저 삭제 테스트")
    fun deleteUserTest() {
        // given
        userRepository.save(User("A", null))

        // when
        userService.deleteUser("A")

        // then
        assertThat(userRepository.findAll()).isEmpty()

    }
}