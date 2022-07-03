package com.abroadbent.money

import com.fasterxml.jackson.core.util.VersionUtil
import com.fasterxml.jackson.databind.module.SimpleModule

class MoneyModule : SimpleModule(
    MoneyModule::class.java.name,
    VersionUtil.parseVersion("0.3.0", "com.abroadbent", "money-jackson")
) {
    override fun setupModule(context: SetupContext) {
        super.setupModule(context)

        context.addSerializers(MoneySerializers())
        context.addDeserializers(MoneyDeserializers())
    }
}
