package com.group.oldjavalibraryapp.domain.user;

import com.group.libraryapp.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JavaUserRepository extends JpaRepository<User, Long> {

  Optional<User> findByName(String name);

}
