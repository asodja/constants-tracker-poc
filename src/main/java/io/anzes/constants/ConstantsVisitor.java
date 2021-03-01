package io.anzes.constants;

import static com.sun.tools.javac.code.TypeTag.CLASS;

import java.util.function.Consumer;

import com.sun.source.util.Trees;
import com.sun.tools.javac.code.Symbol.ClassSymbol;
import com.sun.tools.javac.code.Symbol.VarSymbol;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCAnnotatedType;
import com.sun.tools.javac.tree.JCTree.JCAnnotation;
import com.sun.tools.javac.tree.JCTree.JCAssign;
import com.sun.tools.javac.tree.JCTree.JCAssignOp;
import com.sun.tools.javac.tree.JCTree.JCBinary;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import com.sun.tools.javac.tree.JCTree.JCConditional;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCFieldAccess;
import com.sun.tools.javac.tree.JCTree.JCIdent;
import com.sun.tools.javac.tree.JCTree.JCMemberReference;
import com.sun.tools.javac.tree.JCTree.JCMethodDecl;
import com.sun.tools.javac.tree.JCTree.JCModifiers;
import com.sun.tools.javac.tree.JCTree.JCParens;
import com.sun.tools.javac.tree.JCTree.JCTypeApply;
import com.sun.tools.javac.tree.JCTree.JCTypeCast;
import com.sun.tools.javac.tree.JCTree.JCTypeParameter;
import com.sun.tools.javac.tree.JCTree.JCUnary;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;

public class ConstantsVisitor extends JCTree.Visitor {

    private final Trees trees;
    private Consumer<String> log;
    private Consumer<String> variable;

    public ConstantsVisitor(Trees trees, Consumer<String> log, Consumer<String> variable) {
        this.log = log;
        this.trees = trees;
        this.variable = variable;
    }

    @Override
    public void visitClassDef(JCClassDecl tree) {
        if (tree.getModifiers() != null) {
            tree.getModifiers().accept(this);
        }
        if (tree.typarams != null) {
            for (JCTypeParameter parameter : tree.typarams) {
                parameter.accept(this);
            }
        }
        for (JCTree subtree : tree.defs) {
            subtree.accept(this);
        }
    }

    @Override
    public void visitReference(JCMemberReference tree) {
        System.err.println("REFERENCE");
    }

    @Override
    public void visitTypeParameter(JCTypeParameter tree) {
        if (tree.bounds.size() > 0) {
//            for(JCExpression bound : tree.bounds) {
//                System.err.println(bound);
//                bound.accept(this);
//            }
        }
        if (tree.annotations != null) {
            for (JCAnnotation annotation : tree.annotations) {
                annotation.accept(this);
            }
        }
    }

    @Override
    public void visitTree(JCTree tree) {}

    @Override
    public void visitBinary(JCBinary tree) {
//        System.err.println("LHS: " + tree.type);
        tree.lhs.accept(this);
        tree.rhs.accept(this);
    }

    @Override
    public void visitSelect(JCFieldAccess tree) {
        if (tree.selected != null && tree.selected.type != null && tree.selected.type.hasTag(CLASS)) {
            log.accept("" + tree.selected.type);
//            makeRef(tree.selected.pos(), tree.selected.type);
        } else  {
            variable.accept("" + tree);
            // variable.accept("" + tree.name);
        }
    }

    @Override
    public void visitIdent(JCIdent tree) {
        if (tree.sym != null && tree.sym.owner instanceof ClassSymbol) {
            log.accept("" + tree.sym.owner);
//            poolWriter.putClass((ClassSymbol)tree.sym.owner);
        } else if (tree.type == null) {
            variable.accept("" + tree);
        } else {
            // This is a type
            // variable.accept("" + tree.type);
        }
    }

    @Override
    public void visitConditional(JCConditional tree) {
        tree.cond.accept(this);
        tree.truepart.accept(this);
        tree.falsepart.accept(this);
    }

    @Override
    public void visitUnary(JCUnary tree) {
        tree.arg.accept(this);
    }

    @Override
    public void visitParens(JCParens tree) {
        tree.expr.accept(this);
    }

    @Override
    public void visitTypeCast(JCTypeCast tree) {
        tree.expr.accept(this);
    }

    @Override
    public void visitMethodDef(JCMethodDecl tree) {
        if (tree.getModifiers() != null) {
            tree.getModifiers().accept(this);
        }
        if (tree.typarams != null) {
            for (JCTypeParameter parameter : tree.typarams) {
                parameter.accept(this);
            }
        }
        if (tree.params != null) {
            for (JCVariableDecl param : tree.params) {
                param.accept(this);
            }
        }
    }

    @Override
    public void visitVarDef(JCVariableDecl tree) {
        if (tree.mods != null) {
            tree.mods.accept(this);
        }
        tree.vartype.accept(this);
        if (tree.init != null) {
            VarSymbol sym = tree.sym;
            tree.init.accept(this);
        }
    }

    public void visitTypeApply(JCTypeApply tree) {
        for (JCExpression arg : tree.arguments) {
            arg.accept(this);
        }
    }

    @Override
    public void visitModifiers(JCModifiers that) {
        if (that.getAnnotations() != null) {
            for (JCAnnotation annotation : that.getAnnotations()) {
                annotation.accept(this);
            }
        }
    }

    @Override
    public void visitAssign(JCAssign tree) {
        tree.rhs.accept(this);
    }

    @Override
    public void visitAssignop(JCAssignOp tree) {
        tree.rhs.accept(this);
    }

    @Override
    public void visitAnnotatedType(JCAnnotatedType tree) {
        tree.underlyingType.accept(this);
        for (JCExpression expr : tree.annotations) {
            expr.accept(this);
        }
    }

    @Override
    public void visitAnnotation(JCAnnotation that) {
        that.annotationType.accept(this);
        for (JCExpression expr : that.args) {
            expr.accept(this);
        }
    }

}
