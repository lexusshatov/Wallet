package com.mouse.core.validate.login

import com.mouse.core.interaction.LoginInteraction
import com.mouse.core.validate.BaseValidate

class LoginValidate : BaseValidate<LoginInteraction.Params>(
    LengthStage(3, 30)
)