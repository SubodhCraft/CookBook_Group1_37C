/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cookbook;

import DAO.BookmarkDAO;
import DAO.RecipeDAO;
import DAO.SettingsDAO;
import DAO.UserDao;
import Database.Database;
import Database.MySqlConnection;
import Model.LoggedInUser;
import Model.Recipe;
import View.Sigininframe;
import View.SelfNote;
import View.UserMyProfile;
import View.UserSettings;
import controller.AdminDashboardController;
import controller.BookmarkController;
import controller.BookmarkUIController;
import controller.LoginController;
import java.awt.CardLayout;
import java.util.List;
import java.util.Set;
import javax.swing.JPanel;
import cookbook.admin_dashboard;
import cookbook.update;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import View.k;   
import controller.NoteController;
import controller.SettingsController;
import controller.UserProfileController;
import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.SwingWorker;

public class Dashboard extends javax.swing.JFrame {
    private AdminDashboardController dashboardController;
    private final SettingsDAO settingsDao = new SettingsDAO();
//    private final UserSettings settingsView;
//    private final Dashboard dashView;
//   private AdminDashboardController dashboardController;
    
    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();
        setLocationRelativeTo(null);
//       AdminDashboardController controller = new AdminDashboardController(this);
       setVisible(true);
       setSize(920, 707);
       setLocationRelativeTo(null);
//        logOut=new javax.swing.JButton();
        logOut.setText("Logout");
//        Filters.addActionListener(evt -> handleSearch());
        
        Database db = new MySqlConnection();
        RecipeDAO recipeDAO = new RecipeDAO(db);
        BookmarkDAO bookmarkDAO = new BookmarkDAO(db);
        
        Home home = new Home();
        Bookmark bookmark = new Bookmark();
        admin_dashboard adminDash = new admin_dashboard();
        RecipeDetailPanel recipeDetailPanel = new RecipeDetailPanel();
        update updatePanel = new update(recipeDAO,null);
        
        dashboardController = new AdminDashboardController(
        adminDash,
                home,
                bookmark,
                recipeDAO,
                bookmarkDAO,
                updatePanel,
                Main_panel
//                dashboardController.setupLogoutListener(this)
        );    
//        dashboardController.setupLogoutListener(this);
        int currentUserId = LoggedInUser.getId();
        BookmarkUIController bookmarkUIController = new BookmarkUIController(bookmark,currentUserId);
//        BookmarkController bookmarkController = new BookmarkController(db,bookmark);
        
        updatePanel.setController(dashboardController);
        
        dashboardController.loadRecipesToHome();
        dashboardController.loadBookmarkedRecipes();
        
        
//        BookmarkController bookmarkController = new BookmarkController(db, bookmark);
        
//        Database db = new MySqlConnection();
//        Bookmark bookmarkPanel = new Bookmark();
//        
//        new BookmarkController(db,bookmarkPanel);
        
//        new BookmarkController(adminDash,home,bookmark);
        
        Main_panel.add(home.home_panel,"home");
        Main_panel.add(bookmark.Bookmarkpanel,"bookmark");
        Main_panel.add(adminDash,"admin");
        Main_panel.add(recipeDetailPanel,"detail");
        Main_panel.add(updatePanel,"update");
        
        CardLayout cl = (CardLayout) Main_panel.getLayout();
        cl.show(Main_panel, "home");
        
         getContentPane().add(Main_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 650, 590));

//     Filters.addActionListener(new java.awt.event.ActionListener(){
//        public void actionPerformed(java.awt.event.ActionEvent evt){
//          handleSearch();
//        }
//    });
//        
//    }
   
//    private final SettingsDAO settingsDao = new SettingsDAO();
//    private final UserSettings settingsView;
    
//    public Dashboard(UserSettings view){
//        this. dashView = view;
//        this.dashView.addSettingsListener(new SettingsListener());
//    }
//    
//    void open(){
//        
//    }
//    void close(){
//        
//    }
//    
//    public void setupSettingsListener(UserSettings view){
//        view.addSettingsListener(new SettingsListener());
//    }

//    private void addSettingsListener(SettingsListener settingsListener) {
//    }
//    
//    class SettingsListener implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("Settings Button clicked!");
//            UserSettings dashView = new UserSettings();
//            new SettingsController(dashView);
//            dashView.setVisible(true);
//            
//            if(dashView != null) dashView.dispose();
//        }
//        
//    }
//    private void handleSearch(){
//        String keyword = Search.getText().trim();
//        if(keyword.isEmpty() || keyword.equals("Search")){
//            JOptionPane.showMessageDialog(this, "Please enter a keyword to search.");
//            return;
//        }
//        dashboardController.searchRecipes(keyword);
//    }
    
//    private void handleSearch(){
//    String keyword = Search.getText().trim();
//    if(keyword.isEmpty() || keyword.equals("Search")){
//        JOptionPane.showMessageDialog(this, "Please enter a keyword to search.");
//        return;
//    }

//    boolean found = dashboardController.searchRecipesWithFeedback(keyword);
//    if (!found) {
//        JOptionPane.showMessageDialog(this, "No results found for \"" + keyword + "\".");
//    }
//}
Filters.addActionListener(evt -> {
    String keyword = Search.getText().trim();
    if (keyword.isEmpty() || keyword.equals("Search")) {
        JOptionPane.showMessageDialog(null, "Please enter a keyword to search."); // null = show on center
        return;
    }

    new SwingWorker<Boolean, Void>() {
        @Override
        protected Boolean doInBackground() throws Exception {
            return dashboardController.searchRecipesWithFeedback(keyword);
        }

        @Override
        protected void done() {
            try {
                boolean found = get();
                if (!found) {
                    JOptionPane.showMessageDialog(null, "No results found for \"" + keyword + "\".");
                } else {
                    // Optional: Switch to home panel if results go there
                    CardLayout cl = (CardLayout) Main_panel.getLayout();
                    cl.show(Main_panel, "home");
                    Main_panel.revalidate();
                    Main_panel.repaint();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "An error occurred during search.");
            }
        }
    }.execute();
});

    }

      public JPanel getMainPanel() {
    return Main_panel;
}
    private JPanel notesContainer;
    private JScrollPane scrollPane;
    private List<JPanel> noteCards = new ArrayList<>();
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Menu_panel = new javax.swing.JPanel();
        Category_panel = new javax.swing.JPanel();
        Category = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Selfnote_panel = new javax.swing.JPanel();
        selfNote = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        Logo_label = new javax.swing.JLabel();
        Settings_panel = new javax.swing.JPanel();
        Settings = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        Myprofile_panel = new javax.swing.JPanel();
        myProfile = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        Logout_panel = new javax.swing.JPanel();
        logOut = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        Bookmark_panel = new javax.swing.JPanel();
        Bookmark = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Challenges_panel = new javax.swing.JPanel();
        Challenges = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        Home_panel = new javax.swing.JPanel();
        Home = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        Myprofile_panel1 = new javax.swing.JPanel();
        admin = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Search_panel = new javax.swing.JPanel();
        Search_border = new javax.swing.JPanel();
        Searchicon_label = new javax.swing.JLabel();
        Search = new javax.swing.JTextField();
        Filtericon_label = new javax.swing.JLabel();
        Filters = new javax.swing.JButton();
        Main_panel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main_window");
        setMinimumSize(new java.awt.Dimension(900, 700));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Menu_panel.setBackground(new java.awt.Color(255, 255, 255));
        Menu_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Menu_panel.setMaximumSize(new java.awt.Dimension(241, 982));
        Menu_panel.setMinimumSize(new java.awt.Dimension(241, 982));
        Menu_panel.setPreferredSize(new java.awt.Dimension(241, 982));

        Category_panel.setBackground(new java.awt.Color(255, 255, 255));

        Category.setText("Category");
        Category.setBorder(null);
        Category.setBorderPainted(false);
        Category.setContentAreaFilled(false);
        Category.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CategoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Category_panelLayout = new javax.swing.GroupLayout(Category_panel);
        Category_panel.setLayout(Category_panelLayout);
        Category_panelLayout.setHorizontalGroup(
            Category_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Category_panelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Category)
                .addContainerGap())
        );
        Category_panelLayout.setVerticalGroup(
            Category_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Category_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Category_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(Category, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        Selfnote_panel.setBackground(new java.awt.Color(255, 255, 255));

        selfNote.setText("Self Note");
        selfNote.setBorder(null);
        selfNote.setBorderPainted(false);
        selfNote.setContentAreaFilled(false);
        selfNote.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selfNote.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selfNoteMouseClicked(evt);
            }
        });
        selfNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selfNoteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Selfnote_panelLayout = new javax.swing.GroupLayout(Selfnote_panel);
        Selfnote_panel.setLayout(Selfnote_panelLayout);
        Selfnote_panelLayout.setHorizontalGroup(
            Selfnote_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Selfnote_panelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(selfNote)
                .addContainerGap())
        );
        Selfnote_panelLayout.setVerticalGroup(
            Selfnote_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Selfnote_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selfNote, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Settings_panel.setBackground(new java.awt.Color(255, 255, 255));

        Settings.setText("Settings");
        Settings.setBorder(null);
        Settings.setBorderPainted(false);
        Settings.setContentAreaFilled(false);
        Settings.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Settings_panelLayout = new javax.swing.GroupLayout(Settings_panel);
        Settings_panel.setLayout(Settings_panelLayout);
        Settings_panelLayout.setHorizontalGroup(
            Settings_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Settings_panelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Settings)
                .addContainerGap())
        );
        Settings_panelLayout.setVerticalGroup(
            Settings_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Settings_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Settings, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Myprofile_panel.setBackground(new java.awt.Color(255, 255, 255));

        myProfile.setText("My Profile");
        myProfile.setBorder(null);
        myProfile.setBorderPainted(false);
        myProfile.setContentAreaFilled(false);
        myProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        myProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myProfileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Myprofile_panelLayout = new javax.swing.GroupLayout(Myprofile_panel);
        Myprofile_panel.setLayout(Myprofile_panelLayout);
        Myprofile_panelLayout.setHorizontalGroup(
            Myprofile_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Myprofile_panelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(myProfile)
                .addContainerGap())
        );
        Myprofile_panelLayout.setVerticalGroup(
            Myprofile_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Myprofile_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(myProfile, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Logout_panel.setBackground(new java.awt.Color(255, 255, 255));

        logOut.setText("Log Out");
        logOut.setBorder(null);
        logOut.setBorderPainted(false);
        logOut.setContentAreaFilled(false);
        logOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Logout_panelLayout = new javax.swing.GroupLayout(Logout_panel);
        Logout_panel.setLayout(Logout_panelLayout);
        Logout_panelLayout.setHorizontalGroup(
            Logout_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Logout_panelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(logOut)
                .addGap(124, 124, 124))
        );
        Logout_panelLayout.setVerticalGroup(
            Logout_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
            .addComponent(logOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Bookmark_panel.setBackground(new java.awt.Color(255, 255, 255));

        Bookmark.setText("Bookmark");
        Bookmark.setBorder(null);
        Bookmark.setBorderPainted(false);
        Bookmark.setContentAreaFilled(false);
        Bookmark.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Bookmark.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BookmarkMouseClicked(evt);
            }
        });
        Bookmark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BookmarkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Bookmark_panelLayout = new javax.swing.GroupLayout(Bookmark_panel);
        Bookmark_panel.setLayout(Bookmark_panelLayout);
        Bookmark_panelLayout.setHorizontalGroup(
            Bookmark_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Bookmark_panelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Bookmark)
                .addContainerGap())
        );
        Bookmark_panelLayout.setVerticalGroup(
            Bookmark_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Bookmark_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Bookmark_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(Bookmark, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        Challenges_panel.setBackground(new java.awt.Color(255, 255, 255));

        Challenges.setText("Challenges");
        Challenges.setBorder(null);
        Challenges.setBorderPainted(false);
        Challenges.setContentAreaFilled(false);
        Challenges.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Challenges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChallengesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Challenges_panelLayout = new javax.swing.GroupLayout(Challenges_panel);
        Challenges_panel.setLayout(Challenges_panelLayout);
        Challenges_panelLayout.setHorizontalGroup(
            Challenges_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Challenges_panelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Challenges)
                .addContainerGap())
        );
        Challenges_panelLayout.setVerticalGroup(
            Challenges_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Challenges_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Challenges_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(Challenges, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        Home_panel.setBackground(new java.awt.Color(127, 1, 31));

        Home.setBackground(new java.awt.Color(127, 1, 31));
        Home.setForeground(new java.awt.Color(255, 255, 255));
        Home.setText("Home");
        Home.setBorder(null);
        Home.setBorderPainted(false);
        Home.setContentAreaFilled(false);
        Home.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HomeMouseClicked(evt);
            }
        });
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Home_panelLayout = new javax.swing.GroupLayout(Home_panel);
        Home_panel.setLayout(Home_panelLayout);
        Home_panelLayout.setHorizontalGroup(
            Home_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Home_panelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Home)
                .addContainerGap())
        );
        Home_panelLayout.setVerticalGroup(
            Home_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Home_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Home_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(Home, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        Myprofile_panel1.setBackground(new java.awt.Color(255, 255, 255));

        admin.setText("Admin");
        admin.setBorder(null);
        admin.setBorderPainted(false);
        admin.setContentAreaFilled(false);
        admin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        admin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminMouseClicked(evt);
            }
        });
        admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Myprofile_panel1Layout = new javax.swing.GroupLayout(Myprofile_panel1);
        Myprofile_panel1.setLayout(Myprofile_panel1Layout);
        Myprofile_panel1Layout.setHorizontalGroup(
            Myprofile_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Myprofile_panel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(admin)
                .addContainerGap())
        );
        Myprofile_panel1Layout.setVerticalGroup(
            Myprofile_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
            .addComponent(admin, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/Screenshot 2025-06-22 192617.png"))); // NOI18N

        javax.swing.GroupLayout Menu_panelLayout = new javax.swing.GroupLayout(Menu_panel);
        Menu_panel.setLayout(Menu_panelLayout);
        Menu_panelLayout.setHorizontalGroup(
            Menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Home_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Menu_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Logo_label)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(Menu_panelLayout.createSequentialGroup()
                .addGroup(Menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Category_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Challenges_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Selfnote_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Bookmark_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(Menu_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(Menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Settings_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Myprofile_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Myprofile_panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(Logout_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        Menu_panelLayout.setVerticalGroup(
            Menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Menu_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Logo_label, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(32, 32, 32)
                .addComponent(Home_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Category_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Challenges_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bookmark_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Selfnote_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Settings_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Myprofile_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Logout_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Myprofile_panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(413, Short.MAX_VALUE))
        );

        getContentPane().add(Menu_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Search_panel.setBackground(new java.awt.Color(255, 255, 255));
        Search_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Search_panel.setMaximumSize(new java.awt.Dimension(1512, 108));
        Search_panel.setMinimumSize(new java.awt.Dimension(1512, 108));

        Search_border.setMaximumSize(new java.awt.Dimension(526, 48));
        Search_border.setMinimumSize(new java.awt.Dimension(526, 48));

        Search.setText("Search");
        Search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                SearchFocusLost(evt);
            }
        });

        Filters.setBackground(new java.awt.Color(242, 242, 242));
        Filters.setText("Filters");
        Filters.setBorder(null);
        Filters.setBorderPainted(false);
        Filters.setContentAreaFilled(false);
        Filters.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Filters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiltersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Search_borderLayout = new javax.swing.GroupLayout(Search_border);
        Search_border.setLayout(Search_borderLayout);
        Search_borderLayout.setHorizontalGroup(
            Search_borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Search_borderLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Searchicon_label)
                .addGroup(Search_borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Search_borderLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Filtericon_label, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(224, 224, 224))
                    .addGroup(Search_borderLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(Filters)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Search_borderLayout.setVerticalGroup(
            Search_borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Search_borderLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(Search_borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Search_borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Filtericon_label)
                        .addComponent(Filters))
                    .addComponent(Searchicon_label))
                .addContainerGap())
        );

        javax.swing.GroupLayout Search_panelLayout = new javax.swing.GroupLayout(Search_panel);
        Search_panel.setLayout(Search_panelLayout);
        Search_panelLayout.setHorizontalGroup(
            Search_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Search_panelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(Search_border, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1136, Short.MAX_VALUE))
        );
        Search_panelLayout.setVerticalGroup(
            Search_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Search_panelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(Search_border, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        getContentPane().add(Search_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(241, 0, -1, -1));

        Main_panel.setLayout(new java.awt.CardLayout());
        // Load panels from other files
        Bookmark bookmark = new Bookmark();
        Home home = new Home();
        admin_dashboard adminDash = new admin_dashboard();
        AdminDashboardController controller = new AdminDashboardController(adminDash, home, bookmark);
        BookmarkController bookmarkController = new BookmarkController(adminDash, home, bookmark);
        k selfNotePanel = new k();  // Assuming `k` extends JPanel

        // Add panels to CardLayout
        Main_panel.add(home.home_panel, "home");
        Main_panel.add(bookmark.Bookmarkpanel, "bookmark");
        Main_panel.add(adminDash, "admin");
        Main_panel.add(selfNotePanel, "selfnote");

        // Show home panel by default
        java.awt.CardLayout cl = (java.awt.CardLayout)(Main_panel.getLayout());
        cl.show(Main_panel, "home");
        getContentPane().add(Main_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 650, 590));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void CategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CategoryActionPerformed

    private void BookmarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookmarkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BookmarkActionPerformed

    private void selfNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selfNoteActionPerformed
        // TODO add your handling code here:
        SelfNote note = new SelfNote();
        NoteController control = new NoteController(note);
        note.setVisible(true);
        this.dispose();
        
//        CardLayout cl = (CardLayout)(Main_panel.getLayout());
//        cl.show(Main_panel, "selfnote");  // Show your custom panel
        
    }//GEN-LAST:event_selfNoteActionPerformed

    private void SettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsActionPerformed
        // TODO add your handling code here:
        UserSettings set = new UserSettings();
//        set.setUsername(LoggedInUser.getUsername());
        SettingsController controller = new SettingsController(set);
        controller.open();
        this.dispose();
    }//GEN-LAST:event_SettingsActionPerformed

    private void myProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myProfileActionPerformed
        // TODO add your handling code here:
        UserMyProfile profile = new UserMyProfile();
        UserProfileController controller = new UserProfileController(profile, new UserDao());
        profile.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_myProfileActionPerformed

    private void logOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutActionPerformed
        // TODO add your handling code here:
//        Sigininframe sigin = new Sigininframe();
//        LoginController controller = new LoginController(sigin);
//        
//        controller.open();
    // Show confirmation dialog
    int choice = JOptionPane.showConfirmDialog(
            null,
            "Do you want to logout?",
            "Logout Confirmation",
            JOptionPane.YES_NO_OPTION);

    if (choice == JOptionPane.YES_OPTION) {
        // Proceed with logout
        Sigininframe sigin = new Sigininframe();
        LoginController controller = new LoginController(sigin);
        controller.open();


        // Close current window (if this is a JFrame)
        this.dispose(); // optional: closes the current window
    } else {
        // Logout cancelled
        System.out.println("Logout cancelled by user.");
    }
//    Home.addActionListener(e -> {
//    dashboardController.loadRecipesToHome();
//    Search.setText("");  // clear the search bar
//    System.out.println("Home button clicked!");
//});



        
    }//GEN-LAST:event_logOutActionPerformed

    private void ChallengesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChallengesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChallengesActionPerformed

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        // TODO add your handling code here:
        dashboardController.loadRecipesToHome();  // Refresh full home recipes view
Search.setText("Search");                        // Clear the search bar (if Search is your search JTextField)
System.out.println("Home button clicked!");

        
    }//GEN-LAST:event_HomeActionPerformed

    private void SearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SearchFocusGained
        // TODO add your handling code here:
        if(Search.getText().equals("Search")){
            Search.setText("");
        }
        

    }//GEN-LAST:event_SearchFocusGained

    private void SearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SearchFocusLost
        // TODO add your handling code here:
        if (Search.getText().isEmpty()) {
    Search.setText("Search");
}

    }//GEN-LAST:event_SearchFocusLost

    private void BookmarkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BookmarkMouseClicked
        // TODO add your handling code here:
           java.awt.CardLayout cl = (java.awt.CardLayout)(Main_panel.getLayout());
    cl.show(Main_panel, "bookmark");
    }//GEN-LAST:event_BookmarkMouseClicked

    private void HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeMouseClicked
        // TODO add your handling code here:
        java.awt.CardLayout cl = (java.awt.CardLayout)(Main_panel.getLayout());
    cl.show(Main_panel, "home");
    }//GEN-LAST:event_HomeMouseClicked

    private void adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adminActionPerformed

    private void adminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminMouseClicked
        // TODO add your handling code here:
         CardLayout cl = (CardLayout)(Main_panel.getLayout());
    cl.show(Main_panel, "admin");
    }//GEN-LAST:event_adminMouseClicked

    private void FiltersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiltersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FiltersActionPerformed
    private void selfNoteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selfNoteMouseClicked
        // TODO add your handling code here:
      
        
    }//GEN-LAST:event_selfNoteMouseClicked

public void addAddNoteListener (ActionListener listener){
    
}

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Dashboard().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bookmark;
    private javax.swing.JPanel Bookmark_panel;
    private javax.swing.JButton Category;
    private javax.swing.JPanel Category_panel;
    private javax.swing.JButton Challenges;
    private javax.swing.JPanel Challenges_panel;
    private javax.swing.JLabel Filtericon_label;
    private javax.swing.JButton Filters;
    private javax.swing.JButton Home;
    private javax.swing.JPanel Home_panel;
    private javax.swing.JLabel Logo_label;
    private javax.swing.JPanel Logout_panel;
    public static javax.swing.JPanel Main_panel;
    private javax.swing.JPanel Menu_panel;
    private javax.swing.JPanel Myprofile_panel;
    private javax.swing.JPanel Myprofile_panel1;
    private javax.swing.JTextField Search;
    private javax.swing.JPanel Search_border;
    private javax.swing.JPanel Search_panel;
    private javax.swing.JLabel Searchicon_label;
    private javax.swing.JPanel Selfnote_panel;
    private javax.swing.JButton Settings;
    private javax.swing.JPanel Settings_panel;
    private javax.swing.JButton admin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton logOut;
    private javax.swing.JButton myProfile;
    private javax.swing.JButton selfNote;
    // End of variables declaration//GEN-END:variables


    
    public void addLogoutListener(ActionListener listener){
        System.out.println("Attaching logout listener..");
        logOut.addActionListener(listener);
    } 
//    public void showBookmarkPage() {
//        Container mainPanel = null;
//    CardLayout cl = (CardLayout) mainPanel.getLayout();
//    cl.show(mainPanel, "bookmarkCard");  // "bookmarkCard" is the name for Bookmark panel
//}

//    public void addSettingListener(ActionListener listener){
//        System.out.println("Settings button clicked!");
//        Settings.addActionListener(listener);
//    }
//    public JButton getSettingsButton(){
//          return Settings;
//    }

//    public void addSearchListener (ActionListener listener){
//        Filters.addActionListener(listener);
//    }
//    public String getSearchText(){
//        return Search.getText();
//    }
    
//    private AdminDashboardController dashboardController;
    
//    public void setDashboardController(AdminDashboardController controller){
//        this.dashboardController = controller;
//    }
}
