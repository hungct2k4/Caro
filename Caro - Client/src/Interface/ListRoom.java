package Interface;

import Core.*;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Observable;
import java.util.Observer;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

public class ListRoom extends javax.swing.JFrame implements Observer {

    ClientManager mClientManager;
    String mNickName;
    Login mLogin;

    public ListRoom(Login login, ClientManager clientManager) {
        initComponents();

        btnRefresh.setVisible(false);
        getContentPane().setBackground(Color.WHITE);
        mLogin = login;
        mClientManager = clientManager;
        mClientManager.addObserver(this);
        
        findPanel.getBtnFind().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String s = findPanel.getTxtSearch().getText().trim();
                System.out.println("Search: " + s);
                clientManager.Find(s);
            }
        });
        //        mClientManager.getUserListRoom();
    }

    void playSound(String soundFile) throws MalformedURLException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(getClass().getResource(soundFile)));
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void FillListRoom(Result result) {
        DefaultTableModel dtm = (DefaultTableModel) tblListPhong.getModel();
        dtm.setRowCount(0);
        if (result.mContent.length() > 0) {
            //ds phòng có dạng maphong<col>tenphong<col>songuoi<col><row>
            //                 maphong<col>tenphong<col>songuoi<col><row>
            String[] rows = result.mContent.split("<row>");
            for (int i = 0; i < rows.length; i++) //hàng đầu là trống
            {
                String[] cols = rows[i].split("<col>");
                for (int j = 0; j < 6; j++) {
                    System.out.println(cols[j]);
                }
                dtm.addRow(cols);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListPhong = new javax.swing.JTable();
        txtTenPhong = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnRefresh = new Swing.ButtonBorder();
        buttonBorder2 = new Swing.ButtonBorder();
        buttonBorder3 = new Swing.ButtonBorder();
        buttonBorder4 = new Swing.ButtonBorder();
        jLabel2 = new javax.swing.JLabel();
        txtRow = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtColumn = new javax.swing.JTextField();
        listFriends = new javax.swing.JPanel();
        findPanel = new Swing.FindFriends();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("List Room");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tblListPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phòng", "Tên phòng","Chủ phòng" ,"Số người trong phòng", "số hàng", "số cột"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblListPhong);

        txtTenPhong.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTenPhong.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTenPhong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTenPhongKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setText("GAME CARO");

        btnRefresh.setBackground(new java.awt.Color(51, 153, 255));
        btnRefresh.setText("Tải danh sách phòng");
        btnRefresh.setEffectColor(new java.awt.Color(153, 255, 153));
        btnRefresh.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        buttonBorder2.setBackground(new java.awt.Color(51, 153, 255));
        buttonBorder2.setText("Vào Phòng");
        buttonBorder2.setEffectColor(new java.awt.Color(153, 255, 153));
        buttonBorder2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        buttonBorder2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBorder2ActionPerformed(evt);
            }
        });

        buttonBorder3.setBackground(new java.awt.Color(51, 153, 255));
        buttonBorder3.setText("Tạo Phòng");
        buttonBorder3.setEffectColor(new java.awt.Color(204, 204, 204));
        buttonBorder3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        buttonBorder3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBorder3ActionPerformed(evt);
            }
        });

        buttonBorder4.setBackground(new java.awt.Color(51, 153, 255));
        buttonBorder4.setText("Đăng Xuất");
        buttonBorder4.setEffectColor(new java.awt.Color(153, 255, 153));
        buttonBorder4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        buttonBorder4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBorder4ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Name");

        txtRow.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtRow.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRowActionPerformed(evt);
            }
        });
        txtRow.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRowKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Row");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Column");

        txtColumn.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtColumn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtColumn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtColumnKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonBorder3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(buttonBorder4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(91, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(112, 112, 112)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(219, 219, 219)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(txtTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(25, 25, 25)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(txtRow, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(txtColumn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(286, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(31, 31, 31)
                .addComponent(buttonBorder3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonBorder4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(135, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(79, 79, 79)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtRow, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtColumn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(574, Short.MAX_VALUE)))
        );

        listFriends.setBackground(new java.awt.Color(255, 255, 255));
        listFriends.setLayout(new java.awt.GridLayout(10, 1));
        listFriends.add(findPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(listFriends, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(listFriends, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        mClientManager.GetListRoom();
        this.setTitle("Xin chào " + mClientManager.mNickname);
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        mClientManager.Logout();
        mClientManager.Dispose();
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void txtTenPhongKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenPhongKeyTyped
        // TODO add your handling code here:
        String after = txtTenPhong.getText() + evt.getKeyChar();
        after = after.toLowerCase();
        if (after.contains("<row>") || after.contains("<col>"))  //vì danh sách phòng gửi về dùng <row> và <col> để tách dữ liệu nên không để người dùng đặt <row> <col> làm tên phòng
            evt.consume();
    }//GEN-LAST:event_txtTenPhongKeyTyped

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        btnRefresh.setEnabled(false);
        DefaultTableModel dtm = (DefaultTableModel) tblListPhong.getModel();
        dtm.setRowCount(0);
        mClientManager.GetListRoom();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void buttonBorder2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBorder2ActionPerformed
        int indexRow = -1;
        indexRow = tblListPhong.getSelectedRow();
        if (indexRow != -1) {
            System.out.println("Person: " + tblListPhong.getValueAt(tblListPhong.getSelectedRow(), 2));

            if (tblListPhong.getValueAt(tblListPhong.getSelectedRow(), 3).equals("2")) {
                JOptionPane.showMessageDialog(null, "Phòng đã đủ người, chọn phòng khác", "Phòng đã đủ người", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (indexRow < 0) {
                JOptionPane.showMessageDialog(null, "Bạn chưa chọn phòng nào", "Chưa chọn phòng", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String maPhong = tblListPhong.getValueAt(indexRow, 0).toString();
            mClientManager.JoinRoom(maPhong);
        } else {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn phòng !!", "Lỗi chọn phòng", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            playSound("/Assets/sounds/click.wav");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_buttonBorder2ActionPerformed

    private void buttonBorder3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBorder3ActionPerformed
        int nRoom = tblListPhong.getRowCount();
        try {
            playSound("/Assets/sounds/click.wav");
        } catch (Exception e) {
        }

        String tenPhong = txtTenPhong.getText().trim();
        if (tenPhong.length() == 0) {
            try {
                playSound("/Assets/sounds/wrong.wav");
            } catch (Exception e) {
            }
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên phòng", "Chưa nhập tên phòng", JOptionPane.WARNING_MESSAGE);
            txtTenPhong.requestFocus();

            return;
        }
        if (txtRow.getText().trim().equals("") || txtColumn.getText().trim().equals("")) {
            try {
                playSound("/Assets/sounds/wrong.wav");
            } catch (Exception e) {
            }
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập hàng hoặc cột!", "Lỗi tạo Phòng", JOptionPane.WARNING_MESSAGE);

            return;
        }

        if (Integer.valueOf(txtRow.getText().trim()) > 20 || Integer.valueOf(txtColumn.getText().trim()) > 13) {
            try {
                playSound("/Assets/sounds/wrong.wav");
            } catch (Exception e) {
            }
            JOptionPane.showMessageDialog(null, "Hàng phải nhỏ hơn 20 và cột nhỏ hơn 13", "Lỗi tạo Phòng", JOptionPane.WARNING_MESSAGE);

            return;
        }

        for (int i = 0; i < nRoom; i++) {
            if (tblListPhong.getValueAt(i, 1).equals(txtTenPhong.getText())) {
                JOptionPane.showMessageDialog(null, "Tên phòng không được trùng !!!", "Lỗi tên Phòng", JOptionPane.WARNING_MESSAGE);
                return;
            }

        }

        mClientManager.CreateRoom(tenPhong + ";" + mClientManager.mNickname + ";" + txtRow.getText().trim() + "x" + txtColumn.getText().trim());
//        txtColumn.setText("");
//        txtTenPhong.setText("");
//        txtRow.setText("");
    }//GEN-LAST:event_buttonBorder3ActionPerformed

    private void buttonBorder4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBorder4ActionPerformed
        mClientManager.Logout();
        mClientManager.Dispose();
        mLogin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_buttonBorder4ActionPerformed

    private void txtRowKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRowKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRowKeyTyped

    private void txtColumnKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtColumnKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtColumnKeyTyped

    private void txtRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRowActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRowActionPerformed

    @Override
    public void update(Observable o, Object arg) {
        btnRefresh.setEnabled(true);
        Result result = (Result) arg;

        if (result.mResultCode.equals(ResultCode.ERROR)) {
            JOptionPane.showMessageDialog(null, result.mContent, "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        switch (result.mActionType) {
            case ActionType.GET_LIST_ROOM: {
                FillListRoom(result);
                break;
            }
            case ActionType.CREATE_ROOM: {
                mClientManager.deleteObservers();
                GameMatch roomChat = new GameMatch(this, mClientManager, result.mContent, txtTenPhong.getText().trim(), 1,
                        Integer.parseInt(txtRow.getText()), Integer.parseInt(txtColumn.getText()));
                roomChat.setVisible(true);
                this.setVisible(false);
                break;
            }
            
            case ActionType.GET_USER_LIST_ROOM: {
                String s = (String) arg;
                
                System.out.println("List User: \n" + s);
                break;
            }
            case ActionType.JOIN_ROOM: {

                int indexRow = tblListPhong.getSelectedRow();
                if (indexRow < 0) {
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn phòng nào", "Chưa chọn phòng", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String maPhong = tblListPhong.getValueAt(indexRow, 0).toString();
                String tenPhong = tblListPhong.getValueAt(indexRow, 1).toString();
                String chuPhong = tblListPhong.getValueAt(indexRow, 2).toString();
                int soNguoi = Integer.parseInt(tblListPhong.getValueAt(indexRow, 3).toString());
                String row = tblListPhong.getValueAt(indexRow, 4).toString();
                String col = tblListPhong.getValueAt(indexRow, 5).toString();

                System.out.println("Row: " + row);
                System.out.println("Col: " + col);
                mClientManager.deleteObserver(this);

                GameMatch roomChat = new GameMatch(this, mClientManager, maPhong, tenPhong, soNguoi + 1,
                        Integer.parseInt(row), Integer.parseInt(col));
                roomChat.setVisible(true);
                this.setVisible(false);
                break;
            }

            case ActionType.LEAVE_ROOM: {
                FillListRoom(result);
                break;
            }
            
            case ActionType.FIND_FRIEND: {
                System.out.println(result.mContent);
                break;
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Swing.ButtonBorder btnRefresh;
    private Swing.ButtonBorder buttonBorder2;
    private Swing.ButtonBorder buttonBorder3;
    private Swing.ButtonBorder buttonBorder4;
    private Swing.FindFriends findPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel listFriends;
    private javax.swing.JTable tblListPhong;
    private javax.swing.JTextField txtColumn;
    private javax.swing.JTextField txtRow;
    private javax.swing.JTextField txtTenPhong;
    // End of variables declaration//GEN-END:variables
}
