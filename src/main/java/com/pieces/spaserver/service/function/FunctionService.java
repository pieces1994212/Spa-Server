package com.pieces.spaserver.service.function;

import com.pieces.spaserver.model.function.Function;

import java.util.List;

/**
 * @author Xujing
 * @ClassName: ${CLASS_NAME}
 * @Description:
 * @PackageName: com.pieces.spaserver.service.function
 * @date 15:45 2018/11/9
 * @编辑：
 * @描述：
 */
public interface FunctionService {

    List<Function> queryFunctions(Function info);
}
