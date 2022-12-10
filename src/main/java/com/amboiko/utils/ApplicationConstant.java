package com.amboiko.utils;

import org.springframework.stereotype.Service;

@Service
public class ApplicationConstant {
    public final static String HOME_PAGE_URL = "/pages/home";
    public final static String LOGIN_PAGE_URL = "/pages/login";
    public static final String REGISTRATION_PAGE_URL = "/pages/registration";
    public final static String PRODUCT_PAGE_URL = "/pages/product";
    public final static String PRODUCTS_PAGE_URL = "/pages/products";
    public final static String PRODUCTS_IN_CATEGORY_PAGE_URL = "/pages/products_in_category";
    public final static String CART_PAGE_URL = "/pages/cart";
    public final static String SECRET_PAGE_URL = "/pages/secret";
    public final static String FORBIDDEN_PAGE_URL = "/pages/forbidden";

    public static final String APP_USER = "app_user";
    public final static String APP_CATEGORIES = "app_categories";
    public final static String APP_PRODUCTS = "app_products";
    public final static String APP_PRODUCT = "app_product";
    public final static String APP_USER_IS_AUTHORIZED = "app_user_is_authorized";
    public final static String APP_CART = "app_cart";
}
