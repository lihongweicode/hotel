package org.dppc.vv.hotel.service.impl;


import com.sun.javafx.binding.StringFormatter;
import org.dppc.vv.common.help.BeanHelp;
import org.dppc.vv.common.model.QueryDTO;
import org.dppc.vv.hotel.entity.Client;
import org.dppc.vv.hotel.entity.OperationLog;
import org.dppc.vv.hotel.repository.ClientRepository;
import org.dppc.vv.hotel.repository.OperationLogRepository;
import org.dppc.vv.hotel.service.ClientService;
import org.dppc.vv.util.BigDecimalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
* @Description 客户信息表业务操作层实现类
* @Author lhw
* @Data 2019/06/13 18:10
* @Version 1.0
**/
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private OperationLogRepository operationLogRepository;

    @Override
    public Client findById(Integer clientId) {
        return clientRepository.findOne(clientId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Client client) {
        clientRepository.save(client);
        OperationLog log = new OperationLog();
        log.setClientId(client.getClientId());
        log.setClientName(client.getClientName());
        log.setPhone(client.getPhone());
        log.setOperationInfo(String.format("新创建客户,客户余额为{%1$s},剩余数量为{%2$s}",client.getSurplusPrice(),client.getSurplusNum()));
        log.setCreateTime(new Date());
        operationLogRepository.save(log);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void put(Client client) {
        clientRepository.saveAndFlush(client);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer clientId) {
        Client client = clientRepository.findOne(clientId);
        client.setIsDelete(1);
        clientRepository.saveAndFlush(client);
    }

    @Override
    public Page<Client> findAll(QueryDTO queryDTO, Client client) {
        return clientRepository.findAll((root, query1, cb) -> BeanHelp.getPredicate(root, queryDTO.getQuery(), cb), queryDTO.gainPageable(client));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void patch(Client client) {
        Client oldClient = clientRepository.findOne(client.getClientId());
        if (oldClient != null) {
            //判断数据是否改变
            boolean numOption = client.getChangeNum() != null && client.getChangeNum() != 0;
            //判断价格是否改变
            boolean priceOption = client.getChangePrice() != null && client.getChangePrice() != 0;
            if(numOption) {
                client.setSurplusNum(BigDecimalUtil.add(client.getSurplusNum(),client.getChangeNum()));
                OperationLog log = new OperationLog();
                log.setClientId(oldClient.getClientId());
                log.setClientName(oldClient.getClientName());
                log.setPhone(oldClient.getPhone());
                if(client.getChangeNum() > 0) {
                    log.setOperationInfo(String.format("剩余数量{+%1$s},当前剩余数量为{%2$s}",client.getChangeNum(),client.getSurplusNum()));
                }else {
                    log.setOperationInfo(String.format("剩余数量{%1$s},当前剩余数量为{%2$s}",client.getChangeNum(),client.getSurplusNum()));
                }
                log.setCreateTime(new Date());
                operationLogRepository.save(log);
            }
            if(priceOption) {
                client.setSurplusPrice(BigDecimalUtil.add(client.getSurplusPrice(),client.getChangePrice()));
                OperationLog log = new OperationLog();
                log.setClientId(oldClient.getClientId());
                log.setClientName(oldClient.getClientName());
                log.setPhone(oldClient.getPhone());
                if(client.getChangePrice() > 0) {
                    log.setOperationInfo(String.format("账户余额{+%1$s},当前账户余额为{%2$s}",client.getChangePrice(),client.getSurplusPrice()));
                }else {
                    log.setOperationInfo(String.format("账户余额{%1$s},当前账户余额为{%2$s}",client.getChangePrice(),client.getSurplusPrice()));
                }
                log.setCreateTime(new Date());
                operationLogRepository.save(log);
            }
            clientRepository.saveAndFlush(BeanHelp.updateEntityExceptEmptyProps(oldClient, client));
        }
    }

    @Override
    public Long findCount(Client client) {
        return clientRepository.count((root, query1, cb) -> BeanHelp.getPredicate(root, client, cb));
    }

    @Override
    public List<Client> findAll(Client client) {
        return clientRepository.findAll((root, query1, cb) -> BeanHelp.getPredicate(root, client, cb));
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

}
