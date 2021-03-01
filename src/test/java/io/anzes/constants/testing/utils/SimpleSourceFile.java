package io.anzes.constants.testing.utils;

import java.net.URI;

import javax.tools.SimpleJavaFileObject;

public class SimpleSourceFile extends SimpleJavaFileObject {

    private final String content;

    public SimpleSourceFile(String qualifiedClassName, String testSource) {
        super(URI.create(String.format(
                "file://%s%s", qualifiedClassName.replaceAll("\\.", "/"),
                Kind.SOURCE.extension)), Kind.SOURCE);
        content = testSource;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return content;
    }

}

