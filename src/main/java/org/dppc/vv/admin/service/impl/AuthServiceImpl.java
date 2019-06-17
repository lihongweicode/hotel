package org.dppc.vv.admin.service.impl;

import org.dppc.vv.admin.entity.Api;
import org.dppc.vv.admin.entity.Auth;
import org.dppc.vv.admin.entity.Menu;
import org.dppc.vv.admin.repository.ApiRepository;
import org.dppc.vv.admin.repository.AuthRepository;
import org.dppc.vv.admin.service.ApiService;
import org.dppc.vv.admin.service.AuthService;
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
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Override
    public void update(Auth auth) {
        authRepository.saveAndFlush(auth);
    }

    @Override
    public void delete(Auth auth) {
        authRepository.delete(auth);
    }

    @Override
    public Page<Auth> getAuthPage(QueryDTO queryDTO, Auth auth) {
        Page<Auth> page = authRepository.findAll(
                (root, query1, cb) -> BeanHelp.getPredicate(root, queryDTO.getQuery(), cb),
                queryDTO.gainPageable(auth));
        return page;
    }

    @Override
    public List<Auth> getAuthAll() {
        return authRepository.findAll();
    }

    @Override
    public void saveAuth(Auth auth) {
        authRepository.save(auth);
    }

    @Override
    public Auth getAuth(Auth auth) {
        return authRepository.findOne(auth.getAuthId());
    }

    @Override
    public List<Auth> getAllauth() {
        return authRepository.findAll();
    }
}
