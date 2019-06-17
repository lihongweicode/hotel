package org.dppc.vv.admin.service.impl;

import org.dppc.vv.admin.entity.Api;
import org.dppc.vv.admin.entity.Menu;
import org.dppc.vv.admin.repository.ApiRepository;
import org.dppc.vv.admin.repository.MenuRepository;
import org.dppc.vv.admin.service.ApiService;
import org.dppc.vv.admin.service.MenuService;
import org.dppc.vv.common.help.BeanHelp;
import org.dppc.vv.common.model.QueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @描述 :
 * @作者 :	zhumh
 * @日期 :	2018/12/4
 * @时间 :	10:01
 */
@Service
@Transactional
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiRepository apiRepository;

    @Override
    public void saveApi(Api api) {
        apiRepository.save(api);
    }

    @Override
    public void updateApi(Api api) {
        apiRepository.saveAndFlush(api);
    }

    @Override
    public void deleteApi(Api api) {
        apiRepository.delete(api);
    }

    @Override
    public Page<Api> getApiPage(QueryDTO queryDTO, Api api) {
        Pageable pageable = queryDTO.gainPageable();
        queryDTO.setQuery(api);
        Page<Api> page = apiRepository.findAll((root, query1, cb) -> BeanHelp.getPredicate(root, queryDTO.getQuery(), cb), pageable);
        return page;
    }

    @Override
    public Api getApi(Api api) {
        return apiRepository.findOne(api.getApiId());
    }

    @Override
    public List<Api> getAllApi() {
        return apiRepository.findAll();
    }
}
