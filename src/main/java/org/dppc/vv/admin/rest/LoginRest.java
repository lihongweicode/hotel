package org.dppc.vv.admin.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.dppc.vv.admin.entity.User;
import org.dppc.vv.admin.service.UserService;
import org.dppc.vv.common.annotation.Log;
import org.dppc.vv.common.annotation.NotTokenSecurity;
import org.dppc.vv.common.help.GeetestConfig;
import org.dppc.vv.common.help.GeetestLib;
import org.dppc.vv.common.model.ResultDTO;
import org.dppc.vv.plugin.token.TokenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录控制器
 * @author zhumh
 */
@Api(tags = "用户登录")
@RestController
@RequestMapping("/auth")
public class LoginRest {

	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Token服务
	 */
	@Resource(name="redisTokenManager")
	private TokenManager tokenManager;

	@Autowired
	private UserService userService;

	@NotTokenSecurity
	@Log("初始化登录验证")
	@GetMapping("/startCaptcha")
	public void startCaptcha(HttpServletRequest request,
							 HttpServletResponse response) throws IOException {
		GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(),
				GeetestConfig.isnewfailback());
		String resStr = "{}";
		String userid = "test";

		//自定义参数,可选择添加
		HashMap<String, String> param = new HashMap<>();
		param.put("client_type", "web"); //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式

		//进行验证预处理
		int gtServerStatus = gtSdk.preProcess(param);
		//1表示初始化成功，0表示初始化失败
		if (gtServerStatus == 1) {
			logger.info("登录验证初始化成功！");
		} else if (gtServerStatus == 0) {
			logger.info("登录验证初始化失败！");
		}

		resStr = gtSdk.getResponseStr();
		PrintWriter out = response.getWriter();
		out.println(resStr);
		System.out.println(resStr);
	}

	@NotTokenSecurity
	@Log("用户登录")
	@ApiOperation(value = "获取token",response = ResultDTO.class)
	@ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = ResultDTO.class)})
    @PostMapping(value = "/login")
    public ResultDTO hello(String username,String password){
		/*if(result.hasErrors()){
			return ResultDTO.customFail(ValidateHelp.toStringJson(result));
		}*/
		User sysUser = userService.getUserOne(username,password);
		if(sysUser != null){

			String token = tokenManager.getToken(sysUser);
			Map<String, Object> map = new HashMap<String, Object>();
			return ResultDTO.success().putDataValue("token",token).putDataValue("user",sysUser);
		}
		return ResultDTO.customFail("用户名或密码错误");
    }

	/**
	 * 退出系统
	 * @return
	 */
	@Log("用户退出")
	@ApiOperation(value = "用户登出",response = ResultDTO.class)
    @PostMapping(value = "/logout")
    public ResultDTO logout(String token){
		tokenManager.logout(token);
		return ResultDTO.success();//"退出成功."
    }

	/**
	 * 验证token是否过期
	 * @return
	 */
	@NotTokenSecurity
	@Log("验证token是否过期")
	@ApiOperation(value = "验证token是否过期",response = ResultDTO.class)
	@PostMapping(value = "/tokenValid")
    public ResultDTO tokenValid(String token){
		User user = tokenManager.getUserByToken(token);
		if(user != null && user.getUserId() != null &&
				!user.getUserId().equals("")){
			return ResultDTO.success();
		}
		return ResultDTO.fail();
	}
}
