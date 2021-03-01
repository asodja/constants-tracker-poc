package io.anzes.constants.testclass;

import static io.anzes.constants.constants.AnnConstantOnClass.ANN_ON_CLASS;
import static io.anzes.constants.constants.AnnConstantOnClassTypeParam.ANN_ON_CLASS_TYPE_PARAM;
import static io.anzes.constants.constants.AnnConstantOnConstructor.ANN_ON_CONSTRUCTOR;
import static io.anzes.constants.constants.AnnConstantOnConstructorArgument.ANN_ON_CONSTRUCTOR_ARG;
import static io.anzes.constants.constants.AnnConstantOnField.ANN_ON_FIELD;
import static io.anzes.constants.constants.AnnConstantOnFieldTypeParam.ANN_ON_CONSTANT_FIELD_TYPE;
import static io.anzes.constants.constants.AnnConstantOnMethod.ANN_ON_METHOD;
import static io.anzes.constants.constants.AnnConstantOnMethodArgument.ANN_CONSTANT_ON_METHOD_ARGUMENT;
import static io.anzes.constants.constants.AnnConstantOnMethodTypeParam.ANN_CONSTANT_ON_METHOD_TYPE_PARAM;
import static io.anzes.constants.constants.AnnOnLocalFieldConstant.LOCAL_FIELD;
import static io.anzes.constants.constants.ConstructorFieldConstant.CONSTRUCTOR_FIELD_CONSTANT;
import static io.anzes.constants.constants.FieldDeclarationConstant.FIELD_DECLARATION;
import static io.anzes.constants.constants.FinalFieldDeclarationConstant.FINAL_FIELD_DECLARATION;
import static io.anzes.constants.constants.ForLoopAssignOpConstant.ASSIGN_OP;
import static io.anzes.constants.constants.ForLoopConditionConstant.COND;
import static io.anzes.constants.constants.ForLoopInitConstant.FOR_LOOP_INIT;
import static io.anzes.constants.constants.IfConditionConstant.IF_CONDITION;
import static io.anzes.constants.constants.LambdaConstant.LAMBDA;
import static io.anzes.constants.constants.StaticFinalFieldDeclarationConstant.STATIC_FINAL_FIELD_DECLERATION;
import static io.anzes.constants.constants.SwitchCaseConstant.SWITCH_CASE;

import java.util.Map;

import io.anzes.constants.annotations.Annotation;

@Annotation(ANN_ON_CLASS)
public class StaticImportTestClass<@Annotation(ANN_ON_CLASS_TYPE_PARAM) T> {

    @Annotation(ANN_ON_FIELD + 1)
    private Map<@Annotation(ANN_ON_CONSTANT_FIELD_TYPE) String, String> field;

    public static final int STATIC_FINAL_FIELD = STATIC_FINAL_FIELD_DECLERATION + 1;

    public final int finalField = FINAL_FIELD_DECLARATION + 2;

    private int fieldDecleration = 1 + FIELD_DECLARATION;

    @Annotation(ANN_ON_CONSTRUCTOR + 1)
    public StaticImportTestClass(@Annotation(1 + ANN_ON_CONSTRUCTOR_ARG) String arg0) {
        @Annotation(LOCAL_FIELD)
        int constructorField = CONSTRUCTOR_FIELD_CONSTANT;
    }

    @Annotation(ANN_ON_METHOD + 5)
    <@Annotation(ANN_CONSTANT_ON_METHOD_TYPE_PARAM) T> T methodBody(
            @Annotation(ANN_CONSTANT_ON_METHOD_ARGUMENT) String argument) {
        return null;
    }

    @Annotation(ANN_ON_METHOD)
    void methodBody2() {
        for (int i = FOR_LOOP_INIT; i < COND; i += ASSIGN_OP) {
            @Annotation(LOCAL_FIELD)
            Runnable run = () -> System.out.println(LAMBDA);
            int value = 1;
            if (value == IF_CONDITION) {
            }
            switch (value) {
            case SWITCH_CASE:
            default:
            }
        }
    }

}