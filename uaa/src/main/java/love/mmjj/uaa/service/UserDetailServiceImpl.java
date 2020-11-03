package love.mmjj.uaa.service;

import love.mmjj.uaa.dao.UserDao;
import love.mmjj.uaa.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author YuJian
 * @description 配置用户信息服务
 * @since 2020/11/2
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserDao userDao;

    public UserDetailServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userDao.findByUsername(username);
        AtomicReference<UserDetails> userDetails = new AtomicReference<>();
        userOptional.ifPresent(user -> {
            List<String> permissions = userDao.findPermissionsByUserId(user.getId());
            userDetails.set(org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(user.getPassword()).authorities(permissions.toArray(new String[0])).build());
        });
        return userDetails.get();
    }
}
