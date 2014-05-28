package com.UrlCrawler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SwingUrlCrawler {
    JFrame frame = new JFrame("SwingUrlCrawler");
    UrlStorage storage;
    UrlTableModel urlTableModel;
    JTable table;
    JPanel controlPanel = new JPanel();
    JPanel tablePanel = new JPanel();
    private JLabel urlFieldLabel = new JLabel("Root URL:");
    private JTextField urlField = new JTextField(40);
    private JButton crawlButton = new JButton("Run");
    private Crawler crawler = new Crawler();

    public SwingUrlCrawler() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        initControlPanel();
        initTable();

        frame.pack();
        frame.setVisible(true);
        frame.setSize(600, 300);
        frame.setLocation(200, 200);

    }

    private void initControlPanel() {
        controlPanel.setLayout(new BorderLayout());
        controlPanel.add(urlFieldLabel, BorderLayout.WEST);
        controlPanel.add(urlField, BorderLayout.CENTER);
        controlPanel.add(crawlButton, BorderLayout.EAST);
        frame.add(controlPanel, BorderLayout.NORTH);

        crawlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = urlField.getText();
                if (url != "") {
                    DefaultTableModel dtm = (DefaultTableModel) table.getModel();
                    dtm.addRow(new Object[]{url});
                }
            }
        });
    }

    private void initTable() {
        storage = new UrlStorage();

        storage.setCrawler(crawler);
        urlTableModel = new UrlTableModel();
        urlTableModel.setUrlStorage(storage);
        crawler.setUrlTableModel(urlTableModel);
        crawler.setStorage(storage);
        table = new JTable(urlTableModel);

        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
        tablePanel.add(table, BorderLayout.CENTER);
        frame.add(tablePanel, BorderLayout.CENTER);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int r = table.rowAtPoint(e.getPoint());
                if (r >= 0 && r < table.getRowCount()) {
                    table.setRowSelectionInterval(r, r);
                } else {
                    table.clearSelection();
                }

                int rowindex = table.getSelectedRow();
                if (rowindex < 0)
                    return;
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
                    JPopupMenu menuPopup = new JPopupMenu();
                    JMenuItem removeMenuItem = new JMenuItem((String)table.getValueAt(table.getSelectedRow(), 0));
                    menuPopup.add(removeMenuItem);

                    menuPopup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    public static void main(String[] args){
        SwingUrlCrawler swingUrlCrawler = new SwingUrlCrawler();
    }
}
