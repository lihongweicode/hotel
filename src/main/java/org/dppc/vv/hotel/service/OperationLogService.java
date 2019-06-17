package org.dppc.vv.hotel.service;

import org.dppc.vv.common.model.QueryDTO;
import org.dppc.vv.hotel.entity.OperationLog;
import org.springframework.data.domain.Page;

import java.util.List;

/**
* @Description 业务操作层接口
* @Author lhw
* @Data 2019/06/13 18:10
* @Version 1.0
**/
public interface OperationLogService{

    OperationLog findById(Integer logId);

    void save(OperationLog operationLog);

    void put(OperationLog operationLog);

    void delete(Integer logId);

    Page<OperationLog> findAll(QueryDTO queryDTO, OperationLog operationLog);

    void patch(OperationLog operationLog);

    Long findCount(OperationLog operationLog);

    List<OperationLog> findAll(OperationLog operationLog);

    List<OperationLog> findAll();
}
