package org.dppc.vv.admin.repository;

import org.dppc.vv.admin.entity.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @描述 : 接口
 * @作者 :	zhumh
 * @日期 :	2018/12/3
 * @时间 :	15:49
 */
public interface ApiRepository  extends JpaRepository<Api, Long>, JpaSpecificationExecutor<Api> {
}
