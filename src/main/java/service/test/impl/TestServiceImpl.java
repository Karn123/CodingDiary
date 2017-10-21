package service.test.impl;

import framework.exceptions.ServiceException;
import model.TagEntity;
import model.UserinfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.common.impl.CommServiceImpl;
import service.test.ITestService;

import java.util.List;

/**
 * Created by Karn on 2016/12/4.
 */
@Service("TestService")
public class TestServiceImpl  extends CommServiceImpl implements ITestService{
    Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Override
    public void test() throws ServiceException {
        //新建一条实体记录并插入到数据库中
//        TagEntity tagEntity = new TagEntity();
//        tagEntity.setTagName("test1-30");
//        baseDAO.save(tagEntity);
//        System.out.println("Newly inserted tag id-------->"+tagEntity.getTagId());

        UserinfoEntity userinfoEntity = new UserinfoEntity();
        //根据某属性查询符合条件的实体集
//        List<TagEntity> tagEntityList = baseDAO.findByProperty("tagName","NewTest",TagEntity.class);
//        TagEntity tagEntity1 = new TagEntity();
//        if(tagEntityList.size()>0)
//            tagEntity1 = tagEntityList.get(0);
//        //打印信息。
//        System.out.println("1. Get Tag2 ID ------->"+tagEntity1.getTagId()+"\n"
//                +"2.Get Tag2 Name -------->"+tagEntity1.getTagName());
//        //logger打印日志。可用于打印系统错误即logger.error(..),
//        // 打印信息log.info(..)与调试logger.debug()。
//        //建议学习一下如何使用它来调试
//        logger.error("----Test log4j----");
    }
}