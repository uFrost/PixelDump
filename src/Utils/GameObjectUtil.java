package Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;

import Engine.GameObject;
import Engine.testScript;
import Renderer.Renderer;

/**Utilities for GameObject
 * 
 * @author Barry
 *
 */
public class GameObjectUtil {
	// make Test GameObjects Inside of here for now

	public static void Start() {
		
		
		GameObject go2 = new GameObject("Flaming_skull");
		go2.AddScript(new Renderer("Flaming_skull"));
		//go2.AddScript(ScriptCompiler.getPlayerScript("test"));
		go2.transform.position.x = 50;
		go2.transform.depth = 1;
		GameObject go = new GameObject("jacob");
		go.AddScript(new Renderer("jacob"));
		go.transform.depth = -1;
		System.out.println(GameObjectCreator.getFieldType("Engine",
				"testScript",0));
		
		
		
	}
	public static void recompile(GameObject go2){

	ScriptCompiler.recompile();

	}
	public static class GeneralGameObjectUtils {

	}

	/**Creates GameObjects
	 * 
	 * @author Barry
	 *
	 */
	public static class GameObjectCreator {

		public static void loadScene(File scene) {

		}

		public static ScriptBase newScriptByName(String name) {
			try {
				return (ScriptBase) (Class.forName(name).getConstructor()
						.newInstance());
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException
					| ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		public static ScriptBase newScriptByName(String pack, String name) {
			try {
				return (ScriptBase) (Class.forName(pack + "." + name)
						.getConstructor().newInstance());
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException
					| ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		/**Returns a new instance of a class by its package and name
		 */
		public static Object getObjectByName(String pack, String name) {
			try {
				return (Class.forName(pack + "." + name).getConstructor()
						.newInstance());
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException
					| ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		/**Returns the type of a field (as a String) specified by passed in package, name and field index
		 */
		public static String getFieldType(String pack, String name, int index) {
			try {
				return Class.forName(pack + "." + name).getFields()[index]
						.getType().getName();

			} catch (IllegalArgumentException | SecurityException
					| ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		ObjectOutputStream oos = null;
		FileOutputStream fout = null;

		/**Writes the passed in GameObject to ObjectOutputStream "oos" (with FileOutputStream "fout")
		 */
		public void serializeGameObject(GameObject go) {

			try {
				FileOutputStream fout = new FileOutputStream("src/res/test.GO",
						true);
				ObjectOutputStream oos = new ObjectOutputStream(fout);
				oos.writeObject(go);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (oos != null) {
					try {
						oos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
