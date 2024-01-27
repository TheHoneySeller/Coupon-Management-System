package com.faruk.coupon.constants_enums;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class LanguageProvider {
    public static enum STRING_CODE {
        HELLO
    }
    
    private Map<STRING_CODE, String> currentLanguage;

    public LanguageProvider() {
        currentLanguage = new HashMap<STRING_CODE, String>();
        
        currentLanguage.put(STRING_CODE.HELLO, "hello");
    }




    public String getString(STRING_CODE code) {
        if (currentLanguage.containsKey(code)) {
            return currentLanguage.get(code);
        } else {
            return "";
        }
    }


}
