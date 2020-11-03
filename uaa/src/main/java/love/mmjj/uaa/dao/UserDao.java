package love.mmjj.uaa.dao;

import love.mmjj.uaa.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author YuJian
 * @description
 * @since 2020/11/2
 */
@Component
public interface UserDao {
    Optional<User> findByUsername(@Param("username") String username);

    List<String> findPermissionsByUserId(@Param("userId") Long userId);
}
