package Renderer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

import Engine.GameObject;
import Engine.Main;
import InspectorGui.InspectorPanel;
import Utils.HierarchyPanel;
import Utils.RandomTitles;
import Utils.windowMenu;

/**Creates the JFrame containing the entire project.
 * 
 * @author Jacob
 *
 */
public class Window extends PixDumpWindow {

	public Canvas c = new Canvas();
	public JPanel InspeIarchy = new JPanel();
	public ArrayList<GameObject> base;
	
	public InspectorPanel Inspector;
	public HierarchyPanel Hierarchy;
	JScrollPane InspectorScroll;
	JScrollPane HierarchyScroll;
	JPanel CanvasPanel = new JPanel();
	windowMenu w = new windowMenu();
	JToggleButton play = new JToggleButton();
	
	public Component store;

	/**Constructs a new Window (containing the entire project)
	 * 
	 */
	public Window() {
		super();

		/**Retrieves all GameObjects
		 * 
		 */
		base = new ArrayList<GameObject>();
		for (int shit = 0; shit < GameObject.getAllGameObjects().size(); shit++) {
			base.add(GameObject.getAllGameObjects().get(shit));
		}
		
		Hierarchy = new HierarchyPanel();
		Inspector = new InspectorPanel(null);
		
		
		this.setTitle(RandomTitles.Titles[(int)(Math.random()*RandomTitles.Titles.length)]);
		this.setLayout(new GridLayout(1, 2));
		this.setPreferredSize(new Dimension(700, 400));
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setResizable(true);

		

		InspectorScroll = new JScrollPane(Inspector,InspectorScroll.VERTICAL_SCROLLBAR_AS_NEEDED,InspectorScroll.HORIZONTAL_SCROLLBAR_NEVER);
		HierarchyScroll = new JScrollPane(Hierarchy,HierarchyScroll.VERTICAL_SCROLLBAR_AS_NEEDED,HierarchyScroll.HORIZONTAL_SCROLLBAR_NEVER);
		
		play.setText("Play");
		play.setForeground(new Color(100,100,100));
		play.setAlignmentX(CENTER_ALIGNMENT);
		play.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(play.isSelected()){
					Main.PlayMode=true;
				}
				else{
					Main.PlayMode=false;
				}
				
			}});
		
		InspeIarchy = new JPanel();
		InspeIarchy.setLayout(new GridLayout(1, 2));
		
		CanvasPanel.add(play);
		CanvasPanel.add(c);
		CanvasPanel.setLayout(new BoxLayout(CanvasPanel, BoxLayout.Y_AXIS));
		
		InspeIarchy.add(InspectorScroll);
		InspeIarchy.add(HierarchyScroll);

		this.add(CanvasPanel);
		this.add(InspeIarchy);
		this.setJMenuBar(w);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();

	}




	/**Reads all Components of a passed in GameObject and displays them.
	 * 
	 * @param g - GameObject to inspect
	 */
	public void UpdateInspector(GameObject g) {

		if(Inspector!=null)		
		Inspector.Update(g);
		
		Inspector.revalidate();
		
	}

	

	
}
