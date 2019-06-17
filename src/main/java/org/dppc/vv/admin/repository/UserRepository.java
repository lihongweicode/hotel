package org.dppc.vv.admin.repository;

import org.dppc.vv.admin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @描述 :
 * @作者 :	zhumh
 * @日期 :	2018/11/30
 * @时间 :	9:54
 */
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    User findByusernameAndPassword(String username,String password);
    User findByUsername(String username);
}
