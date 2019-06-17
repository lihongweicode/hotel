package org.dppc.vv.hotel.repository;

import org.dppc.vv.hotel.entity.OperationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Description 数据库操作层
 * @Author lhw
 * @Data 2019/06/13 18:10
 * @Version 1.0
 **/
public interface OperationLogRepository extends JpaRepository<OperationLog, Integer>, JpaSpecificationExecutor<OperationLog>  {

}
