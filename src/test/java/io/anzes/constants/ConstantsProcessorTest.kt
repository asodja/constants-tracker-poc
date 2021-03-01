package io.anzes.constants

import org.joor.CompileOptions
import org.joor.Reflect
import org.junit.jupiter.api.Test

class ConstantsProcessorTest {

    var clazz = """
        package io.anzes.constants;
        import io.anzes.constants.annotations.*;
        import io.anzes.constants.constants.*;
        import java.util.Map;
        
        import static io.anzes.constants.constants.StaticAnnConstantOnMethod.x;
        import static io.anzes.constants.constants.StaticFinalFieldDeclarationConstant.staticFinal;
        import static io.anzes.constants.constants.FieldDeclarationConstant.x2;

        @Annotation(AnnConstantOnClass.x)
        public class TestClass<@Annotation(AnnConstantOnClassTypeParam.x) T> {
        
            @Annotation(AnnConstantOnField.x + 1)
            private Map<@Annotation(AnnConstantOnFieldTypeParam.x) String, String> field;
            
            public static final int staticFinalField = staticFinal + 1;
            
            public final int finalField = FinalFieldDeclarationConstant.x + 1;
            
            private int fieldField = 1 + x2;
        
            @Annotation(AnnConstantOnMethod.x + 5)
            <@Annotation(AnnConstantOnMethodTypeParam.x) T> T methodBody(@Annotation(AnnConstantOnMethodArgument.x) String argument) {
                return null;
            }
            
            @Annotation(x)
            void methodBody2() {
            }
            
        }
""".trimIndent()
//
//    var clazz2 = """
//        package io.anzes.constants;
//        import static io.anzes.constants.constants.StaticFinalFieldDeclarationConstant.staticFinal;
//
//        public class TestClass2 {
//
//            private static final int staticFinalField = staticFinal + 1;
//
//        }
//""".trimIndent()

    @Test
    fun shouldPrintAllConstantsFromClass() {
        val processor = ConstantsProcessor()
        try {
            Reflect.compile(
                "io.anzes.constants.TestClass",
                clazz,
                CompileOptions().processors(processor)
            ).create()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}