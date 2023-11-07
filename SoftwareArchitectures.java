
import javafx.scene.control.Labeled;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class SoftwareArchitectures extends JFrame {

    private JLabel methodLabel;
    private JRadioButton method1Button;
    private JRadioButton method2Button;
    private JRadioButton method3Button;
    private JRadioButton method4Button;
    private JButton processButton;
    private JTextArea resultArea;
    private  JLabel imageLabel=new JLabel();

    public SoftwareArchitectures() {
        setTitle("经典软件体系结构教学软件");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new FlowLayout());


        // 处理方式选择部分
        methodLabel = new JLabel("请选择处理方式：");
        add(methodLabel);

        ButtonGroup methodGroup = new ButtonGroup();

        method1Button = new JRadioButton("主程序-子程序");
        methodGroup.add(method1Button);
        add(method1Button);

        method2Button = new JRadioButton("面向对象");
        methodGroup.add(method2Button);
        add(method2Button);

        method3Button = new JRadioButton("事件系统");
        methodGroup.add(method3Button);
        add(method3Button);

        method4Button = new JRadioButton("管道-过滤器");
        methodGroup.add(method4Button);
        add(method4Button);

        // 处理按钮
        processButton = new JButton("开始处理");
        add(processButton);

        // 处理结果显示区域
        resultArea = new JTextArea(30, 40);
        add(new JScrollPane(resultArea));

        // 处理按钮事件监听
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedMethod = getSelectedMethod();

                // 根据选择的处理方式和文件路径进行处理
                    processFile(selectedMethod);

            }
        });

        pack();
        setVisible(true);
    }

    private String getSelectedMethod() {
        if (method1Button.isSelected()) {
            ImageIcon imageIcon = new ImageIcon("D:\\1.png");
            imageLabel.setIcon(imageIcon);
            add(imageLabel);
            return "主程序-子程序";
        } else if (method2Button.isSelected()) {
            ImageIcon imageIcon = new ImageIcon("D:\\2.png");
            imageLabel.setIcon(imageIcon);
            add(imageLabel);
            return "面向对象";
        } else if (method3Button.isSelected()) {
            ImageIcon imageIcon = new ImageIcon("D:\\3.png");
            imageLabel.setIcon(imageIcon);
            add(imageLabel);
            return "事件系统";
        } else if (method4Button.isSelected()) {
            ImageIcon imageIcon = new ImageIcon("D:\\4.png");
            imageLabel.setIcon(imageIcon);
            add(imageLabel);
            return "管道-过滤器";
        }

        return "";
    }

    private void processFile(String method)  {
        // 循环移位处理
        String result = "";
        String filePath = "D:\\input.txt"; // 用户指定的文件路径
        File file = new File(filePath);
        Labeled resultTextArea;
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                    content.append("\n");
                }
                reader.close();

                if (method.equals("主程序-子程序")) {
                    // 主程序-子程序处理逻辑
                    result = "使用主程序-子程序处理文件：" + content.toString();

                } else if (method.equals("面向对象")) {
                    // 面向对象处理逻辑
                    result = "使用面向对象处理文件：" + content.toString();
                } else if (method.equals("事件系统")) {
                    // 事件系统处理逻辑
                    result = "使用事件系统处理文件：" + content.toString();
                } else if (method.equals("管道-过滤器")) {
                    // 管道-过滤器处理逻辑
                    result = "使用管道-过滤器处理文件：" + content.toString();
                }

                // 循环移位处理
                String[] lines = content.toString().split("\n");
                StringBuilder shiftedContent = new StringBuilder();
                for (String l : lines) {
                    String[] words = l.split(" ");
                    for (int i = 0; i < words.length; i++) {
                        StringBuilder shiftedLine = new StringBuilder();
                        for (int j = i; j < words.length; j++) {
                            shiftedLine.append(words[j]);
                            shiftedLine.append(" ");
                        }
                        for (int j = 0; j < i; j++) {
                            shiftedLine.append(words[j]);
                            shiftedLine.append(" ");
                        }
                        shiftedContent.append(shiftedLine.toString().trim());
                        shiftedContent.append("\n");
                    }
                }

                // 更新结果显示文本区域
                resultArea.setText(result + "\n\n循环移位后的结果：\n" + shiftedContent.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            result = "文件不存在";
            resultArea.setText(result);
        }


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SoftwareArchitectures tutorial = new SoftwareArchitectures();
                tutorial.setVisible(true);
            }
        });
    }
}