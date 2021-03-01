package io.anzes.constants.plugin;

import java.util.HashSet;
import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;

import com.sun.source.tree.IdentifierTree;
import com.sun.source.tree.MemberSelectTree;
import com.sun.source.util.TreePathScanner;
import com.sun.source.util.Trees;

public class ConstantsTreeVisitor extends TreePathScanner<Trees, Trees> {

    // This is here just for POC so it's simple to test...
    public static final Set<String> CONSTANT_CLASSES = new HashSet<>();
    
    @Override
    public Trees visitMemberSelect(MemberSelectTree node, Trees trees) {
        Element element = trees.getElement(getCurrentPath());
        if (element instanceof VariableElement && ((VariableElement) element).getConstantValue() != null) {
            CONSTANT_CLASSES.add(element.getEnclosingElement().toString());
        }
        return super.visitMemberSelect(node, trees);
    }

    @Override
    public Trees visitIdentifier(IdentifierTree node, Trees trees) {
        Element element = trees.getElement(getCurrentPath());
        if (element instanceof VariableElement && ((VariableElement) element).getConstantValue() != null) {
            CONSTANT_CLASSES.add(element.getEnclosingElement().toString());
        }
        return super.visitIdentifier(node, trees);
    }

}