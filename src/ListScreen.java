import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListScreen {
    private JRadioButton rd1;
    private JRadioButton rd2;
    private JRadioButton rd3;
    private JRadioButton rd4;
    private JTextField tag;
    private JTextField year;
    private JTextField month;
    private JTextField day;
    private JButton btAdd;
    private JTextField upTask;
    private JButton btUpdate;
    private JTextField percent;
    private JButton btOpen;
    private JButton btShowList;
    private JButton btSort;
    private JList lbBox;
    private JButton btDelete;
    private JButton btMove;
    private JButton btComplete;
    private JLabel lb1;
    private JLabel lb2;
    private JLabel lb3;
    private JTextField task;
    private JPanel panel;

    static int count;

    public ListScreen() {
        JFrame frame = new JFrame("리스트 화면");

        frame.add(panel);

        frame.setPreferredSize(new Dimension(800, 500));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//        JList 에 할 일 목록 보여줌.
        btShowList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbBox.removeAll();

                TaskManager tm = new TaskManager();
                DefaultListModel<String> m = new DefaultListModel<>();
                for (int i = 0; i < tm.tasks.size(); i++) {
                    m.addElement(tm.tasks.get(i));
                }

                lbBox.setModel(m);
            }
        });

//        할 일 추가함
        btAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaskManager tm = new TaskManager();

                String lb = task.getText();
                if (lb.equals(""))
                    JOptionPane.showMessageDialog(null, "할 일이 비었네요 ~");
                else {
                    tm.tasks.add(lb);

                    lbBox.removeAll();

                    String[] task = tm.tasks.toArray(new String[0]);
                    DefaultListModel<String> m = new DefaultListModel<>();
                    for (int i = 0; i < task.length; i++) {
                        m.addElement(task[i]);
                    }
                    lbBox.setModel(m);

                    tm.addTask(lb);
                }
            }
        });

//        JList 에서 선택된 항목을 눌렀을 때 -> 완료 버튼 눌렀을 때
        lbBox.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
//                완료 버튼 눌렀을 때
                btComplete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        if (!e.getValueIsAdjusting()) {
                            TaskManager tm = new TaskManager();

                            String select = lbBox.getSelectedValuesList().get(0).toString();
                            int result = tm.completeTask(select);
                            count = tm.getCount();

                            lbBox.removeAll();

                            TaskManager tm2 = new TaskManager();

                            String[] task = tm2.tasks.toArray(new String[0]);
                            DefaultListModel<String> m = new DefaultListModel<>();
                            for (int i = 0; i < task.length; i++) {
                                m.addElement(task[i]);
                            }
                            lbBox.setModel(m);

                            if (result == 1) {
                                new ClosetScreen(count);
                            }
                        }
                    }
                });
            }
        });

//        JList 에서 선택된 항목을 눌렀을 때 -> 삭제 버튼 눌렀을 때
        lbBox.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
//                삭제 버튼 눌렀을 때
                btDelete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        if (!e.getValueIsAdjusting()) {
                            TaskManager tm = new TaskManager();

                            String task = lbBox.getSelectedValuesList().get(0).toString();

                            if (tm.tasks.contains(task)) {
                                int num = tm.tasks.indexOf(task);
                                tm.tasks.remove(num);
                            }

                            lbBox.removeAll();

                            TaskManager tm2 = new TaskManager();

                            String[] ts = tm2.tasks.toArray(new String[0]);
                            DefaultListModel<String> m = new DefaultListModel<>();
                            for (int i = 0; i < ts.length; i++) {
                                m.addElement(ts[i]);
                            }
                            lbBox.setModel(m);

                            int listnum = tm.getListnum(task);
                            tm.deleteTask(listnum);
                        }
                    }
                });
            }
        });

//        JList 에서 선택된 항목을 눌렀을 때 -> 업데이트 버튼 눌렀을 때
        lbBox.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
//                업데이트 버튼 눌렀을 때
                btUpdate.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        if (!e.getValueIsAdjusting()) {
                            TaskManager tm = new TaskManager();
                            String content;

                            String lb = lbBox.getSelectedValuesList().get(0).toString();
                            if (lb.equals(upTask.getText())) {
                                content = lb;
                            } else {
                                tm.tasks.remove(lb);
                                tm.tasks.add(upTask.getText());
                                content = upTask.getText();
                            }

                            int per = 0;
                            if (percent.getText().equals("")) {
                                per = 0;
                            } else {
                                per = Integer.parseInt(percent.getText());
                            }
                            int listnum = tm.getListnum(lb);
                            tm.updateTask(listnum, content, per);

                            lbBox.removeAll();

                            TaskManager tm2 = new TaskManager();

                            String[] task = tm2.tasks.toArray(new String[0]);
                            DefaultListModel<String> m = new DefaultListModel<>();
                            for (int i = 0; i < task.length; i++) {
                                m.addElement(task[i]);
                            }
                            lbBox.setModel(m);
                        }
                    }
                });
            }
        });

//        JList 에서 선택된 항목을 눌렀을 때 -> Doing Task 버튼 눌렀을 때
        lbBox.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
//                Doing Task 버튼 눌렀을 때
                btMove.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        if (!e.getValueIsAdjusting()) {
                            TaskManager tm = new TaskManager();

                            String task = lbBox.getSelectedValuesList().get(0).toString();
                            upTask.setText("");
                            upTask.setText(task);
                        }
                    }
                });
            }
        });

        btSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaskManager tm = new TaskManager();
                Collections.sort(tm.tasks);

                lbBox.removeAll();

                DefaultListModel<String> m = new DefaultListModel<>();
                for (int i = 0; i < tm.tasks.size(); i++) {
                    m.addElement(tm.tasks.get(i));
                }

                lbBox.setModel(m);
            }
        });

        btOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClosetScreen(count);
            }
        });
    }

    public static void main(String[] args) {
        ListScreen ls = new ListScreen();
    }
}
