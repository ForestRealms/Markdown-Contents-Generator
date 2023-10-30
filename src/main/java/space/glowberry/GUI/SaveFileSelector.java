package space.glowberry.GUI;

import org.jetbrains.annotations.Nullable;
import space.glowberry.TextFileFilter;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class SaveFileSelector extends JFileChooser {
    public SaveFileSelector() {
        super(FileSystemView.getFileSystemView().getHomeDirectory());
        this.setDialogTitle("你想把它保存在哪里？");
        this.setDialogType(SAVE_DIALOG);
        this.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.setFileFilter(TextFileFilter.getInstance());

    }


    @Nullable
    public File getFile(){
        File file = null;
        while (true){
            if(this.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
                file = this.getSelectedFile();
                String suffix = ".txt";
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
