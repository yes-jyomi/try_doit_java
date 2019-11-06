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

    static int count=0;

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
//                목록 지웠다가
                lbBox.removeAll();

//                다시 JList 에 추가해서 보여주기
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

//                입력된 할 일을 가져옴.
                String lb = task.getText();
//                비었으면 할 일이 비었다고 안내
                if (lb.equals(""))
                    JOptionPane.showMessageDialog(null, "할 일이 비었네요 ~");
                else {
//                    할 일 목록에 추가 후 다시 JList 에 보여줌
                    tm.tasks.add(lb);

                    lbBox.removeAll();

                    String[] task = tm.tasks.toArray(new String[0]);
                    DefaultListModel<String> m = new DefaultListModel<>();
                    for (int i = 0; i < task.length; i++) {
                        m.addElement(task[i]);
                    }
                    lbBox.setModel(m);

//                    DB 에 넣기 위해 TaskManager 에 있는 addTask() 호출
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
//                        JList 에서 선택된 값이 있을 때
                        if (!e.getValueIsAdjusting()) {
                            TaskManager tm = new TaskManager();

//                            하나만 선택되어 select 에 선택된 값 저장
                            String select = lbBox.getSelectedValuesList().get(0).toString();
//                            DB와 연동하여 할 일 완료하는 함수 호출
                            int result = tm.completeTask(select);
//                            count 가져옴
                            count++;

                            lbBox.removeAll();

                            TaskManager tm2 = new TaskManager();

                            String[] task = tm2.tasks.toArray(new String[0]);
                            DefaultListModel<String> m = new DefaultListModel<>();
                            for (int i = 0; i < task.length; i++) {
                                m.addElement(task[i]);
                            }
                            lbBox.setModel(m);

//                            할 일이 완료가 되었으면 ClosetScreen 띄우기
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

//                            tasks 에 있는 task 삭제
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

//                            DB 와 연동하여 삭제하는 함수 호출
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
//                            할 일을 수정하지 않았을 때
                            if (lb.equals(upTask.getText())) {
                                content = lb;
//                            할 일을 수정했을 때
                            } else {
//                                기존에 있던 할 일을 지우고
                                tm.tasks.remove(lb);
//                                수정한 할 일을 추가
                                tm.tasks.add(upTask.getText());
                                content = upTask.getText();
                            }

//                            퍼센트도 입력받은 거 정리
                            int per = 0;
                            if (percent.getText().equals("")) {
                                per = 0;
                            } else {
                                per = Integer.parseInt(percent.getText());
                            }
                            int listnum = tm.getListnum(lb);

//                            DB 와 연동하여 업데이트하는 함수 호출
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
//                            선택한 항목을 Doing Tasks 에 있는 수정할 수 있는 공간에 보여줌.
                            upTask.setText("");
                            upTask.setText(task);
                        }
                    }
                });
            }
        });

//        JList 정렬해서 보여주는 함수
        btSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaskManager tm = new TaskManager();
//                tasks 에 있는 값을 정렬함.
                Collections.sort(tm.tasks);

                lbBox.removeAll();

                DefaultListModel<String> m = new DefaultListModel<>();
                for (int i = 0; i < tm.tasks.size(); i++) {
                    m.addElement(tm.tasks.get(i));
                }

                lbBox.setModel(m);
            }
        });

//        오픈 클로젯 버튼 클릭 시
        btOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                ClosetScreen 호출
                new ClosetScreen(count);
            }
        });
    }

    public static void main(String[] args) {
        ListScreen ls = new ListScreen();
    }
}
