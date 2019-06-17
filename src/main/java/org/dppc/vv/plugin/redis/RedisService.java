package org.dppc.vv.plugin.redis;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @描述 :
 * @作者 :	zhumh
 * @日期 :	2018/11/29
 * @时间 :	16:45
 */
@Service
public class RedisService {
    private static Map<String, Object> map = new HashMap<>();

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            map.put(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            map.remove(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return map.containsKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        result = map.get(key);
        return result;
    }

}
