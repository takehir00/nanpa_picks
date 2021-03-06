package admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    /**
     * 管理画面TOP遷移
     *
     * @param mav
     * @return
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView top(ModelAndView mav) {
        mav.setViewName("top");
        return mav;
    }
}
