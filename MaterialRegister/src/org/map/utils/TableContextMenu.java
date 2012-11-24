package org.map.utils;

import java.net.MalformedURLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ContextMenuBuilder;
import javafx.scene.control.MenuItemBuilder;

public class TableContextMenu {

	private static EventHandler<ActionEvent>[] eventHandler;

	public static ContextMenu getViewUserContextMenu(
			EventHandler... eventHandler) throws MalformedURLException {

		return ContextMenuBuilder
				.create()
				.items(MenuItemBuilder.create().text("Show Password")
						.onAction(eventHandler[0])
						.graphic(FileUtil.getImageAsImageView("search"))
						.build(),
						MenuItemBuilder.create().text("View User")
								.onAction(eventHandler[1])
								.graphic(FileUtil.getImageAsImageView("view_user"))
								.build()).build();
	}

	public static ContextMenu getEditUserContextMenu(
			EventHandler... eventHandler) throws MalformedURLException {

		return ContextMenuBuilder
				.create()
				.items(MenuItemBuilder
						.create()
						.text("Edit User")
						.onAction(eventHandler[0])
						.graphic(
								FileUtil.getImageAsImageView("edit-clear",
										".gif")).build(),
						MenuItemBuilder
								.create()
								.text("Delete User")
								.onAction(eventHandler[1])
								.graphic(FileUtil.getImageAsImageView("delete"))
								.build()).build();
	}

	public static ContextMenu getPrintMaterialContextMenu(
			EventHandler... eventHandler) throws MalformedURLException {

		return ContextMenuBuilder
				.create()
				.items(MenuItemBuilder.create().text("Print")
						.onAction(eventHandler[0])
						.graphic(FileUtil.getImageAsImageView("print")).build())
				.build();
	}

	public static ContextMenu getEditMaterialContextMenu(
			EventHandler... eventHandler) throws MalformedURLException {

		return ContextMenuBuilder
				.create()
				.items(MenuItemBuilder
						.create()
						.text("Edit Material")
						.onAction(eventHandler[0])
						.graphic(
								FileUtil.getImageAsImageView("edit-clear",
										".gif")).build(),
						MenuItemBuilder
								.create()
								.text("Delete Material")
								.onAction(eventHandler[1])
								.graphic(FileUtil.getImageAsImageView("delete"))
								.build()).build();
	}

	public static ContextMenu getAddMaterialContextMenu(
			EventHandler... eventHandler) throws MalformedURLException {

		return ContextMenuBuilder
				.create()
				.items(MenuItemBuilder.create().text("Add Test")
						.onAction(eventHandler[0])
						.graphic(FileUtil.getImageAsImageView("context_add"))
						.build(),
						MenuItemBuilder
								.create()
								.text("Remove Test")
								.onAction(eventHandler[1])
								.graphic(
										FileUtil.getImageAsImageView("context_delete"))
								.build(),
						MenuItemBuilder
								.create()
								.text("Duplicate Test")
								.onAction(eventHandler[2])
								.graphic(
										FileUtil.getImageAsImageView("lightbulb"))
								.build()).build();
	}

	public static ContextMenu getViewMaterialContextMenu(
			EventHandler... eventHandler) throws MalformedURLException {

		return ContextMenuBuilder
				.create()
				.items(MenuItemBuilder.create().text("View Material")
						.onAction(eventHandler[0])
						.graphic(FileUtil.getImageAsImageView("lightbulb"))
						.build()).build();
	}

	public static ContextMenu getAddHeatChartContextMenu(
			EventHandler... eventHandler) throws MalformedURLException {

		return ContextMenuBuilder
				.create()
				.items(MenuItemBuilder.create().text("Add Record")
						.onAction(eventHandler[0])
						.graphic(FileUtil.getImageAsImageView("context_add"))
						.build(),
						MenuItemBuilder
								.create()
								.text("Add Sheet")
								.onAction(eventHandler[1])
								.graphic(
										FileUtil.getImageAsImageView("context_add"))
								.build(),
						MenuItemBuilder
								.create()
								.text("Remove Record")
								.onAction(eventHandler[2])
								.graphic(
										FileUtil.getImageAsImageView("context_delete"))
								.build()).build();
	}

	public static ContextMenu getViewHeatChartContextMenu(
			EventHandler... eventHandler) throws MalformedURLException {

		return ContextMenuBuilder
				.create()
				.items(MenuItemBuilder.create().text("View Heat Chart")
						.onAction(eventHandler[0])
						.graphic(FileUtil.getImageAsImageView("lightbulb"))
						.build()).build();
	}
	
	public static ContextMenu getEditHeatChartContextMenu(
			EventHandler... eventHandler) throws MalformedURLException {

		return ContextMenuBuilder
				.create()
				.items(MenuItemBuilder
						.create()
						.text("Edit Heat Chart")
						.onAction(eventHandler[0])
						.graphic(
								FileUtil.getImageAsImageView("edit-clear",
										".gif")).build(),
						MenuItemBuilder
								.create()
								.text("Delete Heat Chart")
								.onAction(eventHandler[1])
								.graphic(FileUtil.getImageAsImageView("delete"))
								.build()).build();
	}	
}
