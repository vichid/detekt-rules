package io.github.vichid

import io.github.vichid.ktlintrules.NoSingletonAnnotation
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class ExtraRuleSetProvider : RuleSetProvider {

    override val ruleSetId: String = "ExtraRuleSet"

    override fun instance(config: Config): RuleSet = RuleSet(
        ruleSetId,
        listOf(
            NoSingletonAnnotation(config)
        )
    )
}
