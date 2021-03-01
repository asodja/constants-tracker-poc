package io.anzes.constants.plugin;

import com.sun.source.util.JavacTask;
import com.sun.source.util.Plugin;
import com.sun.source.util.TaskEvent;
import com.sun.source.util.TaskEvent.Kind;
import com.sun.source.util.TaskListener;

public class ConstantsDiscoveryPlugin implements Plugin {

    @Override
    public String getName() {
        return "ConstantsPlugin";
    }

    @Override
    public void init(JavacTask task, String... args) {
        //  Context context = ((BasicJavacTask) task).getContext();
        //  Trees trees = Trees.instance(task);
        task.addTaskListener(new TaskListener() {
            public void started(TaskEvent e) {
            }

            public void finished(TaskEvent e) {
                if (e.getKind() == Kind.ANALYZE) {
                    e.getCompilationUnit().accept(new ConstantsTreeVisitor(), null);
                }
            }
        });
    }
}