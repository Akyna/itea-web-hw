package com.amboiko;

import com.amboiko.entity.User;
import com.amboiko.services.DBService;
import com.amboiko.services.UserService;
import com.amboiko.services.ValidatorService;
import com.amboiko.utils.ApplicationConstant;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getLogin(
            HttpSession session,
            DBService dbService,
            @RequestParam(required = false) String logout
    ) {
        String url = ApplicationConstant.LOGIN_PAGE_URL;

        if (logout != null) {
            session.removeAttribute(ApplicationConstant.APP_USER);
            session.setAttribute(ApplicationConstant.APP_USER_IS_AUTHORIZED, Boolean.FALSE);
        }

        Object appUser = session.getAttribute(ApplicationConstant.APP_USER);
        if (appUser instanceof User && userService.getUserById(((User) appUser).getId()) != null) {
            session.setAttribute(ApplicationConstant.APP_USER, appUser);
            session.setAttribute(ApplicationConstant.APP_USER_IS_AUTHORIZED, Boolean.TRUE);
            url = ApplicationConstant.HOME_PAGE_URL;
        }

        return url;
    }

    @PostMapping
    public String postLogin(
            @RequestParam Map<String, String> data,
            HttpSession session,
            ModelMap modelMap
    ) {
        boolean isErrorEmpty;
        String submit_form = ValidatorService.trimInput(data.get("submit"));
        String email = ValidatorService.trimInput(data.get("email"));
        String password = ValidatorService.trimInput(data.get("password"));
        String url = ApplicationConstant.LOGIN_PAGE_URL;

        if ("Confirm".equals(submit_form)) {
            String emailError = ValidatorService.getFieldError(email, ValidatorService.EMAIL_REGEXP, "Email address is invalid");
            String passwordError = ValidatorService.getFieldError(password, ValidatorService.PASSWORD_REGEXP, "The password must contain at least 8 characters, there are capital letters and at least 1 digit");

            modelMap.addAttribute("emailError", emailError);
            modelMap.addAttribute("passwordError", passwordError);

            isErrorEmpty = ValidatorService.validateFields(new String[]{
                    emailError,
                    passwordError,
            });

            User user;
            modelMap.addAttribute("email", email);
            modelMap.addAttribute("password", password);

            if (isErrorEmpty) {
                modelMap.addAttribute("errorMessage", "User not found. Please try login again");
                if ((user = userService.getUserByEmailAndPassword(email, password)) != null) {
                    session.setAttribute(ApplicationConstant.APP_USER, user);
                    session.setAttribute(ApplicationConstant.APP_USER_IS_AUTHORIZED, Boolean.TRUE);
                    url = ApplicationConstant.HOME_PAGE_URL;
                }
            }

        }

        return url;
    }
}

// TODO:
//  @RequestMapping(value = "/delete", method = RequestMethod.GET)
//  public String deleteUser(@RequestParam("login") String login,
//        Model model,RedirectAttributes redirectAttributes) {
//    dao.delete(login); // there is NO exeptions
//    //map.addAttribute("message", "User " + login + " deleted");
//    redirectAttributes.addFlashAttribute("message", "User " + login + " deleted");
//    return "redirect:" + "index";
//  }
//  redirectAttributes.addAttribute constructs request parameters out of your attributes and redirects
//  to the desired page with the request parameters. And addFlashAttribute stores the attributes in a
//  flashmap (maintained in the users session and removed once the next redirected request gets fulfilled).
