package org.map.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItemBuilder;
import javafx.scene.control.SeparatorMenuItemBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageViewBuilder;

public class TableContextMenu extends ContextMenu {

	File undoFile = new File("resources/images/undo.png");
	File deleteFile = new File("resources/images/delete.png");
	File printFile = new File("resources/images/print.png");
	File searchFile = new File("resources/images/search.png");

	public TableContextMenu(EventHandler eventHandler1,
			EventHandler eventHandler2, EventHandler eventHandler3)
			throws URISyntaxException, MalformedURLException {

		getItems().addAll(
				MenuItemBuilder
						.create()
						.text("Restore")
						.graphic(
								ImageViewBuilder
										.create()
										.image(new Image(undoFile.toURI()
												.toURL().toString())).build())
						.onAction(eventHandler1).build(),
				MenuItemBuilder
						.create()
						.text("Delete")
						.onAction(eventHandler2)
						.graphic(
								ImageViewBuilder
										.create()
										.image(new Image(deleteFile.toURI()
												.toURL().toString())).build())
						.build(),
				SeparatorMenuItemBuilder.create().build(),
				MenuItemBuilder
						.create()
						.text("Print")
						.onAction(eventHandler3)
						.graphic(
								ImageViewBuilder
										.create()
										.image(new Image(printFile.toURI()
												.toURL().toString())).build())
						.build());
		setAutoFix(true);
	}

	public TableContextMenu(EventHandler eventHandler1,
			EventHandler eventHandler2) throws URISyntaxException,
			MalformedURLException {

		getItems().addAll(
				MenuItemBuilder
						.create()
						.text("Restore")
						.graphic(
								ImageViewBuilder
										.create()
										.image(new Image(undoFile.toURI()
												.toURL().toString())).build())
						.onAction(eventHandler1).build(),
				MenuItemBuilder
						.create()
						.text("Delete")
						.onAction(eventHandler2)
						.graphic(
								ImageViewBuilder
										.create()
										.image(new Image(deleteFile.toURI()
												.toURL().toString())).build())
						.build());
		setAutoFix(true);
	}

	public TableContextMenu(EventHandler eventHandler1, String type)
			throws URISyntaxException, MalformedURLException {

		switch (type) {
			case "Print":
				getItems().addAll(
						MenuItemBuilder
								.create()
								.text("Print")
								.onAction(eventHandler1)
								.graphic(
										ImageViewBuilder
												.create()
												.image(new Image(printFile
														.toURI().toURL()
														.toString())).build())
								.build());
				break;
			case "Password":
				getItems().addAll(
						MenuItemBuilder
								.create()
								.text("Show Password")
								.onAction(eventHandler1)
								.graphic(
										ImageViewBuilder
												.create()
												.image(new Image(searchFile
														.toURI().toURL()
														.toString())).build())
								.build());
				break;
		}
		setAutoFix(true);
	}
}
