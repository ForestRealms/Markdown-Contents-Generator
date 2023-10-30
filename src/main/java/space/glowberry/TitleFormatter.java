package space.glowberry;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TitleFormatter {
    private final List<Object[]> metadata;

    private final List<String> formattedTitles = new ArrayList<>();
    private final Logger logger = Logger.getLogger("TitleFormatter");

    public TitleFormatter(List<Object[]> metadata) {
        this.metadata = metadata;
    }

    public void format(){
        int maxLevel = 6;
        for (Object[] data : this.metadata) {
            if ((int) data[0] < maxLevel) {
                maxLevel = (int) data[0];
            }
        }
        for (Object[] data : this.metadata) {
            // 一顿操作猛如虎
            logger.info("开始格式化第 " + this.metadata.indexOf(data) + "/" + this.metadata.size() + " 个标题");
            int level = (int) data[0];
            String text = ((String) data[1]);
            text = text.substring(text.indexOf(" ") + 1);
            String original = text;
            text = "- [" + text + "]";
            text = "  ".repeat(Math.max(0, level - maxLevel)) + text;
            text = text + "(#" +
                    original.replaceAll("\\s", "-")
                    + ")";

            formattedTitles.add(text);
            logger.info("第 " + this.metadata.indexOf(data) + " 个标题格式化完毕！");
        }
    }

    public List<String> getFormattedTitles() {
        return formattedTitles;
    }
}
