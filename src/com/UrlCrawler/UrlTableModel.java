package com.UrlCrawler;

import java.util.List;

import javax.swing.table.DefaultTableModel;

public class UrlTableModel extends DefaultTableModel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String[] columnNames = { "URL", "Title", "Children" };
    private UrlStorage storage;

    public String getColumnName(int col) {
        return columnNames[col].toString();
    }

    public void setUrlStorage(UrlStorage storage) {
        this.storage = storage;
    }

    @Override
    public int getRowCount() {
        if (storage != null) {
            return storage.getRowCount();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return storage.getValueByName(rowIndex, columnNames[columnIndex]);
    }

    public void addRows(List<String> rows) {
        System.out.println("UrlTableModel->addRows");
        for (String row : rows) {
            storage.addItem((String) row);
        }
        fireTableRowsInserted(storage.getRowCount() - 1,
                storage.getRowCount() - 1);
    }

    public void addRow(Object[] row) {
        System.out.println("UrlTableModel->addRow");
        storage.addItem((String) row[0]);
        fireTableRowsInserted(storage.getRowCount() - 1,
                storage.getRowCount() - 1);
        if (getRowCount() < 100) {
            storage.fetchNextUrl();
        }
    }
}
