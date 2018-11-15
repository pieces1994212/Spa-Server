package com.pieces.spaserver.control.function;

import com.pieces.spaserver.model.function.Function;
import com.pieces.spaserver.service.function.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Xujing
 * @ClassName: ${CLASS_NAME}
 * @Description:
 * @PackageName: com.pieces.spaserver.control.function
 * @date 14:11 2018/11/15
 * @编辑：
 * @描述：
 */
@RestController
@RequestMapping("/api/function")
public class FunctionController {

    @Autowired
    private FunctionService functionService;

    @RequestMapping("/queryFunction")
    public List<Function> queryFunction(Function info){
        return functionService.queryFunctions(info);
    }
}
