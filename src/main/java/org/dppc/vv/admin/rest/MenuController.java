package org.dppc.vv.admin.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.dppc.vv.admin.entity.Auth;
import org.dppc.vv.admin.entity.Menu;
import org.dppc.vv.admin.entity.User;
import org.dppc.vv.admin.service.AuthService;
import org.dppc.vv.admin.service.MenuService;
import org.dppc.vv.admin.vo.MenuTreeVo;
import org.dppc.vv.common.annotation.Log;
import org.dppc.vv.common.help.ValidateHelp;
import org.dppc.vv.common.model.PageDTO;
import org.dppc.vv.common.model.QueryDTO;
import org.dppc.vv.common.model.ResultDTO;
import org.dppc.vv.plugin.token.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @描述 :  菜单控制
 * @作者 :	zhumh
 * @日期 :	2018/12/4
 * @时间 :	11:27
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private AuthService authService;

    @Resource(name="redisTokenManager")
    private TokenManager tokenManager;

    /**
     * 菜单分页数据
     * @param menu
     * @return
     */
    @Log("获取菜单分页数据")
    @GetMapping("/menuList")
    @ApiOperation(value = "获取菜单分页数据")//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    public PageDTO menuList(QueryDTO queryDTO, Menu menu){
        /** 根据条件分页查询集合 **/
        Page<Menu> page =  menuService.getMenuPage(queryDTO,menu);
        PageDTO pageDTO = new PageDTO(page.getTotalElements(),page.getContent());
        return pageDTO;
    }

    /**
     * 所有菜单数据
     * @return
     */
    @Log("获取所有菜单数据")
    @GetMapping("/menuAll")
    @ApiOperation(value = "获取所有菜单数据")//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    public ResultDTO menuAll(){
        return  ResultDTO.success().putDataValue("menuList",menuService.getMenuAll());
    }

    /**
     * 菜单添加
     * @param menu
     * @param result
     * @return
     */
    @Log("菜单添加")
    @PostMapping("/saveMenu")
    @ApiOperation(value = "菜单添加")//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    public ResultDTO saveMenu(Menu menu,BindingResult result){
       if(result.hasErrors()){
        return ResultDTO.verifyFail().putDataValue("verify",ValidateHelp.toStringJson(result));
        }
        menuService.saveMenu(menu);
       return ResultDTO.success();
    }

    /**
     * 菜单更新
     * @param menu
     * @param result
     * @return
     */
    @Log("菜单更新")
    @PostMapping("/updateMenu")
    @ApiOperation(value = "菜单更新")//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    public ResultDTO updateMenu(Menu menu,BindingResult result){
        if(result.hasErrors()){
            return ResultDTO.customFail(ValidateHelp.toStringJson(result));
        }
        if(menu.getMenuId() == null || menu.getMenuId() <= 0){
            return ResultDTO.verifyFail();
        }
        menuService.update(menu);
        return ResultDTO.success();
    }

    /**
     * 菜单删除
     * @param menu
     * @param result
     * @return
     */
    @Log("菜单删除")
    @PostMapping("/deleteMenu")
    @ApiOperation(value = "菜单删除")//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    public ResultDTO deleteMenu(Menu menu,BindingResult result){
        if(menu == null || menu.getMenuId() == null
                || menu.getMenuId() <= 0){
            return ResultDTO.verifyFail();
        }
        menuService.delete(menu);
        return ResultDTO.success();
    }

    /**
     * 获取一条菜单数据
     * @param menu
     * @param result
     * @return
     */
    @Log("获取一条菜单数据")
    @PostMapping("/menuOne")
    @ApiOperation(value = "获取一条菜单数据")//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    public ResultDTO menuOne(Menu menu,BindingResult result){
        if(menu == null || menu.getMenuId() == null
                || menu.getMenuId() <= 0){
            return ResultDTO.verifyFail();
        }
        return ResultDTO.success().putDataValue("menu",menuService.getMenu(menu));
    }

    /**
     * 获取所有一级菜单
     * @return
     */
    @Log("获取所有一级菜单")
    @GetMapping("/oneLeveMenu")
    @ApiOperation(value = "获取所有一级菜单")//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    public ResultDTO oneLeveMenu(){
        return ResultDTO.success().putDataValue("list",menuService.getAllParentMenu());
    }


    /**
     * 获取tree树形菜单
     * @return
     */
    @Log("获取tree树形菜单")
    @GetMapping("/treeMenu")
    @ApiOperation(value = "获取tree树形菜单")//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    public ResultDTO treeMenu(HttpServletRequest request){
//        return ResultDTO.success().putDataValue("list", MenuTreeVo.generationTreeBySysMenu(menuService.getMenuAll()));
        String token = 	request.getHeader("X-Token");
        if(StringUtils.isEmpty(token)){
            return ResultDTO.tokenFail("当前请求无效，请登录！");
        }
        User u = tokenManager.getUserByToken(token);
        Auth auth = authService.getAuth(u.getAuth());
        return ResultDTO.success().putDataValue("list", MenuTreeVo.generationTreeBySysMenu(auth.getMenuList()));
    }



}
