package io.anzes.constants;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

import com.sun.source.util.TreePath;
import com.sun.source.util.TreePathScanner;
import com.sun.source.util.Trees;
import com.sun.tools.javac.model.JavacTypes;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import com.sun.tools.javac.util.Context;

@SupportedAnnotationTypes("*")
public class ConstantsProcessor extends AbstractProcessor {

    private Trees trees;
    private Context context;
    private JavacTypes types;
    private TreePath treePath;
    //    private Name.Table names;

    @Override
    public synchronized void init(ProcessingEnvironment environment) {
        super.init(environment);
        trees = Trees.instance(environment);
        context = ((JavacProcessingEnvironment) environment).getContext();
        types = JavacTypes.instance(((JavacProcessingEnvironment) environment).getContext());
//        names = Name.Table.instance(context);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            Set<? extends Element> elements = roundEnv.getRootElements();
            for (Element element : elements) {
                if (element.getKind() == ElementKind.CLASS) {
                    JCClassDecl jcTree = (JCClassDecl) trees.getTree(element);
                    // Maybe is better idea to use TreePathScanner?
                    jcTree.accept(new ConstantsVisitor(trees,
                            (log) -> System.err.println("Found actual class for constant: " + log),
                            (log) -> System.err.println("Found just name that might be a constant: " + log)));
                }
            }
        }
        return false;
    }

}
