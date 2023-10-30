package space.glowberry.GUI;

import org.jetbrains.annotations.Nullable;
import space.glowberry.NotebookFileFilter;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class OpenFileSelector extends JFileChooser {

    public OpenFileSelector() {
        super(FileSystemView.getFileSystemView().getHomeDirectory());
        this.setDialogTitle("快告诉我你的Jupyter NoteBook文件在哪里！！！");
        this.setDialogType(OPEN_DIALOG);
        this.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.setFileFilter(NotebookFileFilter.getInstance());
    }

    /**
     * 找到用户指定的文件
     * @return 文件实体（如果用户取消，或者遇到其他错误，则返回空）
     */
    @Nullable
    public File getFile(){
        File file = null;
        while (true){
            if(this.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                file = this.getSelectedFile();
                String suffix = ".ipynb";
                if(file.getName().endsWith(suffix)) {
                    break;
                }else {
                    System.out.println("请指定" + suffix + "文件，您当前指定的是" +
                            file.getName().substring(file.getName().lastIndexOf(".")));

                }
            }else {
                break;
            }
        }
        return file;
    }
}
