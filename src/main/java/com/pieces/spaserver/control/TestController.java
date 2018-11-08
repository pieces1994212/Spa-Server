package com.pieces.spaserver.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xujing
 * @ClassName: ${CLASS_NAME}
 * @Description:
 * @PackageName: com.pieces.spaserver.control
 * @date 11:13 2018/11/8
 * @编辑：
 * @描述：
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/test")
    public String Test(){
        return "test";
    }

}
