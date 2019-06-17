package org.dppc.vv.hotel.rest;

import org.dppc.vv.common.annotation.Log;
import org.dppc.vv.common.help.ErrorHelp;
import org.dppc.vv.common.model.PageDTO;
import org.dppc.vv.common.model.QueryDTO;
import org.dppc.vv.common.model.ResultDTO;
import org.dppc.vv.hotel.entity.Client;;
import org.dppc.vv.hotel.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Description 客户信息表
 * @Author lhw
 * @Data 2019/06/13 18:10
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/client")
public class ClientRest {

    @Autowired
    private ClientService clientService;

    /**
     * @return org.dppc.vv.common.model.ResultDTO
     * @Author lhw
     * @Description 根据id查询客户信息表
     * @Date 2019/06/13 18:10
     * @Param [clientId]
     **/
    @Log("根据id查询客户信息表")
    @GetMapping("/{clientId}")
    public ResultDTO findClient(@PathVariable Integer clientId) {
        Client client = clientService.findById(clientId);
        return ResultDTO.success().putDataValue("client", client);
    }

    /**
     * @return org.dppc.vv.common.model.PageDTO
     * @Author lhw
     * @Description 根据条件分页查询客户信息表
     * @Date 2019/06/13 18:10
     * @Param [queryDTO, client]
     **/
    @Log("根据条件分页查询客户信息表")
    @GetMapping("/page")
    public PageDTO getListClient(QueryDTO queryDTO, Client client) {
        /** 根据条件分页查询集合 **/
        Page<Client> page = clientService.findAll(queryDTO, client);
        PageDTO pageDTO = new PageDTO(page.getTotalElements(), page.getContent());
        return pageDTO;
    }

    /**
     * @return org.dppc.vv.common.model.ResultDTO
     * @Author lhw
     * @Description 添加客户信息表
     * @Date 2019/06/13 18:10
     * @Param [client, result]
     **/
    @Log("添加客户信息表")
    @PostMapping(value = "/")
    public ResultDTO addClient(@Valid Client client, BindingResult result) {
        if (result.hasErrors()) {
            return ResultDTO.verifyFail().putDataValue("verify", ErrorHelp.processor(result));
        }
        /**  添加对象 **/
        try {
            client.setIsDelete(0);
            clientService.save(client);
            return ResultDTO.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultDTO.fail();
    }

    /**
     * @return org.dppc.vv.common.model.ResultDTO
     * @Author lhw
     * @Description 更新客户信息表
     * @Date 2019/06/13 18:10
     * @Param [client, result]
     **/
    @Log("更新客户信息表")
    @PutMapping(value = "/")
    public ResultDTO putClient(@Valid Client client, BindingResult result) {
        if (result.hasErrors()) {
            return ResultDTO.verifyFail().putDataValue("verify", ErrorHelp.processor(result));
        }
        /** 全部更新对象，根据id对整个对象进行更新 **/
        try {
            clientService.put(client);
            return ResultDTO.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultDTO.fail();
    }

    /**
     * @return org.dppc.vv.common.model.ResultDTO
     * @Author lhw
     * @Description 局部更新客户信息表
     * @Date 2019/06/13 18:10
     * @Param [client]
     **/
    @Log("局部更新客户信息表")
    @PatchMapping(value = "/")
    public ResultDTO patchClient(Client client) {
        /** 局部更新对象，根据id对不为空的字段进行修改 **/
        try {
            clientService.patch(client);
            return ResultDTO.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultDTO.fail();
    }

    /**
     * @return org.dppc.vv.common.model.ResultDTO
     * @Author lhw
     * @Description 根据id删除客户信息表
     * @Date 2019/06/13 18:10
     * @Param [clientId]
     **/
    @Log("根据id删除客户信息表")
    @DeleteMapping("/{clientId}")
    public ResultDTO deleteClient(@PathVariable Integer clientId) {
        /** 删除数据 **/
        try {
            clientService.delete(clientId);
            return ResultDTO.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultDTO.fail();
    }
}
