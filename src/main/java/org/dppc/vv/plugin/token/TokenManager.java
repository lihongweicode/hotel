package org.dppc.vv.plugin.token;

import org.dppc.vv.admin.entity.User;

/**
 * Token接口
 * @author Gaojun.Zhou
 * @date 2016年12月29日 下午2:34:00
 */
public interface TokenManager {

	/**
	 * 创建token
	 * @param user
	 * @return
	 */
	String getToken(User user);

	/**
	 * 用户退出登陆
	 * @param token
	 */
	void logout(String token);

	/**
	 * 获取用户信息
	 * @param token
	 * @return
	 */
	User getUserByToken(String token);
}
