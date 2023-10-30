package space.glowberry;

import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.Objects;

public class NotebookFileFilter extends FileFilter {

    private static NotebookFileFilter notebookFileFilter;
    @Override
    public boolean accept(File f) {
        return f.getName().toLowerCase().endsWith(".ipynb");
    }

    @Override
    public String getDescription() {
        return "Jupyter Notebook 文件？？？？？？ (*.ipynb)";
    }

    public static NotebookFileFilter getInstance() {
        return Objects.requireNonNullElseGet(notebookFileFilter, NotebookFileFilter::new);
    }
}
