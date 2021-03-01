package io.anzes.constants.plugin;

import com.sun.source.util.JavacTask;
import com.sun.source.util.Plugin;
import com.sun.source.util.TaskEvent;
import com.sun.source.util.TaskEvent.Kind;
import com.sun.source.util.TaskListener;
import com.sun.source.util.TreePath;
import com.sun.source.util.Trees;

public class ConstantsDiscoveryPlugin implements Plugin {

    private final ConstantsTreeVisitor visitor;

    public ConstantsDiscoveryPlugin() {
        this.visitor = new ConstantsTreeVisitor();
    }

    @Override
    public String getName() {
        return "ConstantsPlugin";
    }

    @Override
    public void init(JavacTask task, String... args) {
        task.addTaskListener(new TaskListener() {
            public void started(TaskEvent e) {
            }

            public void finished(TaskEvent e) {
                if (e.getKind() == Kind.ANALYZE) {
                    Trees trees = Trees.instance(task);
                    TreePath path = trees.getPath(e.getCompilationUnit(), e.getCompilationUnit());
                    visitor.scan(path, trees);
                }
            }
        });
    }
}