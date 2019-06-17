package org.dppc.vv.admin.service.impl;

import org.dppc.vv.admin.entity.User;
import org.dppc.vv.admin.repository.UserRepository;
import org.dppc.vv.admin.service.UserService;
import org.dppc.vv.common.help.BeanHelp;
import org.dppc.vv.common.help.MD5Help;
import org.dppc.vv.common.model.QueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @描述 :
 * @作者 :	zhumh
 * @日期 :	2018/11/30
 * @时间 :	10:18
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        if (!StringUtils.isEmpty(user.getPassword())){
            user.setPassword(MD5Help.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public User getUserOne(String username,String password) {
        return userRepository.findByusernameAndPassword(username,MD5Help.encode(password));
    }

    @Override
    public Page<User> getUserPage(QueryDTO queryDTO, User user) {
        Pageable pageable = queryDTO.gainPageable();
        queryDTO.setQuery(user);
        Page<User> page = userRepository.findAll((root, query1, cb) -> BeanHelp.getPredicate(root, queryDTO.getQuery(), cb), pageable);
        return page;
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public User getUser(User user) {
        return userRepository.findOne(user.getUserId());
    }

    @Override
    public User getUserByUsername(User user) {
        return userRepository.findByUsername(user.getUsername());
    }
}
