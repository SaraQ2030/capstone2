package org.example.capstone2.Repository;

import org.example.capstone2.Model.User;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserById(Integer id);
    User findUserByRole(String role);

    User findUserByUsername(String name);
    User findUserByEmail(String email);
    User findUserByPhoneNumber(Integer number);
}
