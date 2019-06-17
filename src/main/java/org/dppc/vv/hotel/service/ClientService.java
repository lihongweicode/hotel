package org.dppc.vv.hotel.service;

import org.dppc.vv.common.model.QueryDTO;
import org.dppc.vv.hotel.entity.Client;
import org.springframework.data.domain.Page;

import java.util.List;

/**
* @Description 客户信息表业务操作层接口
* @Author lhw
* @Data 2019/06/13 18:10
* @Version 1.0
**/
public interface ClientService{

    Client findById(Integer clientId);
    
    void save(Client client);
    
    void put(Client client);
    
    void delete(Integer clientId);
    
    Page<Client> findAll(QueryDTO queryDTO, Client client);

    void patch(Client client);

    Long findCount(Client client);

    List<Client> findAll(Client client);

    List<Client> findAll();
}
