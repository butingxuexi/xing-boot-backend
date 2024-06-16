package cn.izoro.convert;

import cn.izoro.model.entity.SystemUserDO;
import cn.izoro.model.vo.login.UserRespVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author zoro
 */
@SpringBootTest
public class ConvertTest {
    @Test
    public void test() {
        SystemUserDO systemUserDO = new SystemUserDO();
        systemUserDO.setNickname("不相信");
        UserRespVO authLoginRespVO = SystemUserConvert.INSTANCE.toRespVO(systemUserDO);
        System.out.println(systemUserDO);
    }
}
