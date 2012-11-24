package org.map.service;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import org.map.utils.Context;
import org.map.utils.PageUtil;

public class ChangePageView extends Service<Void> {

	String pageName;
	
	public ChangePageView(String pageName){
		this.pageName = pageName;
	}
	@Override
	protected Task<Void> createTask() {

		return new Task<Void>() {

			@Override
			protected Void call() {

				Context.getPageArea().getChildren()
				.setAll(PageUtil.getPageView(pageName));

				return null;
			}
		};
	}

}