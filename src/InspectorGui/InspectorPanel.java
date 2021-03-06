package InspectorGui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import Engine.GameObject;
import Engine.Main;
import SystemScripts.EditorUtilities;
import Utils.Component;
import Utils.ComponentCombo;
import Utils.ScriptCompiler;
import Utils.TextDefaults;

/**Edits a JPanel to display all of an object's Components.
 * 
 * @author Jacob
 *
 */
public class InspectorPanel extends JPanel{

	
	JRadioButton r = new JRadioButton();
	JPanel p = new JPanel();
	JButton b = new JButton();
	ComponentCombo c = new ComponentCombo();
	ArrayList<Component> components;
	
	/**Creates a new InspectorPanel.
	 * 
	 * @param go - GameObject to display attributes of.
	 */
	public InspectorPanel(GameObject go){	

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		Update(go);
		
	}
	
	public void Update(GameObject go){
		if(go!=null){
			
			this.removeAll();
		
			this.add(TextDefaults.MakeText("Inspector:"));
			this.add(TextDefaults.MakeText(go.name()));
			
			components = go.GetAllComponents();
			
			
			for(int x = 0; x<components.size(); x++){
				this.add(new ComponentGui(components.get(x)));
				}
			
			b.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					go.AddScript(ScriptCompiler.getPlayerScript(String.valueOf(c.getSelectedItem())));
					Main.window.UpdateInspector(EditorUtilities.SelectedObject);
				}});
			
			b.setForeground(new Color(150,150,150));
			b.setText("Add Component");
			b.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
			
			c.setMaximumSize(new Dimension(100,20));
			c.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
			
			this.add(c);
			this.add(b);
			
		}
		
		else{
			this.removeAll();
			this.repaint();
		}
	}
		

}