package test.alexevtushik.justapptest.models.mappers

/**
 * Mapper abstract class used to transform From type into To type
 *
 * @param <From> source type
 * @param <To>   destination type
 */
abstract class Mapper<in From, out To> {

    /**
     * Transform {@link From} into {@link To}
     *
     * @param from source type
     * @return transformed value
     */
    abstract fun transform(from: From): To

    /**
     * Transform list of {@link From} into list of {@link To}
     *
     * @param fromList list of source type
     * @return {@link List} of transformed value
     */
    fun transform(fromList: List<From>): List<To> {
        val toList = mutableListOf<To>()
        fromList.forEach { from: From -> toList.add(transform(from)) }
        return toList
    }
}