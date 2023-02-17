package com.mouse.core.validate.username

import com.mouse.core.validate.BaseValidate

class UsernameValidate : BaseValidate<String>(
    LengthValidate(3, 30)
)