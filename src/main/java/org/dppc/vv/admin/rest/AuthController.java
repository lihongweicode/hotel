package org.dppc.vv.admin.rest;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.dppc.vv.admin.entity.Api;
import org.dppc.vv.admin.entity.Auth;
import org.dppc.vv.admin.entity.Menu;
import org.dppc.vv.admin.service.AuthService;
import org.dppc.vv.common.annotation.Log;
import org.dppc.vv.common.help.ValidateHelp;
import org.dppc.vv.common.model.PageDTO;
import org.dppc.vv.common.model.QueryDTO;
import org.dppc.vv.common.model.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述 :  权限管理
 * @作者 :	zhumh
 * @日期 :	2018/12/7
 * @时间 :	15:59
 */
@io.swagger.annotations.Api(tags = "权限管理")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * 权限分页数据
     * @param auth
     * @return
     */
    @Log("获取权限分页数据")
    @GetMapping("/authList")
    @ApiOperation(value = "获取权限分页数据")//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    public PageDTO authList(QueryDTO queryDTO, Auth auth){
        /** 根据条件分页查询集合 **/
        Page<Auth> page =  authService.getAuthPage(queryDTO,auth);
        PageDTO pageDTO = new PageDTO(page.getTotalElements(),page.getContent());
        return pageDTO;
    }

    /**
     * 权限添加
     * @param auth
     * @param result
     * @return
     */
    @Log("权限添加")
    @PostMapping("/saveAuth")
    @ApiOperation(value = "权限添加")//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    public ResultDTO saveAuth(Auth auth,BindingResult result,String menuIds,String apiIds){
        if(result.hasErrors()){
            return ResultDTO.verifyFail().putDataValue("verify", ValidateHelp.toStringJson(result));
        }
        if(menuIds != null && menuIds.indexOf(",") > -1){
            List<Menu> menuList = new ArrayList<>();
            for (String id : menuIds.split(",")) {
                if (StringUtils.isEmpty(id))
                    continue;
                Menu m = new Menu();
                m.setMenuId(Long.parseLong(id));
                menuList.add(m);
            }
            auth.setMenuList(menuList);
        }
        if(apiIds != null && apiIds.indexOf(",") > -1){
            List<Api> apiList = new ArrayList<>();
            for (String id : apiIds.split(",")) {
                if (StringUtils.isEmpty(id))
                    continue;
                Api api = new Api();
                api.setApiId(Long.parseLong(id));
                apiList.add(api);
            }
            auth.setApiList(apiList);
        }
        authService.saveAuth(auth);
        return ResultDTO.success();
    }

    /**
     * 权限更新
     * @param auth
     * @param result
     * @return
     */
    @Log("权限更新")
    @PostMapping("/updateAuth")
    @ApiOperation(value = "权限更新")//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    public ResultDTO updateAuth(Auth auth,BindingResult result,String menuIds,String apiIds){
        if(result.hasErrors()){
            return ResultDTO.customFail(ValidateHelp.toStringJson(result));
        }
        if(auth.getAuthId() == null || auth.getAuthId() <= 0){
            return ResultDTO.verifyFail();
        }
        if(menuIds != null && menuIds.indexOf(",") > -1){
            List<Menu> menuList = new ArrayList<>();
            for (String id : menuIds.split(",")) {
                if (StringUtils.isEmpty(id))
                    continue;
                Menu m = new Menu();
                m.setMenuId(Long.parseLong(id));
                menuList.add(m);
            }
            auth.setMenuList(menuList);
        }
        if(apiIds != null && apiIds.indexOf(",") > -1){
            List<Api> apiList = new ArrayList<>();
            for (String id : apiIds.split(",")) {
                if (StringUtils.isEmpty(id))
                    continue;
                Api api = new Api();
                api.setApiId(Long.parseLong(id));
                apiList.add(api);
            }
            auth.setApiList(apiList);
        }
        authService.update(auth);
        return ResultDTO.success();
    }

    /**
     * 权限删除
     * @param auth
     * @param result
     * @return
     */
    @Log("权限删除")
    @PostMapping("/deleteAuth")
    @ApiOperation(value = "权限删除")//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    public ResultDTO deleteAuth(Auth auth,BindingResult result){
        if(auth == null || auth.getAuthId() == null
                || auth.getAuthId() <= 0){
            return ResultDTO.verifyFail();
        }
        authService.delete(auth);
        return ResultDTO.success();
    }

    /**
     * 获取一条权限数据
     * @param auth
     * @param result
     * @return
     */
    @Log("获取一条权限数据")
    @PostMapping("/authOne")
    @ApiOperation(value = "获取一条权限数据")//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    public ResultDTO authOne(Auth auth, BindingResult result){
        if(auth == null || auth.getAuthId() == null
                || auth.getAuthId() <= 0){
            return ResultDTO.verifyFail();
        }
        return ResultDTO.success().putDataValue("auth",authService.getAuth(auth));
    }

    /**
     * 获取所有权限
     * @return
     */
    @Log("获取所有权限")
    @GetMapping("/authAll")
    @ApiOperation(value = "获取所有权限")//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    public ResultDTO authAll(){
        return ResultDTO.success().putDataValue("authList",authService.getAllauth());
    }
}
