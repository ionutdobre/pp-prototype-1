package com.example.demo.constants;

import org.apache.wicket.validation.validator.StringValidator;

public final class WebConstants {
    private WebConstants() {

    }

    public static final String DISABLE_FORM_LEAVING_JS
            = "if(typeof disableFormLeavingConfirmation === 'function') disableFormLeavingConfirmation();";

    public static final String ENABLE_FORM_LEAVING_JS
            = "if(typeof enableFormLeavingConfirmation === 'function') enableFormLeavingConfirmation();";


    public static final int PAGE_SIZE = 20;
    public static final int SELECT_PAGE_SIZE = 25;

    public static final String PARAM_PRINT = "print";

    public static final String PARAM_ID = "id";
    public static final String V_POSITION = "vPosition";
    public static final String MAX_HEIGHT = "maxPosition";

    public static final class StringValidators {
        public static final StringValidator MAXIMUM_LENGTH_VALIDATOR_ONE_LINE_TEXT =
                StringValidator.maximumLength(DBConstants.MAX_DEFAULT_TEXT_LENGTH_ONE_LINE);
        public static final StringValidator MAXIMUM_LENGTH_VALIDATOR_STD_DEFAULT_TEXT =
                StringValidator.maximumLength(DBConstants.STD_DEFAULT_TEXT_LENGTH);
        public static final StringValidator MAXIMUM_LENGTH_VALIDATOR_ONE_LINE_TEXTAREA =
                StringValidator.maximumLength(DBConstants.MAX_DEFAULT_TEXT_AREA);
    }
}
