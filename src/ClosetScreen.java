import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;

public class ClosetScreen {
    private JLabel lb1;
    private JRadioButton rd1;
    private JRadioButton rd3;
    private JRadioButton rd4;
    private JRadioButton rd2;
    private JRadioButton rd5;
    private JRadioButton rd6;
    private JRadioButton rd7;
    private JRadioButton rd8;
    private JRadioButton rd9;
    private JRadioButton rd10;
    private JRadioButton rd11;
    private JRadioButton rd12;
    private JRadioButton rd13;
    private JRadioButton rd14;
    private JRadioButton rd15;
    private JRadioButton rd16;
    private JTextField chName;
    private JButton btName;
    private JButton btOftenUsed;
    private JLabel head;
    private JLabel top;
    private JLabel bottom;
    private JLabel shoes;
    private JPanel panel;

    static String[] item_hat = {"h1.png", "h2.png", "h3.png", "h4.png"};
    static String[] item_top = {"t1.png", "t2.png", "t3.png", "t4.png"};
    static String[] item_bottom = {"b1.png", "b2.png", "b3.png", "b4.png"};
    static String[] item_shoes = {"s1.png", "s2.png", "s3.png", "s4.png"};

    ImageIcon i1 = new ImageIcon(ClosetScreen.class.getResource(item_hat[0]));
    ImageIcon i2 = new ImageIcon(ClosetScreen.class.getResource(item_hat[1]));
    ImageIcon i3 = new ImageIcon(ClosetScreen.class.getResource(item_hat[2]));
    ImageIcon i4 = new ImageIcon(ClosetScreen.class.getResource(item_hat[3]));
    ImageIcon i5 = new ImageIcon(ClosetScreen.class.getResource(item_top[0]));
    ImageIcon i6 = new ImageIcon(ClosetScreen.class.getResource(item_top[1]));
    ImageIcon i7 = new ImageIcon(ClosetScreen.class.getResource(item_top[2]));
    ImageIcon i8 = new ImageIcon(ClosetScreen.class.getResource(item_top[3]));
    ImageIcon i9 = new ImageIcon(ClosetScreen.class.getResource(item_bottom[0]));
    ImageIcon i10 = new ImageIcon(ClosetScreen.class.getResource(item_bottom[1]));
    ImageIcon i11 = new ImageIcon(ClosetScreen.class.getResource(item_bottom[2]));
    ImageIcon i12 = new ImageIcon(ClosetScreen.class.getResource(item_bottom[3]));
    ImageIcon i13 = new ImageIcon(ClosetScreen.class.getResource(item_shoes[0]));
    ImageIcon i14 = new ImageIcon(ClosetScreen.class.getResource(item_shoes[1]));
    ImageIcon i15 = new ImageIcon(ClosetScreen.class.getResource(item_shoes[2]));
    ImageIcon i16 = new ImageIcon(ClosetScreen.class.getResource(item_shoes[3]));

    ArrayList<String> used_hat = new ArrayList<>();
    ArrayList<String> used_top = new ArrayList<>();
    ArrayList<String> used_bottom = new ArrayList<>();
    ArrayList<String> used_shoes = new ArrayList<>();

    String[] get_hat = new String[4];
    String[] get_top = new String[4];
    String[] get_bottom = new String[4];
    String[] get_shoes = new String[4];

    int count = 0;

    public ClosetScreen(int count) {
        this.count = count;

        JFrame frame = new JFrame("옷장");

        frame.add(panel);

        ButtonGroup selected_hat = new ButtonGroup();
        ButtonGroup selected_top = new ButtonGroup();
        ButtonGroup selected_bottom = new ButtonGroup();
        ButtonGroup selected_shoes = new ButtonGroup();
        selected_hat.add(rd1);
        selected_hat.add(rd2);
        selected_hat.add(rd3);
        selected_hat.add(rd4);

        selected_top.add(rd5);
        selected_top.add(rd6);
        selected_top.add(rd7);
        selected_top.add(rd8);

        selected_bottom.add(rd5);
        selected_bottom.add(rd6);
        selected_bottom.add(rd7);
        selected_bottom.add(rd8);

        frame.setPreferredSize(new Dimension(1500, 800));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        rd1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count >= 15) {
                    ImageIcon ii = new ImageIcon(ClosetScreen.class.getResource(item_hat[0]));
                    head.setIcon(ii);
                    used_hat.add(item_hat[0]);
                }
            }
        });
        rd2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count >= 55) {
                    ImageIcon ii = new ImageIcon(ClosetScreen.class.getResource(item_hat[1]));
                    head.setIcon(ii);
                    used_hat.add(item_hat[1]);
                }
            }
        });
        rd3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count >= 95) {
                    ImageIcon ii = new ImageIcon(ClosetScreen.class.getResource(item_hat[2]));
                    head.setIcon(ii);
                    used_hat.add(item_hat[2]);
                }
            }
        });
        rd4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count >= 250) {
                    ImageIcon ii = new ImageIcon(ClosetScreen.class.getResource(item_hat[3]));
                    head.setIcon(ii);
                    used_hat.add(item_hat[3]);
                }
            }
        });
        rd5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count >= 1) {
                    ImageIcon ii = new ImageIcon(ClosetScreen.class.getResource(item_top[0]));
                    top.setIcon(ii);
                    used_top.add(item_top[0]);
                }
            }
        });
        rd6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count >= 35) {
                    ImageIcon ii = new ImageIcon(ClosetScreen.class.getResource(item_top[1]));
                    top.setIcon(ii);
                    used_top.add(item_top[1]);
                }
            }
        });
        rd7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count >= 75) {
                    ImageIcon ii = new ImageIcon(ClosetScreen.class.getResource(item_top[2]));
                    top.setIcon(ii);
                    used_top.add(item_top[2]);
                }
            }
        });
        rd8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count >= 165) {
                    ImageIcon ii = new ImageIcon(ClosetScreen.class.getResource(item_top[3]));
                    top.setIcon(ii);
                    used_top.add(item_top[3]);
                }
            }
        });
        rd9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count >= 5) {
                    ImageIcon ii = new ImageIcon(ClosetScreen.class.getResource(item_bottom[0]));
                    bottom.setIcon(ii);
                    used_bottom.add(item_bottom[0]);
                }
            }
        });
        rd10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count >= 45) {
                    ImageIcon ii = new ImageIcon(ClosetScreen.class.getResource(item_bottom[1]));
                    bottom.setIcon(ii);
                    used_bottom.add(item_bottom[1]);
                }
            }
        });
        rd11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count >= 85) {
                    ImageIcon ii = new ImageIcon(ClosetScreen.class.getResource(item_bottom[2]));
                    bottom.setIcon(ii);
                    used_bottom.add(item_bottom[2]);
                }
            }
        });
        rd12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count >= 205) {
                    ImageIcon ii = new ImageIcon(ClosetScreen.class.getResource(item_bottom[3]));
                    bottom.setIcon(ii);
                    used_bottom.add(item_bottom[3]);
                }
            }
        });
        rd13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count >= 25) {
                    ImageIcon ii = new ImageIcon(ClosetScreen.class.getResource(item_shoes[0]));
                    shoes.setIcon(ii);
                    used_shoes.add(item_shoes[0]);
                }
            }
        });
        rd14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count >= 65) {
                    ImageIcon ii = new ImageIcon(ClosetScreen.class.getResource(item_shoes[1]));
                    shoes.setIcon(ii);
                    used_shoes.add(item_shoes[1]);
                }
            }
        });
        rd15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count >= 135) {
                    ImageIcon ii = new ImageIcon(ClosetScreen.class.getResource(item_shoes[2]));
                    shoes.setIcon(ii);
                    used_shoes.add(item_shoes[2]);
                }
            }
        });
        rd16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count >= 305) {
                    ImageIcon ii = new ImageIcon(ClosetScreen.class.getResource(item_shoes[3]));
                    shoes.setIcon(ii);
                    used_shoes.add(item_shoes[3]);
                }
            }
        });

        btOftenUsed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int h1 = Collections.frequency(used_hat, item_hat[0]);
                int h2 = Collections.frequency(used_hat, item_hat[1]);
                int h3 = Collections.frequency(used_hat, item_hat[2]);
                int h4 = Collections.frequency(used_hat, item_hat[3]);

                int[] array = {h1, h2, h3, h4};
                int max = array[0];
                String result = "없음";
                for(int i=0;i<array.length;i++) {
                    if (max <= array[i]) {
                        //max의 값보다 array[i]이 크면 max = array[i], result = item_hat[i]
                        max = array[i];
                        result = item_hat[i];
                    }
                }
                if (result.equals("없음"))
                    JOptionPane.showMessageDialog(null, "사용하신 아이템이 없어요");
                else
                    JOptionPane.showMessageDialog(null, result + "를 많이 사용하셨네요");

                int t1 = Collections.frequency(used_top, item_top[0]);
                int t2 = Collections.frequency(used_top, item_top[1]);
                int t3 = Collections.frequency(used_top, item_top[2]);
                int t4 = Collections.frequency(used_top, item_top[3]);

                int[] array2 = {t1, t2, t3, t4};
                max = array2[0];
                result = "없음";
                for(int i=0;i<array2.length;i++) {
                    if (max <= array2[i]) {
                        //max의 값보다 array[i]이 크면 max = array[i], result = item_hat[i]
                        max = array2[i];
                        result = item_top[i];
                    }
                }
                if (result.equals("없음"))
                    JOptionPane.showMessageDialog(null, "사용하신 아이템이 없어요");
                else
                    JOptionPane.showMessageDialog(null, result + "를 많이 사용하셨네요");

                int b1 = Collections.frequency(used_bottom, item_bottom[0]);
                int b2 = Collections.frequency(used_bottom, item_bottom[1]);
                int b3 = Collections.frequency(used_bottom, item_bottom[2]);
                int b4 = Collections.frequency(used_bottom, item_bottom[3]);

                int[] array3 = {h1, h2, h3, h4};
                max = array3[0];
                result = "없음";
                for(int i=0;i<array3.length;i++) {
                    if (max <= array3[i]) {
                        //max의 값보다 array[i]이 크면 max = array[i], result = item_hat[i]
                        max = array3[i];
                        result = item_bottom[i];
                    }
                }
                if (result.equals("없음"))
                    JOptionPane.showMessageDialog(null, "사용하신 아이템이 없어요");
                else
                    JOptionPane.showMessageDialog(null, result + "를 많이 사용하셨네요");

                int s1 = Collections.frequency(used_shoes, item_shoes[0]);
                int s2 = Collections.frequency(used_shoes, item_shoes[1]);
                int s3 = Collections.frequency(used_shoes, item_shoes[2]);
                int s4 = Collections.frequency(used_shoes, item_shoes[3]);

                int[] array4 = {h1, h2, h3, h4};
                max = array[0];
                result = "없음";
                for(int i=0;i<array4.length;i++) {
                    if (max <= array4[i]) {
                        //max의 값보다 array[i]이 크면 max = array[i], result = item_hat[i]
                        max = array4[i];
                        result = item_shoes[i];
                    }
                }
                if (result.equals("없음"))
                    JOptionPane.showMessageDialog(null, "사용하신 아이템이 없어요");
                else
                    JOptionPane.showMessageDialog(null, result + "를 많이 사용하셨네요");
            }
        });

        btName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String n = chName.getText();
                Profile p = new Profile();
                p.setName(n);
            }
        });

        if (count >= 1) {
            rd5.setIcon(i5);
            rd5.setEnabled(true);
            get_top[0] = item_top[0];
        }
        if (count >= 5) {
            rd9.setIcon(i9);
            rd9.setEnabled(true);
            get_bottom[0] = item_bottom[0];
        }
        if (count >= 15) {
            rd1.setIcon(i1);
            rd1.setEnabled(true);
            get_hat[0] = item_hat[0];
        }
        if (count >= 25) {
            rd13.setIcon(i13);
            rd13.setEnabled(true);
            get_shoes[0] = item_shoes[0];
        }
        if (count >= 35) {
            rd6.setIcon(i6);
            rd6.setEnabled(true);
            get_top[1] = item_top[1];
        }
        if (count >= 45) {
            rd10.setIcon(i10);
            rd10.setEnabled(true);
            get_bottom[1] = item_bottom[1];
        }
        if (count >= 55) {
            rd2.setIcon(i2);
            rd2.setEnabled(true);
            get_hat[1] = item_hat[1];
        }
        if (count >= 65) {
            rd14.setIcon(i14);
            rd14.setEnabled(true);
            get_shoes[1] = item_shoes[1];
        }
        if (count >= 75) {
            rd7.setIcon(i7);
            rd7.setEnabled(true);
            get_top[2] = item_top[2];
        }
        if (count >= 85) {
            rd11.setIcon(i11);
            rd11.setEnabled(true);
            get_bottom[2] = item_bottom[2];
        }
        if (count >= 95) {
            rd3.setIcon(i3);
            rd3.setEnabled(true);
            get_hat[2] = item_hat[2];
        }
        if (count >= 135) {
            rd15.setIcon(i15);
            rd15.setEnabled(true);
            get_shoes[2] = item_shoes[2];
        }
        if (count >= 165) {
            rd8.setIcon(i8);
            rd8.setEnabled(true);
            get_top[3] = item_top[3];
        }
        if (count >= 205) {
            rd12.setIcon(i12);
            rd12.setEnabled(true);
            get_bottom[3] = item_bottom[3];
        }
        if (count >= 250) {
            rd4.setIcon(i4);
            rd4.setEnabled(true);
            get_hat[3] = item_hat[3];
        }
        if (count >= 305) {
            rd16.setIcon(i16);
            rd16.setEnabled(true);
            get_shoes[3] = item_shoes[3];
        }
    }

    public void nullCheck() {
        String name = chName.getText();
        if (name.equals(""))
            JOptionPane.showMessageDialog(null, "이름을 지정해주세요.");
        if (get_hat.length == 0 || get_top.length == 0 || get_bottom.length == 0 || get_shoes.length == 0)
            JOptionPane.showMessageDialog(null, "아이템이 없습니다.");
    }

    public void getName() {
        String name = chName.getText();

        Profile p = new Profile();
        p.setName(name);
    }

    public static void main(String[] args) {
        ClosetScreen c = new ClosetScreen(20);
    }

}
