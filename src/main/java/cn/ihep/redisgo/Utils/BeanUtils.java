package cn.ihep.redisgo.Utils;

import java.util.Map;

/**
 * @author WuYe
 * @vesion 1.0 2019/11/2
 * /
 * /**
 * @program: redisgo
 * @description:
 * @author: WuYe
 * @create: 2019-11-02 15:18
 **/
public class BeanUtils {
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null) {
            return null;
        }

        Object obj = beanClass.newInstance();

        org.apache.commons.beanutils.BeanUtils.populate(obj, map);

        return obj;
    }

    public static Map<?, ?> objectToMap(Object obj) {
        if(obj == null) {
            return null;
        }

        return new org.apache.commons.beanutils.BeanMap(obj);
    }
}
