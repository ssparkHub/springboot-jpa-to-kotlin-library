package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class UserServiceTest @Autowired constructor(

    private val userRepository: UserRepository,
    private val userService: UserService,

) {

    @Test
    fun saveUser() {
        // given
        val resquest = UserCreateRequest("박성술", null)

        // when
        userService.saveUser(resquest)

        // then
        val findAll = userRepository.findAll()
        assertThat(findAll).hasSize(1)
        assertThat(findAll[0].name).isEqualTo("박성술")
        assertThat(findAll[0].age).isNull() //-> error cuz : age가 Integer로 되어있기 때문에 kotlin은 null이 허용 가능한지 알수없다.
        //==> @Nullable 어노테이션 처리
    }

    @Test
    fun updateUserName() {
    }

    @Test
    fun deleteUser() {
    }
}