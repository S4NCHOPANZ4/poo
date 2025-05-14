package projc1;

import java.util.List;
import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class Menu extends javax.swing.JPanel {

    private AnimalManager manager;
    private String currentTextFilter = "";
    private String currentSpeciesFilter = "Todos";
    private String currentAgeFilter = "Todos";
    private String currentWeightFilter = "Todos";

    public Menu() {
        initComponents();
        manager = new AnimalManager();
        List<Animal> all = manager.getAnimals();
        loadAnimalsTabla(all);

        jTextField1.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateTextFilter();
            }
            public void removeUpdate(DocumentEvent e) {
                updateTextFilter();
            }
            public void changedUpdate(DocumentEvent e) {
                updateTextFilter();
            }
            private void updateTextFilter() {
                currentTextFilter = jTextField1.getText().toLowerCase();
                filterAnimals();
            }
        });

        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int fila = jTable1.rowAtPoint(evt.getPoint());
                if (fila >= 0) {
                    String name = jTable1.getValueAt(fila, 0).toString();
                    String specie = jTable1.getValueAt(fila, 1).toString();
                    String weight = jTable1.getValueAt(fila, 2).toString();
                    String age = jTable1.getValueAt(fila, 3).toString();
                    new ExtraInfo(Menu.this,name,specie, Double.parseDouble(weight), Integer.parseInt(age)).setVisible(true); 
                }
            }
        }); 
    }
  private void filterAnimals() {
    List<Animal> filtered = new ArrayList<>();
    List<Animal> allAnimals = manager.getAnimals();
    for (Animal a : allAnimals) {
        boolean passFilter = true;
        if (!currentTextFilter.isEmpty() && !a.getName().toLowerCase().contains(currentTextFilter)) {
            passFilter = false;
        }
        if (!currentSpeciesFilter.equals("Todos") && !a.getSpecie().equalsIgnoreCase(currentSpeciesFilter)) {
            passFilter = false;
        }
        // Filtro por edad
        if (!currentAgeFilter.equals("Todos")) {
            int edad = a.getAge();
            switch (currentAgeFilter) {
                case "Edad < 1":
                    if (edad >= 1) passFilter = false;
                    break;
                case "1 ≤ Edad < 5":
                    if (edad < 1 || edad >= 5) passFilter = false;
                    break;
                case "5 ≤ Edad < 8":
                    if (edad < 5 || edad >= 8) passFilter = false;
                    break;
                case "Edad ≥ 8":
                    if (edad < 8) passFilter = false;
                    break;
            }
        }
        
        // Filtro por peso
        if (!currentWeightFilter.equals("Todos")) {
            double peso = a.getWeight();
            switch (currentWeightFilter) {
                case "Peso < 10kg":
                    if (peso >= 10) passFilter = false;
                    break;
                case "10kg ≤ Peso < 50kg":
                    if (peso < 10 || peso >= 50) passFilter = false;
                    break;
                case "50kg ≤ Peso < 100kg":
                    if (peso < 50 || peso >= 100) passFilter = false;
                    break;
                case "Peso ≥ 100kg":
                    if (peso < 100) passFilter = false;
                    break;
            }
        }
        
        if (passFilter) {
            filtered.add(a);
        }
    }
    
    loadAnimalsTabla(filtered);
}

public void applyFilters(String species, String age, String weight) {
    currentSpeciesFilter = species;
    currentAgeFilter = age;
    currentWeightFilter = weight;
    manager = new AnimalManager();
    filterAnimals();
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Menu");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Especie", "Peso", "Edad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Filtrar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Buscar por nombre");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        new Filters(this).setVisible(true); // Abre la nueva ventana al hacer clic
    }//GEN-LAST:event_jButton1MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    public void loadAnimalsTabla(List<Animal> lista) {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);
        for (Animal a : lista) {
            Object[] fila = {
                a.getName(),
                a.getSpecie(),
                a.getWeight(),
                a.getAge()
            };
            modelo.addRow(fila);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    public String getCurrentTextFilter() {
        return currentTextFilter;
    }

    public String getCurrentSpeciesFilter() {
        return currentSpeciesFilter;
    }

    public String getCurrentAgeFilter() {
        return currentAgeFilter;
    }

    public String getCurrentWeightFilter() {
        return currentWeightFilter;
    }

}
