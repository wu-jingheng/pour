package com.moxie.multi_tenant.blog.repository;

import com.moxie.multi_tenant.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
