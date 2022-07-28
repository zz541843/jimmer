package org.babyfish.jimmer.sql.kt.ast.query

import org.babyfish.jimmer.sql.kt.KSubQueries
import org.babyfish.jimmer.sql.kt.KWildSubQueries
import org.babyfish.jimmer.sql.kt.ast.expression.KNonNullExpression
import org.babyfish.jimmer.sql.kt.ast.table.KNonNullTable
import kotlin.reflect.KClass

interface KFilterable<E: Any> {

    val table: KNonNullTable<E>

    fun where(vararg predicates: KNonNullExpression<Boolean>)

    fun <X: Any, R, SQ: KConfigurableSubQuery<R>> subQuery(
        entityType: KClass<X>,
        block: KMutableSubQuery<E, X>.() -> SQ
    ): SQ =
        subQueries.forEntity(entityType, block)

    fun <X: Any> wildSubQuery(
        entityType: KClass<X>,
        block: KMutableSubQuery<E, X>.() -> Unit
    ): KMutableSubQuery<E, X> =
        wildSubQueries.forEntity(entityType, block)

    val subQueries: KSubQueries<E>

    val wildSubQueries: KWildSubQueries<E>
}