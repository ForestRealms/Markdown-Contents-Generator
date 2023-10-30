package space.glowberry;

import space.glowberry.GUI.OpenFileSelector;
import space.glowberry.GUI.SaveFileSelector;

import java.io.*;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger("Main");
//    private static final List<String> arguments = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("java.runtime.name") + " " + System.getProperty("java.version"));

//        arguments.addAll(Arrays.asList(args));
//        if(arguments.contains("nogui")){
//
//        }else {
//            System.out.println(
//                    "欢迎使用转换器，您将被要求选择一个Jupyter Notebook文件，准备好了吗？\n" +
//                            "请注意：如果在要求选择或保存文件的过程中，您点击了取消或者关闭，本程序将会自动终止，您可能需要重新启动此程序。"
//            );
//        }


        File inputFile = new OpenFileSelector().getFile();
        if(inputFile == null){
            System.out.println("未选择文件，程序退出");
            return;
        }

        emptyLine(3);
        Logger.getLogger("Main").info("开始转换工作");
        long t1 = System.currentTimeMillis();

        NbMarkdownConverter nbMarkdownConverter = new NbMarkdownConverter(inputFile);
        nbMarkdownConverter.convert();

        logger.info("开始提取标题……");
        TitleExtractor extractor = new TitleExtractor(nbMarkdownConverter.getMarkdownContent());
        extractor.extract();
        logger.info("提取标题结束！");

        StringBuilder result = new StringBuilder();
        logger.info("开始格式化标题……");
        TitleFormatter formatter = new TitleFormatter(extractor.getMetadata());
        formatter.format();
        for (String formattedTitle : formatter.getFormattedTitles()) {
            result.append(formattedTitle).append("\n");
        }

        long t2 = System.currentTimeMillis();
        Logger.getLogger("Main").info("转换工作结束，耗时 "+(t2-t1)+" 毫秒");
        System.out.println("目录生成完毕，您接下来将被要求选择一个保存的位置。");
        File outputFile = new SaveFileSelector().getFile();
        if(outputFile == null){
            System.out.println("未选择文件，程序退出");
            return;
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(outputFile))));
        writer.write(result.toString());
        writer.close();


    }


    public static void emptyLine(int lines){
        for (int i = 0; i < lines; i++) {
            System.out.println();
        }
    }

}
