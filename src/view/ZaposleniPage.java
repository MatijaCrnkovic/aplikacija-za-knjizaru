package view;

import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.TableModel;
import kontroler.KnjigaKontroler;
import kontroler.KorisniciKontroler;
import kontroler.ZaposleniKontroler;
import table.model.KnjigeTableModel;
import table.model.KorisniciTableModel;
/**
 * @author Matija Crnkovic
 * @date November 2019
 * @version 1.0
 */

public class ZaposleniPage extends JFrame {
    
    private static ZaposleniPage zaposleniPage;
    
    Color pozadina = new Color(232, 232, 232);
    Color glavnaBoja = new Color(228, 230, 195);
    Color glavnaBojaTamnije = new Color(208, 210, 178);
    Color tamnoSivaBoja = new Color(64, 63, 62);
    Color zutaBoja = new Color(254, 189, 1);
    
    private final KnjigaKontroler knjigaKontroler;
    private final ZaposleniKontroler zaposleniKontroler;
    private final KorisniciKontroler korisniciKontroler;
    
    public Integer ID;
    
    public static ZaposleniPage zaposleniPage() {
        if (zaposleniPage != null) {
            zaposleniPage.dispose();
        } else {
        }
        return zaposleniPage;
    }
    
    public ZaposleniPage(Integer ID) {
        knjigaKontroler = new KnjigaKontroler();
        zaposleniKontroler = new ZaposleniKontroler();
        korisniciKontroler = new KorisniciKontroler();
        this.ID = ID;
        
        // Gornji deo
        Icon icon = new ImageIcon("images/logo.png");
        JLabel logo = new JLabel(icon, SwingConstants.LEFT);
        meniKategorijePanel = new JPanel(new GridBagLayout());
        meniKategorijePanel.setBackground(glavnaBoja);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        JLabel danasnjiDatum = new JLabel("<html>Današnji datum: " + formatter.format(date) + "<br><br> Trenutni korisnik: " + zaposleniKontroler.ispisZaposlenog(ID) + "\t</html> ", SwingConstants.RIGHT);
        danasnjiDatum.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 13));
        meniKategorijePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, tamnoSivaBoja));
        meniKategorijePanel.add(logo);
        meniKategorijePanel.add(danasnjiDatum);

        // Levi meni
        JPanel leviMeni = new JPanel(new GridLayout(4, 0));
        jLabelArr = new JLabel[3];
            icon = new ImageIcon("images/knjige.png");
            jLabelArr[0] = new JLabel("Knjige", icon, JLabel.CENTER);
            icon = new ImageIcon("images/korisnici.png");
            jLabelArr[1] = new JLabel("Korisnici", icon, JLabel.CENTER);
            icon = new ImageIcon("images/izvestaj.png");
            jLabelArr[2] = new JLabel("Izveštaj", icon, JLabel.CENTER);
            
        for (int i = 0; i < 3; i++) {
            jLabelArr[i].setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, tamnoSivaBoja));
            jLabelArr[i].setVerticalTextPosition(JButton.BOTTOM);
            jLabelArr[i].setHorizontalTextPosition(JButton.CENTER);
            jLabelArr[i].setOpaque(true);
            jLabelArr[i].setBackground(glavnaBoja);
            leviMeni.setBackground(glavnaBoja);
            jLabelArr[0].setBackground(glavnaBojaTamnije);

            leviMeni.add(jLabelArr[i]);
            jLabelArr[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getSource() == jLabelArr[0]) {
                        tabela.setModel(new KnjigeTableModel(knjigaKontroler));
                        tabela.updateUI();
                        tabelaKnjige(e);
                        jLabelArr[0].setBackground(glavnaBojaTamnije);
                        jLabelArr[1].setBackground(glavnaBoja);
                        jLabelArr[2].setBackground(glavnaBoja);
                        } else if (e.getSource() == jLabelArr[1]) {
                        tabela.setModel(new KorisniciTableModel(korisniciKontroler));
                        tabela.updateUI();
                        tabelaKorisnici(e);
                        jLabelArr[0].setBackground(glavnaBoja);
                        jLabelArr[1].setBackground(glavnaBojaTamnije);
                        jLabelArr[2].setBackground(glavnaBoja);
                    } else if (e.getSource() == jLabelArr[2]) {
                        jLabelArr[0].setBackground(glavnaBoja);
                        jLabelArr[1].setBackground(glavnaBoja);
                        jLabelArr[2].setBackground(glavnaBojaTamnije);
                    }
                }
                @Override
                public void mouseEntered(MouseEvent evt) {
                    leviMeni.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            });
        }

        // Tabela
        tabelaPanel = new JPanel(new BorderLayout());
        tabela = new JTable(new KnjigeTableModel(knjigaKontroler));
        tabela.setRowHeight(25);
        scrollPane = new JScrollPane(tabela);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        tabelaPanel.add(scrollPane);

        // Logout meni
        podesavanjaPanel = new JPanel(new GridLayout(0, 2));
        podesavanjaPanel.setBackground(glavnaBoja);
        podesavanjaPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, tamnoSivaBoja));
        JLabel[] labelPodesavanja = new JLabel[2];
        icon = new ImageIcon("images/password.png");
        labelPodesavanja[0] = new JLabel(icon, JLabel.CENTER); 
        labelPodesavanja[0].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, tamnoSivaBoja));
        icon = new ImageIcon("images/logout.png");
        labelPodesavanja[1] = new JLabel(icon, JLabel.CENTER);
            
        for (int i = 0; i < 2; i++) {
            labelPodesavanja[i].setBackground(glavnaBoja);
            podesavanjaPanel.add(labelPodesavanja[i]);
            labelPodesavanja[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getSource() == labelPodesavanja[0]) {
                        JTextField prvi = new JTextField(15);
                        Object[] sifra = {
                            "Nova šifra", prvi};
                        int odgovor = JOptionPane.showConfirmDialog(null, sifra, "Promena šifre", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                        if (odgovor == JOptionPane.YES_OPTION) {
                            zaposleniKontroler.promeniSifru(ID, prvi.getText());
                            JOptionPane.showMessageDialog(null, "Uspešno ste promenili šifru", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        } 
                    } else if (e.getSource() == labelPodesavanja[1]) {
                        int reply = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da želite da se odjavite?", "Da li želite da se odjavite?", 0, JOptionPane.INFORMATION_MESSAGE);
                        if (reply == JOptionPane.YES_OPTION) {
                            dispose();
                            LoginPage loginPage = new LoginPage();
                            loginPage.createAndShowGUI();
                        }
                    }
                }
                @Override
                public void mouseEntered(MouseEvent evt) {
                    podesavanjaPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
            });
        }
        
        // Podkategorija Meni
        meniPodkategorijePanel = new JPanel(new GridBagLayout());
        icon = new ImageIcon("images/plus_math_30px.png");
        dodajKnjiguLabel = new JLabel(icon);
        dodajKorisnikaLabel = new JLabel(icon);
        dodajKnjiguLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                dodajKnjigu();
            }
            @Override
            public void mouseEntered(MouseEvent evt) {
                dodajKnjiguLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        dodajKorisnikaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                dodajKorisnika();
            }
            @Override
            public void mouseEntered(MouseEvent evt) {
                dodajKorisnikaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        meniPodkategorijePanel.add(dodajKnjiguLabel);
        
        icon = new ImageIcon("images/edit_30px.png");
        izmeniKnjiguLabel = new JLabel(icon);
        izmeniKorisnikaLabel = new JLabel(icon);
        izmeniKnjiguLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                if (tabela.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(null, "Izaberite zaposlenog", "Izmenite informacije o zaposlenom", JOptionPane.ERROR_MESSAGE);
                } else {
                    izmeniKnjigu();
                    postaviPoljaKnjige(evt);
                }
            }
        });
        izmeniKorisnikaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                
                if (tabela.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(null, "Izaberite zaposlenog", "Izmenite informacije o zaposlenom", JOptionPane.ERROR_MESSAGE);
                } else {
                    izmeniKorisnika();
                    postaviPoljaKorisnici(evt);
                }
            }
        });
        meniPodkategorijePanel.add(izmeniKnjiguLabel);
        
        
        icon = new ImageIcon("images/delete_sign_30px.png");
        obrisiKnjiguLabel = new JLabel(icon);
        obrisiKorisnikaLabel = new JLabel(icon);
        obrisiKnjiguLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
            if (tabela.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(null, "Izaberite zaposlenog", "Obrišite zaposlenog", JOptionPane.ERROR_MESSAGE);
            } else {
                String i = tabela.getValueAt(tabela.getSelectedRow(), tabela.convertColumnIndexToModel(0)).toString();
                knjigaKontroler.obrisiKnjigu(i);
                JOptionPane.showMessageDialog(rootPane, "Uspešno");
                tabela.setAutoCreateRowSorter(true);
                tabela.setModel(new KnjigeTableModel(knjigaKontroler));
                tabela.updateUI();
                }  
            }
        });
        obrisiKorisnikaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
            if (tabela.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(null, "Izaberite zaposlenog", "Obrišite zaposlenog", JOptionPane.ERROR_MESSAGE);
            } else {
                int i = Integer.valueOf(tabela.getValueAt(tabela.getSelectedRow(), tabela.convertColumnIndexToModel(0)).toString());
                korisniciKontroler.obrisiKorisnika(i);
                JOptionPane.showMessageDialog(rootPane, "Uspešno");
                tabela.setAutoCreateRowSorter(true);
                tabela.setModel(new KorisniciTableModel(korisniciKontroler));
                tabela.updateUI();
                }  
            }
        });
        meniPodkategorijePanel.add(obrisiKnjiguLabel);
        
        // GRID
        glavniPanel = new JPanel(new GridBagLayout());
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridwidth = 5;
        gbc.insets = new Insets(10, 10, 10, 10);
        meniKategorijePanel.add(logo, gbc);
        gbc.gridx = 5;
        gbc.gridwidth = 5;
        meniKategorijePanel.add(danasnjiDatum, gbc);
        
        gbc.gridx = 0;
        gbc.gridwidth = 10;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weighty = 0.1;
        glavniPanel.add(meniKategorijePanel, gbc);
        
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridheight = 5;
        gbc.weightx = 0.2;
        gbc.weighty = 0.8;
        glavniPanel.add(leviMeni, gbc);
        
        gbc.gridx = 2;
        gbc.gridwidth = 8;
        gbc.gridy = 1;
        gbc.gridheight = 5;
        gbc.weightx = 0.8;
        glavniPanel.add(tabelaPanel, gbc);
        
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridy = 6;
        gbc.gridheight = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 0.1;
        glavniPanel.add(podesavanjaPanel, gbc);

        gbc.gridx = 2;
        gbc.gridwidth =  3;
        gbc.gridy = 6;
        gbc.gridheight = 1;
        gbc.weightx = 0.8;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 5;
        gbc.gridwidth =  5;
        gbc.insets = new Insets(0, 0, 0, 5);
        gbc.anchor = GridBagConstraints.EAST;
        glavniPanel.add(meniPodkategorijePanel, gbc);
        
        // Podesavanje glavnog frame-a
        getContentPane().add(glavniPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Admin Panel");
        setSize(1200,800);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    
    public void dodajKnjigu() {
        JFrame dodajKnjiguFrame = new JFrame("Dodaj Knjigu");
        JPanel dodajKnjiguGlavniPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel isbn = new JLabel("ISBN:");
        isbnTextField= new JTextField(15);
        JLabel imeKnjige = new JLabel("Ime knjige:");
        imeKnjigeTextField= new JTextField(15);
        JLabel pisac = new JLabel("Pisac:");
        pisacTextField= new JTextField(15);
        JLabel kategorija = new JLabel("Kategorija:");
        String [] kategorije = {"Odaberi", "Akcioni", "Autobiografija", "Drama", "Epska fantazija", "Domaći autori", "Istorija", "Ljubavni", "Muzika", "Putopisi"};
        kategorijeComboBox = new JComboBox(kategorije);
        JLabel godinaIzdavanja = new JLabel("Godina izdavanja:");
        godinaIzdavanjaTextField= new JTextField(15);
        JLabel cena = new JLabel("Cena:");
        
        cenaTextField= new JTextField(15);
        
        JButton dodajKnjiguBtn = new JButton("Dodaj");
        dodajKnjiguBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajKnjiguActionPerformed(evt);
                dodajKnjiguFrame.dispose();
                tabela.setModel(new KnjigeTableModel(knjigaKontroler));
                tabela.updateUI();
            }
        });
        
        
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        dodajKnjiguGlavniPanel.add(isbn, gbc);
        dodajKnjiguGlavniPanel.add(imeKnjige, gbc);
        dodajKnjiguGlavniPanel.add(pisac, gbc);
        dodajKnjiguGlavniPanel.add(kategorija, gbc);
        dodajKnjiguGlavniPanel.add(godinaIzdavanja, gbc);
        dodajKnjiguGlavniPanel.add(cena, gbc);
        gbc.gridx = 1;
        dodajKnjiguGlavniPanel.add(isbnTextField, gbc);
        dodajKnjiguGlavniPanel.add(imeKnjigeTextField, gbc);
        dodajKnjiguGlavniPanel.add(pisacTextField, gbc);
        dodajKnjiguGlavniPanel.add(kategorijeComboBox, gbc);
        dodajKnjiguGlavniPanel.add(godinaIzdavanjaTextField, gbc);
        dodajKnjiguGlavniPanel.add(cenaTextField, gbc);
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        dodajKnjiguGlavniPanel.add(dodajKnjiguBtn, gbc);
        
        dodajKnjiguFrame.getContentPane().add(dodajKnjiguGlavniPanel);
        dodajKnjiguFrame.pack();
        dodajKnjiguFrame.setResizable(false);
        dodajKnjiguFrame.setLocationRelativeTo(null);
        dodajKnjiguFrame.setVisible(true);
    }
    
    public void izmeniKnjigu() {
        JFrame izmeniKnjiguFrame = new JFrame("Izmeni Knjigu");
        JPanel izmeniKnjiguPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel imeKnjige = new JLabel("Ime knjige:");
        izmeniKnjiguPanel.add(imeKnjige);
        imeKnjigeTextField= new JTextField(15);
        izmeniKnjiguPanel.add(imeKnjigeTextField);
        JLabel pisac = new JLabel("Pisac:");
        izmeniKnjiguPanel.add(pisac);
        pisacTextField= new JTextField(15);
        izmeniKnjiguPanel.add(pisacTextField);
        JLabel kategorija = new JLabel("Kategorija:");
        izmeniKnjiguPanel.add(kategorija);
        String [] kategorije = {"Odaberi", "Akcioni", "Autobiografija", "Drama", "Epska fantazija", "Domaci autori", "Istorija", "Ljubavni", "Muzika", "Putopisi"};
        kategorijeComboBox = new JComboBox(kategorije);
        izmeniKnjiguPanel.add(kategorijeComboBox);
        JLabel isbn = new JLabel("ISBN:");
        izmeniKnjiguPanel.add(isbn);
        isbnTextField= new JTextField(15);
        isbnTextField.setEditable(false);
        izmeniKnjiguPanel.add(isbnTextField);
        JLabel godinaIzdavanja = new JLabel("Godina izdavanja:");
        izmeniKnjiguPanel.add(godinaIzdavanja);
        godinaIzdavanjaTextField= new JTextField(15);
        izmeniKnjiguPanel.add(godinaIzdavanjaTextField);
        JLabel cena = new JLabel("Cena:");
        izmeniKnjiguPanel.add(cena);
        cenaTextField= new JTextField(15);
        izmeniKnjiguPanel.add(cenaTextField);
        JButton izmeniZaposlenogBtn = new JButton("Izmeni");
        izmeniZaposlenogBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                izmeniKnjigu(evt);
                izmeniKnjiguFrame.dispose();
                tabela.setModel(new KnjigeTableModel(knjigaKontroler));
                tabela.updateUI();
            }
        });
        
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        izmeniKnjiguPanel.add(isbn, gbc);
        izmeniKnjiguPanel.add(imeKnjige, gbc);
        izmeniKnjiguPanel.add(pisac, gbc);
        izmeniKnjiguPanel.add(kategorija, gbc);
        izmeniKnjiguPanel.add(godinaIzdavanja, gbc);
        izmeniKnjiguPanel.add(cena, gbc);
        gbc.gridx = 1;
        izmeniKnjiguPanel.add(isbnTextField, gbc);
        izmeniKnjiguPanel.add(imeKnjigeTextField, gbc);
        izmeniKnjiguPanel.add(pisacTextField, gbc);
        izmeniKnjiguPanel.add(kategorijeComboBox, gbc);
        izmeniKnjiguPanel.add(godinaIzdavanjaTextField, gbc);
        izmeniKnjiguPanel.add(cenaTextField, gbc);
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        izmeniKnjiguPanel.add(izmeniZaposlenogBtn, gbc);
        
        izmeniKnjiguFrame.getContentPane().add(izmeniKnjiguPanel);
        izmeniKnjiguFrame.pack();
        izmeniKnjiguFrame.setLocationRelativeTo(null);
        izmeniKnjiguFrame.setResizable(false);
        izmeniKnjiguFrame.setVisible(true); 
    }
    
        public void dodajKorisnika() {
        JFrame dodajKorisnikaFrame = new JFrame("Dodaj Korisnika");
        JPanel dodajKorisnikaPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel ime = new JLabel("Ime korisnika:");
        imeTextField= new JTextField(15);
        JLabel prezime = new JLabel("Prezime korisnika:");
        prezimeTextField= new JTextField(15);
        JLabel adresa = new JLabel("Adresa:");
        adresaTextField= new JTextField(15);
        JLabel grad = new JLabel("Grad:");
        gradTextField= new JTextField(15);
        JLabel brTelefona = new JLabel("Broj telefona:");
        brTelefonaTextField= new JTextField(15);
        JLabel JMBG = new JLabel("JMBG:");
        JMBGTextField= new JTextField(15);
        JLabel email = new JLabel("Email adresa:");
        emailTextField= new JTextField(15);
        JButton dodajZaposlenogBtn = new JButton("Dodaj");
        dodajZaposlenogBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajKorisnikaActionPerformed(evt);
                dodajKorisnikaFrame.dispose();
                tabela.setModel(new KorisniciTableModel(korisniciKontroler));
                tabela.updateUI();
            }
        });
        
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        dodajKorisnikaPanel.add(ime, gbc);
        dodajKorisnikaPanel.add(prezime, gbc);
        dodajKorisnikaPanel.add(adresa, gbc);
        dodajKorisnikaPanel.add(grad, gbc);
        dodajKorisnikaPanel.add(JMBG, gbc);
        dodajKorisnikaPanel.add(brTelefona, gbc);
        dodajKorisnikaPanel.add(email, gbc);
        gbc.gridx = 1;
        dodajKorisnikaPanel.add(imeTextField, gbc);
        dodajKorisnikaPanel.add(prezimeTextField, gbc);
        dodajKorisnikaPanel.add(adresaTextField, gbc);
        dodajKorisnikaPanel.add(gradTextField, gbc);
        dodajKorisnikaPanel.add(JMBGTextField, gbc);
        dodajKorisnikaPanel.add(brTelefonaTextField, gbc);
        dodajKorisnikaPanel.add(emailTextField, gbc);
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.gridy = 7;
        gbc.gridheight = 12;
        dodajKorisnikaPanel.add(dodajZaposlenogBtn, gbc);

        dodajKorisnikaFrame.getContentPane().add(dodajKorisnikaPanel);
        dodajKorisnikaFrame.pack();
        dodajKorisnikaFrame.setLocationRelativeTo(null);
        dodajKorisnikaFrame.setResizable(false);
        dodajKorisnikaFrame.setVisible(true);
    }
    
    public void izmeniKorisnika() {
        JFrame izmeniKorisnikaFrame = new JFrame("Izmeni Korisnika");
        JPanel izmeniKorisnikaPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel ime = new JLabel("Ime korisnika:");
        imeTextField= new JTextField(15);
        JLabel prezime = new JLabel("Prezime korisnika:");
        prezimeTextField= new JTextField(15);
        JLabel adresa = new JLabel("Adresa:");
        adresaTextField= new JTextField(15);
        JLabel grad = new JLabel("Grad:");
        gradTextField= new JTextField(15);
        JLabel JMBG = new JLabel("JMBG:");
        JMBGTextField= new JTextField(15);
        JLabel brTelefona = new JLabel("Broj telefona:");
        brTelefonaTextField= new JTextField(15);
        JLabel email = new JLabel("Email adresa:");
        emailTextField= new JTextField(15);
        JButton izmeniZaposlenogBtn = new JButton("Izmeni");
        izmeniZaposlenogBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                izmeniKorisnika(evt);
                izmeniKorisnikaFrame.dispose();
                tabela.setModel(new KorisniciTableModel(korisniciKontroler));
                tabela.updateUI();
                
            }
        });
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        izmeniKorisnikaPanel.add(ime, gbc);
        izmeniKorisnikaPanel.add(prezime, gbc);
        izmeniKorisnikaPanel.add(adresa, gbc);
        izmeniKorisnikaPanel.add(grad, gbc);
        izmeniKorisnikaPanel.add(JMBG, gbc);
        izmeniKorisnikaPanel.add(brTelefona, gbc);
        izmeniKorisnikaPanel.add(email, gbc);
        gbc.gridx = 1;
        izmeniKorisnikaPanel.add(imeTextField, gbc);
        izmeniKorisnikaPanel.add(prezimeTextField, gbc);
        izmeniKorisnikaPanel.add(adresaTextField, gbc);
        izmeniKorisnikaPanel.add(gradTextField, gbc);
        izmeniKorisnikaPanel.add(JMBGTextField, gbc);
        izmeniKorisnikaPanel.add(brTelefonaTextField, gbc);
        izmeniKorisnikaPanel.add(emailTextField, gbc);
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.gridy = 7;
        gbc.gridheight = 12;
        izmeniKorisnikaPanel.add(izmeniZaposlenogBtn, gbc);
        
        izmeniKorisnikaFrame.getContentPane().add(izmeniKorisnikaPanel);
        izmeniKorisnikaFrame.pack();
        izmeniKorisnikaFrame.setLocationRelativeTo(null);
        izmeniKorisnikaFrame.setResizable(false);
        izmeniKorisnikaFrame.setVisible(true);
    }
    
    private void dodajKnjiguActionPerformed(ActionEvent evt) {                                                 
        java.awt.EventQueue.invokeLater(() -> {
            String ISBN = isbnTextField.getText();
            String imeKnjige = imeKnjigeTextField.getText();
            String pisac = pisacTextField.getText();
            String kategorija = kategorijeComboBox.getSelectedItem().toString();
            Integer godinaIzdavanja = Integer.valueOf(godinaIzdavanjaTextField.getText());
            double cena = Double.valueOf(cenaTextField.getText());
            
            if (knjigaKontroler.dodajKnjigu(ISBN, imeKnjige, pisac, kategorija, godinaIzdavanja, cena) == true) {
                JOptionPane.showMessageDialog(null, "Uspešno dodata knjiga", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                isbnTextField.setText(null);
                imeKnjigeTextField.setText(null);
                pisacTextField.setText(null);
                kategorijeComboBox.setSelectedIndex(0);
                godinaIzdavanjaTextField.setText(null);
                cenaTextField.setText(null);
            }
        });
    } 
    private void izmeniKnjigu(ActionEvent evt) {
        
        String ISBN = isbnTextField.getText();
        String imeKnjige = imeKnjigeTextField.getText();
        String pisac = pisacTextField.getText();
        String kategorija = kategorijeComboBox.getSelectedItem().toString();
        Integer godinaIzdavanja = Integer.valueOf(godinaIzdavanjaTextField.getText());
        double cena = Double.valueOf(cenaTextField.getText());
        if (knjigaKontroler.izmeniKnjigu(ISBN, imeKnjige, pisac, kategorija, godinaIzdavanja, cena) == true) {
            JOptionPane.showMessageDialog(null, "Uspešno izmenjena knjiga", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void dodajKorisnikaActionPerformed(ActionEvent evt) {                                                 
        java.awt.EventQueue.invokeLater(() -> {
            String JMBG = JMBGTextField.getText();
            String ime = imeTextField.getText();
            String prezime = prezimeTextField.getText();
            String adresa = adresaTextField.getText();
            String grad = gradTextField.getText();
            String brTelefona = brTelefonaTextField.getText();
            String email = emailTextField.getText();
            
            if (korisniciKontroler.dodajKorisnika(JMBG, ime, prezime, adresa, grad, brTelefona, email) == true) {
                JOptionPane.showMessageDialog(null, "Uspešno dodat korisnik", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                JMBGTextField.setText(null);
                imeTextField.setText(null);
                prezimeTextField.setText(null);
                adresaTextField.setText(null);
                gradTextField.setText(null);
                brTelefonaTextField.setText(null);
                emailTextField.setText(null);
            }
        });
    }
    private void izmeniKorisnika(ActionEvent evt) {
        String JMBG = JMBGTextField.getText();
        String ime = imeTextField.getText();
        String prezime = prezimeTextField.getText();
        String adresa = adresaTextField.getText();
        String grad = gradTextField.getText();
        String brTelefona = brTelefonaTextField.getText();
        String email = emailTextField.getText();
        if (korisniciKontroler.izmeniKorisnika(JMBG, ime, prezime, adresa, grad, brTelefona, email) == true) {
            JOptionPane.showMessageDialog(rootPane, "Uspešno su izmenjeni podaci o korisniku", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
            tabela.updateUI();
        }
    }
    
    private void postaviPoljaKnjige(MouseEvent evt) {
            TableModel model = tabela.getModel();
            int i = tabela.convertRowIndexToModel(tabela.getSelectedRow());
            isbnTextField.setText(model.getValueAt(i, 0).toString());
            imeKnjigeTextField.setText(model.getValueAt(i, 1).toString());
            pisacTextField.setText(model.getValueAt(i, 2).toString());
            kategorijeComboBox.setSelectedItem(model.getValueAt(i, 3));
            godinaIzdavanjaTextField.setText(model.getValueAt(i, 4).toString());
            cenaTextField.setText(model.getValueAt(i, 5).toString());
    }
    private void postaviPoljaKorisnici(MouseEvent evt) {
            TableModel model = tabela.getModel();
            int i = tabela.convertRowIndexToModel(tabela.getSelectedRow());
            imeTextField.setText(model.getValueAt(i, 1).toString());
            prezimeTextField.setText(model.getValueAt(i, 2).toString());
            adresaTextField.setText(model.getValueAt(i, 3).toString());
            gradTextField.setText(model.getValueAt(i, 4).toString());
            JMBGTextField.setText(model.getValueAt(i, 5).toString());
            brTelefonaTextField.setText(model.getValueAt(i, 6).toString());
            emailTextField.setText(model.getValueAt(i, 7).toString());
    }
    
    private void tabelaKnjige(MouseEvent evt) {
        try {
            meniPodkategorijePanel.removeAll();
            meniPodkategorijePanel.repaint();
            meniPodkategorijePanel.revalidate();
            
            meniPodkategorijePanel.add(dodajKnjiguLabel);
            meniPodkategorijePanel.add(izmeniKnjiguLabel);
            meniPodkategorijePanel.add(obrisiKnjiguLabel);
            meniPodkategorijePanel.repaint();
            meniPodkategorijePanel.revalidate();
        } catch (Exception e) {
        }
    }
    private void tabelaKorisnici(MouseEvent evt) {
        try {
            meniPodkategorijePanel.removeAll();
            meniPodkategorijePanel.repaint();
            meniPodkategorijePanel.revalidate();
            
            meniPodkategorijePanel.add(dodajKorisnikaLabel);
            meniPodkategorijePanel.add(izmeniKorisnikaLabel);
            meniPodkategorijePanel.add(obrisiKorisnikaLabel);
            meniPodkategorijePanel.repaint();
            meniPodkategorijePanel.revalidate();
        } catch (Exception e) {
        }
    }
    
    private JTextField JMBGTextField, imeTextField, prezimeTextField, adresaTextField, gradTextField, brTelefonaTextField, emailTextField, plataTextField, usernameTextField, passwordTextField, search;
    private JComboBox pozicijeComboBox, kategorijeComboBox;
    private JTextField imeKnjigeTextField, pisacTextField, isbnTextField, godinaIzdavanjaTextField, cenaTextField;
    private JDateChooser datum;
    private JScrollPane scrollPane;
    private JLabel dodajZaposlenogLabel, dodajKnjiguLabel, dodajKorisnikaLabel, izmeniZaposlenogLabel, 
            izmeniKnjiguLabel, izmeniKorisnikaLabel, obrisiZaposlenogLabel, obrisiKnjiguLabel, obrisiKorisnikaLabel;
    private JPanel glavniPanel, meniKategorijePanel, meniPodkategorijePanel, tabelaPanel, podesavanjaPanel;
    private JTable tabela;
    private JLabel[] jLabelArr;
    private final GridBagConstraints gbc = new GridBagConstraints();
}
