package org.dppc.vv.admin.service;

import org.dppc.vv.admin.entity.Api;
import org.dppc.vv.common.model.QueryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @描述 :  接口
 * @作者 :	zhumh
 * @日期 :	2018/12/7
 * @时间 :	15:51
 */
public interface ApiService {
    /**
     *保存接口
     * @param api
     */
    void saveApi(Api api);

    /**
     * 更新接口
     * @param api
     */
    void updateApi(Api api);

    /**
     * 删除接口
     * @param api
     */
    void deleteApi(Api api);

    /**
     * 接口分页
     * @param api
     * @return
     */
    Page<Api> getApiPage(QueryDTO queryDTO, Api api);

    /**
     * 获取一条接口数据
     * @param api
     * @return
     */
    Api getApi(Api api);

    /**
     * 获取所有API
     * @return
     */
    List<Api> getAllApi();
}
