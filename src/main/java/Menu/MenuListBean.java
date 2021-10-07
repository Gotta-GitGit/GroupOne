package Menu;

import java.io.Serializable;
import java.util.Vector;

public class MenuListBean implements Serializable {

	private Vector<String> menulist;

	public MenuListBean() {
		super();
	}

	public Vector<String> getMenulist() {
		return menulist;
	}

	public void setMenulist(Vector<String> menulist) {
		this.menulist = menulist;
	}

	public MenuListBean(Vector<String> menulist) {
		super();
		this.menulist = menulist;
	}

}
