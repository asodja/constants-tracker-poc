package io.anzes.constants.testing.utils;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import io.anzes.constants.plugin.ConstantsDiscoveryPlugin;

public class TestCompiler {

    public byte[] compile(String qualifiedClassName, String testSource) {
        StringWriter output = new StringWriter();
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        SimpleFileManager fileManager = new SimpleFileManager(compiler.getStandardFileManager(null, null, null));
        List<SimpleSourceFile> compilationUnits = singletonList(new SimpleSourceFile(qualifiedClassName, testSource));
        List<String> arguments = new ArrayList<>();
        arguments.addAll(asList("-classpath", System.getProperty("java.class.path"),
                "-Xplugin:" + new ConstantsDiscoveryPlugin().getName()));
        JavaCompiler.CompilationTask task = compiler.getTask(output, fileManager, null, arguments, null,
                compilationUnits);

        if (task.call()) {
            return fileManager.getCompiled().iterator().next().getCompiledBinaries();
        }
        throw new RuntimeException(output.toString());
    }

}