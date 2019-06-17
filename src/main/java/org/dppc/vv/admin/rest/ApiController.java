package org.dppc.vv.admin.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.dppc.vv.admin.service.ApiService;
import org.dppc.vv.common.annotation.Log;
import org.dppc.vv.common.help.ValidateHelp;
import org.dppc.vv.common.model.PageDTO;
import org.dppc.vv.common.model.QueryDTO;
import org.dppc.vv.common.model.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.dppc.vv.admin.entity.Api;

/**
 * @描述 :  接口管理
 * @作者 :	zhumh
 * @日期 :	2018/12/7
 * @时间 :	15:59
 */
@io.swagger.annotations.Api(tags = "接口管理")
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    /**
     * 接口分页数据
     * @param api
     * @return
     */
    @Log("获取接口分页数据")
    @GetMapping("/apiList")
    @ApiOperation(value = "获取接口分页数据")//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    public PageDTO apiList(QueryDTO queryDTO, Api api){
        /** 根据条件分页查询集合 **/
        Page<Api> page =  apiService.getApiPage(queryDTO,api);
        PageDTO pageDTO = new PageDTO(page.getTotalElements(),page.getContent());
        return pageDTO;
    }

    /**
     * 接口添加
     * @param api
     * @param result
     * @return
     */
    @Log("接口添加")
    @PostMapping("/saveApi")
    @ApiOperation(value = "接口添加")//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    public ResultDTO saveApi(Api api,BindingResult result){
        if(result.hasErrors()){
            return ResultDTO.verifyFail().putDataValue("verify", ValidateHelp.toStringJson(result));
        }
        apiService.saveApi(api);
        return ResultDTO.success();
    }

    /**
     * 接口更新
     * @param api
     * @param result
     * @return
     */
    @Log("接口更新")
    @PostMapping("/updateApi")
    @ApiOperation(value = "接口更新")//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    public ResultDTO updateApi(Api api,BindingResult result){
        if(result.hasErrors()){
            return ResultDTO.customFail(ValidateHelp.toStringJson(result));
        }
        if(api.getApiId() == null || api.getApiId() <= 0){
            return ResultDTO.verifyFail();
        }
        apiService.updateApi(api);
        return ResultDTO.success();
    }

    /**
     * 接口删除
     * @param api
     * @param result
     * @return
     */
    @Log("接口删除")
    @PostMapping("/deleteApi")
    @ApiOperation(value = "接口删除")//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    public ResultDTO deleteApi(Api api,BindingResult result){
        if(api == null || api.getApiId() == null
                || api.getApiId() <= 0){
            return ResultDTO.verifyFail();
        }
        apiService.deleteApi(api);
        return ResultDTO.success();
    }

    /**
     * 获取一条接口数据
     * @param api
     * @param result
     * @return
     */
    @Log("获取一条接口数据")
    @PostMapping("/apiOne")
    @ApiOperation(value = "获取一条接口数据")//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    public ResultDTO apiOne(Api api, BindingResult result){
        if(api == null || api.getApiId() == null
                || api.getApiId() <= 0){
            return ResultDTO.verifyFail();
        }
        return ResultDTO.success().putDataValue("api",apiService.getApi(api));
    }

    /**
     * 获取所有接口
     * @return
     */
    @Log("获取所有接口")
    @GetMapping("/apiAll")
    @ApiOperation(value = "获取所有接口")//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    public ResultDTO apiAll(){
        return ResultDTO.success().putDataValue("apiList",apiService.getAllApi());
    }
}
