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
    fun `should find all constants from class`() {
        // Given
        val clazz = File("${File("").absolutePath}/src/test/java/io/anzes/constants/testclass/TestClass.java").readText()

        // When
        compiler.compile("io.anzes.constants.testclass.TestClass", clazz)

        // Then
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
    fun `should find all static constants from class`() {
        // Given
        val clazz = File("${File("").absolutePath}/src/test/java/io/anzes/constants/testclass/StaticImportTestClass.java").readText()

        // When
        compiler.compile("io.anzes.constants.testclass.StaticImportTestClass", clazz)

        // Then
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
    fun `should find all constants from annotation class`() {
        // Given
        val clazz =
            File("${File("").absolutePath}/src/test/java/io/anzes/constants/testclass/TestAnnotation.java").readText()

        // When
        compiler.compile("io.anzes.constants.testclass.TestAnnotation", clazz)

        // Then
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
    fun `should not find non primitive constants from class`() {
        // Given
        val clazz =
            File("${File("").absolutePath}/src/test/java/io/anzes/constants/testclass/NonPrimitiveTestClass.java").readText()

        // When
        compiler.compile("io.anzes.constants.testclass.NonPrimitiveTestClass", clazz)

        // Then
        assertThat(CONSTANT_CLASSES).isEmpty()
    }

    @Test
    fun `should not return reference class for static variable that is not aConstants from class`() {
        // Given
        val clazz =
            File("${File("").absolutePath}/src/test/java/io/anzes/constants/testclass/NotAConstantTestClass.java").readText()

        // When
        compiler.compile("io.anzes.constants.testclass.NotAConstantTestClass", clazz)

        // Then
        assertThat(CONSTANT_CLASSES).isEmpty()
    }

    @Test
    fun `should find self as constant class`() {
        // Given
        val clazz =
            File("${File("").absolutePath}/src/test/java/io/anzes/constants/testclass/ReferenceToSelf.java").readText()

        // When
        compiler.compile("io.anzes.constants.testclass.ReferenceToSelf", clazz)

        // Then
        assertThat(CONSTANT_CLASSES).containsExactly("io.anzes.constants.testclass.ReferenceToSelf")
    }

}