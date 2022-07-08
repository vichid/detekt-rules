package io.github.vichid.ktlintrules

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtClass

class NoSingletonAnnotation(config: Config) : Rule(config) {

    override val issue = Issue(
        javaClass.simpleName,
        Severity.Maintainability,
        "Reports the wrong usage of the @Singleton annotation",
        Debt(mins = 1)
    )

    override fun visitClass(klass: KtClass) {
        if (klass.annotationEntries.map { it.text }.any { it.startsWith("@Singleton") }) {
            report(
                CodeSmell(
                    issue, Entity.from(klass),
                    "Prefer @SingleIn($::class)"
                )
            )
        }
    }
}
