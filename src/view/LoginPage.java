package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import kontroler.ZaposleniKontroler;

/**
 * @author Matija Crnkovic
 * @date November 2019
 * @version 1.0
 */

public final class LoginPage extends JFrame {

    // Deklaracija svih potrebnih parametara za laksi rad kasnije.
    private JPanel glavniPanel;
    private final ZaposleniKontroler zaposleniKontroler;
    
    private AdminPage adminPage;
    private ZaposleniPage zaposleniPage;
    
    Color pozadina = new Color(232, 232, 232);
    Color glavnaBoja = new Color(228, 230, 195);
    Color glavnaTamna = new Color(20, 83, 116);
    Color glavnaTamnija = new Color(155, 155, 155);
    Font font = new Font("TimesRoman", Font.PLAIN, 5);

    public LoginPage(){
        super();
        zaposleniKontroler = new ZaposleniKontroler();
        loginPage();
    }
    
    public void createAndShowGUI() {
        setTitle("Knjizara - Login Meni");
        getContentPane().add(glavniPanel);
        setResizable(false);
        setVisible(true);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    public void loginPage() {
        glavniPanel = new JPanel(new GridBagLayout());
        glavniPanel.setBackground(glavnaBoja);
        GridBagConstraints gbc = new GridBagConstraints();
        Icon icon = new ImageIcon("images/icons8-name-100.png");
        JLabel logo = new JLabel(icon);
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                loginInEnter(e);
            }
        });
        icon = new ImageIcon("images/loginBtn.png");
        JButton login = new JButton(icon);
        login.setBorderPainted(false);
        login.setFocusPainted(false);
        login.setContentAreaFilled(false);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                LogInButtonActionPerformed(evt);
            }
        });
        icon = new ImageIcon("images/123.png");
        JLabel logoIcon = new JLabel(icon, SwingConstants.RIGHT);
        
        
        // Grid
        
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        gbc.gridy = 0;
        gbc.insets = new Insets(50, 50, 0, 50);
        glavniPanel.add(logo, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(40, 50, 5, 50);
        glavniPanel.add(usernameLabel, gbc);
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 50, 20, 50);
        glavniPanel.add(usernameField, gbc);
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 50, 5, 50);
        glavniPanel.add(passwordLabel, gbc);
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        glavniPanel.add(passwordField, gbc);
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridy = 5;
        glavniPanel.add(login, gbc);
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        gbc.gridy = 6;
        gbc.insets = new Insets(0, 0, 20, 0);
        glavniPanel.add(logoIcon, gbc);
    }
    
    private void loginInEnter(KeyEvent evt) {
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            int employeeID;
            try {
                if (usernameField.getText().isEmpty() || String.valueOf(
                        passwordField.getPassword()).equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Morate popuniti sva polja!", 
                        "Greška", JOptionPane.INFORMATION_MESSAGE);
            } else {
                employeeID = zaposleniKontroler.proveriZaposlenog(null, null, 
                        null, null, null, null, null, null, null, null, null, 
                        username, password).get(0).getID();

                    String pozicija = zaposleniKontroler.getByID(employeeID).getPozicija();
                    switch (pozicija) {
                        case "Admin":
                            adminPage = new AdminPage(employeeID);
                            dispose();
                            break;
                        case "Radnik":
                            zaposleniPage = new ZaposleniPage(employeeID);
                            dispose();
                            break;
                        default:
                            break;
                    }

                }
            } catch (IndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(null, "Pogrešan username ili password!", 
                        "Greška", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
    private void LogInButtonActionPerformed(ActionEvent evt) {

        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());
        int employeeID;
        
        try {
            if (usernameField.getText().isEmpty() || String.valueOf(passwordField.getPassword()).equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Morate popuniti sva polja!", "Greška", JOptionPane.INFORMATION_MESSAGE);
        } else {
            employeeID = zaposleniKontroler.proveriZaposlenog(null, null, null, null, null, null, null, null, null, null, null, username, password).get(0).getID();
                
                String pozicija = zaposleniKontroler.getByID(employeeID).getPozicija();
                switch (pozicija) {
                    case "Admin":
                        adminPage = new AdminPage(employeeID);
                        dispose();
                        break;
                    case "Radnik":
                        zaposleniPage = new ZaposleniPage(employeeID);
                        dispose();
                        break;
                    default:
                        break;
                }
                
            }
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Pogrešan username ili password!", "Greška", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    private JTextField usernameField;
    private JPasswordField passwordField;
}
