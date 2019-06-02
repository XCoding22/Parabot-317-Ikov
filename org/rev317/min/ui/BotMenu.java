package org.rev317.min.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import org.parabot.core.Context;
import org.parabot.core.paint.AbstractDebugger;
import org.parabot.core.paint.PaintDebugger;
import org.rev317.min.callback.MenuAction;
import org.rev317.min.debug.DActions;
import org.rev317.min.debug.DAnimation;
import org.rev317.min.debug.DBank;
import org.rev317.min.debug.DCollisionFlags;
import org.rev317.min.debug.DGroundItems;
import org.rev317.min.debug.DInterfaces;
import org.rev317.min.debug.DInventory;
import org.rev317.min.debug.DMap;
import org.rev317.min.debug.DMessages;
import org.rev317.min.debug.DMouse;
import org.rev317.min.debug.DNpcs;
import org.rev317.min.debug.DPlayers;
import org.rev317.min.debug.DSceneObjects;
import org.rev317.min.debug.DSceneObjectsGroundDec;
import org.rev317.min.debug.DSceneObjectsGroundItems;
import org.rev317.min.debug.DSceneObjectsInteractiveObj;
import org.rev317.min.debug.DSceneObjectsWallDec;
import org.rev317.min.debug.DSceneObjectsWallObj;
import org.rev317.min.debug.DSkills;

public class BotMenu implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		Context.getInstance().getPaintDebugger().toggle(actionEvent.getActionCommand());
	}

	private JMenuItem newItem(String string) {
		JCheckBoxMenuItem jCheckBoxMenuItem = new JCheckBoxMenuItem(string);
		jCheckBoxMenuItem.addActionListener((ActionListener) this);
		return jCheckBoxMenuItem;
	}

	public BotMenu(JMenuBar jMenuBar) {
		PaintDebugger paintDebugger = Context.getInstance().getPaintDebugger();
		JMenu jMenu = new JMenu("Debug");
		JMenu jMenu2 = new JMenu("Actions");
		JMenuItem jMenuItem = this.newItem("Enable Actions");
		JMenuItem jMenuItem2 = this.newItem("Animation");
		JMenuItem jMenuItem3 = this.newItem("Bank");
		JMenuItem jMenuItem4 = this.newItem("Collision flags");
		JMenuItem jMenuItem5 = this.newItem("GroundItems");
		JMenuItem jMenuItem6 = this.newItem("Interfaces");
		JMenuItem jMenuItem7 = this.newItem("Inventory");
		JMenuItem jMenuItem8 = this.newItem("Map");
		JMenuItem jMenuItem9 = this.newItem("Messages");
		JMenuItem jMenuItem10 = this.newItem("Mouse");
		JMenuItem jMenuItem11 = this.newItem("Npcs");
		JMenu jMenu3 = new JMenu("Objects");
		JMenuItem jMenuItem12 = this.newItem("Enable All Objects");
		JMenuItem jMenuItem13 = this.newItem("Ground Decorations");
		JMenuItem jMenuItem14 = this.newItem("Interactive Objects");
		JMenuItem jMenuItem15 = this.newItem("Wall Objects");
		JMenuItem jMenuItem16 = this.newItem("Wall Decorations");
		JMenuItem jMenuItem17 = this.newItem("Ground Items");
		JMenuItem jMenuItem18 = this.newItem("Players");
		JMenuItem jMenuItem19 = this.newItem("Skills");
		paintDebugger.addDebugger("Enable Actions", (AbstractDebugger) new DActions());
		paintDebugger.addDebugger("Animation", (AbstractDebugger) new DAnimation());
		paintDebugger.addDebugger("Bank", (AbstractDebugger) new DBank());
		paintDebugger.addDebugger("Collision flags", (AbstractDebugger) new DCollisionFlags());
		paintDebugger.addDebugger("GroundItems", (AbstractDebugger) new DGroundItems());
		paintDebugger.addDebugger("Interfaces", (AbstractDebugger) new DInterfaces());
		paintDebugger.addDebugger("Inventory", (AbstractDebugger) new DInventory());
		paintDebugger.addDebugger("Map", (AbstractDebugger) new DMap());
		paintDebugger.addDebugger("Messages", (AbstractDebugger) new DMessages());
		paintDebugger.addDebugger("Mouse", (AbstractDebugger) new DMouse());
		paintDebugger.addDebugger("Npcs", (AbstractDebugger) new DNpcs());
		paintDebugger.addDebugger("Enable All Objects", (AbstractDebugger) new DSceneObjects());
		paintDebugger.addDebugger("Ground Decorations", (AbstractDebugger) new DSceneObjectsGroundDec());
		paintDebugger.addDebugger("Interactive Objects", (AbstractDebugger) new DSceneObjectsInteractiveObj());
		paintDebugger.addDebugger("Wall Objects", (AbstractDebugger) new DSceneObjectsWallObj());
		paintDebugger.addDebugger("Wall Decorations", (AbstractDebugger) new DSceneObjectsWallDec());
		paintDebugger.addDebugger("Ground Items", (AbstractDebugger) new DSceneObjectsGroundItems());
		paintDebugger.addDebugger("Players", (AbstractDebugger) new DPlayers());
		paintDebugger.addDebugger("Skills", (AbstractDebugger) new DSkills());
		jMenu.add(jMenu2);
		jMenu2.add(jMenuItem);
		jMenu.add(jMenuItem2);
		jMenu.add(jMenuItem3);
		jMenu.add(jMenuItem4);
		jMenu.add(jMenuItem5);
		jMenu.add(jMenuItem6);
		jMenu.add(jMenuItem7);
		jMenu.add(jMenuItem8);
		jMenu.add(jMenuItem9);
		jMenu.add(jMenuItem10);
		jMenu.add(jMenuItem11);
		jMenu.add(jMenu3);
		jMenu3.add(jMenuItem12);
		jMenu3.addSeparator();
		jMenu3.add(jMenuItem13);
		jMenu3.add(jMenuItem17);
		jMenu3.add(jMenuItem16);
		jMenu3.add(jMenuItem15);
		jMenu3.add(jMenuItem14);
		jMenu.add(jMenuItem18);
		jMenu.add(jMenuItem19);
		jMenu2.addSeparator();
		ButtonGroup buttonGroup = new ButtonGroup();
		for (int i = 0; i < MenuAction.getOutputs().length; i++) {
			final int z = i;
			JRadioButtonMenuItem jRadioButtonMenuItem = new JRadioButtonMenuItem("Output: " + i);
			jRadioButtonMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					MenuAction.setCurrentOutputIndex(z);
				}
			});
			if (i == 0) {
				buttonGroup.setSelected(jRadioButtonMenuItem.getModel(), true);
			}
			buttonGroup.add(jRadioButtonMenuItem);
			jMenu2.add(jRadioButtonMenuItem);
		}
		jMenuBar.add(jMenu);
	}
}
