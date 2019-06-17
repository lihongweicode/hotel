package org.dppc.vv.admin.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.dppc.vv.admin.entity.User;
import org.dppc.vv.admin.service.UserService;
import org.dppc.vv.common.annotation.Log;
import org.dppc.vv.common.help.ValidateHelp;
import org.dppc.vv.common.model.PageDTO;
import org.dppc.vv.common.model.QueryDTO;
import org.dppc.vv.common.model.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @描述 :  用户管理
 * @作者 :	zhumh
 * @日期 :	2018/12/7
 * @时间 :	15:59
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    /**
     * 用户分页数据
     * @param user
     * @return
     */
    @Log("获取用户分页数据")
    @GetMapping("/userList")
    @ApiOperation(value = "获取用户分页数据")//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    public PageDTO userList(QueryDTO queryDTO, User user){
        /** 根据条件分页查询集合 **/
        Page<User> page =  userService.getUserPage(queryDTO,user);
        PageDTO pageDTO = new PageDTO(page.getTotalElements(),page.getContent());
        return pageDTO;
    }

    /**
     * 用户添加
     * @param user
     * @param result
     * @return
     */
    @Log("用户添加")
    @PostMapping("/saveUser")
    @ApiOperation(value = "用户添加")//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    public ResultDTO saveUser(User user,BindingResult result){
        if(result.hasErrors()){
            return ResultDTO.verifyFail().putDataValue("verify",ValidateHelp.toStringJson(result));
        }
        userService.saveUser(user);
        return ResultDTO.success();
    }

    /**
     * 用户更新
     * @param user
     * @param result
     * @return
     */
    @Log("用户更新")
    @PostMapping("/updateUser")
    @ApiOperation(value = "用户更新")//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    public ResultDTO updateUser(User user,BindingResult result){
        if(result.hasErrors()){
            return ResultDTO.customFail(ValidateHelp.toStringJson(result));
        }
        if(user.getUserId() == null || user.getUserId() <= 0){
            return ResultDTO.verifyFail();
        }
        userService.updateUser(user);
        return ResultDTO.success();
    }

    /**
     * 用户删除
     * @param user
     * @param result
     * @return
     */
    @Log("用户删除")
    @PostMapping("/deleteUser")
    @ApiOperation(value = "用户删除")//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    public ResultDTO deleteUser(User user,BindingResult result){
        if(user == null || user.getUserId() == null
                || user.getUserId() <= 0){
            return ResultDTO.verifyFail();
        }
        userService.deleteUser(user);
        return ResultDTO.success();
    }

    /**
     * 获取一条用户数据
     * @param userId
     * @return
     */
    @Log("获取一条用户数据")
    @GetMapping("/userOne/{userId}")
    @ApiOperation(value = "获取一条用户数据")//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    public ResultDTO userOne(@PathVariable Integer userId){
        if(userId == null || userId <= 0){
            return ResultDTO.verifyFail();
        }
        User user = new User();
        user.setUserId(userId);
        return ResultDTO.success().putDataValue("user",userService.getUser(user));
    }

    /**
     * 通过用户名验证用户是否已经存在
     * @param username
     * @return
     */
    @Log("通过用户名验证用户是否已经存在")
    @GetMapping("/checkUser")
    @ApiOperation(value = "通过用户名验证用户是否已经存在")//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    public ResultDTO checkUser(String username){
        if(username == null || username.equals("")){
            return ResultDTO.verifyFail();
        }
        User user = new User();
        user.setUsername(username);
        return ResultDTO.success().putDataValue("user",userService.getUserByUsername(user));
    }
}
