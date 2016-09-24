package org.darebeat.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StaticController {
    @RequestMapping(value="/static",method=RequestMethod.GET)
    public String index(){
        return "static";
    }

    @RequestMapping(value="/staticPage",method=RequestMethod.GET)
    public String redirect(){
        return "redirect:/pages/final.htm";
    }
}
