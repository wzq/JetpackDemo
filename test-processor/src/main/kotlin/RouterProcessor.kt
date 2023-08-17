import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.validate

/**
 * create by wzq on 2023/8/17
 *
 */

private const val TAG = "ksp-->"
class RouterProcessor(
    val codeGenerator: CodeGenerator,
    val logger: KSPLogger,
    val options: Map<String, String>
): SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbol = resolver.getSymbolsWithAnnotation("com.wzq.router.MyRouter")
        val ret = symbol.filter { !it.validate() }.toList()
        logger.info("$TAG ret -->> $symbol")
        options.forEach {
            logger.info("$TAG kv:${it.key} -- ${it.value}")
        }
        return ret
    }
}

class RouterProcessorProvider: SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return RouterProcessor(environment.codeGenerator, environment.logger, environment.options)
    }

}