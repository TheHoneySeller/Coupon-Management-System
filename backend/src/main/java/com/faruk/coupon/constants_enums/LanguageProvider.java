package com.faruk.coupon.constants_enums;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

//to do: not a priority. implement and use later.
@Component
public class LanguageProvider {
    public enum STRING_CODE {
        HELLO, COUPONS_NULL_OR_TOO_MANY
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
