package io.anzes.constants.plugin;

import java.util.HashSet;
import java.util.Set;

import com.sun.source.tree.IdentifierTree;
import com.sun.source.tree.MemberSelectTree;
import com.sun.source.util.TreeScanner;
import com.sun.tools.javac.code.Symbol.VarSymbol;
import com.sun.tools.javac.tree.JCTree.JCFieldAccess;
import com.sun.tools.javac.tree.JCTree.JCIdent;

public class ConstantsTreeVisitor extends TreeScanner<Void, Void> {

    // This is here just for POC...
    public static final Set<String> CONSTANT_CLASSES = new HashSet<>();

    @Override
    public Void visitMemberSelect(MemberSelectTree node, Void p) {
        JCFieldAccess jcTree = (JCFieldAccess) node;
        if (jcTree.sym instanceof VarSymbol && ((VarSymbol) jcTree.sym).getConstValue() != null) {
            CONSTANT_CLASSES.add(jcTree.sym.owner.toString());
        }
        return super.visitMemberSelect(node, p);
    }

    @Override
    public Void visitIdentifier(IdentifierTree node, Void p) {
        JCIdent jcTree = (JCIdent) node;
        if (jcTree.type != null && jcTree.type.constValue() != null) {
            CONSTANT_CLASSES.add(jcTree.sym.owner.toString());
        }
        return super.visitIdentifier(node, null);
    }

}