package io.anzes.constants

import io.anzes.constants.plugin.ConstantsTreeVisitor.CONSTANT_CLASSES
import io.anzes.constants.testing.utils.TestCompiler
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File


class ConstantsProcessorTest {

    private val compiler = TestCompiler()

    @BeforeEach
    fun setUp() {
        CONSTANT_CLASSES.clear()
    }

    @Test
    fun shouldFindAllConstantsFromClass() {
        // Given
        val clazz = File("${File("").absolutePath}/src/test/java/io/anzes/constants/testclass/TestClass.java").readText()

        // When
        compiler.compile("io.anzes.constants.testclass.TestClass", clazz)

        // Then
        println("Found next constants classes:\n\t${CONSTANT_CLASSES.joinToString(separator = "\n\t")}")
        assertThat(CONSTANT_CLASSES).containsExactlyInAnyOrderElementsOf(setOf(
            "io.anzes.constants.constants.AnnConstantOnConstructorArgument",
            "io.anzes.constants.constants.AnnConstantOnConstructor",
            "io.anzes.constants.constants.SwitchCaseConstant",
            "io.anzes.constants.constants.AnnConstantOnClass",
            "io.anzes.constants.constants.AnnConstantOnClassTypeParam",
            "io.anzes.constants.constants.AnnConstantOnMethodArgument",
            "io.anzes.constants.constants.AnnConstantOnFieldTypeParam",
            "io.anzes.constants.constants.IfConditionConstant",
            "io.anzes.constants.constants.AnnConstantOnField",
            "io.anzes.constants.constants.ConstructorFieldConstant",
            "io.anzes.constants.constants.StaticFinalFieldDeclarationConstant",
            "io.anzes.constants.constants.FinalFieldDeclarationConstant",
            "io.anzes.constants.constants.FieldDeclarationConstant",
            "io.anzes.constants.constants.AnnOnLocalFieldConstant",
            "io.anzes.constants.constants.AnnConstantOnMethod",
            "io.anzes.constants.constants.AnnConstantOnMethodTypeParam",
            "io.anzes.constants.constants.ForLoopInitConstant",
            "io.anzes.constants.constants.ForLoopConditionConstant",
            "io.anzes.constants.constants.ForLoopAssignOpConstant",
            "io.anzes.constants.constants.LambdaConstant",
        ))
    }

    @Test
    fun shouldFindAllStaticConstantsFromClass() {
        // Given
        val clazz = File("${File("").absolutePath}/src/test/java/io/anzes/constants/testclass/StaticImportTestClass.java").readText()

        // When
        compiler.compile("io.anzes.constants.testclass.StaticImportTestClass", clazz)

        // Then
        println("Found next constants classes:\n\t${CONSTANT_CLASSES.joinToString(separator = "\n\t")}")
        assertThat(CONSTANT_CLASSES).containsExactlyInAnyOrderElementsOf(setOf(
            "io.anzes.constants.constants.AnnConstantOnConstructorArgument",
            "io.anzes.constants.constants.AnnConstantOnConstructor",
            "io.anzes.constants.constants.SwitchCaseConstant",
            "io.anzes.constants.constants.AnnConstantOnClass",
            "io.anzes.constants.constants.ConstructorFieldConstant",
            "io.anzes.constants.constants.AnnConstantOnClassTypeParam",
            "io.anzes.constants.constants.AnnConstantOnMethodArgument",
            "io.anzes.constants.constants.AnnConstantOnFieldTypeParam",
            "io.anzes.constants.constants.IfConditionConstant",
            "io.anzes.constants.constants.AnnConstantOnField",
            "io.anzes.constants.constants.StaticFinalFieldDeclarationConstant",
            "io.anzes.constants.constants.FinalFieldDeclarationConstant",
            "io.anzes.constants.constants.FieldDeclarationConstant",
            "io.anzes.constants.constants.AnnOnLocalFieldConstant",
            "io.anzes.constants.constants.AnnConstantOnMethod",
            "io.anzes.constants.constants.AnnConstantOnMethodTypeParam",
            "io.anzes.constants.constants.ForLoopInitConstant",
            "io.anzes.constants.constants.ForLoopConditionConstant",
            "io.anzes.constants.constants.ForLoopAssignOpConstant",
            "io.anzes.constants.constants.LambdaConstant",
        ))
    }

    @Test
    fun shouldFindAllConstantsFromAnnotationClass() {
        // Given
        val clazz =
            File("${File("").absolutePath}/src/test/java/io/anzes/constants/testclass/TestAnnotation.java").readText()

        // When
        compiler.compile("io.anzes.constants.testclass.TestAnnotation", clazz)

        // Then
        println("Found next constants classes:\n\t${CONSTANT_CLASSES.joinToString(separator = "\n\t")}")
        assertThat(CONSTANT_CLASSES).containsExactlyInAnyOrderElementsOf(
            setOf(
                "io.anzes.constants.constants.AnnotationDefaultArrayValueConstant",
                "io.anzes.constants.constants.AnnotationDefaultValueConstant",
                "io.anzes.constants.constants.AnnotationValueAnnotationConstant",
                "io.anzes.constants.constants.AnnOnAnnotationConstans"
            )
        )
    }

    @Test
    fun shouldNotFindNonPrimitiveConstantsFromClass() {
        // Given
        val clazz =
            File("${File("").absolutePath}/src/test/java/io/anzes/constants/testclass/NonPrimitiveTestClass.java").readText()

        // When
        compiler.compile("io.anzes.constants.testclass.NonPrimitiveTestClass", clazz)

        // Then
        println("Found next constants classes:\n\t${CONSTANT_CLASSES.joinToString(separator = "\n\t")}")
        assertThat(CONSTANT_CLASSES).isEmpty()
    }

    @Test
    fun shouldNotReturnReferenceClassForStaticVariableThatIsNotAConstantsFromClass() {
        // Given
        val clazz =
            File("${File("").absolutePath}/src/test/java/io/anzes/constants/testclass/NotAConstantTestClass.java").readText()

        // When
        compiler.compile("io.anzes.constants.testclass.NotAConstantTestClass", clazz)

        // Then
        println("Found next constants classes:\n\t${CONSTANT_CLASSES.joinToString(separator = "\n\t")}")
        assertThat(CONSTANT_CLASSES).isEmpty()
    }

    @Test
    fun shouldFindSelfAsConstantClass() {
        // Given
        val clazz =
            File("${File("").absolutePath}/src/test/java/io/anzes/constants/testclass/ReferenceToSelf.java").readText()

        // When
        compiler.compile("io.anzes.constants.testclass.ReferenceToSelf", clazz)

        // Then
        println("Found next constants classes:\n\t${CONSTANT_CLASSES.joinToString(separator = "\n\t")}")
        assertThat(CONSTANT_CLASSES).containsExactly("io.anzes.constants.testclass.ReferenceToSelf")
    }

}