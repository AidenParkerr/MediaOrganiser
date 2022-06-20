package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MediaLibraryOrganiser {

	/**
	 * 
	 */
	private ButtonActionListener actionList = new ButtonActionListener();

	private ImageIcon explorerIcon = new ImageIcon(".\\assets\\explorer24.png");
	private ImageIcon explorerIconHovered = new ImageIcon(".\\assets\\explorer24-hover.png");
	private ImageIcon searchIcon = new ImageIcon(".\\assets\\search.png");
	private ImageIcon searchIconHovered = new ImageIcon(".\\assets\\search-hover.png");

	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	/** Sets the minimum width of the user interface to half the size of the display size. */
	private final static int MIN_WIDTH = (int) screenSize.getWidth() / 2;
	
	/** Sets the minimum height of the user interface to half the size of the display size. */
	private final static int MIN_HEIGHT = (int) screenSize.getHeight() / 2;

	private static JTabbedPane tabbedPane;
	private  JPanel header, mainBody;

	/** Creates the {@code GridBagConstraints} object. */
	private static JPanel fileAndPlaylistPanel;

	/** Creates a generic {@code JPanel} whose value changes based on where it is being used. */
	private JPanel panel;

	/** Creates a {@code JPanel}  object. */
	private static JPanel noFilesPanel;
	
	/** Creates a generic {@code JLabel} object to be populated with text. */
	private JLabel label;

	/** Creates a {@code JTextField} to display the location the system is currently in. */
	private static JTextField folderLocationTextField;
	
	/** Creates a {@code JTextField} that is used to search for a file in the specified location. */
	private static JTextField fileSearch;
	
	/** Creates the {@code GridBagConstraints} object. */
	private GridBagConstraints c = new GridBagConstraints();

	/*************************
	 * START OF HEADER PANEL *
	 *************************/
	private JPanel createHeaderPanel() {
		header = new JPanel();
		header.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		header.setLayout(new BorderLayout());
		header.add(createExplorerSearchPanel(), BorderLayout.WEST);
		header.add(createSearchForFilePanel(), BorderLayout.EAST);
		return header;
	}

	private JPanel createExplorerSearchPanel() {
		panel = new JPanel();
		panel.add(createSystemExplorerButton());
		panel.add(createFolderChosenLabel());
		panel.add(createFolderLocationTextField());
		return panel;
	}

	private JLabel createFolderChosenLabel() {
		return new JLabel("Folder Chosen");
	}

	private JTextField createFolderLocationTextField() {
		folderLocationTextField = new JTextField(15);
		folderLocationTextField.setEditable(false);
		folderLocationTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		folderLocationTextField.setToolTipText("Shows the current folder selected.");
		return folderLocationTextField;
	}

	public static  void setExplorerLocationFieldText(String text) {
		folderLocationTextField.setText(text);
	}

	public static  String getFolderLocationText() {
		return folderLocationTextField.getText();
	}

////Search for files panel in Header//////////////
	private JPanel createSearchForFilePanel() {
		panel = new JPanel();
		panel.add(createSearchFileLabel());
		panel.add(createSearchFileTextField());
		panel.add(createSearchButton());
		return panel;
	}

	private JLabel createSearchFileLabel() {
		return new JLabel("Search");
	}

	private JTextField createSearchFileTextField() {
		fileSearch = new JTextField(15);
		return fileSearch;
	}

	public static  String getSearchFieldText() {
		return fileSearch.getText();
	}

	/*******************************
	 * CHANGE BUTTON ICON ON HOVER *
	 *******************************/
	private void changeButtonHover(JButton btn) {
		char button = btn.getName() == "explorerButton" ? 'e' : 's';
		btn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evnt) {
				if (button == 'e') {
					btn.setIcon(explorerIconHovered);
				} else {
					btn.setIcon(searchIconHovered);
				}
			}

			public void mouseExited(MouseEvent evnt) {
				if (button == 'e')
					btn.setIcon(explorerIcon);
				else
					btn.setIcon(searchIcon);
			}
		});
	}

	/***********************************
	 * END OF CHANGE BUTTON ICON HOVER *
	 ***********************************/
////End of search panels//////////////////////

	/***********************
	 * END OF HEADER PANEL *
	 ***********************/

	/****************************
	 * START OF MAIN BODY PANEL *
	 ****************************/
	private JPanel createMainBodyPanel() {
		mainBody = new JPanel();
		mainBody.setLayout(new GridBagLayout());
		mainBody.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		mainBody.add(createSideButtonsPanel(), c);
		mainBody.add(createFileAndPlaylistPanel(), c);
		return mainBody;
	}

	private JPanel createSideButtonsPanel() {
		JPanel sideButtons = new JPanel();
		sideButtons.setLayout(new GridLayout(6, 1, 0, 10));
		sideButtons.setOpaque(false);
		c.gridy = 1;
		c.gridx = 0;
		c.weightx = 0.1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0, 0, 0, 10);
		sideButtons.add(createFileButton());
		sideButtons.add(deleteFileButton());
		sideButtons.add(addFileToPlaylistButton());
		sideButtons.add(deleteFileFromPlaylistButton());
		sideButtons.add(createPlaylistButton());
		sideButtons.add(deletePlaylistButton());
		return sideButtons;
	}

	public  JPanel createFileAndPlaylistPanel() {
		fileAndPlaylistPanel = new JPanel();
		fileAndPlaylistPanel.setLayout(new BorderLayout());
		c.gridx = 1;
		c.gridy = 1;
		c.weighty = 1;
		c.weightx = 0.9;
//		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0, 0, 0, 0);
		fileAndPlaylistPanel.add(createNoFilesPanel());

		return fileAndPlaylistPanel;
	}

	public static  void showTabbedPane() {
		removeFilePlaylistPanelContents();
		fileAndPlaylistPanel.add(tabbedPane);
		fileAndPlaylistPanel.revalidate();
		fileAndPlaylistPanel.repaint();
	}

	public static  void showNoFilesPanel() {
		removeFilePlaylistPanelContents();
		fileAndPlaylistPanel.add(noFilesPanel);
		fileAndPlaylistPanel.revalidate();
		fileAndPlaylistPanel.repaint();
	}

	private static  void removeFilePlaylistPanelContents() {
		fileAndPlaylistPanel.removeAll();
		fileAndPlaylistPanel.revalidate();
	}
//
////Create "No Files Found" label /////////////////////
	protected static  void createTabbedPane(JScrollPane mediaTableSP, JScrollPane playlistTableSP) {
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Files", mediaTableSP);
		tabbedPane.addTab("Playlists", playlistTableSP);
	}

////Create "No Files Found" Panel /////////////////////
	private JPanel createNoFilesPanel() {
		noFilesPanel = new JPanel();
		noFilesPanel.setLayout(new BorderLayout());
		noFilesPanel.add(createNoFilesLabel());
		return noFilesPanel;
	}

//// Create "No Files Found" label /////////////////////
	private  JLabel createNoFilesLabel() {
		label = new JLabel("No media files found in the folder provided.");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		return label;
	}

	/**********************************************
	 * Header | Select Folder/Search File Buttons *
	 **********************************************/

	private JButton createSystemExplorerButton() {
		JButton explorerButton = new JButton();
		explorerButton.setName("explorerButton");
		explorerButton.setBorder(BorderFactory.createEtchedBorder(1));
		explorerButton.setToolTipText("Select a folder to search for media files.");
		explorerButton.setIcon(explorerIcon);
		changeButtonHover(explorerButton);
		actionList.systemExplorer(explorerButton);
		return explorerButton;
	}

	private JButton createSearchButton() {
		JButton searchButton = new JButton();
		searchButton.setName("searchButton");
		searchButton.setOpaque(false);
		searchButton.setBorder(null);
		searchButton.setContentAreaFilled(false);
		searchButton.setIcon(searchIcon);
		changeButtonHover(searchButton);
		actionList.fileSearch(searchButton);
		return searchButton;
	}

	/*************************
	 * END OF HEADER BUTTONS *
	 *************************/

	/**********************************
	 * Main Body | Side Panel Buttons *
	 **********************************/

	/*
	 * @TODO
	 * 
	 * Sort this duplicated mess out :{.
	 * 
	 */
	private JButton createFileButton() {
		JButton createFileButton = new JButton("Create media file");
		actionList.createFile(createFileButton);
		return createFileButton;
	}

	private JButton deleteFileButton() {
		JButton deleteFileButton = new JButton("Delete media file");
		actionList.deleteFile(deleteFileButton);
		return deleteFileButton;
	}

	private JButton addFileToPlaylistButton() {
		JButton addFileToPlaylistButton = new JButton("Add media file to playlist");
		actionList.addFileToPlaylist(addFileToPlaylistButton);
		return addFileToPlaylistButton;
	}

	private JButton deleteFileFromPlaylistButton() {
		JButton deleteFileFromPlaylistButton = new JButton("Delete media file from playlist");
		actionList.deleteFileFromPlaylist(deleteFileFromPlaylistButton);
		return deleteFileFromPlaylistButton;
	}

	private JButton createPlaylistButton() {
		JButton createPlaylistButton = new JButton("Create a playlist");
		actionList.createPlaylist(createPlaylistButton);
		return createPlaylistButton;
	}

	private JButton deletePlaylistButton() {
		JButton deletePlaylistButton = new JButton("Delete a playlist");
		actionList.deletePlaylist(deletePlaylistButton);
		return deletePlaylistButton;
	}

	/*****************************
	 * END OF SIDE PANEL BUTTONS *
	 *****************************/

//// Setting LookAndFeel of the application ///////////////
	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	private void addComponentsToPane(Container pane) {
		header = createHeaderPanel();
		mainBody = createMainBodyPanel();

		pane.add(header, BorderLayout.NORTH);
		pane.add(mainBody, BorderLayout.CENTER);
	}

	public static void createAndShowGUI() {
		MediaLibraryOrganiser mediaOrganiser = new MediaLibraryOrganiser();
		JFrame frame = new JFrame();
		frame.setTitle("Merarser"); // 'Me'dia Lib'rar'y Organi'ser'.
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the window when the "X" is pressed.
		frame.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT)); // Set the minimum width of the window.
		mediaOrganiser.addComponentsToPane(frame.getContentPane());
		mediaOrganiser.setLookAndFeel();
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});

	}
}

