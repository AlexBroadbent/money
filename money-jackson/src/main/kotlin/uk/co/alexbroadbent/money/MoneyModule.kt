package uk.co.alexbroadbent.money

import com.fasterxml.jackson.core.util.VersionUtil
import com.fasterxml.jackson.databind.module.SimpleModule

class MoneyModule : SimpleModule(
    MoneyModule::class.java.name,
    VersionUtil.parseVersion("0.1.0", "uk.co.alexbroadbent", "money-jackson")
) {
    override fun setupModule(context: SetupContext) {
        super.setupModule(context)

        context.addSerializers(MoneySerializers())
        context.addDeserializers(MoneyDeserializers())
    }
}
