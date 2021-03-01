package io.anzes.constants.testing.utils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;

public class SimpleFileManager extends ForwardingJavaFileManager<StandardJavaFileManager> {

    private List<SimpleClassFile> compiled = new ArrayList<>();

    protected SimpleFileManager(StandardJavaFileManager fileManager) {
        super(fileManager);
    }

    // standard constructors/getters

    @Override
    public JavaFileObject getJavaFileForOutput(Location location,
      String className, JavaFileObject.Kind kind, FileObject sibling) {
        SimpleClassFile result = new SimpleClassFile(
          URI.create("string://" + className));
        compiled.add(result);
        return result;
    }

    public List<SimpleClassFile> getCompiled() {
        return compiled;
    }
}