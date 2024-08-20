package com.idb.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.idb.crud.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query("select u from User u where u.username = :username and u.password = :password")
    User findByUsernameAndPassword(
            @Param("username") String username,
            @Param("password") String password);

//     @Query(value = "select count(u.id) > 0 from User u where u.username = :username and u.password = :password", nativeQuery = true)
//     boolean loginValid(
//             @Param("username") String username,
//             @Param("password") String password);

}
