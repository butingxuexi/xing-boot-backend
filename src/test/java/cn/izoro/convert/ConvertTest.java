package cn.izoro.convert;

import cn.izoro.model.VO.userRespVO;
import cn.izoro.model.dataobject.SystemUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author zoro
 */
@SpringBootTest
public class ConvertTest {
    @Test
    public void test() {
        SystemUser systemUser = new SystemUser();
        systemUser.setNickname("不相信");
        userRespVO authLoginRespVO = SystemUserConvert.INSTANCE.toRespVO(systemUser);
        System.out.println(systemUser);
    }
}
