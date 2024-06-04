package cn.izoro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("cn.izoro.mapper")
public class XingBootBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(XingBootBackendApplication.class, args);
    }

}
