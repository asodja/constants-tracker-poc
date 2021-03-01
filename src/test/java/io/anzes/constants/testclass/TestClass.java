package io.anzes.constants.testclass;

import io.anzes.constants.annotations.*;
import io.anzes.constants.constants.*;

import java.util.Map;

@Annotation(AnnConstantOnClass.ANN_ON_CLASS)
public class TestClass<@Annotation(AnnConstantOnClassTypeParam.ANN_ON_CLASS_TYPE_PARAM) T> {

    @Annotation(AnnConstantOnField.ANN_ON_FIELD + 1)
    private Map<@Annotation(AnnConstantOnFieldTypeParam.ANN_ON_CONSTANT_FIELD_TYPE) String, String> field;

    public static final int staticFinalField = StaticFinalFieldDeclarationConstant.STATIC_FINAL_FIELD_DECLERATION + 1;

    public final int finalField = FinalFieldDeclarationConstant.FINAL_FIELD_DECLARATION + 1;

    private int fieldField = 1 + FieldDeclarationConstant.FIELD_DECLARATION;

    @Annotation(AnnConstantOnConstructor.ANN_ON_CONSTRUCTOR + 1)
    public TestClass(@Annotation(1 + AnnConstantOnConstructorArgument.ANN_ON_CONSTRUCTOR_ARG) String arg0) {
        int constructorField = ConstructorFieldConstant.CONSTRUCTOR_FIELD_CONSTANT;
    }

    @Annotation(AnnConstantOnMethod.ANN_ON_METHOD + 5)
    <@Annotation(AnnConstantOnMethodTypeParam.ANN_CONSTANT_ON_METHOD_TYPE_PARAM) T> T methodBody(
            @Annotation(AnnConstantOnMethodArgument.ANN_CONSTANT_ON_METHOD_ARGUMENT) String argument) {
        return null;
    }

    @Annotation(AnnConstantOnMethod.ANN_ON_METHOD * 4)
    void methodBody2() {
        for (int i = ForLoopInitConstant.FOR_LOOP_INIT; i < ForLoopConditionConstant.COND; i += ForLoopAssignOpConstant.ASSIGN_OP) {
            @Annotation(AnnOnLocalFieldConstant.LOCAL_FIELD)
            Runnable run = () -> System.out.println(LambdaConstant.LAMBDA);
            int value = NotAConstant.NOT_A_CONSTANT;
            if (value == IfConditionConstant.IF_CONDITION) {
            }
            switch (value) {
            case SwitchCaseConstant.SWITCH_CASE:
            default:
            }
        }
    }

}