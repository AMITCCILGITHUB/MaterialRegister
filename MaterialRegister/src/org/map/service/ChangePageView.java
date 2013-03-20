package org.map.service;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

import org.map.utils.Context;
import org.map.utils.PageUtil;

public class ChangePageView extends Service<Void> {

	private String pageName;
	private Node startPage = PageUtil.getPageView("LoadingView");
	private Node finishPage = null;

	public ChangePageView(String pageName) {
		this.pageName = pageName;
	}

	@Override
	protected Task<Void> createTask() {

		return new Task<Void>() {

			@Override
			protected Void call() {

				try {
					Platform.runLater(new Runnable() {

						@Override
						public void run() {

							Timeline timeline = new Timeline();

							EventHandler<ActionEvent> onStart = new EventHandler<ActionEvent>() {
								public void handle(ActionEvent t) {

									Context.getPageArea().getChildren()
											.setAll(startPage);
								}
							};

							EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>() {
								public void handle(ActionEvent t) {

									finishPage = PageUtil.getPageView(pageName);
								}
							};

							EventHandler<ActionEvent> onComplete = new EventHandler<ActionEvent>() {
								public void handle(ActionEvent t) {

									Context.getPageArea().getChildren()
											.setAll(finishPage);
								}
							};
							
							timeline.getKeyFrames().addAll(
									new KeyFrame(Duration.ZERO, onStart,
											new KeyValue(startPage
													.opacityProperty(), 1)),
									new KeyFrame(Duration.seconds(1),
											onFinished, new KeyValue(startPage
													.opacityProperty(), 1)),
									new KeyFrame(Duration.seconds(2),
											onComplete, new KeyValue(startPage
													.opacityProperty(), 0)));
							timeline.play();
						}
					});

				} catch (Exception ex) {
					ex.printStackTrace();
				}

				return null;
			}
		};
	}

}