import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated

class TestProcessor(private val environment: SymbolProcessorEnvironment) :
    SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        println("$resolver-----")
        return emptyList()
    }

}
