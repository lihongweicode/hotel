package org.dppc.vv.plugin.token;

import org.dppc.vv.admin.entity.Auth;
import org.dppc.vv.admin.entity.User;
import org.dppc.vv.common.enums.UserTypeEnum;
import org.dppc.vv.plugin.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Redis Token存储实现
 *
 * @author Gaojun.Zhou
 * @date 2016年12月29日 下午2:31:08
 */
@Component
public class RedisTokenManager implements TokenManager {

    @Autowired
    private RedisService redisService;

    @Override
    public String getToken(User user) {
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        User u = new User();
        u.setUserId(user.getUserId());
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setType(user.getType());
        if (user.getType() == UserTypeEnum.DEALER.getValue()) {
            u.setDealerCode(user.getDealerCode());
        }
        Auth auth = new Auth();
        auth.setAuthId(user.getAuth().getAuthId());
        u.setAuth(auth);
        redisService.set(token, u);
        return token;
    }

    @Override
    public void logout(String token) {
        if (redisService.exists(token)) {
            redisService.remove(token);
        }
    }

    @Override
    public User getUserByToken(String token) {
        if (redisService.exists(token)) {
            return (User) redisService.get(token);
        }
        return null;
    }
}
