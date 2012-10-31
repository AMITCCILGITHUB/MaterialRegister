package org.map.utils;

import java.net.MalformedURLException;

import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ContextMenuBuilder;
import javafx.scene.control.MenuItemBuilder;

public class TableContextMenu {

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
								.graphic(FileUtil.getImageAsImageView("user"))
								.build()).build();
	}

	public static ContextMenu getEditUserContextMenu(
			EventHandler... eventHandler) throws MalformedURLException {

		return ContextMenuBuilder
				.create()
				.items(MenuItemBuilder.create().text("Edit")
						.onAction(eventHandler[0])
						.graphic(FileUtil.getImageAsImageView("user")).build(),
						MenuItemBuilder.create().text("Restore")
								.onAction(eventHandler[1])
								.graphic(FileUtil.getImageAsImageView("undo"))
								.build(),
						MenuItemBuilder
								.create()
								.text("Delete")
								.onAction(eventHandler[2])
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
				.items(MenuItemBuilder.create().text("Edit")
						.onAction(eventHandler[0])
						.graphic(FileUtil.getImageAsImageView("user")).build(),
						MenuItemBuilder.create().text("Restore")
								.onAction(eventHandler[1])
								.graphic(FileUtil.getImageAsImageView("undo"))
								.build(),
						MenuItemBuilder
								.create()
								.text("Delete")
								.onAction(eventHandler[2])
								.graphic(FileUtil.getImageAsImageView("delete"))
								.build()).build();
	}

	// public TableContextMenu(EventHandler eventHandler1,
	// EventHandler eventHandler2, EventHandler eventHandler3)
	// throws URISyntaxException, MalformedURLException {
	//
	// getItems().addAll(
	// MenuItemBuilder
	// .create()
	// .text("Restore")
	// .graphic(
	// ImageViewBuilder
	// .create()
	// .image(new Image(undoFile.toURI()
	// .toURL().toString())).build())
	// .onAction(eventHandler1).build(),
	// MenuItemBuilder
	// .create()
	// .text("Delete")
	// .onAction(eventHandler2)
	// .graphic(
	// ImageViewBuilder
	// .create()
	// .image(new Image(deleteFile.toURI()
	// .toURL().toString())).build())
	// .build(),
	// SeparatorMenuItemBuilder.create().build(),
	// MenuItemBuilder
	// .create()
	// .text("Print")
	// .onAction(eventHandler3)
	// .graphic(
	// ImageViewBuilder
	// .create()
	// .image(new Image(printFile.toURI()
	// .toURL().toString())).build())
	// .build());
	// setAutoFix(true);
	// }
	//
	// public TableContextMenu(EventHandler eventHandler1,
	// EventHandler eventHandler2) throws URISyntaxException,
	// MalformedURLException {
	//
	// getItems().addAll(
	// MenuItemBuilder
	// .create()
	// .text("Restore")
	// .graphic(
	// ImageViewBuilder
	// .create()
	// .image(new Image(undoFile.toURI()
	// .toURL().toString())).build())
	// .onAction(eventHandler1).build(),
	// MenuItemBuilder
	// .create()
	// .text("Delete")
	// .onAction(eventHandler2)
	// .graphic(
	// ImageViewBuilder
	// .create()
	// .image(new Image(deleteFile.toURI()
	// .toURL().toString())).build())
	// .build());
	// setAutoFix(true);
	// }
	//
	// public TableContextMenu(EventHandler eventHandler1, String type)
	// throws URISyntaxException, MalformedURLException {
	//
	// switch (type) {
	// case "Print":
	// getItems().addAll(
	// MenuItemBuilder
	// .create()
	// .text("Print")
	// .onAction(eventHandler1)
	// .graphic(
	// ImageViewBuilder
	// .create()
	// .image(new Image(printFile.toURI()
	// .toURL().toString()))
	// .build()).build());
	// break;
	// case "Password":
	// getItems().addAll(
	// MenuItemBuilder
	// .create()
	// .text("Show Password")
	// .onAction(eventHandler1)
	// .graphic(
	// ImageViewBuilder
	// .create()
	// .image(new Image(searchFile.toURI()
	// .toURL().toString()))
	// .build()).build());
	// break;
	// }
	// setAutoFix(true);
	// }

}
