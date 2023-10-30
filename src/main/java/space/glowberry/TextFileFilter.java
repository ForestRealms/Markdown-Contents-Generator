package space.glowberry;

import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.Objects;

public class TextFileFilter extends FileFilter {
    private static TextFileFilter textFileFilter;
    @Override
    public boolean accept(File f) {
        return f.getName().endsWith(".txt");
    }

    @Override
    public String getDescription() {
        return "文本文件(*.txt)";
    }

    public static TextFileFilter getInstance() {
        return Objects.requireNonNullElseGet(textFileFilter, TextFileFilter::new);
    }
}
