import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import com.google.devtools.ksp.validate

/**
 * create by wzq on 2023/8/17
 *
 */

private const val TAG = "ksp-->"

class NavProcessor(
    val codeGenerator: CodeGenerator, val logger: KSPLogger, val options: Map<String, String>
) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation("com.wzq.router.MyRouter")
        val ret = symbols.filter { !it.validate() }.toList()
        val kv = mutableListOf<Pair<String, String>>()

        val routes = symbols.filter { it.validate() && it is KSClassDeclaration }

        routes.forEach {
            val annotation =
                it.annotations.find { et -> et.annotationType.resolve().declaration.qualifiedName?.asString() == "com.wzq.router.MyRouter" }
            if (annotation != null) {
                val path =
                    annotation.arguments.find { it.name?.asString() == "path" }?.value.toString()
                val clazz = (it as KSClassDeclaration).qualifiedName?.asString() ?: ""
                kv.add(path to clazz)
            }
        }

        if (kv.isNotEmpty()) {
            buildMap(kv)
        }

        return ret
    }

    private fun buildMap(routes: List<Pair<String, String>>) {
        val packageName = "com.wzq.router"
        val className = "MyRouterMap"
        val file = codeGenerator.createNewFile(
            dependencies = Dependencies(false),
            packageName = packageName,
            fileName = className
        )
        file.bufferedWriter().use {
            val kv = buildString {
                for ((k, v) in routes) {
                    appendLine("\"$k\" to \"$v\",")
                }
            }
            it.appendLine()
            it.write("""
                package $packageName 
                
                object $className {

                    private val routerMap = mapOf(
                        $kv
                    )

                    fun getClassByPath(path: String) = routerMap[path]
                }
            """.trimIndent()
            )
        }

        logger.info("$TAG  Generated route map: $packageName.$className")
    }
}

class NavProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return NavProcessor(environment.codeGenerator, environment.logger, environment.options)
    }

}