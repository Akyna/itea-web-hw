package com.amboiko;

import com.amboiko.entity.User;
import com.amboiko.services.EncodingService;
import com.amboiko.services.UserService;
import com.amboiko.services.ValidatorService;
import com.amboiko.utils.ApplicationConstant;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegistration(HttpSession session) {
        String url = ApplicationConstant.REGISTRATION_PAGE_URL;

        Object appUser = session.getAttribute(ApplicationConstant.APP_USER);

        if (appUser instanceof User && userService.getUserById(((User) appUser).getId()) != null) {
            session.setAttribute(ApplicationConstant.APP_USER, appUser);
            session.setAttribute(ApplicationConstant.APP_USER_IS_AUTHORIZED, Boolean.TRUE);
            url = ApplicationConstant.HOME_PAGE_URL;
        }

        return url;
    }


    @PostMapping
    public String postRegistration(
            @RequestParam Map<String,
                    String> data,
            HttpSession session,
            ModelMap modelMap,
            @ModelAttribute User user
    ) {
        String url = ApplicationConstant.REGISTRATION_PAGE_URL;

        boolean isErrorEmpty;
        String submit_form = data.get("submit");
        String email = ValidatorService.trimInput(user.getEmail());
        String password = ValidatorService.trimInput(user.getPassword());
        String retypePassword = ValidatorService.trimInput(data.get("retypePassword"));
        String fullName = ValidatorService.trimInput(user.getFullName());

        if ("Confirm".equals(submit_form)) {
            String emailError = ValidatorService.getFieldError(email, ValidatorService.EMAIL_REGEXP, "Email address is invalid");
            String passwordError = ValidatorService.getFieldError(password, ValidatorService.PASSWORD_REGEXP, "The password must contain at least 8 characters, there are capital letters and at least 1 digit");
            String retypePasswordError = ValidatorService.getRetypePasswordError(password, retypePassword);
            String fullNameError = ValidatorService.checkIsNotEmpty(fullName);


            modelMap.addAttribute("emailError", emailError);
            modelMap.addAttribute("passwordError", passwordError);
            modelMap.addAttribute("retypePasswordError", retypePasswordError);
            modelMap.addAttribute("fullNameError", fullNameError);

            isErrorEmpty = ValidatorService.validateFields(new String[]{
                    emailError,
                    passwordError,
                    retypePasswordError,
                    fullNameError
            });


            modelMap.addAttribute("email", email);
            modelMap.addAttribute("password", password);
            modelMap.addAttribute("retypePassword", retypePassword);
            modelMap.addAttribute("fullName", fullName);

            if (isErrorEmpty) {
                modelMap.addAttribute("errorMessage", "Registration failed. Please try again");

                user.setPassword(EncodingService.md5EncryptionWithSalt(password));
                if (userService.saveUser(user) > 0) {
                    session.setAttribute(ApplicationConstant.APP_USER, new User().setFullName(fullName));
                    session.setAttribute(ApplicationConstant.APP_USER_IS_AUTHORIZED, Boolean.TRUE);
                    url = ApplicationConstant.HOME_PAGE_URL;
                }
            }
        }

        return url;
    }
}
