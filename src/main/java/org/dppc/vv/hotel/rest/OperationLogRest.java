package org.dppc.vv.hotel.rest;

import org.dppc.vv.common.annotation.Log;
import org.dppc.vv.common.help.ErrorHelp;
import org.dppc.vv.common.model.PageDTO;
import org.dppc.vv.common.model.QueryDTO;
import org.dppc.vv.common.model.ResultDTO;
import org.dppc.vv.hotel.entity.OperationLog;;
import org.dppc.vv.hotel.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
* @Description
* @Author lhw
* @Data 2019/06/13 18:10
* @Version 1.0
**/
@RestController
@RequestMapping(value = "/operationLog")
public class OperationLogRest {

    @Autowired
    private OperationLogService operationLogService;

    /**
    * @Author lhw
    * @Description 根据id查询
    * @Date 2019/06/13 18:10
    * @Param [logId]
    * @return org.dppc.vv.common.model.ResultDTO
    **/
    @Log("根据id查询")
    @GetMapping("/{logId}")
    public ResultDTO findOperationLog(@PathVariable Integer logId) {
        OperationLog operationLog = operationLogService.findById(logId);
        return ResultDTO.success().putDataValue("operationLog", operationLog);
    }

    /**
    * @Author lhw
    * @Description 根据条件分页查询
    * @Date 2019/06/13 18:10
    * @Param [queryDTO, operationLog]
    * @return org.dppc.vv.common.model.PageDTO
    **/
    @Log("根据条件分页查询")
    @GetMapping("/page")
        public PageDTO getListOperationLog(QueryDTO queryDTO, OperationLog operationLog) {
        /** 根据条件分页查询集合 **/
        Page<OperationLog> page = operationLogService.findAll(queryDTO, operationLog);
        PageDTO pageDTO = new PageDTO(page.getTotalElements(), page.getContent());
        return pageDTO;
    }

    /**
    * @Author lhw
    * @Description 添加
    * @Date 2019/06/13 18:10
    * @Param [operationLog, result]
    * @return org.dppc.vv.common.model.ResultDTO
    **/
    @Log("添加")
    @PostMapping(value = "/")
    public ResultDTO addOperationLog(@Valid OperationLog operationLog, BindingResult result) {
        if (result.hasErrors()) {
        return ResultDTO.verifyFail().putDataValue("verify", ErrorHelp.processor(result));
        }
        /**  添加对象 **/
        try {
        operationLogService.save(operationLog);
        return ResultDTO.success();
        } catch (Exception e) {
        e.printStackTrace();
        }
        return ResultDTO.fail();
    }

    /**
    * @Author lhw
    * @Description 更新
    * @Date 2019/06/13 18:10
    * @Param [operationLog, result]
    * @return org.dppc.vv.common.model.ResultDTO
    **/
    @Log("更新")
    @PutMapping(value = "/")
    public ResultDTO putOperationLog(@Valid OperationLog operationLog, BindingResult result) {
        if (result.hasErrors()) {
        return ResultDTO.verifyFail().putDataValue("verify", ErrorHelp.processor(result));
        }
        /** 全部更新对象，根据id对整个对象进行更新 **/
        try {
        operationLogService.put(operationLog);
        return ResultDTO.success();
        } catch (Exception e) {
        e.printStackTrace();
        }
        return ResultDTO.fail();
    }

    /**
    * @Author lhw
    * @Description 局部更新
    * @Date 2019/06/13 18:10
    * @Param [operationLog]
    * @return org.dppc.vv.common.model.ResultDTO
    **/
    @Log("局部更新")
    @PatchMapping(value = "/")
    public ResultDTO patchOperationLog(OperationLog operationLog) {
        /** 局部更新对象，根据id对不为空的字段进行修改 **/
        try {
        operationLogService.patch(operationLog);
        return ResultDTO.success();
        } catch (Exception e) {
        e.printStackTrace();
        }
        return ResultDTO.fail();
    }

    /**
    * @Author lhw
    * @Description 根据id删除
    * @Date 2019/06/13 18:10
    * @Param [logId]
    * @return org.dppc.vv.common.model.ResultDTO
    **/
    @Log("根据id删除")
    @DeleteMapping("/{logId}")
    public ResultDTO deleteOperationLog(@PathVariable Integer logId) {
        /** 删除数据 **/
        try {
        operationLogService.delete(logId);
        return ResultDTO.success();
        } catch (Exception e) {
        e.printStackTrace();
        }
        return ResultDTO.fail();
    }
}
