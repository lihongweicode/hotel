package org.dppc.vv.admin.repository;

import org.dppc.vv.admin.entity.Api;
import org.dppc.vv.admin.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @描述 : 权限
 * @作者 :	zhumh
 * @日期 :	2018/12/3
 * @时间 :	15:49
 */
public interface AuthRepository extends JpaRepository<Auth, Long>, JpaSpecificationExecutor<Auth> {
}
