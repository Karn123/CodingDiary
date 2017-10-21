package framework.config;

import common.util.DesThreeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Properties;

/**
 * Created by Karn on 2016/11/29.
 */
public class CodingDiaryConfigure extends PropertyPlaceholderConfigurer{
    private static final Logger log = LoggerFactory.getLogger(CodingDiaryConfigure.class);
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory,Properties props)
            throws BeansException{
        String password = props.getProperty("hibernate.connection.password");
     //   String s2= DesThreeUtil.filePassEncrypt("chuangx");
     //   System.out.println("PASSWORD After Decryption: "+ s2);
     //   log.info(s2);
        if(password != null && !password.trim().equals("")){
            props.setProperty("hibernate.connection.password", DesThreeUtil.filePassDecrypt(password));
        }

        super.processProperties(beanFactory, props);
    }
}