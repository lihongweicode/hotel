package org.dppc.vv.hotel.service.impl;


import org.dppc.vv.common.help.BeanHelp;
import org.dppc.vv.common.model.QueryDTO;
import org.dppc.vv.hotel.entity.OperationLog;
import org.dppc.vv.hotel.repository.OperationLogRepository;
import org.dppc.vv.hotel.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @Description 业务操作层实现类
* @Author lhw
* @Data 2019/06/13 18:10
* @Version 1.0
**/
@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Autowired
    private OperationLogRepository operationLogRepository;

    @Override
    public OperationLog findById(Integer logId) {
        return operationLogRepository.findOne(logId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(OperationLog operationLog) {
        operationLogRepository.save(operationLog);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void put(OperationLog operationLog) {
        operationLogRepository.saveAndFlush(operationLog);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer logId) {
        operationLogRepository.delete(logId);
    }

    @Override
    public Page<OperationLog> findAll(QueryDTO queryDTO, OperationLog operationLog) {
        return operationLogRepository.findAll((root, query1, cb) -> BeanHelp.getPredicate(root, queryDTO.getQuery(), cb), queryDTO.gainPageable(operationLog));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void patch(OperationLog operationLog) {
        OperationLog oldOperationLog = operationLogRepository.findOne(operationLog.getLogId());
        if (oldOperationLog != null) {
            operationLogRepository.saveAndFlush(BeanHelp.updateEntityExceptEmptyProps(oldOperationLog, operationLog));
        }
    }

    @Override
    public Long findCount(OperationLog operationLog) {
        return operationLogRepository.count((root, query1, cb) -> BeanHelp.getPredicate(root, operationLog, cb));
    }

    @Override
    public List<OperationLog> findAll(OperationLog operationLog) {
        return operationLogRepository.findAll((root, query1, cb) -> BeanHelp.getPredicate(root, operationLog, cb));
    }

    @Override
    public List<OperationLog> findAll() {
        return operationLogRepository.findAll();
    }

}
