package me.leig.task.conf;

import lombok.extern.slf4j.Slf4j;
import me.leig.task.base.aes.AESUtils;
import me.leig.task.base.aes.impl.AESUtilsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AESUtilsConfig {

    @Bean("aesUtils")
    public AESUtils getAESUtils() {
        log.info("AESUtilsConfig.getAESUtils()");
        AESUtilsImpl.seed = "task";
        return AESUtilsImpl.getInstance();
    }

}
