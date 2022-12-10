package com.amboiko;

import com.amboiko.model.User;
import com.amboiko.services.DBService;
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
@RequestMapping("/registration")
public class RegistrationController {

    @GetMapping
    public String getRegistration(HttpSession session, DBService dbService) {
        String url = ApplicationConstant.REGISTRATION_PAGE_URL;

        Object appUser = session.getAttribute(ApplicationConstant.APP_USER);

        if (appUser instanceof User && dbService.getUserById(((User) appUser).getId()) != null) {
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
            DBService dbService
    ) {

        boolean isErrorEmpty;
        String submit_form = ValidatorService.trimInput(data.get("submit"));
        String email = ValidatorService.trimInput(data.get("email"));
        String password = ValidatorService.trimInput(data.get("password"));
        String retypePassword = ValidatorService.trimInput(data.get("retypePassword"));
        String fullName = ValidatorService.trimInput(data.get("fullName"));
        String url = ApplicationConstant.REGISTRATION_PAGE_URL;

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
                int userId;
                if ((userId = dbService.storeUser(email, password, fullName)) > 0) {
                    session.setAttribute(ApplicationConstant.APP_USER, new User(userId, fullName));
                    session.setAttribute(ApplicationConstant.APP_USER_IS_AUTHORIZED, Boolean.TRUE);
                    url = ApplicationConstant.HOME_PAGE_URL;
                }
            }
        }

        return url;
    }
}
