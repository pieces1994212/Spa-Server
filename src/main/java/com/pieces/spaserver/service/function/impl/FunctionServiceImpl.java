package com.pieces.spaserver.service.function.impl;

import com.pieces.spaserver.mapper.function.FunctionMapper;
import com.pieces.spaserver.model.function.Function;
import com.pieces.spaserver.service.function.FunctionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xujing
 * @ClassName: ${CLASS_NAME}
 * @Description:
 * @PackageName: com.pieces.spaserver.service.function.impl
 * @date 15:46 2018/11/9
 * @编辑：
 * @描述：
 */
@Service
public class FunctionServiceImpl implements FunctionService {

    private Logger logger = LoggerFactory.getLogger(FunctionServiceImpl.class);

    @Autowired
    FunctionMapper functionMapper;

    @Autowired
    RedisTemplate<String,Serializable> redisTemplate;

    @Override
    public List<Function> queryFunctions(Function info) {
        List list;
        if(redisTemplate.hasKey("queryFunctions"+info.toString())){
            list = redisTemplate.opsForList().range("queryFunctions"+info.toString(),0,-1);
            logger.info("从缓存获取结果");
            return list;
        }
        list = functionMapper.queryFunctions(info);
        logger.info("从数据库获取结果");
        redisTemplate.opsForList().leftPushAll("queryFunctions"+info.toString(), list);
        return list;
    }
}
