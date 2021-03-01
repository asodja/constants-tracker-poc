package io.anzes.constants.testclass;

import static io.anzes.constants.constants.AnnOnAnnotationConstans.DEFAULT_ANN_ON_ANNOTATION_VALUE;
import static io.anzes.constants.constants.AnnotationDefaultArrayValueConstant.DEFAULT_ARRAY_VALUE_CONSTANT;
import static io.anzes.constants.constants.AnnotationDefaultValueConstant.DEFAULT_VALUE_CONSTANT;
import static io.anzes.constants.constants.AnnotationValueAnnotationConstant.ANNOTATION_VALUE_ANNOTATION;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import io.anzes.constants.annotations.AnnotationOnAnnotation;

@AnnotationOnAnnotation(value = DEFAULT_ANN_ON_ANNOTATION_VALUE + "asd")
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {

    @AnnotationOnAnnotation(value = ANNOTATION_VALUE_ANNOTATION + "bla")
    String value() default DEFAULT_VALUE_CONSTANT + "hopsasa";

    String[] values() default { DEFAULT_ARRAY_VALUE_CONSTANT + "tralal"};

}
